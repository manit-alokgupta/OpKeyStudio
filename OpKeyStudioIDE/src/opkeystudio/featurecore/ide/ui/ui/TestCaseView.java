package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.wb.swt.ResourceManager;

public class TestCaseView extends Composite {
	private Table testCaseStepsTable;

	/**
	 * Create the composite.
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
		
		ToolBar testCaseStepToolbar = new ToolBar(testCaseStepsHolder, SWT.FLAT | SWT.RIGHT);
		testCaseStepToolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TableViewer tableViewer = new TableViewer(testCaseStepsHolder, SWT.BORDER | SWT.FULL_SELECTION);
		testCaseStepsTable = tableViewer.getTable();
		testCaseStepsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite testCaseArgumentsHolder = new Composite(testCaseSashForm, SWT.NONE);
		testCaseArgumentsHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		testCaseArgumentsHolder.setLayout(new GridLayout(1, false));
		
		ToolBar testCaseArgumentsToolbar = new ToolBar(testCaseArgumentsHolder, SWT.FLAT | SWT.RIGHT);
		testCaseArgumentsToolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TabFolder testCaseArgumentsTabFolder = new TabFolder(testCaseArgumentsHolder, SWT.NONE);
		testCaseArgumentsTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem testCaseAargumentInputTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		testCaseAargumentInputTabItem.setText("New Item");
		testCaseSashForm.setWeights(new int[] {2, 1});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
