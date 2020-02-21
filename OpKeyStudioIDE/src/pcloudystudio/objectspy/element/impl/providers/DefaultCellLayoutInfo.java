package pcloudystudio.objectspy.element.impl.providers;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.core.runtime.Platform;

public class DefaultCellLayoutInfo implements CellLayoutInfo {
	@Override
	public int getLeftMargin() {
		return "win32".equals(Platform.getOS()) ? 0 : 2;
	}

	@Override
	public int getRightMargin() {
		return 0;
	}

	@Override
	public int getSpace() {
		return 5;
	}
}