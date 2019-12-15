package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class SourceCodeEditor extends Composite {
	private StyledText styledText;

	public SourceCodeEditor(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		this.setLayout(new GridLayout(1, false));
		ToolBar sourceCodeToolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		sourceCodeToolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem sourceCodeToolBarItem = new ToolItem(sourceCodeToolBar, SWT.NONE);
		sourceCodeToolBarItem.setText("New Item");

		ToolItem seperator = new ToolItem(sourceCodeToolBar, SWT.SEPARATOR);

		ToolItem sourceCodeToolBarItem_1 = new ToolItem(sourceCodeToolBar, SWT.NONE);
		sourceCodeToolBarItem_1.setText("New Item");

		Composite composite_16 = new Composite(this, SWT.NONE);
		composite_16.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_16.setLayout(new GridLayout(2, false));

		Tree sourceCodeTree = new Tree(composite_16, SWT.BORDER);
		sourceCodeTree.setHeaderVisible(true);
		sourceCodeTree.setLinesVisible(false);

		renderTreeItems(sourceCodeTree);

		GridData gd_sourceCodeTree = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_sourceCodeTree.widthHint = 275;
		sourceCodeTree.setLayoutData(gd_sourceCodeTree);

		TabFolder tabFolder = new TabFolder(composite_16, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TabItem sourceCodeTab = new TabItem(tabFolder, SWT.NONE);
		sourceCodeTab.setText("New Item Neon");

		styledText = new StyledText(tabFolder, SWT.BORDER);
		sourceCodeTab.setControl(styledText);
		styledText.setMouseNavigatorEnabled(true);
		styledText.setText("int a=5;");
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("New Item");
	}

	private void renderTreeItems(Tree tree) {
		String[] items = new String[] { "Global Variables", "TestCases", "Object Repositories", "Function Libraries" };
		for (String item : items) {
			TreeItem sourceCodeTreeitem_1 = new TreeItem(tree, SWT.NONE);
			sourceCodeTreeitem_1.setText(item);
		}
	}

}
