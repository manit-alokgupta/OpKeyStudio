package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.mylyn.commons.ui.dialogs.AbstractNotificationPopup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol.SuiteStepTable;
import opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol.SuiteStepTableItem;
import opkeystudio.notification.DeleteNotificationPopup;
import opkeystudio.notification.SaveNotificationPopup;
import opkeystudio.opkeystudiocore.core.apis.dbapi.testsuite.TestSuiteApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;

public class TestSuiteView extends Composite {
	private Text searchTextBox;
//	private SuiteStepTable table;
	private SuiteStepTable testSuiteTable;
	private TestSuite testSuite;
	private ToolItem runtoolitem;
	private ToolItem tagVersionRunToolItem;
	private ToolItem debugToolItem;
	private ToolItem runOnCloudToolItem;
	private ToolItem runOnBrowserToolItem;
	private ToolItem runOnSauceLabsToolItem;
	private ToolItem runOnPcloudyToolItem;
	private ToolItem toolGenerateDoc;
	private ToolItem scheduleSuiteToolItem;
	private ToolItem deleteSuiteStepToolItem;
	private ToolItem moveUpToolItem;
	private ToolItem moveDownToolItem;
	private ToolItem saveToolItem;
	private ToolItem refreshToolItem;
	private ToolItem javaExportToolItem;
	private ToolItem toolDropDown;
	private ToolBar toolBar_1;
	private Menu dropDownMenu;
	private MenuItem menuDraft;
	private MenuItem menuReview;
	private MenuItem menuApproved;
	private MenuItem menuPublished;
	private Display display;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TestSuiteView(Composite parent, int style) {
		super(parent, style);
		display = getParent().getDisplay();
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);

		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_1.setLayout(new GridLayout(1, false));

		toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_1.setBounds(0, 0, 87, 23);

		runtoolitem = new ToolItem(toolBar_1, SWT.NONE);
		runtoolitem.setToolTipText("Run Now");
		runtoolitem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/run_icon.png"));

		ToolItem toolItem_12 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		tagVersionRunToolItem = new ToolItem(toolBar_1, SWT.NONE);
		tagVersionRunToolItem.setToolTipText("Run With Tag Version");
		tagVersionRunToolItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/run_icon.png"));

		ToolItem toolItem_13 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		debugToolItem = new ToolItem(toolBar_1, SWT.NONE);
		debugToolItem.setToolTipText("Debug Now");
		debugToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/bug.png"));

		ToolItem toolItem_14 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		runOnCloudToolItem = new ToolItem(toolBar_1, SWT.NONE);
		runOnCloudToolItem.setToolTipText("Run on Cloud");
		runOnCloudToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/cloud.png"));

		ToolItem toolItem = new ToolItem(toolBar_1, SWT.SEPARATOR);

		runOnBrowserToolItem = new ToolItem(toolBar_1, SWT.NONE);
		runOnBrowserToolItem.setToolTipText("Run on Browser Stack");
		runOnBrowserToolItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/stackbrowser.png"));

		ToolItem toolItem_1 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		runOnSauceLabsToolItem = new ToolItem(toolBar_1, SWT.NONE);
		runOnSauceLabsToolItem.setToolTipText("Run on SauceLabs");
		runOnSauceLabsToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/saucelab.png"));

		ToolItem toolItem_2 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		runOnPcloudyToolItem = new ToolItem(toolBar_1, SWT.NONE);
		runOnPcloudyToolItem.setToolTipText("Run on PCloudy");
		runOnPcloudyToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/pcloudy.png"));

		ToolItem toolItem_3 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolGenerateDoc = new ToolItem(toolBar_1, SWT.NONE);
		toolGenerateDoc.setToolTipText("Generate Document");
		toolGenerateDoc
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/generate document.png"));

		ToolItem toolItem_4 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		scheduleSuiteToolItem = new ToolItem(toolBar_1, SWT.NONE);
		scheduleSuiteToolItem.setToolTipText("Schedule this Suite");
		scheduleSuiteToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/schedule.png"));

