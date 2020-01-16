package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import java.util.ArrayList;
import java.util.Collections;
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

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomCombo;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryConstruct;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.dtoMaker.FunctionLibraryMaker;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class OutputTable extends CustomTable {
	private boolean paintCalled = false;

	private BottomFactoryFLUi parentBottomFactoryFLUi;

	public OutputTable(Composite parent, int style, BottomFactoryFLUi parentView) {
		super(parent, style);
		this.setParentBottomFactoryFLUi(parentView);
		init();
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
				CustomTableItem selectedTableItem = (CustomTableItem) cursor.getRow();
				ComponentOutputArgument componentOutputArgument = (ComponentOutputArgument) selectedTableItem
						.getControlData();
				CustomText text = new CustomText(cursor, 0);
				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						text.dispose();
						saveAllComponentOutputArguments();
						renderAllBottomFactoryOutputData();
					}

					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub

					}
				});

				text.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						if (selectedColumn == 0) {
							componentOutputArgument.setName(text.getText());
							componentOutputArgument.setModified(true);
						}
						if (selectedColumn == 3) {
							componentOutputArgument.setDescription(text.getText());
							componentOutputArgument.setModified(true);
						}
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

	public void setComponentOutputData(List<ComponentOutputArgument> bottomFactoryOutputs) {
		super.setControlData(bottomFactoryOutputs);
	}

	@SuppressWarnings("unchecked")
	public List<ComponentOutputArgument> getComponentOutputData() {
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

		CustomCombo combo = new CustomCombo(this, SWT.READ_ONLY);
		combo.setItems(ServiceRepository.getInstance().getAllVaraiblesType());
		combo.select(Utilities.getInstance().getIndexOfItem(ServiceRepository.getInstance().getAllVaraiblesType(),
				bottomFactoryOutput.getType()));
		combo.setControlData(bottomFactoryOutput);

		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelection(outputTableItem);
				CustomCombo button = (CustomCombo) e.getSource();
				ComponentOutputArgument bottomFactoryOutput = (ComponentOutputArgument) button.getControlData();
				int selected = combo.getSelectionIndex();
				String selectedDataType = combo.getItem(selected);
				bottomFactoryOutput.setModified(true);
				bottomFactoryOutput.setType(selectedDataType);
				saveAllComponentOutputArguments();
				renderAllBottomFactoryOutputData();
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
		List<ComponentOutputArgument> bottomFactoryInputs = getComponentOutputData();
		Collections.sort(bottomFactoryInputs);
		setComponentOutputData(bottomFactoryInputs);
		for (ComponentOutputArgument componentOutputArg : bottomFactoryInputs) {
			if (componentOutputArg.isDeleted() == false) {
				OutputTableItem outputTableItem = new OutputTableItem(this, 0);
				outputTableItem.setText(new String[] { componentOutputArg.getName(), componentOutputArg.getType(), "",
						componentOutputArg.getDescription() });
				outputTableItem.setBottomFactoryOutputData(componentOutputArg);
				addTableEditor(outputTableItem);
			}
		}
		selectDefaultRow();
	}

	public void renderAllBottomFactoryOutputData() {
		disposeAllTableEditors();
		this.removeAll();
		Artifact artifact = getParentBottomFactoryFLUi().getParentTestCaseView().getArtifact();
		String artifactId = artifact.getId();
		List<ComponentOutputArgument> bottomFactoryInputs = new FunctionLibraryApi()
				.getAllComponentOutputArgument(artifactId);
		setComponentOutputData(bottomFactoryInputs);
		for (ComponentOutputArgument componentOutputArg : bottomFactoryInputs) {
			if (componentOutputArg.isDeleted() == false) {
				OutputTableItem inputTableItem = new OutputTableItem(this, 0);
				inputTableItem.setText(new String[] { componentOutputArg.getName().toString(),
						componentOutputArg.getType(), "", componentOutputArg.getDescription() });
				inputTableItem.setBottomFactoryOutputData(componentOutputArg);
				addTableEditor(inputTableItem);
			}
		}
		selectDefaultRow();
	}

	public void moveFl_BottomFactoryOutputUp(ComponentOutputArgument bottomFactoryOutput1,
			ComponentOutputArgument bottomFactoryOutput2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		selectRow(selectedIndex - 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);
		saveAllComponentOutputArguments();
		renderAllBottomFactoryOutputData();

	}

	public void moveFl_BottomFactoryOutputDown(ComponentOutputArgument bottomFactoryOutput1,
			ComponentOutputArgument bottomFactoryOutput2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		selectRow(selectedIndex + 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);
		saveAllComponentOutputArguments();
		renderAllBottomFactoryOutputData();

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
		Artifact artifact = getParentBottomFactoryFLUi().getParentTestCaseView().getArtifact();
		ComponentOutputArgument componentInputArgument = new FunctionLibraryMaker().createComponentOutputParameterDTO(
				artifact, getSelectedComponentOutputArgument(), getComponentOutputData());
		getComponentOutputData().add(componentInputArgument);
		Collections.sort(getComponentOutputData());
		saveAllComponentOutputArguments();
		renderAllBottomFactoryOutputData();
	}

	public void deleteBottomFactoryOutputData() {
		ComponentOutputArgument componentInputArgument = getSelectedComponentOutputArgument();
		componentInputArgument.setDeleted(true);
		saveAllComponentOutputArguments();
		renderAllBottomFactoryOutputData();
	}

	public ComponentOutputArgument getSelectedInputParemeter() {
		OutputTableItem inputTableItem = (OutputTableItem) this.getSelection()[0];
		return inputTableItem.getBottomFactoryOutputData();
	}

	public ComponentOutputArgument getPrevInputParemeter() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == 0) {
			return null;
		}
		selectedIndex = selectedIndex - 1;
		OutputTableItem inputTableItem = (OutputTableItem) this.getItem(selectedIndex);
		if (inputTableItem != null) {
			return inputTableItem.getBottomFactoryOutputData();
		}
		return null;
	}

	public ComponentOutputArgument getNextInputParemeter() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == this.getItemCount() - 1) {
			return null;
		}
		selectedIndex = selectedIndex + 1;
		OutputTableItem inputTableItem = (OutputTableItem) this.getItem(selectedIndex);
		if (inputTableItem != null) {
			return inputTableItem.getBottomFactoryOutputData();
		}
		return null;
	}

	public ComponentOutputArgument getSelectedComponentOutputArgument() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		CustomTableItem cti = (CustomTableItem) this.getSelection()[0];
		if (cti == null) {
			return null;
		}
		if (cti.getControlData() == null) {
			return null;
		}
		return (ComponentOutputArgument) cti.getControlData();
	}

	public BottomFactoryFLUi getParentBottomFactoryFLUi() {
		return parentBottomFactoryFLUi;
	}

	public void setParentBottomFactoryFLUi(BottomFactoryFLUi parentBottomFactoryFLUi) {
		this.parentBottomFactoryFLUi = parentBottomFactoryFLUi;
	}

	private void saveAllComponentOutputArguments() {
		new FunctionLibraryConstruct().saveComponentOutputArguments(getComponentOutputData());
	}
}
