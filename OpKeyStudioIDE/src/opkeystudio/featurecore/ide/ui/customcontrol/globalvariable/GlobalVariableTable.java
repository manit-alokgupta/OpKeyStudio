package opkeystudio.featurecore.ide.ui.customcontrol.globalvariable;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomCombo;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.featurecore.ide.ui.ui.GlobalVariableDialog;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalVariableTable extends CustomTable {
	private GlobalVariableDialog parentGlobalVariableView;
	private TestCaseView parentTestCaseView;
	private boolean insideArtifact = false;
	private List<GlobalVariable> globalVariables = new ArrayList<GlobalVariable>();
	String[] tableHeaders_gv = { "Name", "Data Type", "Value", "Externally Updatable" };
	String[] tableHeaders_arti = { "Name", "Data Type", "Value" };

	public GlobalVariableTable(Composite parent, int style, GlobalVariableDialog gvDialog) {
		super(parent, style);
		setInsideArtifact(false);
		setParentGlobalVariableView(gvDialog);
		initHeaders();
		init();
	}

	public GlobalVariableTable(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		setInsideArtifact(true);
		setParentTestCaseView(testCaseView);
		initHeaders();
		refreshGlobalVariables();
	}

	private void initHeaders() {
		if (isInsideArtifact()) {
			for (String header : tableHeaders_arti) {
				TableColumn column = new TableColumn(this, 0);
				column.setText(header);
			}
			this.pack();
			for (int i = 0; i < tableHeaders_arti.length; i++) {
				this.getColumn(i).pack();
			}

			this.addPaintListener(new PaintListener() {

				@Override
				public void paintControl(PaintEvent arg0) {
					Table table_0 = (Table) arg0.getSource();
					for (TableColumn column : table_0.getColumns()) {
						column.setWidth(table_0.getBounds().width / 4);
					}
				}
			});

		} else {
			for (String header : tableHeaders_gv) {
				TableColumn column = new TableColumn(this, 0);
				column.setText(header);
			}
			this.pack();
			for (int i = 0; i < tableHeaders_gv.length; i++) {
				this.getColumn(i).pack();
			}
		}
	}

	private void init() {
		TableCursor cursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(cursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;
		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// thisTable.deselectAll();
				CustomTableItem selectedTableItem = (CustomTableItem) cursor.getRow();
				GlobalVariable globalVariable = (GlobalVariable) selectedTableItem.getControlData();
				int selectedColumn = cursor.getColumn();
				CustomText text = new CustomText(cursor, 0);
				if (selectedColumn == 0) {
					text.setText(globalVariable.getName());
				}
				if (selectedColumn == 2) {
					if (globalVariable.getValue() == null) {
						text.setText("");
					} else {
						text.setText(globalVariable.getValue());
					}
				}
				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						text.dispose();
					}

					@Override
					public void focusGained(FocusEvent e) {

					}
				});

				text.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						selectedTableItem.setText(selectedColumn, text.getText());
						globalVariable.setModified(true);
						getParentGlobalVariableView().toggleSaveToolItem(true);
						if (selectedColumn == 0) {
							globalVariable.setName(text.getText());
						}
						if (selectedColumn == 2) {
							globalVariable.setValue(text.getText());
						}
					}
				});
				controlEditor.setEditor(text);
				getParentGlobalVariableView().toggleDeleteToolItem(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setGlobalVariablesData(List<GlobalVariable> gvars) {
		this.globalVariables = gvars;
	}

	public List<GlobalVariable> getGlobalVariablesData() {
		return this.globalVariables;
	}

	public void addGlobalVariable(GlobalVariable gv) {
		getGlobalVariablesData().add(gv);
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		editor.horizontalAlignment = SWT.CENTER;
		editor.verticalAlignment = SWT.CENTER;
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		return editor;
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private void addTableEditor(CustomTableItem item) {
		GlobalVariable globalVariable = (GlobalVariable) item.getControlData();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		CustomCombo combo = new CustomCombo(this, SWT.READ_ONLY);
		combo.setItems(ServiceRepository.getInstance().getAllVaraiblesType());
		combo.select(Utilities.getInstance().getIndexOfItem(ServiceRepository.getInstance().getAllVaraiblesType(),
				globalVariable.getDatatype()));

		combo.setControlData(globalVariable);
		CustomButton isExternallyUpdatable = new CustomButton(this, SWT.CHECK | SWT.CENTER);
		isExternallyUpdatable.setSelection(globalVariable.isExternallyupdatable());

		isExternallyUpdatable.setControlData(globalVariable);
		isExternallyUpdatable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelection(item);
				CustomButton button = (CustomButton) e.getSource();
				GlobalVariable oatr = (GlobalVariable) button.getControlData();
				oatr.setModified(true);
				oatr.setExternallyupdatable(button.getSelection());
				getParentGlobalVariableView().toggleSaveToolItem(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelection(item);
				CustomCombo button = (CustomCombo) e.getSource();
				GlobalVariable oatr = (GlobalVariable) button.getControlData();
				int selected = combo.getSelectionIndex();
				String selectedDataType = combo.getItem(selected);
				oatr.setDatatype(selectedDataType);
				oatr.setModified(true);
				getParentGlobalVariableView().toggleSaveToolItem(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		editor1.setEditor(isExternallyUpdatable, item, 3);
		editor2.setEditor(combo, item, 1);
		allTableEditors.add(editor1.getEditor());
		allTableEditors.add(editor2.getEditor());
	}

	private void disposeAllTableEditors() {
		for (Control editor : allTableEditors) {
			editor.dispose();
		}
	}

	public void refreshGlobalVariables() {
		GlobalLoader.getInstance().initGlobalVariables();
		disposeAllTableEditors();
		this.removeAll();
		List<GlobalVariable> globalvariables = GlobalLoader.getInstance().getGlobalVaribles();
		for (GlobalVariable globalvariable : globalvariables) {

			CustomTableItem ti = new CustomTableItem(this, 0);
			ti.setData(globalvariable);
			ti.setControlData(globalvariable);
			if (isInsideArtifact()) {
				ti.setText(new String[] { globalvariable.getName(), globalvariable.getDatatype(),
						globalvariable.getValue() });
			} else {
				ti.setText(new String[] { globalvariable.getName(), globalvariable.getDatatype(),
						globalvariable.getValue(), "" });
				addTableEditor(ti);
			}

		}
		this.globalVariables = globalvariables;
	}

	public void filterGlobalVariableTable(String searchValue) {
		List<GlobalVariable> globalVariables = this.getGlobalVariablesData();
		for (GlobalVariable globalVariable : globalVariables) {
			if (globalVariable.getName().trim().toLowerCase().contains(searchValue.trim().toLowerCase())) {
				globalVariable.setVisible(true);
			} else {
				globalVariable.setVisible(false);
			}
		}
		this.renderGlobalVariables();
	}

	private void renderGlobalVariables() {
		disposeAllTableEditors();
		this.removeAll();
		List<GlobalVariable> globalvariables = this.getGlobalVariablesData();
		for (GlobalVariable globalvariable : globalvariables) {
			if (globalvariable.isVisible()) {
				if (globalvariable.isDeleted() == false) {
					CustomTableItem ti = new CustomTableItem(this, 0);
					ti.setData(globalvariable);
					ti.setControlData(globalvariable);
					ti.setText(new String[] { globalvariable.getName(), globalvariable.getDatatype(),
							globalvariable.getValue(), "" });
					addTableEditor(ti);
				}
			}
		}
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void addBlankGlobalVariableStep() {
		int lastPosition = 0;
		GlobalVariable gv = new GlobalVariable();
		System.out.println("  " + getGlobalVariablesData().size());
		if ((getGlobalVariablesData().size()) == 0) {
			lastPosition = (getGlobalVariablesData().size() - 1);
		} else {
			lastPosition = getGlobalVariablesData().get(getGlobalVariablesData().size() - 1).getPosition();
		}
		gv.setPosition(lastPosition + 1);
		gv.setGv_id(Utilities.getInstance().getUniqueUUID(""));
		gv.setP_id(ServiceRepository.getInstance().getDefaultProject().getP_id());
		gv.setName("");
		gv.setValue("");
		gv.setDatatype("STRING");
		gv.setAdded(true);
		gv.setExternallyupdatable(true);
		addGlobalVariable(gv);
		renderGlobalVariables();
		parentGlobalVariableView.toggleSaveToolItem(true);
	}

	public void deleteGlobalVariableStep() {
		int selectedIndex = this.getSelectionIndex();
		getGlobalVariablesData().get(selectedIndex).setDeleted(true);
		renderGlobalVariables();
		selectRow(0);
	}

	public boolean saveAll() {

		List<GlobalVariable> allGlobalVariables = getGlobalVariablesData();
		for (GlobalVariable gv : allGlobalVariables) {
			if (gv.isDeleted() || gv.isModified() || gv.isAdded()) {
				if (gv.getName().trim().isEmpty()) {
					new MessageDialogs().openErrorDialog("OpKey", "Name Should Not Be Blank");
					renderGlobalVariables();
					return false;
				}
			}
			if (gv.isDeleted()) {
				new GlobalVariableApi().deleteGlobalVariable(gv);
			}
			if (gv.isAdded()) {
				new GlobalVariableApi().insertGlobalVaribale(gv);
			}
			if (gv.isModified()) {
				new GlobalVariableApi().updateGlobalVariable(gv);
			}
		}
		GlobalLoader.getInstance().initGlobalVariables();
		refreshGlobalVariables();
		return true;
	}

	public GlobalVariableDialog getParentGlobalVariableView() {
		return parentGlobalVariableView;
	}

	public void setParentGlobalVariableView(GlobalVariableDialog parentGlobalVariableView) {
		this.parentGlobalVariableView = parentGlobalVariableView;
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

	public boolean isInsideArtifact() {
		return insideArtifact;
	}

	public void setInsideArtifact(boolean insideArtifact) {
		this.insideArtifact = insideArtifact;
	}

	public GlobalVariable getSelectedGlobalVariable() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}

		CustomTableItem tableItem = (CustomTableItem) this.getSelection()[0];
		Object controlData = tableItem.getControlData();
		if (controlData != null) {
			return (GlobalVariable) controlData;
		}
		return null;
	}
}
