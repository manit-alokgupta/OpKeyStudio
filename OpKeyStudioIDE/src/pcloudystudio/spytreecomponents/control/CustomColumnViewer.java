package pcloudystudio.spytreecomponents.control;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerRow;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Widget;

import pcloudystudio.spytreecomponents.providers.TypeCheckedStyleCellLabelProvider;

public interface CustomColumnViewer {
	Widget getColumn(int index);

	ViewerRow getViewerRowFromWidgetItem(Widget item);

	TypeCheckedStyleCellLabelProvider<?> getCellLabelProvider(int p0);

	void enableTooltipSupport();

	ViewerCell getCell(Point p0);
}
