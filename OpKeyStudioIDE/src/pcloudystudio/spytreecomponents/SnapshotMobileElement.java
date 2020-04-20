package pcloudystudio.spytreecomponents;

// Created by Alok Gupta on 20/02/2020.
// Copyright � 2020 SSTS Inc. All rights reserved.

public interface SnapshotMobileElement<T> extends TreeMobileElement {
	void render(T element);

	String getTagName();
}