package pcloudystudio.objectspy.element;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.List;

import pcloudystudio.objectspy.element.impl.CapturedMobileElement;

public interface TreeMobileElement extends MobileElement {
	TreeMobileElement getParentElement();

	List<? extends TreeMobileElement> getChildrenElement();
}