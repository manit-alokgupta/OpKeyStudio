package pcloudystudio.objectspy.element.impl.control;

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.jface.viewers.ViewerRow;
import org.eclipse.swt.widgets.Widget;

import pcloudystudio.objectspy.element.impl.providers.TypeCheckedStyleCellLabelProvider;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.jface.viewers.TableViewer;

public class CTableViewer extends TableViewer implements CustomColumnViewer {
	public CTableViewer(final Composite parent, final int style) {
		super(parent, style);
	}

	public Widget getColumn(final int columnIndex) {
		return this.getColumnViewerOwner(columnIndex);
	}

	public ViewerRow getViewerRowFromWidgetItem(final Widget item) {
		return super.getViewerRowFromItem(item);
	}

	public ViewerRow getViewerRowFromItem(final Widget item) {
		return super.getViewerRowFromItem(item);
	}

	public ViewerCell getCell(final Point point) {
		return super.getCell(point);
	}

	public void showLastItem() {
		final Table table = this.getTable();
		if (table == null || table.isDisposed()) {
			return;
		}
		final int lastItemIndex = table.getItemCount() - 1;
		if (lastItemIndex >= 0) {
			table.showItem(table.getItem(lastItemIndex));
		}
	}

	public TypeCheckedStyleCellLabelProvider<?> getCellLabelProvider(final int columnIndex) {
		return new CellLayoutColumnViewerHelper((ColumnViewer) this).getCellLabelProvider(columnIndex);
	}

	public void enableTooltipSupport() {
		this.getTable().setToolTipText("");
		ColumnViewerToolTipSupport.enableFor((ColumnViewer) this);
	}
}
