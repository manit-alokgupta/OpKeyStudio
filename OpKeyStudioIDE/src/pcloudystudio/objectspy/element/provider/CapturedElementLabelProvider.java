package pcloudystudio.objectspy.element.provider;

import org.eclipse.swt.graphics.Image;

import pcloudystudio.objectspy.element.impl.CapturedMobileElement;
import pcloudystudio.objectspy.element.impl.providers.CellLayoutInfo;
import pcloudystudio.objectspy.element.impl.providers.TableCellLayoutInfo;
import pcloudystudio.objectspy.element.impl.providers.TypeCheckStyleCellTableLabelProvider;
import pcloudystudio.objectspy.resources.image.ImageManager;

public class CapturedElementLabelProvider extends TypeCheckStyleCellTableLabelProvider<CapturedMobileElement> {
	public static final int SELECTION_COLUMN_IDX = 0;
	public static final int ELEMENT_COLUMN_IDX = 1;

	public CapturedElementLabelProvider(final int columnIdx) {
		super(columnIdx);
	}

	protected Class<CapturedMobileElement> getElementType() {
		return CapturedMobileElement.class;
	}

	protected Image getImage(final CapturedMobileElement element) {
		switch (this.columnIndex) {
		case 0: {
			return element.isChecked() ? ImageManager.getImage("pcloudystudio/checkbox_checked_16.png") : ImageManager.getImage("pcloudystudio/checkbox_unchecked_16.png");
		}
		case 1: {
			return (element.getLink() != null) ? ImageManager.getImage("pcloudystudio/active_16.png") : ImageManager.getImage("pcloudystudio/inactive_16.png");
		}
		default: {
			return null;
		}
		}
	}

	protected String getText(final CapturedMobileElement element) {
		if (this.columnIndex == 1) {
			return element.getName();
		}
		return "";
	}

	protected String getElementToolTipText(final CapturedMobileElement element) {
		switch (this.columnIndex) {
		case 1: {
			if (element.getLink() != null) {
				return "CELL_TOOLTIP_ACTIVE";
			}
			return "CELL_TOOLTIP_INACTIVE";
		}
		default: {
			return "";
		}
		}
	}

	public CellLayoutInfo getCellLayoutInfo() {
		return (CellLayoutInfo) new TableCellLayoutInfo() {
			public int getLeftMargin() {
				return 2;
			}
		};
	}
}
