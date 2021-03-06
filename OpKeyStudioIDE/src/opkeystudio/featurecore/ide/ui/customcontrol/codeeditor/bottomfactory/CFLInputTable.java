package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

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
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomCombo;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.usedby.CFLUsedBy;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.dtoMaker.CFLDMaker;
import opkeystudio.opkeystudiocore.core.utils.OpKeyVariables;
import pcloudystudio.core.utils.notification.CustomNotificationUtil;

public class CFLInputTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;
	private List<CFLInputParameter> cflInputParameters = new ArrayList<CFLInputParameter>();
	private MenuItem addNewMenuItem;
	private MenuItem deleteMenuItem;
	private MenuItem moveUpMenuItem;
	private MenuItem moveDownMenuItem;
	private TableCursor cursor;

	public CFLInputTable(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactory) {
		super(parent, style);
		setParentBottomFactoryUI(parentBottomFactory);
		init();
		initContextMenu();
	}

	private void init() {

		String[] headers = new String[] { "Name", "Data Type", "DefaultValue", "Optional", "Description" };
		for (String header : headers) {
			TableColumn column = new TableColumn(this, 0);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < headers.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Table table_0 = (Table) e.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / headers.length);
				}
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
				CFLInputParameter componentInputAargument = (CFLInputParameter) selectedTableItem.getControlData();
				CustomText text = new CustomText(cursor, 0);
				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						String value = getColumnTextWithCursor(cursor, 0);
						text.dispose();
						boolean isunique = isColumnDataUnique(value, 0);
						if (isunique == false) {
							componentInputAargument.setModified(false);
							renderCFLInputParameters();
							new MessageDialogs().openErrorDialog("OpKey", "Variable Name Must Be Unique");
						}
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
							getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
						}
						if (selectedColumn == 2) {
							componentInputAargument.setDefaultvalue(text.getText());
							componentInputAargument.setModified(true);
							getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
						}
						if (selectedColumn == 4) {
							componentInputAargument.setDescription(text.getText());
							componentInputAargument.setModified(true);
							getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
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

	public CodedFunctionBottomFactoryUI getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		editor.horizontalAlignment = SWT.CENTER;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(CustomTableItem inputTableItem) {
		CFLInputParameter bottomFactoryInput = (CFLInputParameter) inputTableItem.getControlData();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		CustomCombo combo = new CustomCombo(this, SWT.READ_ONLY);
		combo.setItems(OpKeyVariables.getInstance().getAllCFLInputVariablesType());
		combo.select(Utilities.getInstance().getIndexOfItem(OpKeyVariables.getInstance().getAllCFLInputVariablesType(),
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
				getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
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
				getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
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

	public void renderCFLInputParameters() {
		disposeAllTableEditors();
		this.removeAll();
		Artifact artifact = getParentBottomFactoryUI().getParentArtifactCodeView().getArtifact();
		List<CFLInputParameter> cflInputParameters = new CodedFunctionApi().getCodedFLInputParameters(artifact);
		Collections.sort(cflInputParameters);
		this.setCflInputParameters(cflInputParameters);
		for (CFLInputParameter cflinputparam : cflInputParameters) {
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setControlData(cflinputparam);
			String description = cflinputparam.getDescription();
			if (description == null) {
				description = "";
			}
			cti.setText(new String[] { cflinputparam.getName(), cflinputparam.getType(),
					cflinputparam.getDefaultvalue(), "", description });
			addTableEditor(cti);
		}
		selectDefaultRow();
	}

	public void refreshCFLInputParameters() {
		disposeAllTableEditors();
		this.removeAll();
		List<CFLInputParameter> cflInputParameters = this.getCflInputParameters();
		Collections.sort(cflInputParameters);
		this.setCflInputParameters(cflInputParameters);
		for (CFLInputParameter cflinputparam : cflInputParameters) {
			if (cflinputparam.isDeleted()) {
				continue;
			}
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setControlData(cflinputparam);
			String description = cflinputparam.getDescription();
			if (description == null) {
				description = "";
			}
			cti.setText(new String[] { cflinputparam.getName(), cflinputparam.getType(),
					cflinputparam.getDefaultvalue(), "", description });
			addTableEditor(cti);
		}
		selectDefaultRow();
	}

	public CFLInputParameter getSelectedCFLInputArgument() {
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
		return (CFLInputParameter) cti.getControlData();
	}

	public CFLInputParameter getPrevInputParemeter() {
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
		CustomTableItem inputTableItem = (CustomTableItem) this.getItem(selectedIndex);
		if (inputTableItem != null) {
			return (CFLInputParameter) inputTableItem.getControlData();
		}
		return null;
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public CFLInputParameter getNextInputParemeter() {
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
		CustomTableItem inputTableItem = (CustomTableItem) this.getItem(selectedIndex);
		if (inputTableItem != null) {
			return (CFLInputParameter) inputTableItem.getControlData();
		}
		return null;
	}

	public void moveFl_BottomFactoryInputUp() {
		CFLInputParameter bottomFactoryInput1 = getSelectedCFLInputArgument();
		CFLInputParameter bottomFactoryInput2 = getPrevInputParemeter();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryInput1.getPosition();
		int fpos2 = bottomFactoryInput2.getPosition();

		bottomFactoryInput1.setPosition(fpos2);
		bottomFactoryInput2.setPosition(fpos1);
		refreshCFLInputParameters();
		selectRow(selectedIndex - 1);
		bottomFactoryInput1.setModified(true);
		bottomFactoryInput2.setModified(true);
		getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
		refreshCFLInputParameters();
		CustomNotificationUtil.openInformationNotification("OpKey", bottomFactoryInput1.getName() + " moved up!");
	}

	public void moveFl_BottomFactoryInputDown() {
		CFLInputParameter bottomFactoryInput1 = getSelectedCFLInputArgument();
		CFLInputParameter bottomFactoryInput2 = getNextInputParemeter();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryInput1.getPosition();
		int fpos2 = bottomFactoryInput2.getPosition();

		bottomFactoryInput1.setPosition(fpos2);
		bottomFactoryInput2.setPosition(fpos1);
		refreshCFLInputParameters();
		selectRow(selectedIndex + 1);
		bottomFactoryInput1.setModified(true);
		bottomFactoryInput2.setModified(true);
		getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
		refreshCFLInputParameters();
		CustomNotificationUtil.openInformationNotification("OpKey", bottomFactoryInput1.getName() + " moved down!");
	}

	public void deleteBottomFactoryInputData() {
		CFLInputParameter componentInputArgument = getSelectedCFLInputArgument();
		if (componentInputArgument.isAdded() == false) {
			Artifact artifact = getParentBottomFactoryUI().getParentArtifactCodeView().getArtifact();
			boolean usedby = new CFLUsedBy().isCFLIsUsed(artifact);
			if (usedby) {
				new MessageDialogs().openErrorDialog("OpKey",
						"Unable to delete Input Paramater as CFL is getting used in higher components.");
				return;
			}
		}
		componentInputArgument.setAdded(false);
		componentInputArgument.setModified(false);
		componentInputArgument.setDeleted(true);
		getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
		refreshCFLInputParameters();
		CustomNotificationUtil.openInformationNotification("OpKey",
				componentInputArgument.getName() + " deleted successfully!");
	}

	public void addBlankInputPArameter() {
		String variableName = this.getUniqueColumnData("input-parameter-", 0);
		CodedFunctionArtifact artifact = getParentBottomFactoryUI().getParentArtifactCodeView()
				.getCodedFunctionArtifact();
		CFLInputParameter inputParameter = new CFLDMaker().createCFInputParameterDTO(artifact, variableName,
				getSelectedCFLInputArgument(), this.getCflInputParameters());
		this.getCflInputParameters().add(inputParameter);
		getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
		refreshCFLInputParameters();
		CustomNotificationUtil.openInformationNotification("OpKey",
				inputParameter.getName() + " created successfully!");
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

	public List<CFLInputParameter> getCflInputParameters() {
		return cflInputParameters;
	}

	public void setCflInputParameters(List<CFLInputParameter> cflInputParameters) {
		this.cflInputParameters = cflInputParameters;
	}

}
