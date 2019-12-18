package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.BottomFactoryTag;

public class TagTableItem extends CustomTableItem {

	public TagTableItem(Table parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public BottomFactoryTag getTagData() {
		return (BottomFactoryTag) super.getControlData();
	}

	public void setTagData(BottomFactoryTag bottomFactoryTag) {
		super.setControlData(bottomFactoryTag);
	}
}
