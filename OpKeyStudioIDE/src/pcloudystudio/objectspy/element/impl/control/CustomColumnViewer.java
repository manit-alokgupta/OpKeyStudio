package pcloudystudio.objectspy.element.impl.control;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerRow;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Widget;

import pcloudystudio.objectspy.element.impl.providers.TypeCheckedStyleCellLabelProvider;

public interface CustomColumnViewer {
	Widget getColumn(final int p0);

	ViewerRow getViewerRowFromWidgetItem(final Widget p0);

	TypeCheckedStyleCellLabelProvider<?> getCellLabelProvider(final int p0);

	void enableTooltipSupport();

	ViewerCell getCell(final Point p0);
}
