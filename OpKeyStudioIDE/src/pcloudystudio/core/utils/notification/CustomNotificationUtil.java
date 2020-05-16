package pcloudystudio.core.utils.notification;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

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

	public enum DialogResult {

		Yes(0), No(1), Cancel(2);

		private int index = -1;

		private DialogResult(int i) {
			this.index = i;
		}

		public static DialogResult fromIndex(int i) {
			switch (i) {
			case 0:
				return DialogResult.Yes;
			case 1:
				return DialogResult.No;
			case 2:
				return DialogResult.Cancel;
			default:
				return null;

			}
		}
	}

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

	public static DialogResult openConfirmDialog(String title, String message) {
		int index = new MessageDialog(Display.getCurrent().getActiveShell(), title,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"), message,
				MessageDialog.CONFIRM, new String[] { "Yes", "No", "Cancel" }, 0).open();
		return DialogResult.fromIndex(index);
	}

	public static DialogResult openConfirmDialog(Shell shell, String title, String message) {
		if (shell.isDisposed()) {
			return DialogResult.Cancel;
		}
		int index = new MessageDialog(shell, title,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"), message,
				MessageDialog.CONFIRM, new String[] { "Yes", "No", "Cancel" }, 0).open();
		return DialogResult.fromIndex(index);
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

	public static void openInformationNotification(String title, String message, Boolean isError) {
		if (isError)
			Notify.notify(ImageConstants.IMG_16_OPKEY_LOGO, title, message, NotificationTheme.RED_THEME);
		else
			Notify.notify(ImageConstants.IMG_16_OPKEY_LOGO, title, message, NotificationTheme.BLUE_THEME);
	}

	public static void openInformationNotification(String title, String message) {
		openInformationNotification(title, message, false);
	}

	public static void openErrorNotification(String title, String message) {
		openInformationNotification(title, message, true);
	}

}
