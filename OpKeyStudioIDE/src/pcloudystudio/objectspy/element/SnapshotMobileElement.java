package pcloudystudio.objectspy.element;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

public interface SnapshotMobileElement<T> extends TreeMobileElement {
	void render(final T element);

	String getTagName();
}
