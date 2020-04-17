package pcloudystudio.spytreecomponents.control;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;

import pcloudystudio.spytreecomponents.providers.TypeCheckedStyleCellLabelProvider;

public class CellLayoutColumnViewerHelper {
	private ColumnViewer viewer;

	public CellLayoutColumnViewerHelper(ColumnViewer viewer) {
		this.viewer = viewer;
	}

	public TypeCheckedStyleCellLabelProvider<?> getCellLabelProvider(int columnIndex) {
		if (this.viewer == null) {
			return null;
		}
		CellLabelProvider cellLabelProvider = this.viewer.getLabelProvider(columnIndex);
		if (cellLabelProvider instanceof TypeCheckedStyleCellLabelProvider) {
			return (TypeCheckedStyleCellLabelProvider<?>) cellLabelProvider;
		}
		return null;
	}
}
