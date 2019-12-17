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
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory.BottomFactoryInputParemeterApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;

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
				CustomText text = new CustomText(cursor, 0);
				System.out.println("Column number:-" + cursor.getColumn());
				System.out.println("Row number:-" + cursor.getRow());

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

	public void setBottomFactoryInputData(List<Fl_BottomFactoryInput> bottomFactoryInputs) {
		super.setControlData(bottomFactoryInputs);
	}

	public List<Fl_BottomFactoryInput> getBottomFactoryInputData() {
		return (List<Fl_BottomFactoryInput>) super.getControlData();
	}

	public void deleteBottomFactoryInputData(Fl_BottomFactoryInput bottomFactoryInput) {
		bottomFactoryInput.setDeleted(true);
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
		editor1.setEditor(isOptional, inputTableItem, 3);
		allTableEditors.add(editor1.getEditor());
	}

	public void refreshAllBottomFactoryInputData() {
		this.removeAll();
		List<Fl_BottomFactoryInput> bottomFactoryInputs = getBottomFactoryInputData();
		setBottomFactoryInputData(bottomFactoryInputs);
		for (Fl_BottomFactoryInput fl_BottomFactoryInput : bottomFactoryInputs) {
			if (fl_BottomFactoryInput.isDeleted() == false) {
				InputTableItem inputTableItem = new InputTableItem(this, 0);
				inputTableItem.setText(new String[] { fl_BottomFactoryInput.getName(), fl_BottomFactoryInput.getType(),
						"", "", fl_BottomFactoryInput.getDescription() });
				inputTableItem.setBottomFactoryInputData(fl_BottomFactoryInput);
			}
		}
	}

	public void renderAllBottomFactoryInputData()
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
		List<Fl_BottomFactoryInput> bottomFactoryInputs = new BottomFactoryInputParemeterApi()
				.getAllBottomFactoryInputParameter(artifactId);
		setBottomFactoryInputData(bottomFactoryInputs);
		for (Fl_BottomFactoryInput fl_BottomFactoryInput : bottomFactoryInputs) {
			if (fl_BottomFactoryInput.isDeleted() == false) {
				InputTableItem inputTableItem = new InputTableItem(this, 0);
				System.out.println(fl_BottomFactoryInput.getName().toString());
				System.out.println(fl_BottomFactoryInput.getType());
				System.out.println(fl_BottomFactoryInput.getDefault_value());
				System.out.println(fl_BottomFactoryInput.isIs_mandatory());
				System.out.println(fl_BottomFactoryInput.getDescription());
				inputTableItem.setText(
						new String[] { fl_BottomFactoryInput.getName().toString(), fl_BottomFactoryInput.getType(),
								fl_BottomFactoryInput.getDefault_value(), "", fl_BottomFactoryInput.getDescription() });
				inputTableItem.setBottomFactoryInputData(fl_BottomFactoryInput);
				addTableEditor(inputTableItem);
			}
		}
	}

	public BottomFactoryFLUi getParentBottomFactoryFLUi() {
		return parentBottomFactoryFLUi;
	}

	public void setParentBottomFactoryFLUi(BottomFactoryFLUi parentBottomFactoryFLUi) {
		this.parentBottomFactoryFLUi = parentBottomFactoryFLUi;
	}

}
