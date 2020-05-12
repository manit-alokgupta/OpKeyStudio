package pcloudystudio.core.utils.notification;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.swt.graphics.Color;

// a simple POJO that holds colors used by the Notify widget

class NotificationColors {
	Color titleColor;
	Color textColor;
	Color borderColor;
	Color leftColor;
	Color rightColor;

	void dispose() {
		SWTGraphicUtil.safeDispose(titleColor);
		SWTGraphicUtil.safeDispose(borderColor);
		SWTGraphicUtil.safeDispose(leftColor);
		SWTGraphicUtil.safeDispose(rightColor);
		SWTGraphicUtil.safeDispose(textColor);
	}
}
