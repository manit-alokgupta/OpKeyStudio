package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.AuditTrailsTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.BackupTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.InputTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.OutputTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.TagTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.UsedByTable;
import opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory.BottomFactoryInputParameterApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory.BottomFactoryOutputParameterApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory.BottomFactoryTagApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.BottomFactoryTag;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryOutput;

public class BottomFactoryFLUi extends Composite {
	private UsedByTable usedByTable;
	private AuditTrailsTable auditTrailsTable;
	private TagTable tagsTable;
	private BackupTable backupTable;
	private InputTable inputTable;
	private OutputTable outputTable;

	private TabItem inputTabItem;
	private ToolItem addInputItem;
	private ToolItem deleteInputItem;
	private ToolItem moveUpInputItem;
	private ToolItem moveDownInputItem;
	private ToolItem refreshInputItem;
	private TabItem outputTabItem;
	private ToolItem addOutputItem;
	private ToolItem deleteOutputItem;
	private ToolItem moveUpOutputItem;
	private ToolItem moveDownOutputItem;
	private ToolItem refreshOutputItem;
	private ToolItem addTagItem;
	private ToolItem deleteTagItem;
	private ToolItem copyTagItem;
	private ToolItem pasteTagItem;
	private ToolItem moveUpTagItem;
	private ToolItem moveDownTagItem;
	public boolean flag = false;
	private Display display;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws SQLException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unused")
	public BottomFactoryFLUi(Composite parent, int style) {
		super(parent, style);

		display = getParent().getDisplay();
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite.setLayout(new GridLayout(1, false));

//		Composite composite_1 = new Composite(composite, SWT.NONE);
//		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
//		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
//		gd_composite_1.heightHint = 54;
//		composite_1.setLayoutData(gd_composite_1);

		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));

		ExpandItem item = new ExpandItem(expandBar, SWT.NONE);
		item.setText("Bottom Factory");
		item.setHeight(400);

		Group grpMenu = new Group(expandBar, SWT.NONE);
		grpMenu.setText("Menu");
		item.setControl(grpMenu);
		grpMenu.setLayout(new GridLayout(1, false));

		TabFolder tabFolder = new TabFolder(grpMenu, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setBounds(0, 0, 120, 43);
		tabFolder.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		TabItem detailsTabItem = new TabItem(tabFolder, SWT.NONE);
		detailsTabItem.setText("Details");
		detailsTabItem.setToolTipText("Details");

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		detailsTabItem.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		SashForm sashForm = new SashForm(composite_2, SWT.NONE);

		Composite composite_3 = new Composite(sashForm, SWT.BORDER);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		Composite composite_4 = new Composite(sashForm, SWT.BORDER);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sashForm.setWeights(new int[] { 1, 1 });

		TabItem usedByTabItem = new TabItem(tabFolder, SWT.NONE);
		usedByTabItem.setText("Used By");
		usedByTabItem.setToolTipText("Used By");

		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		usedByTabItem.setControl(composite_5);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		usedByTable = new UsedByTable(composite_5, SWT.BORDER | SWT.FULL_SELECTION, this);
//		usedByTable = new Table(composite_5, SWT.BORDER | SWT.FULL_SELECTION);
		usedByTable.setHeaderVisible(true);
		usedByTable.setLinesVisible(true);

		TableCursor tableCursor = new TableCursor(usedByTable, SWT.NONE);

		TabItem auditTrailsTabItem = new TabItem(tabFolder, SWT.NONE);
		auditTrailsTabItem.setText("Audit Trails");
		auditTrailsTabItem.setToolTipText("Audit Trails");

		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		auditTrailsTabItem.setControl(composite_6);
		composite_6.setLayout(new GridLayout(1, false));
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar = new ToolBar(composite_6, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolItem exportAuditToolItem = new ToolItem(toolBar, SWT.NONE);
		exportAuditToolItem.setWidth(27);
		exportAuditToolItem.setToolTipText("Export Audit Trails");
		exportAuditToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/export.png"));

		auditTrailsTable = new AuditTrailsTable(composite_6, SWT.BORDER | SWT.FULL_SELECTION, this);
//		auditTrailsTable = new Table(composite_6, SWT.BORDER | SWT.FULL_SELECTION);
		auditTrailsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		auditTrailsTable.setHeaderVisible(true);
		auditTrailsTable.setLinesVisible(true);

		TabItem tagsTabItem = new TabItem(tabFolder, SWT.NONE);
		tagsTabItem.setText("Tags");
		tagsTabItem.setToolTipText("Tags");

		Composite composite_7 = new Composite(tabFolder, SWT.NONE);
		tagsTabItem.setControl(composite_7);
		composite_7.setLayout(new GridLayout(1, false));
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_1 = new ToolBar(composite_7, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar_1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		addTagItem = new ToolItem(toolBar_1, SWT.NONE);
		addTagItem.setWidth(27);
		addTagItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		addTagItem.setToolTipText("Add");
		ToolItem toolItem1 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		deleteTagItem = new ToolItem(toolBar_1, SWT.NONE);
		deleteTagItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteTagItem.setToolTipText("Delete");
		deleteTagItem.setEnabled(false);

		ToolItem toolItem2 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		copyTagItem = new ToolItem(toolBar_1, SWT.NONE);
		copyTagItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/copy.png"));
		copyTagItem.setToolTipText("copy");

		ToolItem toolItem3 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		pasteTagItem = new ToolItem(toolBar_1, SWT.NONE);
		pasteTagItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/paste.png"));
		pasteTagItem.setToolTipText("Paste");

		ToolItem toolItem4 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		moveUpTagItem = new ToolItem(toolBar_1, SWT.NONE);
		moveUpTagItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/moveup_icon.png"));
		moveUpTagItem.setToolTipText("Move Up");
		moveUpTagItem.setEnabled(false);

		ToolItem toolItem5 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		moveDownTagItem = new ToolItem(toolBar_1, SWT.NONE);
		moveDownTagItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/movedown_icon.png"));
		moveDownTagItem.setToolTipText("Move Down");
		moveDownTagItem.setEnabled(false);

		tagsTable = new TagTable(composite_7, SWT.BORDER | SWT.FULL_SELECTION, this);
//		tagsTable = new Table(composite_7, SWT.BORDER | SWT.FULL_SELECTION);
		tagsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tagsTable.setHeaderVisible(true);
		tagsTable.setLinesVisible(true);

		TabItem backupTabItem = new TabItem(tabFolder, SWT.NONE);
		backupTabItem.setText("Backup");
		backupTabItem.setToolTipText("Backup");

		Composite composite_9 = new Composite(tabFolder, SWT.NONE);
		backupTabItem.setControl(composite_9);
		composite_9.setLayout(new GridLayout(1, false));
		composite_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_3 = new ToolBar(composite_9, SWT.FLAT | SWT.RIGHT);
		toolBar_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolItem compareBackupItem = new ToolItem(toolBar_3, SWT.NONE);
		compareBackupItem.setToolTipText("Compare With Backup");
		compareBackupItem.setText("New Item");

		backupTable = new BackupTable(composite_9, SWT.BORDER | SWT.FULL_SELECTION, this);
//		backupTable = new Table(composite_9, SWT.BORDER | SWT.FULL_SELECTION);
		backupTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		backupTable.setHeaderVisible(true);
		backupTable.setLinesVisible(true);

		inputTabItem = new TabItem(tabFolder, SWT.NONE);
		inputTabItem.setText("Input");
		inputTabItem.setToolTipText("Input");

		Composite composite_11 = new Composite(tabFolder, SWT.NONE);
		inputTabItem.setControl(composite_11);
		composite_11.setLayout(new GridLayout(1, false));

		ToolBar inputTabToolBar = new ToolBar(composite_11, SWT.FLAT | SWT.RIGHT);
		inputTabToolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		inputTabToolBar.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		addInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		addInputItem.setToolTipText("Add");
		addInputItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));

		ToolItem toolItem = new ToolItem(inputTabToolBar, SWT.SEPARATOR);

		deleteInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		deleteInputItem.setToolTipText("Delete");
		deleteInputItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteInputItem.setEnabled(false);

		ToolItem toolItem_1 = new ToolItem(inputTabToolBar, SWT.SEPARATOR);

		moveUpInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		moveUpInputItem.setToolTipText("Move Up");
		moveUpInputItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/moveup_icon.png"));
		moveUpInputItem.setEnabled(false);

		ToolItem toolItem_2 = new ToolItem(inputTabToolBar, SWT.SEPARATOR);

		moveDownInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		moveDownInputItem.setToolTipText("Move Down");
		moveDownInputItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/movedown_icon.png"));
		moveDownInputItem.setEnabled(false);

		ToolItem toolItem_3 = new ToolItem(inputTabToolBar, SWT.SEPARATOR);

		refreshInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		refreshInputItem.setToolTipText("Refresh");
		refreshInputItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));

		inputTable = new InputTable(composite_11, SWT.BORDER | SWT.FULL_SELECTION, this);
