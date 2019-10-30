package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Tree;

import opkeystudio.featurecore.ide.ui.customcontrol.ObjectRepositoryTree;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;

public class ObjectRepositoryView extends Composite {
	private Table objectattributepropertytable;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ObjectRepositoryView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(this, SWT.BORDER | SWT.SMOOTH);

		SashForm sashForm_2 = new SashForm(sashForm, SWT.VERTICAL);

		ToolBar toolBar = new ToolBar(sashForm_2, SWT.FLAT | SWT.RIGHT);

		ObjectRepositoryTree objecttree = new ObjectRepositoryTree(sashForm_2, SWT.BORDER);
		sashForm_2.setWeights(new int[] { 1, 9 });

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		ToolBar toolBar_1 = new ToolBar(sashForm_1, SWT.FLAT | SWT.RIGHT);

		objectattributepropertytable = new Table(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		objectattributepropertytable.setHeaderVisible(true);
		objectattributepropertytable.setLinesVisible(true);
		sashForm_1.setWeights(new int[] { 1, 9 });
		sashForm.setWeights(new int[] { 2, 1 });

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
