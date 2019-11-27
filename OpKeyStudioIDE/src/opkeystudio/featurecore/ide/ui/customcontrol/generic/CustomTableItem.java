package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class CustomTableItem extends TableItem {
	private Object data;

	public CustomTableItem(Table parent, int style) {
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
