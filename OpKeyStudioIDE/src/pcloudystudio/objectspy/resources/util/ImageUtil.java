package pcloudystudio.objectspy.resources.util;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.Map;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;
import org.eclipse.swt.graphics.Image;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.resource.ImageDescriptor;

public class ImageUtil {
	public static ImageDescriptor loadImageDescriptor(final String imageUrl) throws MalformedURLException {
		return ImageDescriptor.createFromURL(new URL(imageUrl));
	}

	public static Image loadImage(final String imageUrl) throws MalformedURLException {
		return loadImageDescriptor(imageUrl).createImage();
	}

	public static Image loadImage(final Bundle bundle, final String imageURI) {
		final URL url = FileLocator.find(bundle, (IPath) new Path(imageURI), (Map<String, String>) null);
		final ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();
	}

	public static String getImageURLString(final Bundle bundle, final String imageURI) {
		return getImageURL(bundle, imageURI).toString();
	}

	public static URL getImageURL(final Bundle bundle, final String imageURI) {
		return FileLocator.find(bundle, (IPath) new Path(imageURI), (Map<String, String>) null);
	}
}
