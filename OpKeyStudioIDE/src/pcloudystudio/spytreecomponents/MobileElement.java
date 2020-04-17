package pcloudystudio.spytreecomponents;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.Serializable;
import java.util.Map;

public interface MobileElement extends Cloneable, Serializable {
	String getName();

	void setName(String name);

	String getXpath();

	Map<String, String> getAttributes();

	MobileElement clone();
}
