package pcloudystudio.commandhandler;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import pcloudystudio.featurecore.ui.dialog.AppiumSettingsDialog;

public class AppiumSettingsHandler {
	@Execute
	public void execute(Shell shell) {
		AppiumSettingsDialog sdialog = new AppiumSettingsDialog(shell, SWT.CENTER);
		sdialog.open();
	}
}