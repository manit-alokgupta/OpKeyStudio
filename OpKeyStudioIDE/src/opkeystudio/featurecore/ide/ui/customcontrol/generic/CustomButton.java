package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class CustomButton extends Button {
	private Object controlData;

	public CustomButton(Composite parent, int style) {
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
