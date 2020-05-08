package pcloudystudio.core.utils;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.jface.dialogs.InputDialog;

public class CustomMessageDialogUtil {

	public static void openInformationDialog(String title, String message) {
		MessageDialog mDialog = new MessageDialog(Display.getCurrent().getActiveShell(), title,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"), message, 2, 0,
				"OK");
		mDialog.open();
	}

	public static void openErrorDialog(String title, String message) {
		MessageDialog mDialog = new MessageDialog(Display.getCurrent().getActiveShell(), title,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"), message, 1, 0,
				"OK");
		mDialog.open();
	}

	public static int openConfirmDialog(String title, String message) {
		MessageDialog mDialog = new MessageDialog(Display.getCurrent().getActiveShell(), title,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"), message,
				MessageDialog.CONFIRM, new String[] { "Yes", "No", }, 0);
		int result = mDialog.open();
		return result;
	}

	public static String openInputDialog(String dialogTitle, String dialogContent, String defaultValue) {
		InputDialog input = new InputDialog(Display.getCurrent().getActiveShell(), dialogTitle, dialogContent,
				defaultValue, null);

		if (input.open() != InputDialog.OK) {
			return null;
		}
		return input.getValue();
	}

}
