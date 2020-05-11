package pcloudystudio.resources.constant;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.swt.graphics.Image;

import pcloudystudio.resources.image.ImageManager;

public class ImageConstants {
	public static final Image IMG_16_ADD_CAPABILITY;
	public static final Image IMG_16_ADD_TO_TABLE;
	public static final Image IMG_16_BROWSE;
	public static final Image IMG_16_REFRESH_TABLE;
	public static final Image IMG_16_DELETE_CAPABILITY;
	public static final Image IMG_16_CANCEL;
	public static final Image IMG_16_HELP;
	public static final Image IMG_16_OPKEY_LOGO;
	public static final Image IMG_APPIUM_LOGO;
	public static final Image IMG_13_HELP;
	public static final Image IMG_16_TEST_OBJECT_MOBILE;

	static {
		IMG_16_ADD_CAPABILITY = ImageManager.getImage("add_capability_16.png");
		IMG_16_ADD_TO_TABLE = ImageManager.getImage("add_to_table_16.png");
		IMG_16_BROWSE = ImageManager.getImage("browse_16.png");
		IMG_16_REFRESH_TABLE = ImageManager.getImage("refresh_table_16.png");
		IMG_16_CANCEL = ImageManager.getImage("cancel_16.png");
		IMG_16_DELETE_CAPABILITY = ImageManager.getImage("delete_capability_16.png");
		IMG_16_HELP = ImageManager.getImage("help_16.png");
		IMG_16_OPKEY_LOGO = ImageManager.getImage("opkey-16x16.png");
		IMG_APPIUM_LOGO = ImageManager.getImage("appium_logo.jpg");
		IMG_13_HELP = ImageManager.getImage("help_13.png");
		IMG_16_TEST_OBJECT_MOBILE = ImageManager.getImage("test_object_mobile-16x16.png");
	}
}
