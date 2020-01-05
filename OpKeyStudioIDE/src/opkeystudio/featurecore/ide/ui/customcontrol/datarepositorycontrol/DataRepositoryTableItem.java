package opkeystudio.featurecore.ide.ui.customcontrol.datarepositorycontrol;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;

public class DataRepositoryTableItem extends CustomTableItem {
	private List<DRCellAttributes> drCellAttributes = new ArrayList<DRCellAttributes>();

	public DataRepositoryTableItem(Table parent, int style) {
		super(parent, style);
	}

	public List<DRCellAttributes> getDrCellAttributes() {
		return drCellAttributes;
	}

	public void setDrCellAttributes(List<DRCellAttributes> drCellAttributes) {
		this.drCellAttributes = drCellAttributes;
	}

}
