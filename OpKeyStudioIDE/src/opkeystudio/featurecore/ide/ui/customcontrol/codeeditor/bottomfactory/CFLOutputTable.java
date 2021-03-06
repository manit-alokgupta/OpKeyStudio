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
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomCombo;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.usedby.CFLUsedBy;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.dtoMaker.CFLDMaker;
import opkeystudio.opkeystudiocore.core.utils.OpKeyVariables;
import pcloudystudio.core.utils.notification.CustomNotificationUtil;

public class CFLOutputTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;
	private List<CFLOutputParameter> cflOutputParameters = new ArrayList<CFLOutputParameter>();
	private MenuItem addNewMenuItem;
	private MenuItem deleteMenuItem;
	private MenuItem moveUpMenuItem;
	private MenuItem moveDownMenuItem;
	private TableCursor cursor;

	public CFLOutputTable(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactory) {
		super(parent, style);
		setParentBottomFactoryUI(parentBottomFactory);
		init();
		initContextMenu();
	}

	private void init() {

		String[] headers = new String[] { "Name", "Data Type", "Description" };
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
				CFLOutputParameter componentOutputArgument = (CFLOutputParameter) selectedTableItem.getControlData();
				CustomText text = new CustomText(cursor, 0);
				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						String value = getColumnTextWithCursor(cursor, 0);
						text.dispose();
						boolean isunique = isColumnDataUnique(value, 0);
						if (isunique == false) {
							componentOutputArgument.setModified(false);
							renderCFLOutputParameters();
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
							componentOutputArgument.setName(text.getText());
							componentOutputArgument.setModified(true);
							getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
						}
						if (selectedColumn == 3) {
							componentOutputArgument.setDescription(text.getText());
							componentOutputArgument.setModified(true);
							getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
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

	private List<Control> allTableEditors = new ArrayList<Control>();

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		editor.horizontalAlignment = SWT.CENTER;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(CustomTableItem outputTableItem) {
		CFLOutputParameter bottomFactoryOutput = (CFLOutputParameter) outputTableItem.getControlData();
		TableEditor editor1 = getTableEditor();

		CustomCombo combo = new CustomCombo(this, SWT.READ_ONLY);
		combo.setItems(OpKeyVariables.getInstance().getAllCFLOutputVariablesType());
		combo.select(Utilities.getInstance().getIndexOfItem(OpKeyVariables.getInstance().getAllCFLOutputVariablesType(),
				bottomFactoryOutput.getType()));
		combo.setControlData(bottomFactoryOutput);

		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelection(outputTableItem);
				CustomCombo button = (CustomCombo) e.getSource();
				CFLOutputParameter bottomFactoryOutput = (CFLOutputParameter) button.getControlData();
				int selected = combo.getSelectionIndex();
				String selectedDataType = combo.getItem(selected);
				bottomFactoryOutput.setModified(true);
				bottomFactoryOutput.setType(selectedDataType);
				getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
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

	public void renderCFLOutputParameters() {
		disposeAllTableEditors();
		this.removeAll();
		Artifact artifact = getParentBottomFactoryUI().getParentArtifactCodeView().getArtifact();
		List<CFLOutputParameter> outputParameters = new CodedFunctionApi().getCodedFLOutputParameters(artifact);
		this.setCflOutputParameters(outputParameters);
		for (CFLOutputParameter cfloutputparam : outputParameters) {
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setControlData(cfloutputparam);
			String description = cfloutputparam.getDescription();
			if (description == null) {
				description = "";
			}
			cti.setText(new String[] { cfloutputparam.getName(), cfloutputparam.getType(), description });
			addTableEditor(cti);
		}
		selectDefaultRow();
	}

	public void refreshCFLOutputParameters() {
		disposeAllTableEditors();
		this.removeAll();
		List<CFLOutputParameter> outputParameters = this.getCflOutputParameters();
		Collections.sort(outputParameters);
		this.setCflOutputParameters(outputParameters);
		for (CFLOutputParameter cfloutputparam : outputParameters) {
			if (cfloutputparam.isDeleted()) {
				continue;
			}
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setControlData(cfloutputparam);
			String description = cfloutputparam.getDescription();
			if (description == null) {
				description = "";
			}
			cti.setText(new String[] { cfloutputparam.getName(), cfloutputparam.getType(), description });
			addTableEditor(cti);
		}
		selectDefaultRow();
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void moveFl_BottomFactoryOutputUp() {
		CFLOutputParameter bottomFactoryOutput1 = getSelectedComponentOutputArgument();
		CFLOutputParameter bottomFactoryOutput2 = getPrevOutputParemeter();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		selectRow(selectedIndex - 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);
		getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
		refreshCFLOutputParameters();
		CustomNotificationUtil.openInformationNotification("OpKey", bottomFactoryOutput1.getName() + " moved up!");
	}

	public void moveFl_BottomFactoryOutputDown() {
		CFLOutputParameter bottomFactoryOutput1 = getSelectedComponentOutputArgument();
		CFLOutputParameter bottomFactoryOutput2 = getNextOutputParemeter();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		selectRow(selectedIndex + 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);
		getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
		refreshCFLOutputParameters();
		CustomNotificationUtil.openInformationNotification("OpKey", bottomFactoryOutput1.getName() + " moved down!");
	}

	public CFLOutputParameter getSelectedOutputParemeter() {
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
		return (CFLOutputParameter) cti.getControlData();
	}

	public CFLOutputParameter getPrevOutputParemeter() {
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
		CustomTableItem OutputTableItem = (CustomTableItem) this.getItem(selectedIndex);
		if (OutputTableItem != null) {
			return (CFLOutputParameter) OutputTableItem.getControlData();
		}
		return null;
	}

	public CFLOutputParameter getNextOutputParemeter() {
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
		CustomTableItem OutputTableItem = (CustomTableItem) this.getItem(selectedIndex);
		if (OutputTableItem != null) {
			return (CFLOutputParameter) OutputTableItem.getControlData();
		}
		return null;
	}

	public void addBlankOutputPrameter() {
		String variableName = this.getUniqueColumnData("output-parameter-", 0);
		CodedFunctionArtifact artifact = getParentBottomFactoryUI().getParentArtifactCodeView()
				.getCodedFunctionArtifact();
		CFLOutputParameter inputParameter = new CFLDMaker().createCFOutputParameterDTO(artifact, variableName,
				getSelectedComponentOutputArgument(), this.getCflOutputParameters());
		this.getCflOutputParameters().add(inputParameter);
		getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
		refreshCFLOutputParameters();
		CustomNotificationUtil.openInformationNotification("OpKey",
				inputParameter.getName() + " created successfully!");
	}

	public void deleteBottomFactoryOutputData() {
		CFLOutputParameter componentInputArgument = getSelectedComponentOutputArgument();
		Artifact artifact = getParentBottomFactoryUI().getParentArtifactCodeView().getArtifact();
		if (componentInputArgument.isAdded() == false) {
			boolean used = new CFLUsedBy().isCFLIsUsed(artifact);
			if (used) {
				new MessageDialogs().openErrorDialog("OpKey",
						"Unable to delete Output Paramater as CFL is getting used in higher components.");
				return;
			}
		}
		componentInputArgument.setAdded(false);
		componentInputArgument.setModified(false);
		componentInputArgument.setDeleted(true);
		getParentBottomFactoryUI().getParentArtifactCodeView().toggleSaveButton(true);
		refreshCFLOutputParameters();
		CustomNotificationUtil.openInformationNotification("OpKey",
				componentInputArgument.getName() + " deleted successfully!");
	}

	public CFLOutputParameter getSelectedComponentOutputArgument() {
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
		return (CFLOutputParameter) cti.getControlData();
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

	public CodedFunctionBottomFactoryUI getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

	public List<CFLOutputParameter> getCflOutputParameters() {
		return cflOutputParameters;
	}

	public void setCflOutputParameters(List<CFLOutputParameter> cflOutputParameters) {
		this.cflOutputParameters = cflOutputParameters;
	}

}
