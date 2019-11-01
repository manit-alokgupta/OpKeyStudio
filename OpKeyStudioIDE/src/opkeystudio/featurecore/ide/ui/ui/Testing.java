package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class Testing extends Composite {
	private Table table;
	private Table table_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Testing(Composite parent, int style) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(1, false));
		
		CBanner banner = new CBanner(this, SWT.NONE);
		GridData gd_banner = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_banner.heightHint = 552;
		gd_banner.widthHint = 0;
		gd_banner.verticalIndent = 1;
		banner.setLayoutData(gd_banner);
		
		Composite composite_1 = new Composite(banner, SWT.EMBEDDED);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		banner.setRight(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		Composite composite_4 = new Composite(composite_1, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_4.setLayout(new GridLayout(1, false));
		GridData gd_composite_4 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_4.heightHint = 139;
		composite_4.setLayoutData(gd_composite_4);
		
		ToolBar toolBar_1 = new ToolBar(composite_4, SWT.RIGHT);
		toolBar_1.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false, 1, 1));
		
		Composite composite_7 = new Composite(composite_4, SWT.NONE);
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_7.setBounds(0, 0, 64, 64);
		composite_7.setLayout(new TableColumnLayout());
		
		TableViewer tableViewer = new TableViewer(composite_7, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Composite composite = new Composite(banner, SWT.EMBEDDED);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		banner.setBottom(composite);
		banner.setLeft(composite);
		composite.setLayout(new GridLayout(1, false));
		
		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_3.setLayout(new GridLayout(1, false));
		GridData gd_composite_3 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_3.widthHint = 409;
		gd_composite_3.heightHint = 542;
		composite_3.setLayoutData(gd_composite_3);
		
		ToolBar toolBar = new ToolBar(composite_3, SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_6 = new Composite(composite_3, SWT.NONE);
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_6.setLayout(new TreeColumnLayout());
		
		TreeViewer treeViewer = new TreeViewer(composite_6, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_2.setLayout(new GridLayout(1, false));
		
		table_1 = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_1.setBounds(0, 0, 85, 45);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
