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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomCombo;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.usedby.FLUsedBy;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.dtoMaker.FunctionLibraryMaker;
import opkeystudio.opkeystudiocore.core.utils.OpKeyVariables;

public class OutputTable extends CustomTable {
	private boolean paintCalled = false;

	private BottomFactoryFLUi parentBottomFactoryFLUi;
	private List<ComponentOutputArgument> bottomFactoryOutputs = new ArrayList<ComponentOutputArgument>();
	private TableCursor cursor;
	private MenuItem addNewMenuItem;
	private MenuItem deleteMenuItem;
	private MenuItem moveUpMenuItem;
	private MenuItem moveDownMenuItem;

	public OutputTable(Composite parent, int style, BottomFactoryFLUi parentView) {
		super(parent, style);
		this.setParentBottomFactoryFLUi(parentView);
		init();
		initContextMenu();
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

		cursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(cursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;

		cursor.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button == 3) {
					return;
				}
				int selectedColumn = cursor.getColumn();
				CustomTableItem selectedTableItem = (CustomTableItem) cursor.getRow();
				ComponentOutputArgument componentOutputArgument = (ComponentOutputArgument) selectedTableItem
						.getControlData();
				CustomText text = new CustomText(cursor, 0);
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
						if (selectedColumn == 0) {
							componentOutputArgument.setName(text.getText());
							componentOutputArgument.setModified(true);
							getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
						}
						if (selectedColumn == 3) {
							componentOutputArgument.setDescription(text.getText());
							componentOutputArgument.setModified(true);
							getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
						}
						setColumnTextWithCursor(cursor, selectedColumn, text.getText());
					}
				});
				if (selectedColumn == 0) {
					text.setFocus();
					if (componentOutputArgument.getName() != null) {
						text.setText(componentOutputArgument.getName());
					} else {
						text.setText("");
					}
				}
				if (selectedColumn == 3) {
					text.setFocus();
					if (componentOutputArgument.getDescription() != null) {
						text.setText(componentOutputArgument.getDescription());
					} else {
						text.setText("");
					}
				}
				controlEditor.setEditor(text);

			}

			@Override
			public void mouseDown(MouseEvent e) {

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {

			}
		});
		renderAllBottomFactoryOutputData();
	}

	private void initContextMenu() {
		Menu menu = new Menu(this);
		this.setMenu(menu);
		cursor.setMenu(menu);
		addNewMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		deleteMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		moveUpMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		moveDownMenuItem = new MenuItem(menu, 0);

		addNewMenuItem.setText("Add New");
		deleteMenuItem.setText("Delete");
		moveUpMenuItem.setText("Move Up");
		moveDownMenuItem.setText("Move Down");

		addNewMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_ICON));
		deleteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		moveUpMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_UP_ICON));
		moveDownMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_DOWN_ICON));
		addNewMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addBlankOutputPrameter();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		deleteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteBottomFactoryOutputData();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveUpMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				moveFl_BottomFactoryOutputUp();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveDownMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				moveFl_BottomFactoryOutputDown();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void setComponentOutputData(List<ComponentOutputArgument> bottomFactoryOutputs) {
		this.bottomFactoryOutputs = bottomFactoryOutputs;
	}

	public List<ComponentOutputArgument> getComponentOutputData() {
		return this.bottomFactoryOutputs;
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
		combo.setItems(OpKeyVariables.getInstance().getAllFLOutputVariablesType());
		combo.select(Utilities.getInstance().getIndexOfItem(OpKeyVariables.getInstance().getAllFLOutputVariablesType(),
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
				getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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

	public void moveFl_BottomFactoryOutputUp() {
		ComponentOutputArgument bottomFactoryOutput1 = getSelectedOutputParemeter();
		ComponentOutputArgument bottomFactoryOutput2 = getPrevOutputParemeter();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		selectRow(selectedIndex - 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);
		getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
		refreshAllBottomFactoryOutputData();

	}

	public void moveFl_BottomFactoryOutputDown() {
		ComponentOutputArgument bottomFactoryOutput1 = getSelectedOutputParemeter();
		ComponentOutputArgument bottomFactoryOutput2 = getNextOutputParemeter();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		selectRow(selectedIndex + 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);
		getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
		refreshAllBottomFactoryOutputData();

	}

	public ComponentOutputArgument getSelectedOutputParemeter() {
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

	public ComponentOutputArgument getPrevOutputParemeter() {
		if (this.getSelectionIndices() == null) {
			return null;
		}
		if (this.getSelectionIndices().length == 0) {
			return null;
		}
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
		if (this.getSelectionIndices() == null) {
			return null;
		}
		if (this.getSelectionIndices().length == 0) {
			return null;
		}
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
		String variableName = this.getUniqueColumnData("output_parameter-", 0);
		Artifact artifact = getParentBottomFactoryFLUi().getParentTestCaseView().getArtifact();
		ComponentOutputArgument componentInputArgument = new FunctionLibraryMaker().createComponentOutputParameterDTO(
				artifact, variableName, getSelectedComponentOutputArgument(), getComponentOutputData());
		getComponentOutputData().add(componentInputArgument);
		Collections.sort(getComponentOutputData());
		getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
		refreshAllBottomFactoryOutputData();
	}

	public void deleteBottomFactoryOutputData() {
		ComponentOutputArgument componentInputArgument = getSelectedComponentOutputArgument();
		Artifact artifact = getParentBottomFactoryFLUi().getParentTestCaseView().getArtifact();
		boolean used = new FLUsedBy().isFLIsUsed(artifact);
		if (componentInputArgument.isAdded() == false) {
			if (used) {
				new MessageDialogs().openErrorDialog("OpKey",
						"Unable to delete Output Paramater as FL is getting used in higher components.");
				return;
			}
		}
		componentInputArgument.setDeleted(true);
		getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
		refreshAllBottomFactoryOutputData();
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

	public void toggleAddNewMenuItem(boolean status) {
		addNewMenuItem.setEnabled(status);
	}

	public void toggleDeleteMenuItem(boolean status) {
		deleteMenuItem.setEnabled(status);
	}

	public void toggleMoveUpMenuItem(boolean status) {
		moveUpMenuItem.setEnabled(status);
	}

	public void toggleMoveDownMenuItem(boolean status) {
		moveDownMenuItem.setEnabled(status);
	}

	public BottomFactoryFLUi getParentBottomFactoryFLUi() {
		return parentBottomFactoryFLUi;
	}

	public void setParentBottomFactoryFLUi(BottomFactoryFLUi parentBottomFactoryFLUi) {
		this.parentBottomFactoryFLUi = parentBottomFactoryFLUi;
	}

}
