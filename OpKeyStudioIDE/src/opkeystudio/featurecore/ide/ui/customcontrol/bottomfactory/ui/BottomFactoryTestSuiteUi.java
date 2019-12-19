package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.AuditTrailsTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.BackupTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.ExecutionStatusTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.TagTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.TestCaseDocumentTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.UsedByTable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.BottomFactoryTag;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class BottomFactoryTestSuiteUi extends Composite {
//	private Table auditTrailsTable;
//	private Table tagsTable;
//	private Table executionStatusTable;
//	private Table testCaseDocTable;
	private Table table;
	private Table mailToTable;
	private Table table_1;
	private Text textSearch;

	private TagTable tagsTable;
	private AuditTrailsTable auditTrailsTable;
	private ExecutionStatusTable executionStatusTable;
	private TestCaseDocumentTable testCaseDocTable;

	private ToolItem addTagItem;
	private ToolItem deleteTagItem;
	private ToolItem copyTagItem;
	private ToolItem pasteTagItem;
	private ToolItem moveUpTagItem;
	private ToolItem moveDownTagItem;
	private ToolItem addMailToItem;
	private ToolItem deleteMailToItem;
	private ToolItem sessionRefreshToolItem;
	private ToolItem testCaseDocRefreshItem;
	private ToolItem executionStatusrRefreshItem;
	private ToolItem exportAuditToolItem;
	private Display display;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	@SuppressWarnings("unused")
	public BottomFactoryTestSuiteUi(Composite parent, int style) {
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

		exportAuditToolItem = new ToolItem(toolBar, SWT.NONE);
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

		addTagItem = new ToolItem(toolBar_1, SWT.NONE);
//		addToolItem.setWidth(27);
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

		TabItem executionStatusTabItem = new TabItem(tabFolder, SWT.NONE);
		executionStatusTabItem.setText("Execution Status");
		executionStatusTabItem.setToolTipText("Execution Status");

		Composite composite_8 = new Composite(tabFolder, SWT.NONE);
		executionStatusTabItem.setControl(composite_8);
		composite_8.setLayout(new GridLayout(1, false));
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_2 = new ToolBar(composite_8, SWT.FLAT | SWT.RIGHT);
		toolBar_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		executionStatusrRefreshItem = new ToolItem(toolBar_2, SWT.NONE);
		executionStatusrRefreshItem.setToolTipText("Refresh");
		executionStatusrRefreshItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));

		executionStatusTable = new ExecutionStatusTable(composite_8, SWT.BORDER | SWT.FULL_SELECTION, this);
//		executionStatusTable = new Table(composite_8, SWT.BORDER | SWT.FULL_SELECTION);
		executionStatusTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		executionStatusTable.setHeaderVisible(true);
		executionStatusTable.setLinesVisible(true);

		TabItem mailToTabItem = new TabItem(tabFolder, SWT.NONE);
		mailToTabItem.setText("Mail To");

		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		mailToTabItem.setControl(composite_5);
		composite_5.setLayout(new GridLayout(1, false));

		ToolBar mailToToolbar = new ToolBar(composite_5, SWT.FLAT | SWT.RIGHT);
		mailToToolbar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		addMailToItem = new ToolItem(mailToToolbar, SWT.NONE);
		addMailToItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		addMailToItem.setToolTipText("Add");

		ToolItem toolItem = new ToolItem(mailToToolbar, SWT.SEPARATOR);

		deleteMailToItem = new ToolItem(mailToToolbar, SWT.NONE);
		deleteMailToItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteMailToItem.setToolTipText("Delete");

		mailToTable = new Table(composite_5, SWT.BORDER | SWT.FULL_SELECTION);
		mailToTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		mailToTable.setHeaderVisible(true);
		mailToTable.setLinesVisible(true);

		TabItem scheduleSessionTabItem = new TabItem(tabFolder, SWT.NONE);
		scheduleSessionTabItem.setText("Scheduled Session");

		Composite composite_12 = new Composite(tabFolder, SWT.NONE);
		scheduleSessionTabItem.setControl(composite_12);
		composite_12.setLayout(new GridLayout(1, false));

		Composite composite_13 = new Composite(composite_12, SWT.NONE);
		composite_13.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_13.setLayout(new GridLayout(4, false));

		ToolBar sessionToolbar = new ToolBar(composite_13, SWT.FLAT | SWT.RIGHT);
		sessionToolbar.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));

		sessionRefreshToolItem = new ToolItem(sessionToolbar, SWT.NONE);
		sessionRefreshToolItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		sessionRefreshToolItem.setToolTipText("Refresh");

		Label label = new Label(composite_13, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setVisible(false);
		GridData gd_label = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_label.widthHint = 163;
		label.setLayoutData(gd_label);

		textSearch = new Text(composite_13, SWT.BORDER);
		textSearch.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textSearch.setMessage("Search");

		Button clearButton = new Button(composite_13, SWT.NONE);
		clearButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/erase.png"));
		clearButton.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1));
		clearButton.setToolTipText("Clear Search");

		table_1 = new Table(composite_12, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TabItem testCaseDocTabItem = new TabItem(tabFolder, SWT.NONE);
		testCaseDocTabItem.setText("Test Case Document");
		testCaseDocTabItem.setToolTipText("Test Case Document");

		Composite composite_10 = new Composite(tabFolder, SWT.NONE);
		testCaseDocTabItem.setControl(composite_10);
		composite_10.setLayout(new GridLayout(1, false));
		composite_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_4 = new ToolBar(composite_10, SWT.FLAT | SWT.RIGHT);
		toolBar_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		testCaseDocRefreshItem = new ToolItem(toolBar_4, SWT.NONE);
		testCaseDocRefreshItem.setToolTipText("Refresh");
		testCaseDocRefreshItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));

		testCaseDocTable = new TestCaseDocumentTable(composite_10, SWT.BORDER | SWT.FULL_SELECTION, this);
//		testCaseDocTable = new Table(composite_10, SWT.BORDER | SWT.FULL_SELECTION);
		testCaseDocTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		testCaseDocTable.setHeaderVisible(true);
		testCaseDocTable.setLinesVisible(true);

		textSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				Text text = (Text) e.getSource();
				String searchValue = text.getText();
				if (searchValue.length() >= 1 || searchValue.trim().isEmpty()) {
					
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		clearButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				textSearch.setText("");
				String textToSearch = textSearch.getText();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

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
		addButtonListener();
	}

	public void toggleMoveUpButton(boolean status) {
		moveUpTagItem.setEnabled(status);
	}

	public void toggleMoveDownButton(boolean status) {
		moveDownTagItem.setEnabled(status);
	}

	public void toggleDeleteButton(boolean status) {
		deleteTagItem.setEnabled(status);
	}

	public void addButtonListener() {

		addMailToItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteMailToItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		sessionRefreshToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		testCaseDocRefreshItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addTagItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

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

		executionStatusrRefreshItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		exportAuditToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void refreshBottomFactory() throws JsonParseException, JsonMappingException, IOException, SQLException {
		tagsTable.renderAllTagData();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
