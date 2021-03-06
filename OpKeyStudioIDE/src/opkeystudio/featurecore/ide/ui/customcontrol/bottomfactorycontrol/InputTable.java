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
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomCombo;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.usedby.FLUsedBy;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.dtoMaker.FunctionLibraryMaker;
import opkeystudio.opkeystudiocore.core.utils.OpKeyVariables;

public class InputTable extends CustomTable {

	private boolean paintCalled = false;
	private BottomFactoryFLUi parentBottomFactoryFLUi;
	private List<ComponentInputArgument> bottomFactoryInputs = new ArrayList<ComponentInputArgument>();
	private TableCursor cursor;
	private MenuItem addNewMenuItem;
	private MenuItem deleteMenuItem;
	private MenuItem moveUpMenuItem;
	private MenuItem moveDownMenuItem;

	public InputTable(Composite parent, int style, BottomFactoryFLUi parentView) {
		super(parent, style);
		this.setParentBottomFactoryFLUi(parentView);
		init();
		initContextMenu();
	}

	private void init() {
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
				ComponentInputArgument componentInputAargument = (ComponentInputArgument) selectedTableItem
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
							componentInputAargument.setName(text.getText());
							componentInputAargument.setModified(true);
							getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
						}
						if (selectedColumn == 2) {
							componentInputAargument.setDefaultvalue(text.getText());
							componentInputAargument.setModified(true);
							getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
						}
						if (selectedColumn == 4) {
							componentInputAargument.setDescription(text.getText());
							componentInputAargument.setModified(true);
							getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
						}
						setColumnTextWithCursor(cursor, selectedColumn, text.getText());
					}
				});

				if (selectedColumn == 0) {
					text.setFocus();
					if (componentInputAargument.getName() != null) {
						text.setText(componentInputAargument.getName());
					} else {
						text.setText("");
					}
				}
				if (selectedColumn == 2) {
					text.setFocus();
					if (componentInputAargument.getDefaultvalue() != null) {
						text.setText(componentInputAargument.getDefaultvalue());
					} else {
						text.setText("");
					}
				}
				if (selectedColumn == 4) {
					text.setFocus();
					if (componentInputAargument.getDescription() != null) {
						text.setText(componentInputAargument.getDescription());
					} else {
						text.setText("");
					}
				}
				controlEditor.setEditor(text);

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
		renderAllBottomFactoryInputData();
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
				addBlankInputPArameter();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		deleteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteBottomFactoryInputData();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveUpMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				moveFl_BottomFactoryInputUp();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveDownMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				moveFl_BottomFactoryInputDown();
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

	public void setComponentInputData(List<ComponentInputArgument> bottomFactoryInputs) {
		this.bottomFactoryInputs = bottomFactoryInputs;
	}

	public List<ComponentInputArgument> getComponentInputData() {
		return this.bottomFactoryInputs;
	}

	public void addInputParameter(ComponentInputArgument bottomFactoryInput) {
		getComponentInputData().add(bottomFactoryInput);
	}

	public void deleteBottomFactoryInputData() {
		ComponentInputArgument componentInputArgument = getSelectedComponentInputArgument();
		Artifact artifact = getParentBottomFactoryFLUi().getParentTestCaseView().getArtifact();
		if (componentInputArgument.isAdded() == false) {
			boolean used = new FLUsedBy().isFLIsUsed(artifact);
			if (used) {
				new MessageDialogs().openErrorDialog("OpKey",
						"Unable to delete Input Paramater as FL is getting used in higher components.");
				return;
			}
		}
		componentInputArgument.setDeleted(true);
		getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
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
		ComponentInputArgument bottomFactoryInput = inputTableItem.getBottomFactoryInputData();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		CustomCombo combo = new CustomCombo(this, SWT.READ_ONLY);
		combo.setItems(OpKeyVariables.getInstance().getAllFLInputVariablesType());
		combo.select(Utilities.getInstance().getIndexOfItem(OpKeyVariables.getInstance().getAllFLInputVariablesType(),
				bottomFactoryInput.getType()));
		combo.setControlData(bottomFactoryInput);

		CustomButton isOptional = new CustomButton(this, SWT.CHECK);
		isOptional.setSelection(bottomFactoryInput.isIsmandatory());
		isOptional.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelection(inputTableItem);
				bottomFactoryInput.setModified(true);
				bottomFactoryInput.setIsmandatory(isOptional.getSelection());
				getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedData = combo.getText();
				setSelection(inputTableItem);
				bottomFactoryInput.setModified(true);
				bottomFactoryInput.setType(selectedData);
				getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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
		List<ComponentInputArgument> bottomFactoryInputs = getComponentInputData();
		Collections.sort(bottomFactoryInputs);
		setComponentInputData(bottomFactoryInputs);
		for (ComponentInputArgument fl_BottomFactoryInput : bottomFactoryInputs) {
			if (fl_BottomFactoryInput.isDeleted() == false) {
				InputTableItem inputTableItem = new InputTableItem(this, 0);
				inputTableItem.setText(new String[] { fl_BottomFactoryInput.getName(), fl_BottomFactoryInput.getType(),
						fl_BottomFactoryInput.getDefaultvalue(), "", fl_BottomFactoryInput.getDescription() });
				inputTableItem.setBottomFactoryInputData(fl_BottomFactoryInput);
				addTableEditor(inputTableItem);
			}
		}
		selectDefaultRow();
	}

	public void renderAllBottomFactoryInputData() {
		disposeAllTableEditors();
		this.removeAll();
		Artifact artifact = getParentBottomFactoryFLUi().getParentTestCaseView().getArtifact();
		String artifactId = artifact.getId();
		List<ComponentInputArgument> bottomFactoryInputs = new FunctionLibraryApi()
				.getAllComponentInputArgument(artifactId);
		setComponentInputData(bottomFactoryInputs);
		for (ComponentInputArgument fl_BottomFactoryInput : bottomFactoryInputs) {
			if (fl_BottomFactoryInput.isDeleted() == false) {
				InputTableItem inputTableItem = new InputTableItem(this, 0);
				inputTableItem.setText(
						new String[] { fl_BottomFactoryInput.getName().toString(), fl_BottomFactoryInput.getType(),
								fl_BottomFactoryInput.getDefaultvalue(), "", fl_BottomFactoryInput.getDescription() });
				inputTableItem.setBottomFactoryInputData(fl_BottomFactoryInput);
				addTableEditor(inputTableItem);
			}
		}
		selectDefaultRow();
	}

	public void moveFl_BottomFactoryInputUp() {

		ComponentInputArgument bottomFactoryInput1 = getSelectedComponentInputArgument();
		ComponentInputArgument bottomFactoryInput2 = getPrevInputParemeter();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryInput1.getPosition();
		int fpos2 = bottomFactoryInput2.getPosition();

		bottomFactoryInput1.setPosition(fpos2);
		bottomFactoryInput2.setPosition(fpos1);
		refreshAllBottomFactoryInputData();
		selectRow(selectedIndex - 1);
		bottomFactoryInput1.setModified(true);
		bottomFactoryInput2.setModified(true);
		getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
		refreshAllBottomFactoryInputData();

	}

	public void moveFl_BottomFactoryInputDown() {
		ComponentInputArgument bottomFactoryInput1 = getSelectedComponentInputArgument();
		ComponentInputArgument bottomFactoryInput2 = getNextInputParemeter();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryInput1.getPosition();
		int fpos2 = bottomFactoryInput2.getPosition();

		bottomFactoryInput1.setPosition(fpos2);
		bottomFactoryInput2.setPosition(fpos1);
		refreshAllBottomFactoryInputData();
		selectRow(selectedIndex + 1);
		bottomFactoryInput1.setModified(true);
		bottomFactoryInput2.setModified(true);
		getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
		refreshAllBottomFactoryInputData();
	}

	public ComponentInputArgument getSelectedInputParemeter() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		InputTableItem inputTableItem = (InputTableItem) this.getSelection()[0];
		return inputTableItem.getBottomFactoryInputData();
	}

	public ComponentInputArgument getPrevInputParemeter() {
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
		InputTableItem inputTableItem = (InputTableItem) this.getItem(selectedIndex);
		if (inputTableItem != null) {
			return inputTableItem.getBottomFactoryInputData();
		}
		return null;
	}

	public ComponentInputArgument getNextInputParemeter() {
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
		InputTableItem inputTableItem = (InputTableItem) this.getItem(selectedIndex);
		if (inputTableItem != null) {
			return inputTableItem.getBottomFactoryInputData();
		}
		return null;
	}

	public void addBlankInputPArameter() {
		String variableName = this.getUniqueColumnData("input-parameter-", 0);
		Artifact artifact = getParentBottomFactoryFLUi().getParentTestCaseView().getArtifact();
		ComponentInputArgument componentInputArgument = new FunctionLibraryMaker().createComponentInputParameterDTO(
				artifact, variableName, getSelectedComponentInputArgument(), getComponentInputData());
		getComponentInputData().add(componentInputArgument);
		Collections.sort(getComponentInputData());
		getParentBottomFactoryFLUi().getParentTestCaseView().toggleSaveButton(true);
		refreshAllBottomFactoryInputData();
		selectNextRow();
		selectDefaultRowByCursor(cursor, 0);
	}

	public ComponentInputArgument getSelectedComponentInputArgument() {
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
		return (ComponentInputArgument) cti.getControlData();
	}

	public BottomFactoryFLUi getParentBottomFactoryFLUi() {
		return parentBottomFactoryFLUi;
	}

	public void setParentBottomFactoryFLUi(BottomFactoryFLUi parentBottomFactoryFLUi) {
		this.parentBottomFactoryFLUi = parentBottomFactoryFLUi;
	}

	public MenuItem getAddNewMenuItem() {
		return addNewMenuItem;
	}

	public MenuItem getDeleteMenuItem() {
		return deleteMenuItem;
	}

	public MenuItem getMoveUpMenuItem() {
		return moveUpMenuItem;
	}

	public MenuItem getMoveDownMenuItem() {
		return moveDownMenuItem;
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

}
