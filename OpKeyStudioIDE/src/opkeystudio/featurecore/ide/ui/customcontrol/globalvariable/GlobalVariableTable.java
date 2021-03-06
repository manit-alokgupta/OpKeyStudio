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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
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
import opkeystudio.featurecore.ide.ui.ui.superview.events.GlobalLoadListener;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalLoadListenerDispatcher;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyIntellisenseListenerDispatcher;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.GlobalVariablesTranspiler;
import opkeystudio.opkeystudiocore.core.utils.OpKeyVariables;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalVariableTable extends CustomTable {
	private GlobalVariableDialog parentGlobalVariableView;
	private TestCaseView parentTestCaseView;
	private boolean insideArtifact = false;
	private List<GlobalVariable> globalVariables = new ArrayList<GlobalVariable>();
	String[] tableHeaders_gv = { "Name", "Data Type", "Value", "Externally Updatable" };
	String[] tableHeaders_arti = { "Name", "Data Type", "Value" };

	private TableCursor tableCursor;

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
		initGlobalLoadeListener();
	}

	private void initGlobalLoadeListener() {
		this.addOpKeyGlobalLoadListener(new GlobalLoadListener() {

			@Override
			public void handleGlobalEvent() {
				refreshGlobalVariables();
			}
		});
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
			this.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					GlobalVariable gv = getSelectedGlobalVariable();
					if (gv != null) {
						getParentGlobalVariableView().toggleDeleteToolItem(true);
						return;
					}
					getParentGlobalVariableView().toggleDeleteToolItem(false);
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	private void init() {
		tableCursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(tableCursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;
		setTableCursor(tableCursor);
		tableCursor.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				CustomTableItem selectedTableItem = (CustomTableItem) tableCursor.getRow();
				GlobalVariable globalVariable = (GlobalVariable) selectedTableItem.getControlData();
				String dataType = globalVariable.getDatatype();
				boolean isNumberType = isDataTypeIntegerType(dataType);
				boolean isBooleanType = isDataTypeBooleanrType(dataType);
				boolean externalUpdatable = globalVariable.isExternallyupdatable();

				int selectedColumn = tableCursor.getColumn();
				CustomText text = new CustomText(tableCursor, 0);
				if (selectedColumn == 0) {
					if (externalUpdatable) {
						text.setText(globalVariable.getName());
						text.setFocus();
					} else {
						text.dispose();
						return;
					}
				}
				if (selectedColumn == 2) {
					if (isBooleanType) {
						Button checkedButton = new Button(tableCursor, SWT.CHECK);
						checkedButton.addFocusListener(new FocusListener() {

							@Override
							public void focusLost(FocusEvent e) {
								checkedButton.dispose();

							}

							@Override
							public void focusGained(FocusEvent e) {
								// TODO Auto-generated method stub

							}
						});

						checkedButton.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								String status = convertBooleanToString(checkedButton.getSelection());
								globalVariable.setValue(status);
								globalVariable.setModified(true);
								getParentGlobalVariableView().toggleSaveToolItem(true);
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {

							}
						});

						if (globalVariable.getValue() != null) {
							boolean checkedStatus = convertStringToBoolean(globalVariable.getValue());
							checkedButton.setSelection(checkedStatus);
							controlEditor.setEditor(checkedButton);
						} else {
							checkedButton.setSelection(false);
							controlEditor.setEditor(checkedButton);
						}
						return;
					}

					if (dataType.equals("String") || dataType.equals("Integer") || dataType.equals("Double")
							|| dataType.equals("MobileDevice")) {
						text.setEditable(true);
					} else {
						text.setEditable(false);
					}

					if (globalVariable.getValue() == null) {
						text.setText("");
						text.setFocus();
					} else {
						text.setText(globalVariable.getValue());
						text.setFocus();
					}
					text.addVerifyListener(new VerifyListener() {

						@Override
						public void verifyText(VerifyEvent e) {
							if (isNumberType) {
								final String oldS = text.getText();
								String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);
								if (newS.trim().isEmpty()) {
									return;
								}
								boolean isNumber = true;
								if (dataType.equals("Integer")) {
									try {
										Integer.parseInt(newS);
									} catch (NumberFormatException ex) {
										isNumber = false;
									}
								}

								if (dataType.equals("Double")) {
									try {
										Double.parseDouble(newS);
									} catch (NumberFormatException ex) {
										isNumber = false;
									}
								}
								if (!isNumber) {
									e.doit = false;
								}
							}
						}
					});
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
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private boolean convertStringToBoolean(String status) {
		if (status.toLowerCase().equals("true")) {
			return true;
		}
		return false;
	}

	private String convertBooleanToString(boolean status) {
		if (status) {
			return "true";
		}
		return "false";
	}

	private boolean isDataTypeIntegerType(String dataType) {
		if (dataType.equals("Integer")) {
			return true;
		}
		if (dataType.equals("Float")) {
			return true;
		}
		if (dataType.equals("Double")) {
			return true;
		}
		return false;
	}

	private boolean isDataTypeBooleanrType(String dataType) {
		if (dataType.equals("Boolean")) {
			return true;
		}
		return false;
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
		boolean extrenalUpdate = globalVariable.isExternallyupdatable();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		CustomCombo combo = new CustomCombo(this, SWT.READ_ONLY);
		combo.setItems(OpKeyVariables.getInstance().getAllGlobalVariablesType());
		combo.select(Utilities.getInstance().getIndexOfItem(OpKeyVariables.getInstance().getAllGlobalVariablesType(),
				globalVariable.getDatatype()));

		combo.setEnabled(extrenalUpdate);
		combo.setControlData(globalVariable);
		CustomButton isExternallyUpdatable = new CustomButton(this, SWT.CHECK | SWT.CENTER);
		isExternallyUpdatable.setEnabled(extrenalUpdate);
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
				oatr.setValue("");
				oatr.setModified(true);
				renderGlobalVariables();
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
		if (isInsideArtifact() == false) {
			selectLastRow();
		}
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
		if (isInsideArtifact() == false) {
			selectLastRow();
		}
	}

	public void addBlankGlobalVariableStep() {
		int lastPosition = 0;
		GlobalVariable gv = new GlobalVariable();
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
		gv.setDatatype("String");
		gv.setAdded(true);
		gv.setExternallyupdatable(true);
		addGlobalVariable(gv);
		renderGlobalVariables();
		if (isInsideArtifact() == false) {
			selectDefaultRowByCursor(tableCursor, 0);
		}
	}

	public void deleteGlobalVariableStep() {
		GlobalVariable gv = getSelectedGlobalVariable();
		if (gv != null) {
			gv.setDeleted(true);
		}
		renderGlobalVariables();
		selectDefaultRow();
	}

	public boolean saveAll() {

		List<GlobalVariable> allGlobalVariables = getGlobalVariablesData();
		for (GlobalVariable gv : allGlobalVariables) {
			if (gv.isModified() || gv.isAdded()) {
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
		new GlobalVariablesTranspiler().transpile();
		OpKeyIntellisenseListenerDispatcher.getInstance().fireIntellisenseListener();
		OpKeyGlobalLoadListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
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

	public TableCursor getTableCursor() {
		return this.tableCursor;
	}

	public GlobalVariable getSelectedGlobalVariable() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection()[0] == null) {
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
