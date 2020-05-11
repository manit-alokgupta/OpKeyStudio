package pcloudystudio.resources.image;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

public class ImageManager {
	public static String IMAGE_PATH = "/icons/pcloudystudio/";

	private ImageManager() {
	}

	public static Image getImage(String key) {
		return ResourceManager.getPluginImage("OpKeyStudio", IMAGE_PATH + key);
	}
}
