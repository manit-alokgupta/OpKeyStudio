package pcloudystudio.core.utils;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

import pcloudystudio.resources.constant.ImageConstants;

public class NotificationUtil {

	public static void showNotificationPopup(Shell shell, String notificationMessage) {
		ToolTip tip = new ToolTip(shell, SWT.BALLOON | SWT.ICON_INFORMATION);
		tip.setMessage(notificationMessage);
		Display display = shell.getDisplay();
		Tray tray = display.getSystemTray();
		if (tray != null) {
			TrayItem item = new TrayItem(tray, SWT.NONE);
			item.setImage(ImageConstants.IMG_16_OPKEY_LOGO);
			tip.setText("Notification from a Windows Tray");
			item.setToolTip(tip);
		} else {
			tip.setText("Notification from anywhere");
			tip.setLocation(400, 400);
		}
		tip.setVisible(true);
	}
}
