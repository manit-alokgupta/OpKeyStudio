package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.ObjectRepositoryTree;

public class ObjectRepositoryView extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ObjectRepositoryView(Composite parent, int style) {
		super(parent, style);
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		fillLayout.marginWidth = 5;
		fillLayout.marginHeight = 5;
		setLayout(fillLayout);

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ToolBar toolBar = new ToolBar(composite_3, SWT.FLAT | SWT.RIGHT);
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setText("New Item");
		
		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.setText("New Item");
		
		ToolItem tltmNewItem_2 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_2.setText("New Item");
		
		Composite composite_4 = new Composite(sashForm_1, SWT.BORDER);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ObjectRepositoryTree tree = new ObjectRepositoryTree(composite_4, SWT.BORDER);
		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		sashForm_1.setWeights(new int[] {1, 15});
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_2, SWT.VERTICAL);
		
		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ToolBar toolBar_1 = new ToolBar(composite_5, SWT.FLAT | SWT.RIGHT);
		
		ToolItem tltmNewItem_3 = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem_3.setText("New Item");
		
		ToolItem tltmNewItem_4 = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem_4.setText("New Item");
		
		Composite composite_6 = new Composite(sashForm_2, SWT.BORDER);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_6, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		sashForm_2.setWeights(new int[] {1, 15});
		sashForm.setWeights(new int[] {2, 1});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