//		inputTable = new Table(composite_11, SWT.BORDER | SWT.FULL_SELECTION);
		inputTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		inputTable.setHeaderVisible(true);
		inputTable.setLinesVisible(true);

		outputTabItem = new TabItem(tabFolder, SWT.NONE);
		outputTabItem.setText("Output");
		outputTabItem.setToolTipText("Output");

		Composite composite_12 = new Composite(tabFolder, SWT.NONE);
		outputTabItem.setControl(composite_12);
		composite_12.setLayout(new GridLayout(1, false));

		ToolBar outputTabToolBar = new ToolBar(composite_12, SWT.FLAT | SWT.RIGHT);
		outputTabToolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		outputTabToolBar.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		addOutputItem = new ToolItem(outputTabToolBar, SWT.NONE);
		addOutputItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		addOutputItem.setToolTipText("Add");

		ToolItem toolItem_4 = new ToolItem(outputTabToolBar, SWT.SEPARATOR);

		deleteOutputItem = new ToolItem(outputTabToolBar, SWT.NONE);
		deleteOutputItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteOutputItem.setToolTipText("Delete");
		deleteOutputItem.setEnabled(false);

		ToolItem toolItem_5 = new ToolItem(outputTabToolBar, SWT.SEPARATOR);

		moveUpOutputItem = new ToolItem(outputTabToolBar, SWT.NONE);
		moveUpOutputItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/moveup_icon.png"));
		moveUpOutputItem.setToolTipText("Move Up");
		moveUpOutputItem.setEnabled(false);

		ToolItem toolItem_6 = new ToolItem(outputTabToolBar, SWT.SEPARATOR);

		moveDownOutputItem = new ToolItem(outputTabToolBar, SWT.NONE);
		moveDownOutputItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/movedown_icon.png"));
		moveDownOutputItem.setToolTipText("Move Down");
		moveDownOutputItem.setEnabled(false);

		ToolItem toolItem_7 = new ToolItem(outputTabToolBar, SWT.SEPARATOR);

		refreshOutputItem = new ToolItem(outputTabToolBar, SWT.NONE);
		refreshOutputItem.setToolTipText("Refresh");
		refreshOutputItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));

		outputTable = new OutputTable(composite_12, SWT.BORDER | SWT.FULL_SELECTION, this);
