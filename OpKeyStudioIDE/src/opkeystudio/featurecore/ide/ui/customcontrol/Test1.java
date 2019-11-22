package opkeystudio.featurecore.ide.ui.customcontrol;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.FillLayout;

public class Test1 extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Test1(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
