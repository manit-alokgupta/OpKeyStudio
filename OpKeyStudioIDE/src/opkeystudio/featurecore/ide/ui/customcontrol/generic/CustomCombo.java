package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class CustomCombo extends Combo {
	private Object data;

	public CustomCombo(Composite parent, int style) {
		super(parent, style);
	}

	public Object getControlData() {
		return this.data;
	}

	public void setControlData(Object data) {
		this.data = data;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
