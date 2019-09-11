package opkeystudio.parts;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.jface.viewers.TreeViewer;

public class TreeView extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TreeView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TreeViewer treeViewer = new TreeViewer(this, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		TreeItem treeItem=new TreeItem(tree, SWT.BORDER);
		treeItem.setText("Hello");
		TreeItem treeItem_1=new TreeItem(treeItem, 0);
		treeItem_1.setText("Hello2");
		
		TreeItem treeItem_2 = new TreeItem(treeItem, 0);
		treeItem_2.setText("New TreeItem");
		treeItem.setExpanded(true);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
