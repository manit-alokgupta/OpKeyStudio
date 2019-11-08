package opkeystudio.featurecore.ide.ui.ui;

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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.FlowStepTable;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CLabel;

public class TestCaseView extends Composite {
	private FlowStepTable table;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TestCaseView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder mainTestCaseTabFolder = new TabFolder(this, SWT.BORDER | SWT.BOTTOM);
		mainTestCaseTabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		TabItem testCaseTabItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		testCaseTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase.gif"));
		testCaseTabItem.setText("TestCase");

		Composite testCaseHolder = new Composite(mainTestCaseTabFolder, SWT.NONE);
		testCaseHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		testCaseTabItem.setControl(testCaseHolder);
		testCaseHolder.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm testCaseSashForm = new SashForm(testCaseHolder, SWT.NONE);
		testCaseSashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		testCaseSashForm.setSashWidth(6);

		Composite testCaseStepsHolder = new Composite(testCaseSashForm, SWT.NONE);
		testCaseStepsHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		testCaseStepsHolder.setLayout(new GridLayout(1, false));

		ToolBar toolBar_1 = new ToolBar(testCaseStepsHolder, SWT.RIGHT);

		ToolItem tltmNewItem = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem.setText("New Item");

		ToolItem tltmNewItem_1 = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem_1.setText("New Item");

		ToolItem tltmNewItem_2 = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem_2.setText("New Item");

		table = new FlowStepTable(testCaseStepsHolder, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite testCaseArgumentsHolder = new Composite(testCaseSashForm, SWT.NONE);
		testCaseArgumentsHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		testCaseArgumentsHolder.setLayout(new GridLayout(1, false));

		TabFolder testCaseArgumentsTabFolder = new TabFolder(testCaseArgumentsHolder, SWT.NONE);
		testCaseArgumentsTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TabItem stepDetailsTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		stepDetailsTabItem.setImage(null);
		stepDetailsTabItem.setText("Step Details");

		Composite composite = new Composite(testCaseArgumentsTabFolder, SWT.BORDER);
		stepDetailsTabItem.setControl(composite);
		composite.setLayout(new GridLayout(1, false));
		
		ExpandBar expandBar = new ExpandBar(composite, SWT.BORDER);
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setExpanded(true);
		xpndtmNewExpanditem.setText("New ExpandItem");
		
		Composite composite_5 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setControl(composite_5);
		xpndtmNewExpanditem.setHeight(xpndtmNewExpanditem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		ExpandItem xpndtmNewExpanditem_1 = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem_1.setExpanded(true);
		xpndtmNewExpanditem_1.setText("New ExpandItem");
		
		Composite composite_6 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem_1.setControl(composite_6);
		xpndtmNewExpanditem_1.setHeight(xpndtmNewExpanditem_1.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		ExpandItem xpndtmNewExpanditem_2 = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem_2.setExpanded(true);
		xpndtmNewExpanditem_2.setText("New ExpandItem");
		
		Composite composite_7 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem_2.setControl(composite_7);
		xpndtmNewExpanditem_2.setHeight(xpndtmNewExpanditem_2.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		TabItem addStepTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		addStepTabItem.setText("Add Step");
		
		Composite composite_1 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		addStepTabItem.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));

		TabItem testObjectTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setText("Test Object");
		
		Composite composite_2 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));

		TabItem inputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		inputDataTabItem.setText("Input Data");
		
		Composite composite_3 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		inputDataTabItem.setControl(composite_3);
		composite_3.setLayout(new GridLayout(1, false));

		TabItem outputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setText("Output Data");
		
		Composite composite_4 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setControl(composite_4);
		composite_4.setLayout(new GridLayout(1, false));
		testCaseSashForm.setWeights(new int[] {3, 1});

		TableCursor cursor = new TableCursor(table, 0);
		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				table.deselectAll();
				table.setSelection(new TableItem[] { cursor.getRow() });
				int selectedColumn = cursor.getColumn();
				System.out.println("Column " + selectedColumn);
				if (selectedColumn == 1) {
					testCaseArgumentsTabFolder.setSelection(1);
				} else if (selectedColumn == 2) {
					testCaseArgumentsTabFolder.setSelection(2);
				} else if (selectedColumn == 3) {
					testCaseArgumentsTabFolder.setSelection(3);
				} else if (selectedColumn == 4) {
					testCaseArgumentsTabFolder.setSelection(4);
				} else {
					testCaseArgumentsTabFolder.setSelection(0);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		try {
			table.renderFlowSteps();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
