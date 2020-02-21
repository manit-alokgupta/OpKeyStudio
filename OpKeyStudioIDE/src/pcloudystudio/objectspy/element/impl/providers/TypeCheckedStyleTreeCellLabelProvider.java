package pcloudystudio.objectspy.element.impl.providers;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;

import pcloudystudio.objectspy.element.impl.control.CTreeViewer;

@SuppressWarnings("unused")
public abstract class TypeCheckedStyleTreeCellLabelProvider<T> extends TypeCheckedStyleCellLabelProvider<T> {
	public TypeCheckedStyleTreeCellLabelProvider(final int columnIndex) {
		super(columnIndex);
	}

	public void initialize(final ColumnViewer viewer, final ViewerColumn column) {
		super.initialize(viewer, column);
	}

	@Override
	protected ViewerCell getOwnedViewerCell(final Event event) {
		final CTreeViewer treeViewer = (CTreeViewer) this.getViewer();
		return treeViewer.getViewerRowFromWidgetItem(event.item).getCell(this.columnIndex);
	}

	@Override
	protected boolean canNotDrawSafely(final Object element) {
		return super.canNotDrawSafely(element) || !(this.getViewer() instanceof CTreeViewer);
	}

	@Override
	protected Rectangle getTextBounds(final Rectangle originalBounds) {
		return new Rectangle(originalBounds.x, originalBounds.y, originalBounds.width + 1, originalBounds.height);
	}
}