//		outputTable = new Table(composite_12, SWT.BORDER | SWT.FULL_SELECTION);
		outputTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		outputTable.setHeaderVisible(true);
		outputTable.setLinesVisible(true);

		expandBar.addListener(SWT.Expand, new Listener() {

			@Override
			public void handleEvent(Event event) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						parent.layout();
					}
				});
			}
		});
		expandBar.addListener(SWT.Collapse, new Listener() {

			@Override
			public void handleEvent(Event event) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						parent.layout();
					}
				});
			}
		});

		inputTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Fl_BottomFactoryInput bottomFactoryInput = inputTable.getSelectedInputParemeter();
				if (inputTable.getSelectedInputParemeter() != null) {
					toggleDeleteButton(true);
				} else {
					toggleDeleteButton(false);
				}
				if (inputTable.getPrevInputParemeter() != null) {
					toggleMoveUpButton(true);
				} else {
					toggleMoveUpButton(false);
				}
				if (inputTable.getNextInputParemeter() != null) {
					toggleMoveDownButton(true);
				} else {
					toggleMoveDownButton(false);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		outputTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Fl_BottomFactoryOutput bottomFactoryOutput = outputTable.getSelectedOutputParemeter();
				if (outputTable.getSelectedOutputParemeter() != null) {
					toggleDeleteButton(true);
				} else {
					toggleDeleteButton(true);
				}
				if (outputTable.getPrevOutputParemeter() != null) {
					toggleMoveUpButton(true);
				} else {
					toggleMoveUpButton(false);
				}
				if (outputTable.getNextOutputParemeter() != null) {
					toggleMoveDownButton(true);
				} else {
					toggleMoveDownButton(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		tagsTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				BottomFactoryTag bottomFactoryTag = tagsTable.getSelectedTagData();
				if (tagsTable.getSelectedTagData() != null) {
					toggleDeleteButton(true);
				} else {
					toggleDeleteButton(false);
				}

				if (tagsTable.getPrevTagData() != null) {
					toggleMoveUpButton(true);
				} else {
					toggleMoveUpButton(false);
				}

				if (tagsTable.getNextTagData() != null) {
					toggleMoveDownButton(true);
				} else {
					toggleMoveDownButton(false);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		addButtonListeners();
	}

	public void toggleMoveUpButton(boolean status) {
		moveUpInputItem.setEnabled(status);
		moveUpOutputItem.setEnabled(status);
		moveUpTagItem.setEnabled(status);
	}

	public void toggleMoveDownButton(boolean status) {
		moveDownInputItem.setEnabled(status);
		moveDownOutputItem.setEnabled(status);
		moveDownTagItem.setEnabled(status);
	}

	public void toggleRefreshInputButton(boolean status) {
		refreshInputItem.setEnabled(status);
	}

	public void toggleDeleteButton(boolean status) {
		deleteInputItem.setEnabled(status);
		deleteOutputItem.setEnabled(status);
		deleteTagItem.setEnabled(status);
	}

//	public void toggleMoveupOutputButton(boolean status) {
//		moveUpOutputItem.setEnabled(status);
//	}
//
//	public void toggleMovedownOutputButton(boolean status) {
//		moveDownOutputItem.setEnabled(status);
//	}
//
//	public void toggleDeleteOutputButton(boolean status) {
//		deleteOutputItem.setEnabled(status);
//	}

	public void toggleFlag(boolean status) {
		flag = status;
	}

	public void addButtonListeners() {
		addInputItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				inputTable.addBlankInputPArameter();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshInputItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (flag == true) {
					toggleFlag(false);

					boolean status = new MessageDialogs().openConfirmDialog("OpKey",
							"Do you want to Save changes?");
					if (!status) {
						inputTable.renderAllBottomFactoryInputData();
						return;
					}
					new BottomFactoryInputParameterApi()
							.saveAllBottomFactoryInputParameter(inputTable.getBottomFactoryInputData());
					inputTable.renderAllBottomFactoryInputData();
				}

				outputTable.renderAllBottomFactoryOutputData();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveUpInputItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				inputTable.moveFl_BottomFactoryInputUp(inputTable.getSelectedInputParemeter(),
						inputTable.getPrevInputParemeter());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveDownInputItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				inputTable.moveFl_BottomFactoryInputDown(inputTable.getSelectedInputParemeter(),
						inputTable.getNextInputParemeter());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteInputItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
//				inputTable.deleteBottomFactoryInputData(inputTable.getSelectedInputParemeter());
				inputTable.deleteBottomFactoryInputData();
				toggleDeleteButton(false);
				flag = true;
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveUpOutputItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				outputTable.moveFl_BottomFactoryOutputUp(outputTable.getSelectedOutputParemeter(),
						outputTable.getPrevOutputParemeter());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveDownOutputItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				outputTable.moveFl_BottomFactoryOutputDown(outputTable.getSelectedOutputParemeter(),
						outputTable.getNextOutputParemeter());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteOutputItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				outputTable.deleteBottomFactoryOutputData(outputTable.getSelectedOutputParemeter());
				flag = true;
//				fLView.toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshOutputItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (flag == true) {
					toggleFlag(false);
					boolean status = new MessageDialogs().openConfirmDialog("OpKey",
							"Do you want to Save changes?");
					if (!status) {
						outputTable.renderAllBottomFactoryOutputData();
						return;
					}
					new BottomFactoryOutputParameterApi()
							.saveAllBottomFactoryOutputParameter(outputTable.getBottomFactoryOutputData());
					outputTable.renderAllBottomFactoryOutputData();
				}

				outputTable.renderAllBottomFactoryOutputData();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addTagItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tagsTable.addBlankTagData();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteTagItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tagsTable.deleteTagData(tagsTable.getSelectedTagData());
				flag = true;

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		copyTagItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		pasteTagItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveUpTagItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tagsTable.moveTagDataUp(tagsTable.getSelectedTagData(), tagsTable.getPrevTagData());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveDownTagItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tagsTable.moveTagDataDown(tagsTable.getSelectedTagData(), tagsTable.getNextTagData());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void refreshBottomFactory() throws JsonParseException, JsonMappingException, IOException, SQLException {
		tagsTable.renderAllTagData();
		inputTable.renderAllBottomFactoryInputData();
		outputTable.renderAllBottomFactoryOutputData();
	}

	public void save() {
		new BottomFactoryOutputParameterApi()
				.saveAllBottomFactoryOutputParameter(outputTable.getBottomFactoryOutputData());
		new BottomFactoryInputParameterApi().saveAllBottomFactoryInputParameter(inputTable.getBottomFactoryInputData());
		new BottomFactoryTagApi().saveAllBottomFactoryTag(tagsTable.getTagData());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
