package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class CustomText extends Text {
	private Object controlData;

	public CustomText(Composite parent, int style) {
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
