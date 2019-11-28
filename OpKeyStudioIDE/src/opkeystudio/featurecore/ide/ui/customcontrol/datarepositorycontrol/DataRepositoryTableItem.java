package opkeystudio.featurecore.ide.ui.customcontrol.datarepositorycontrol;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;

public class DataRepositoryTableItem extends CustomTableItem {

	public DataRepositoryTableItem(Table parent, int style) {
		super(parent, style);
	}

	public DRColumnAttributes getDRColumnData() {
		return (DRColumnAttributes) super.getControlData();
	}

	public DRCellAttributes getDRCellData() {
		return (DRCellAttributes) super.getControlData();
	}

	public void setDRColumnData(DRColumnAttributes drColumn) {
		super.setControlData(drColumn);
	}

	public void setDRCellData(DRCellAttributes drCell) {
		super.setControlData(drCell);
	}

}
