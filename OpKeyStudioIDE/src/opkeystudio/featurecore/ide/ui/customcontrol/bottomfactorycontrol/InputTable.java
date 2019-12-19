package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomCombo;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory.BottomFactoryInputParameterApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class InputTable extends CustomTable {

	private boolean paintCalled = false;
	private InputTable thisTable = this;
	private BottomFactoryFLUi parentBottomFactoryFLUi;

	public InputTable(Composite parent, int style, BottomFactoryFLUi parentView)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		super(parent, style);
		init();
		thisTable = this;
		this.setParentBottomFactoryFLUi(parentView);
	}

	private void init() throws JsonParseException, JsonMappingException, IOException, SQLException {
		String[] tableHeaders = { "Name", "Data Type", "Default Value", "Optional", "Description" };
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, 0);
			column.setText(header);
			column.setWidth(100);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				if (paintCalled) {
					// return;
				}
				Table table_0 = (Table) e.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 5);
				}
				paintCalled = true;

			}
		});

		TableCursor cursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(cursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;

		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedColumn = cursor.getColumn();
				CustomTableItem selectedTableItem = (CustomTableItem) cursor.getRow();
				CustomText text = new CustomText(cursor, 0);
				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						text.dispose();

					}

					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub

					}
				});

				text.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						selectedTableItem.setText(selectedColumn, text.getText());
						cursor.getRow().setText(selectedColumn, text.getText());

					}
				});

				controlEditor.setEditor(text);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		renderAllBottomFactoryInputData();
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void setBottomFactoryInputData(List<Fl_BottomFactoryInput> bottomFactoryInputs) {
		super.setControlData(bottomFactoryInputs);
	}

	@SuppressWarnings("unchecked")
	public List<Fl_BottomFactoryInput> getBottomFactoryInputData() {
		return (List<Fl_BottomFactoryInput>) super.getControlData();
	}

	public void addInputParameter(Fl_BottomFactoryInput bottomFactoryInput) {
		getBottomFactoryInputData().add(bottomFactoryInput);
	}

