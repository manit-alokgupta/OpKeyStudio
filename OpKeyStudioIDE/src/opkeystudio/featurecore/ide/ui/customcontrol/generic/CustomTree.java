package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class CustomTree extends Tree {
	private Object controlData;

	public CustomTree(Composite parent, int style) {
		super(parent, style);
	}

	public Object getControlData() {
		return controlData;
	}

	public void setControlData(Object controlData) {
		this.controlData = controlData;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
