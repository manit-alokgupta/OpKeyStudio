package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.StackLayout;
import swing2swt.layout.FlowLayout;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.BorderLayout;

public class ObjectRepositoryView2 extends Composite {
	private Table table;
	private Table table_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ObjectRepositoryView2(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		SashForm sashForm_1 = new SashForm(sashForm, SWT.NONE);
		sashForm_1.setSashWidth(2);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.BORDER);
		GridLayout gl_composite_3 = new GridLayout(1, false);
		gl_composite_3.marginHeight = 0;
		gl_composite_3.marginWidth = 0;
		gl_composite_3.verticalSpacing = 0;
		composite_3.setLayout(gl_composite_3);
		
		ToolBar toolBar = new ToolBar(composite_3, SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Composite composite_1 = new Composite(composite_3, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_1.setLayout(new TreeColumnLayout());
		
		TreeViewer treeViewer = new TreeViewer(composite_1, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.BORDER);
		GridLayout gl_composite_4 = new GridLayout(1, false);
		gl_composite_4.marginHeight = 0;
		gl_composite_4.marginWidth = 0;
		gl_composite_4.verticalSpacing = 0;
		composite_4.setLayout(gl_composite_4);
		
		ToolBar toolBar_1 = new ToolBar(composite_4, SWT.RIGHT);
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Composite composite_2 = new Composite(composite_4, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_composite_2 = new GridLayout(1, false);
		gl_composite_2.marginHeight = 0;
		gl_composite_2.verticalSpacing = 0;
		gl_composite_2.marginWidth = 0;
		composite_2.setLayout(gl_composite_2);
		
		TableViewer tableViewer = new TableViewer(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		sashForm_1.setWeights(new int[] {2, 1});
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_5 = new Composite(composite, SWT.NONE);
		composite_5.setLayout(new TableColumnLayout());
		
		TableViewer tableViewer_1 = new TableViewer(composite_5, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		sashForm.setWeights(new int[] {3, 1});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
