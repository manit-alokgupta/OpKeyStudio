package pcloudystudio.spytreecomponents;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.swt.graphics.Image;

import pcloudystudio.resources.constant.ImageConstants;
import pcloudystudio.spytreecomponents.providers.TypeCheckedStyleTreeCellLabelProvider;

public class MobileElementLabelProvider extends TypeCheckedStyleTreeCellLabelProvider<TreeMobileElement> {
	public MobileElementLabelProvider() {
		super(0);
	}

	protected Class<TreeMobileElement> getElementType() {
		return TreeMobileElement.class;
	}

	protected Image getImage(TreeMobileElement element) {
		return ImageConstants.IMG_16_TEST_OBJECT;
	}

	protected String getElementToolTipText(TreeMobileElement element) {
		return this.getText(element);
	}

	protected String getText(TreeMobileElement element) {
		return element.getName();
	}
}
