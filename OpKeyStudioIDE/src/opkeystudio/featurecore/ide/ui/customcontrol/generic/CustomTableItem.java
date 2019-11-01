package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class CustomTableItem extends TableItem {
	private Object data;

	public CustomTableItem(Table parent, int style) {
		super(parent, style);
	}

	public Object getOpKeyData() {
		return this.data;
	}

	public void setOpKeyData(Object data) {
		this.data = data;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
