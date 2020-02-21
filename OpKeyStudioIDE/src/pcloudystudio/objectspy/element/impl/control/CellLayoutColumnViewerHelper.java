package pcloudystudio.objectspy.element.impl.control;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;

import pcloudystudio.objectspy.element.impl.providers.TypeCheckedStyleCellLabelProvider;

public class CellLayoutColumnViewerHelper {
	private ColumnViewer viewer;

	public CellLayoutColumnViewerHelper(final ColumnViewer viewer) {
		this.viewer = viewer;
	}

	public TypeCheckedStyleCellLabelProvider<?> getCellLabelProvider(final int columnIndex) {
		if (this.viewer == null) {
			return null;
		}
		final CellLabelProvider cellLabelProvider = this.viewer.getLabelProvider(columnIndex);
		if (cellLabelProvider instanceof TypeCheckedStyleCellLabelProvider) {
			return (TypeCheckedStyleCellLabelProvider<?>) cellLabelProvider;
		}
		return null;
	}
}
