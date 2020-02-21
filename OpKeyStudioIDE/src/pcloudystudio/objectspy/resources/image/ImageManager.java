package pcloudystudio.objectspy.resources.image;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.swt.graphics.Image;
import java.net.URL;
import org.eclipse.jface.resource.JFaceResources;
import org.osgi.framework.FrameworkUtil;

import pcloudystudio.objectspy.resources.util.ImageUtil;

import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;

public class ImageManager {
	public static final String IMAGE_PATH = "/icons/";
	private static final Bundle currentBundle;
	private static ImageRegistry imageRegistry;

	static {
		currentBundle = FrameworkUtil.getBundle((Class) ImageManager.class);
		ImageManager.imageRegistry = JFaceResources.getImageRegistry();
	}

	private ImageManager() {
	}

	public static ImageRegistry getImageRegistry() {
		return ImageManager.imageRegistry;
	}

	public static String getImageURLString(final String key) {
		return ImageUtil.getImageURLString(ImageManager.currentBundle, "/icons/" + key);
	}

	public static URL getImageURL(final String key) {
		return ImageUtil.getImageURL(ImageManager.currentBundle, "/icons/" + key);
	}

	public static Image getImage(final String key) {
		final Image registeredImage = ImageManager.imageRegistry.get(key);
		if (registeredImage != null && registeredImage.isDisposed()) {
			removeImage(key);
			registerImage(key);
		}
		return ImageManager.imageRegistry.get(key);
	}

	public static void registerImage(final String key, final Image image) {
		final Image registeredImage = getImage(key);
		if (registeredImage != null && registeredImage.isDisposed()) {
			removeImage(key);
		}
		if (registeredImage == null) {
			ImageManager.imageRegistry.put(key, image);
		}
	}

	public static void registerImage(final String key) {
		registerImage(key, ImageUtil.loadImage(ImageManager.currentBundle, "/icons/" + key));
	}

	public static void removeImage(final String key) {
		ImageManager.imageRegistry.remove(key);
	}

	public static void updateImage(final String key, final Image image) {
		ImageManager.imageRegistry.remove(key);
		ImageManager.imageRegistry.put(key, image);
	}
}
