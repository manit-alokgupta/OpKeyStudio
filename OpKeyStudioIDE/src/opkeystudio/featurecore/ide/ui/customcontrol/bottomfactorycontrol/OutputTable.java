package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

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

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomCombo;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class OutputTable extends CustomTable {
	private boolean paintCalled = false;
	private OutputTable thisTable = this;

	private BottomFactoryFLUi parentBottomFactoryFLUi;

	public OutputTable(Composite parent, int style, BottomFactoryFLUi parentView) {
		super(parent, style);
		init();
		thisTable = this;
		this.setParentBottomFactoryFLUi(parentView);
	}

	private void init() {
		String[] tableHeaders = { "Name", "Data Type", "Associated Step Output", "Description" };
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
					column.setWidth(table_0.getBounds().width / 4);
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
		renderAllBottomFactoryOutputData();
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void setBottomFactoryOutputData(List<ComponentOutputArgument> bottomFactoryOutputs) {
		super.setControlData(bottomFactoryOutputs);
	}

	@SuppressWarnings("unchecked")
	public List<ComponentOutputArgument> getBottomFactoryOutputData() {
		return (List<ComponentOutputArgument>) super.getControlData();
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		editor.horizontalAlignment = SWT.CENTER;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(OutputTableItem outputTableItem) {
		ComponentOutputArgument bottomFactoryOutput = outputTableItem.getBottomFactoryOutputData();
		TableEditor editor1 = getTableEditor();

		CustomCombo combo = new CustomCombo(this, 0);
		combo.setItems(ServiceRepository.getInstance().getAllVaraiblesType());
		combo.select(Utilities.getInstance().getIndexOfItem(ServiceRepository.getInstance().getAllVaraiblesType(),
				bottomFactoryOutput.getType()));
		combo.setControlData(bottomFactoryOutput);

		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				thisTable.setSelection(outputTableItem);
				CustomCombo button = (CustomCombo) e.getSource();
				ComponentOutputArgument bottomFactoryOutput = (ComponentOutputArgument) button.getControlData();
				int selected = combo.getSelectionIndex();
				String selectedDataType = combo.getItem(selected);
				bottomFactoryOutput.setModified(true);
				bottomFactoryOutput.setType(selectedDataType);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		editor1.setEditor(combo, outputTableItem, 1);
		allTableEditors.add(editor1.getEditor());
	}

	private void disposeAllTableEditors() {
		for (Control editor : allTableEditors) {
			editor.dispose();
		}
	}

	public void refreshAllBottomFactoryOutputData() {
		disposeAllTableEditors();
		this.removeAll();
		List<ComponentOutputArgument> bottomFactoryOutputs = getBottomFactoryOutputData();
		setBottomFactoryOutputData(bottomFactoryOutputs);
		for (ComponentOutputArgument fl_BottomFactoryOutput : bottomFactoryOutputs) {
			if (fl_BottomFactoryOutput.isDeleted() == false) {
				OutputTableItem outputTableItem = new OutputTableItem(this, 0);
				outputTableItem.setText(new String[] { fl_BottomFactoryOutput.getName(),
						fl_BottomFactoryOutput.getType(), fl_BottomFactoryOutput.getComponentstep_oa_id(),
						fl_BottomFactoryOutput.getDescription() });
				outputTableItem.setBottomFactoryOutputData(fl_BottomFactoryOutput);
				addTableEditor(outputTableItem);
			}
		}
		selectRow(0);
	}

	public void renderAllBottomFactoryOutputData() {
		disposeAllTableEditors();
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
		List<ComponentOutputArgument> bottomFactoryOutputs = new FunctionLibraryApi()
				.getAllComponentOutputArgument(artifactId);
		setBottomFactoryOutputData(bottomFactoryOutputs);
		for (ComponentOutputArgument fl_BottomFactoryOutput : bottomFactoryOutputs) {
			if (fl_BottomFactoryOutput.isDeleted() == false) {
				OutputTableItem outputTableItem = new OutputTableItem(this, 0);
				System.out.println(fl_BottomFactoryOutput.getName().toString());
				System.out.println(fl_BottomFactoryOutput.getType());
				System.out.println(fl_BottomFactoryOutput.getDescription());
				outputTableItem.setText(new String[] { fl_BottomFactoryOutput.getName().toString(),
						fl_BottomFactoryOutput.getType(), fl_BottomFactoryOutput.getComponentstep_oa_id(),
						fl_BottomFactoryOutput.getDescription() });
				outputTableItem.setBottomFactoryOutputData(fl_BottomFactoryOutput);
				addTableEditor(outputTableItem);
			}
		}
		selectRow(0);
	}

	public void moveFl_BottomFactoryOutputUp(ComponentOutputArgument bottomFactoryOutput1,
			ComponentOutputArgument bottomFactoryOutput2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		refreshAllBottomFactoryOutputData();
		selectRow(selectedIndex - 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);

	}

	public void moveFl_BottomFactoryOutputDown(ComponentOutputArgument bottomFactoryOutput1,
			ComponentOutputArgument bottomFactoryOutput2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		refreshAllBottomFactoryOutputData();
		selectRow(selectedIndex + 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);

	}

	public ComponentOutputArgument getSelectedOutputParemeter() {
		OutputTableItem OutputTableItem = (OutputTableItem) this.getSelection()[0];
		return OutputTableItem.getBottomFactoryOutputData();
	}

	public ComponentOutputArgument getPrevOutputParemeter() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == 0) {
			return null;
		}
		selectedIndex = selectedIndex - 1;
		OutputTableItem OutputTableItem = (opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.OutputTableItem) this
				.getItem(selectedIndex);
		if (OutputTableItem != null) {
			return OutputTableItem.getBottomFactoryOutputData();
		}
		return null;
	}

	public ComponentOutputArgument getNextOutputParemeter() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == this.getItemCount() - 1) {
			return null;
		}
		selectedIndex = selectedIndex + 1;
		OutputTableItem OutputTableItem = (opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.OutputTableItem) this
				.getItem(selectedIndex);
		if (OutputTableItem != null) {
			return OutputTableItem.getBottomFactoryOutputData();
		}
		return null;
	}

	public void addBlankOutputPrameter() {
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
		int lastPosition = 0;
		ComponentOutputArgument bottomFactoryOutput = new ComponentOutputArgument();
		System.out.println(getBottomFactoryOutputData().size());

		if ((getBottomFactoryOutputData().size()) == 0) {
			lastPosition = (getBottomFactoryOutputData().size() - 1);

		} else {
			lastPosition = getBottomFactoryOutputData().get(getBottomFactoryOutputData().size() - 1).getPosition();
		}
		bottomFactoryOutput.setPosition(lastPosition + 1);
		bottomFactoryOutput.setOp_id(Utilities.getInstance().getUniqueUUID(""));
		bottomFactoryOutput.setComponent_id(artifactId);
		bottomFactoryOutput.setName("Default Name" + getBottomFactoryOutputData().size());
		bottomFactoryOutput.setType("String");
		bottomFactoryOutput.setDescription("");

		renderAllBottomFactoryOutputData();
	}

	public BottomFactoryFLUi getParentBottomFactoryFLUi() {
		return parentBottomFactoryFLUi;
	}

	public void setParentBottomFactoryFLUi(BottomFactoryFLUi parentBottomFactoryFLUi) {
		this.parentBottomFactoryFLUi = parentBottomFactoryFLUi;
	}
}
