package pcloudystudio.spytreecomponents.control;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.widgets.Control;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.jface.viewers.ViewerRow;
import org.eclipse.swt.widgets.Widget;

import pcloudystudio.spytreecomponents.providers.TypeCheckedStyleCellLabelProvider;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.jface.viewers.TreeViewer;

public class CTreeViewer extends TreeViewer implements CustomColumnViewer {
	private static int SECOND_CLICK = 2;
	private MouseListener mouseListener;

	public CTreeViewer(Composite parent, int style) {
		super(parent, style);
	}

	public CTreeViewer(Tree tree) {
		super(tree);
	}

	public Widget getColumn(int columnIndex) {
		return this.getColumnViewerOwner(columnIndex);
	}

	public ViewerRow getViewerRowFromWidgetItem(Widget item) {
		return this.getViewerRowFromItem(item);
	}

	public ViewerCell getCell(Point point) {
		return super.getCell(point);
	}

	public TypeCheckedStyleCellLabelProvider<?> getCellLabelProvider(int columnIndex) {
		return new CellLayoutColumnViewerHelper((ColumnViewer) this).getCellLabelProvider(columnIndex);
	}

	public void enableTooltipSupport() {
		this.getTree().setToolTipText("");
		ColumnViewerToolTipSupport.enableFor((ColumnViewer) this);
	}

	protected void hookEditingSupport(Control control) {
		if (this.getColumnViewerEditor() == null) {
			return;
		}
		control.addMouseListener(this.mouseListener = (MouseListener) new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				if (e.count != SECOND_CLICK) {
					CTreeViewer.this.handleMouseDown(e);
				}
			}

			public void mouseDoubleClick(MouseEvent e) {
				if (e.count == SECOND_CLICK) {
					CTreeViewer.this.handleMouseDown(e);
				}
			}
		});
	}

	protected void handleDispose(DisposeEvent event) {
		if (this.mouseListener != null && event.widget instanceof Control) {
			((Control) event.widget).removeMouseListener(this.mouseListener);
			this.mouseListener = null;
		}
		super.handleDispose(event);
	}

	private void handleMouseDown(MouseEvent e) {
		ViewerCell cell = this.getCell(new Point(e.x, e.y));
		if (cell != null) {
			this.triggerEditorActivationEvent(new ColumnViewerEditorActivationEvent(cell, e));
		}
	}
}