//	public void deleteBottomFactoryInputData(Fl_BottomFactoryInput bottomFactoryInput) {
	public void deleteBottomFactoryInputData() {
		int selectedIndex = this.getSelectionIndex();
		getBottomFactoryInputData().get(selectedIndex).setDeleted(true);
//		bottomFactoryInput.setDeleted(true);
		refreshAllBottomFactoryInputData();
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		editor.horizontalAlignment = SWT.CENTER;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(InputTableItem inputTableItem) {
		Fl_BottomFactoryInput bottomFactoryInput = inputTableItem.getBottomFactoryInputData();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		CustomCombo combo = new CustomCombo(this, 0);
		combo.setItems(ServiceRepository.getInstance().getAllVaraiblesType());
		combo.select(Utilities.getInstance().getIndexOfItem(ServiceRepository.getInstance().getAllVaraiblesType(),
				bottomFactoryInput.getType()));
		combo.setControlData(bottomFactoryInput);

		CustomButton isOptional = new CustomButton(this, SWT.CHECK);
		isOptional.setSelection(bottomFactoryInput.isIs_mandatory());
		isOptional.setControlData(bottomFactoryInput);
		isOptional.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				thisTable.setSelection(inputTableItem);
				CustomButton button = (CustomButton) e.getSource();
				Fl_BottomFactoryInput bottomFactoryInput1 = (Fl_BottomFactoryInput) button.getControlData();
				bottomFactoryInput1.setModified(true);
				bottomFactoryInput1.setIs_mandatory(button.getSelection());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				thisTable.setSelection(inputTableItem);
				CustomCombo button = (CustomCombo) e.getSource();
				Fl_BottomFactoryInput bottomFactoryInput1 = (Fl_BottomFactoryInput) button.getControlData();
				int selected = combo.getSelectionIndex();
				String selectedDataType = combo.getItem(selected);
				bottomFactoryInput1.setModified(true);
				bottomFactoryInput1.setType(selectedDataType);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		editor1.setEditor(isOptional, inputTableItem, 3);
		editor2.setEditor(combo, inputTableItem, 1);
		allTableEditors.add(editor1.getEditor());
		allTableEditors.add(editor2.getEditor());
	}

	private void disposeAllTableEditors() {
		for (Control editor : allTableEditors) {
			editor.dispose();
		}
	}

	public void refreshAllBottomFactoryInputData() {
		disposeAllTableEditors();
		this.removeAll();
		List<Fl_BottomFactoryInput> bottomFactoryInputs = getBottomFactoryInputData();
		setBottomFactoryInputData(bottomFactoryInputs);
		for (Fl_BottomFactoryInput fl_BottomFactoryInput : bottomFactoryInputs) {
			if (fl_BottomFactoryInput.isDeleted() == false) {
				InputTableItem inputTableItem = new InputTableItem(this, 0);
				inputTableItem.setText(new String[] { fl_BottomFactoryInput.getName(), fl_BottomFactoryInput.getType(),
						fl_BottomFactoryInput.getDefault_value(), "", fl_BottomFactoryInput.getDescription() });
				inputTableItem.setBottomFactoryInputData(fl_BottomFactoryInput);
				addTableEditor(inputTableItem);
			}
		}
		selectRow(0);
	}

	public void renderAllBottomFactoryInputData()
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		disposeAllTableEditors();
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
		List<Fl_BottomFactoryInput> bottomFactoryInputs = new BottomFactoryInputParameterApi()
				.getAllBottomFactoryInputParameter(artifactId);
		setBottomFactoryInputData(bottomFactoryInputs);
		for (Fl_BottomFactoryInput fl_BottomFactoryInput : bottomFactoryInputs) {
			if (fl_BottomFactoryInput.isDeleted() == false) {
				InputTableItem inputTableItem = new InputTableItem(this, 0);
				inputTableItem.setText(
						new String[] { fl_BottomFactoryInput.getName().toString(), fl_BottomFactoryInput.getType(),
								fl_BottomFactoryInput.getDefault_value(), "", fl_BottomFactoryInput.getDescription() });
				inputTableItem.setBottomFactoryInputData(fl_BottomFactoryInput);
				addTableEditor(inputTableItem);
			}
		}
		selectRow(0);
	}

	public void moveFl_BottomFactoryInputUp(Fl_BottomFactoryInput bottomFactoryInput1,
			Fl_BottomFactoryInput bottomFactoryInput2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryInput1.getPosition();
		int fpos2 = bottomFactoryInput2.getPosition();

		bottomFactoryInput1.setPosition(fpos2);
		bottomFactoryInput2.setPosition(fpos1);
		refreshAllBottomFactoryInputData();
		selectRow(selectedIndex - 1);
		bottomFactoryInput1.setModified(true);
		bottomFactoryInput2.setModified(true);

	}

	public void moveFl_BottomFactoryInputDown(Fl_BottomFactoryInput bottomFactoryInput1,
			Fl_BottomFactoryInput bottomFactoryInput2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryInput1.getPosition();
		int fpos2 = bottomFactoryInput2.getPosition();

		bottomFactoryInput1.setPosition(fpos2);
		bottomFactoryInput2.setPosition(fpos1);
		refreshAllBottomFactoryInputData();
		selectRow(selectedIndex + 1);
		bottomFactoryInput1.setModified(true);
		bottomFactoryInput2.setModified(true);

	}

	public Fl_BottomFactoryInput getSelectedInputParemeter() {
		InputTableItem inputTableItem = (InputTableItem) this.getSelection()[0];
		return inputTableItem.getBottomFactoryInputData();
	}

	public Fl_BottomFactoryInput getPrevInputParemeter() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == 0) {
			return null;
		}
		selectedIndex = selectedIndex - 1;
		InputTableItem inputTableItem = (InputTableItem) this.getItem(selectedIndex);
		if (inputTableItem != null) {
			return inputTableItem.getBottomFactoryInputData();
		}
		return null;
	}

	public Fl_BottomFactoryInput getNextInputParemeter() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == this.getItemCount() - 1) {
			return null;
		}
		selectedIndex = selectedIndex + 1;
		InputTableItem inputTableItem = (InputTableItem) this.getItem(selectedIndex);
		if (inputTableItem != null) {
			return inputTableItem.getBottomFactoryInputData();
		}
		return null;
	}

	public void addBlankInputPArameter() {
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
		int lastPosition = 0;
		Fl_BottomFactoryInput bottomFactoryInput = new Fl_BottomFactoryInput();
		System.out.println(getBottomFactoryInputData().size());

		if ((getBottomFactoryInputData().size()) == 0) {
			lastPosition = (getBottomFactoryInputData().size() - 1);

		} else {
			lastPosition = getBottomFactoryInputData().get(getBottomFactoryInputData().size() - 1).getPosition();
		}
		bottomFactoryInput.setPosition(lastPosition + 1);

		bottomFactoryInput.setIp_id(Utilities.getInstance().getUniqueUUID(""));

		bottomFactoryInput.setComponent_id(artifactId);

		bottomFactoryInput.setName("Default Name" + getBottomFactoryInputData().size());

		bottomFactoryInput.setType("String");

		bottomFactoryInput.setIs_mandatory(true);

		bottomFactoryInput.setDefault_value("");

		bottomFactoryInput.setDescription("");

		try {
			new BottomFactoryInputParameterApi().insertInputParameter(bottomFactoryInput);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		addInputParameter(bottomFactoryInput);
		try {
			renderAllBottomFactoryInputData();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BottomFactoryFLUi getParentBottomFactoryFLUi() {
		return parentBottomFactoryFLUi;
	}

	public void setParentBottomFactoryFLUi(BottomFactoryFLUi parentBottomFactoryFLUi) {
		this.parentBottomFactoryFLUi = parentBottomFactoryFLUi;
	}

}
