package pcloudystudio.objectspy.element.tree;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.providers.TypeCheckedStyleTreeCellLabelProvider;

public class MobileElementLabelProvider extends TypeCheckedStyleTreeCellLabelProvider<TreeMobileElement> {
	public MobileElementLabelProvider() {
		super(0);
	}

	protected Class<TreeMobileElement> getElementType() {
		return TreeMobileElement.class;
	}

	protected Image getImage(final TreeMobileElement element) {
		return ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/test_object-16x16.png");
	}

	protected String getElementToolTipText(final TreeMobileElement element) {
		return this.getText(element);
	}

	protected String getText(final TreeMobileElement element) {
		return element.getName();
	}
}