		ToolItem toolItem_5 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		deleteSuiteStepToolItem = new ToolItem(toolBar_1, SWT.NONE);
		deleteSuiteStepToolItem.setToolTipText("Delete Suite Step");
		deleteSuiteStepToolItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));

		ToolItem toolItem_6 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		moveUpToolItem = new ToolItem(toolBar_1, SWT.NONE);
		moveUpToolItem.setToolTipText("Move UP");
		moveUpToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/moveup_icon.png"));

		ToolItem toolItem_7 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		moveDownToolItem = new ToolItem(toolBar_1, SWT.NONE);
		moveDownToolItem.setToolTipText("Move Down");
		moveDownToolItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/movedown_icon.png"));

		ToolItem toolItem_8 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		saveToolItem = new ToolItem(toolBar_1, SWT.NONE);
		saveToolItem.setToolTipText("Save");
		saveToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/save.png"));

		ToolItem toolItem_9 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		refreshToolItem = new ToolItem(toolBar_1, SWT.NONE);
		refreshToolItem.setToolTipText("Refresh");
		refreshToolItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));

		ToolItem toolItem_10 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		javaExportToolItem = new ToolItem(toolBar_1, SWT.NONE);
		javaExportToolItem.setToolTipText("Java Export");
		javaExportToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/export.png"));

		ToolItem toolItem_11 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolDropDown = new ToolItem(toolBar_1, SWT.DROP_DOWN);
		toolDropDown.setText("More");

		dropDownMenu = new Menu(toolBar_1);
		toolBar_1.setMenu(dropDownMenu);

		menuDraft = new MenuItem(dropDownMenu, SWT.NONE);
		menuDraft.setText("Draft");

		new MenuItem(dropDownMenu, SWT.SEPARATOR);

		menuReview = new MenuItem(dropDownMenu, SWT.NONE);
		menuReview.setText("Review");

		new MenuItem(dropDownMenu, SWT.SEPARATOR);

		menuApproved = new MenuItem(dropDownMenu, SWT.NONE);
		menuApproved.setText("Approved");

		new MenuItem(dropDownMenu, SWT.SEPARATOR);

		menuPublished = new MenuItem(dropDownMenu, SWT.NONE);
		menuPublished.setText("Published");

