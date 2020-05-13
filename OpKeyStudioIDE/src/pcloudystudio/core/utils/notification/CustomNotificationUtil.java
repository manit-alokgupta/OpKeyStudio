package pcloudystudio.core.utils.notification;

//Created by Alok Gupta on 20/02/2020.
//Copyright � 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.wb.swt.ResourceManager;

import pcloudystudio.core.utils.notification.NotificationColorsFactory.NotificationTheme;
import pcloudystudio.resources.constant.ImageConstants;

public class CustomNotificationUtil {

	public static void openInformationNotificationDialog(String title, String message) {
		new MessageDialog(Display.getCurrent().getActiveShell(), title,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"), message, 2, 0,
				"OK").open();
	}

	public static void openErrorNotificationDialog(String title, String message) {
		new MessageDialog(Display.getCurrent().getActiveShell(), title,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"), message, 1, 0,
				"OK").open();
	}

	public static int openConfirmDialog(String title, String message) {
		return new MessageDialog(Display.getCurrent().getActiveShell(), title,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"), message,
				MessageDialog.CONFIRM, new String[] { "Yes", "No", "Cancel" }, 0).open();
	}

	public static int openConfirmDialog(Shell shell, String title, String message) {
		return new MessageDialog(shell, title,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"), message,
				MessageDialog.CONFIRM, new String[] { "Yes", "No", "Cancel" }, 0).open();
	}

	public static String openInputDialog(String dialogTitle, String dialogContent, String defaultValue) {
		InputDialog input = new InputDialog(Display.getCurrent().getActiveShell(), dialogTitle, dialogContent,
				defaultValue, null);

		if (input.open() != InputDialog.OK) {
			return null;
		}
		return input.getValue();
	}

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

	public static void openInformationNotification(String title, String message) {
		// TODO: Need to check disposed case and type 2, and type 1 notify + ( add new
		// sub heading )
		Notify.notify(ImageConstants.IMG_16_OPKEY_LOGO, title, message, NotificationTheme.BLUE_THEME);
	}

}
