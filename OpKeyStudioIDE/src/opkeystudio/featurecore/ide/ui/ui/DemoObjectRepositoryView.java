package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;

public class DemoObjectRepositoryView extends Composite {
	private Table table_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DemoObjectRepositoryView(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setSashWidth(2);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginHeight = 1;
		gl_composite.verticalSpacing = 1;
		gl_composite.marginWidth = 1;
		composite.setLayout(gl_composite);
		
		ToolBar toolBar = new ToolBar(composite, SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Composite composite_4 = new Composite(composite, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_4.setLayout(new TreeColumnLayout());
		
		TreeViewer treeViewer_1 = new TreeViewer(composite_4, SWT.BORDER);
		Tree tree_1 = treeViewer_1.getTree();
		tree_1.setHeaderVisible(true);
		tree_1.setLinesVisible(true);
		
		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.marginHeight = 1;
		gl_composite_1.verticalSpacing = 1;
		gl_composite_1.marginWidth = 1;
		composite_1.setLayout(gl_composite_1);
		
		ToolBar toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_3.setBounds(0, 0, 64, 64);
		composite_3.setLayout(new TableColumnLayout());
		
		TableViewer tableViewer_1 = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		sashForm.setWeights(new int[] {2, 1});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
