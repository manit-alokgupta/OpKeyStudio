package pcloudystudio.objectspy.element.impl.providers;

import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Event;

import pcloudystudio.objectspy.element.impl.control.CTableViewer;

import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.jface.viewers.ColumnViewer;

public abstract class TypeCheckStyleCellTableLabelProvider<T> extends TypeCheckedStyleCellLabelProvider<T> {
	private CellLayoutInfo cellLayoutInfo;

	public TypeCheckStyleCellTableLabelProvider(final int columnIndex) {
		super(columnIndex);
		this.cellLayoutInfo = new TableCellLayoutInfo();
	}

	public void initialize(final ColumnViewer viewer, final ViewerColumn column) {
		super.initialize(viewer, column);
	}

	@Override
	protected ViewerCell getOwnedViewerCell(final Event event) {
		final CTableViewer tableViewer = (CTableViewer) this.getViewer();
		return tableViewer.getViewerRowFromItem(event.item).getCell(this.columnIndex);
	}

	@Override
	protected boolean canNotDrawSafely(final Object element) {
		return super.canNotDrawSafely(element) || !(this.getViewer() instanceof CTableViewer);
	}

	@Override
	public CellLayoutInfo getCellLayoutInfo() {
		return this.cellLayoutInfo;
	}
}