//		table = new SuiteStepTable(composite_1, SWT.BORDER | SWT.FULL_SELECTION,this);
		testSuiteTable = new SuiteStepTable(composite_1, SWT.BORDER | SWT.FULL_SELECTION, this);
		testSuiteTable.setHeaderVisible(true);
		testSuiteTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		testSuiteTable.setBounds(0, 0, 85, 45);
		testSuiteTable.setLinesVisible(true);

		TableCursor tableCursor = new TableCursor(testSuiteTable, SWT.NONE);

		Composite composite_2 = new Composite(sashForm, SWT.BORDER);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_2.setLayout(new GridLayout(1, false));

		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_3.setLayout(new GridLayout(1, false));

		SashForm sashForm_1 = new SashForm(composite_3, SWT.NONE);
		sashForm_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sashForm_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		sashForm_1.setLocation(0, 0);

		Composite composite_5 = new Composite(sashForm_1, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_5.setLayout(new GridLayout(3, false));
		composite_5.setBounds(0, 0, 64, 64);

		Button btnNewButton = new Button(composite_5, SWT.NONE);
		btnNewButton.setToolTipText("Add Available Test Cases/Sparkin/Gherkin Features");
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		btnNewButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/leftArrow.png"));

		searchTextBox = new Text(composite_5, SWT.BORDER);
		searchTextBox.setToolTipText("Available Test Cases/Sparkin/Gherkin Features");
		searchTextBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		searchTextBox.setMessage("Available Test Cases");

		Button searchBtn = new Button(composite_5, SWT.NONE);
		searchBtn.setToolTipText("Search");
		searchBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		searchBtn.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/search.png"));
		searchBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		Composite composite_6 = new Composite(sashForm_1, SWT.NONE);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_6.setOrientation(SWT.RIGHT_TO_LEFT);
		composite_6.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(composite_6, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setToolTipText("Refresh");
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		tltmNewItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		sashForm_1.setWeights(new int[] { 4, 1 });

		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_4.setLayout(new GridLayout(1, false));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Tree tree = new Tree(composite_4, SWT.BORDER);
		tree.setLinesVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem.setText("New TreeItem");

		TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_1.setText("New TreeItem");
		sashForm.setWeights(new int[] { 2, 1 });

		toolDropDown.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Arrow Pressed");
				Rectangle rect = toolDropDown.getBounds();
				Point pt = new Point(rect.x, rect.y + rect.height);
				pt = toolBar_1.toDisplay(pt);
				dropDownMenu.setLocation(pt.x, pt.y);
				dropDownMenu.setVisible(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		toolBar_1.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button == 3) {
					System.out.println("Clicked");
					dropDownMenu.setVisible(false);
				}

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

		testSuiteTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TestSuite testSuite = testSuiteTable.getSelectedTestSuite();
				populateTestSuiteData(testSuite);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addButtonListeners();
		try {
			testSuiteTable.renderAllTestSuites();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		toggleSaveButton(false);
		toggleDeleteButton(false);
		toggleMoveUpButton(false);
		toggleMoveDownButton(false);

	}

	public void populateTestSuiteData(TestSuite testSuite) {
		if (testSuite != null) {
			setSelectedTEstSuite(testSuite);
			if (testSuiteTable.getSelectedTestSuite() == null) {
				toggleDeleteButton(false);
			} else {
				toggleDeleteButton(true);
			}
			if (testSuiteTable.getPrevTestSuite() == null) {
				moveUpToolItem.setEnabled(false);
			} else {
				moveUpToolItem.setEnabled(true);
			}
			if (testSuiteTable.getNextTestSuite() == null) {
				moveDownToolItem.setEnabled(false);
			} else {
				moveDownToolItem.setEnabled(true);
			}
		} else {
			toggleSaveButton(false);
			toggleDeleteButton(false);
			toggleMoveUpButton(false);
			toggleMoveDownButton(false);
		}

	}

	public void toggleSaveButton(boolean status) {
		saveToolItem.setEnabled(status);
	}

	public void toggleMoveUpButton(boolean status) {
		moveUpToolItem.setEnabled(status);
	}

	public void toggleMoveDownButton(boolean status) {
		moveDownToolItem.setEnabled(status);
	}

	public void toggleDeleteButton(boolean status) {
		deleteSuiteStepToolItem.setEnabled(status);
	}

	private void addButtonListeners() {

		runtoolitem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		tagVersionRunToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		debugToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		runOnCloudToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		runOnBrowserToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		runOnSauceLabsToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		runOnPcloudyToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		toolGenerateDoc.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		scheduleSuiteToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteSuiteStepToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean status = new MessageDialogs().openConfirmDialog("OpKey",
						"Do you want to delete '" + testSuiteTable.getSelectedTestSuite().getSuite_id() + "'?");
				if (!status) {
					return;
				}
				AbstractNotificationPopup notification = new DeleteNotificationPopup(display);
				notification.setDelayClose(1L);
				notification.open();
				try {
					testSuiteTable.deleteSuiteStep(testSuiteTable.getSelectedTestSuite());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				saveToolItem.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveUpToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					testSuiteTable.moveStepUp(testSuiteTable.getSelectedTestSuite(), testSuiteTable.getPrevTestSuite());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				saveToolItem.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveDownToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					testSuiteTable.moveStepDown(testSuiteTable.getSelectedTestSuite(),
							testSuiteTable.getNextTestSuite());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				saveToolItem.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		saveToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				saving();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (saveToolItem.isEnabled()) {
						boolean status = new MessageDialogs().openConfirmDialog("OpKey",
								"Do you want to Save changes?");
						if (!status) {
							toggleSaveButton(false);

							testSuiteTable.renderAllTestSuites();
							return;
						}
						AbstractNotificationPopup notification = new SaveNotificationPopup(display);
						notification.setDelayClose(1L);
						notification.open();
						new TestSuiteApi().saveAllTestSuite(testSuiteTable.getTestSuiteData());
						try {
							testSuiteTable.renderAllTestSuites();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						saveToolItem.setEnabled(false);
					}

					toggleDeleteButton(false);
					toggleMoveUpButton(false);
					toggleMoveDownButton(false);
					testSuiteTable.renderAllTestSuites();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		javaExportToolItem.addSelectionListener(new SelectionListener() {

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

	public void saving() {
		AbstractNotificationPopup notification = new SaveNotificationPopup(display);
		notification.setDelayClose(1L);
		notification.open();
		new TestSuiteApi().saveAllTestSuite(testSuiteTable.getTestSuiteData());
		try {
			testSuiteTable.renderAllTestSuites();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		saveToolItem.setEnabled(false);
	}

	private void setSelectedTEstSuite(TestSuite testSuite) {
		this.testSuite = testSuite;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
