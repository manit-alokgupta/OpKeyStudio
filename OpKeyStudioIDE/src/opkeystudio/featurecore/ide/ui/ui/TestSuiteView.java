package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.TableItem;

public class TestSuiteView extends Composite {
	private Text text;
	private Table table;
	private ToolItem toolRun;
	private ToolItem toolRunOnBrowser;
	private ToolItem toolRunOnSauceLabs;
	private ToolItem toolRunOnPCloudy;
	private ToolItem toolGenerateDoc;
	private ToolItem toolScheduleSuite;
	private ToolItem toolDeleteSuiteStep;
	private ToolItem toolMoveUp;
	private ToolItem toolMoveDown;
	private ToolItem toolSave;
	private ToolItem toolRefresh;
	private ToolItem toolJavaExport;
	private ToolItem toolDropDown;
	private ToolBar toolBar_1;
	private Menu dropDownMenu;
	private MenuItem menuDraft;
	private MenuItem menuReview;
	private MenuItem menuApproved;
	private MenuItem menuPublished;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TestSuiteView(Composite parent, int style) {
		super(parent, style);
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

		toolRun = new ToolItem(toolBar_1, SWT.NONE);
		toolRun.setToolTipText("Run Now");
		toolRun.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/run_icon.png"));

		ToolItem toolItem = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolRunOnBrowser = new ToolItem(toolBar_1, SWT.NONE);
		toolRunOnBrowser.setToolTipText("Run on Browser Stack");
		toolRunOnBrowser.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/stackbrowser.png"));

		ToolItem toolItem_1 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolRunOnSauceLabs = new ToolItem(toolBar_1, SWT.NONE);
		toolRunOnSauceLabs.setToolTipText("Run on SauceLabs");
		toolRunOnSauceLabs.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/saucelab.png"));

		ToolItem toolItem_2 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolRunOnPCloudy = new ToolItem(toolBar_1, SWT.NONE);
		toolRunOnPCloudy.setToolTipText("Run on PCloudy");
		toolRunOnPCloudy.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/pcloudy.png"));

		ToolItem toolItem_3 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolGenerateDoc = new ToolItem(toolBar_1, SWT.NONE);
		toolGenerateDoc.setToolTipText("Generate Document");
		toolGenerateDoc
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/generate document.png"));

		ToolItem toolItem_4 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolScheduleSuite = new ToolItem(toolBar_1, SWT.NONE);
		toolScheduleSuite.setToolTipText("Schedule this Suite");
		toolScheduleSuite.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/schedule.png"));

		ToolItem toolItem_5 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolDeleteSuiteStep = new ToolItem(toolBar_1, SWT.NONE);
		toolDeleteSuiteStep.setToolTipText("Delete Suite Step");
		toolDeleteSuiteStep
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));

		ToolItem toolItem_6 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolMoveUp = new ToolItem(toolBar_1, SWT.NONE);
		toolMoveUp.setToolTipText("Move UP");
		toolMoveUp.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/moveup_icon.png"));

		ToolItem toolItem_7 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolMoveDown = new ToolItem(toolBar_1, SWT.NONE);
		toolMoveDown.setToolTipText("Move Down");
		toolMoveDown.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/movedown_icon.png"));

		ToolItem toolItem_8 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolSave = new ToolItem(toolBar_1, SWT.NONE);
		toolSave.setToolTipText("Save");
		toolSave.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/save_icon.png"));

		ToolItem toolItem_9 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolRefresh = new ToolItem(toolBar_1, SWT.NONE);
		toolRefresh.setToolTipText("Refresh");
		toolRefresh.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));

		ToolItem toolItem_10 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolJavaExport = new ToolItem(toolBar_1, SWT.NONE);
		toolJavaExport.setToolTipText("Java Export");
		toolJavaExport.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testSuite/export.png"));

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

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setBounds(0, 0, 85, 45);
		table.setLinesVisible(true);
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("New TableItem");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("New TableItem");

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

		text = new Text(composite_5, SWT.BORDER);
		text.setToolTipText("Available Test Cases/Sparkin/Gherkin Features");
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Button btnNewButton_1 = new Button(composite_5, SWT.NONE);
		btnNewButton_1.setToolTipText("Search");
		btnNewButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		btnNewButton_1.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/search.png"));
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

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
		
		toolRun.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		toolRunOnBrowser.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		toolRunOnSauceLabs.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		toolRunOnPCloudy.addSelectionListener(new SelectionListener() {
			
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
		
		toolScheduleSuite.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		toolDeleteSuiteStep.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		toolMoveUp.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		toolMoveDown.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		toolSave.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		toolRefresh.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		toolJavaExport.addSelectionListener(new SelectionListener() {
			
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

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
