package pcloudystudio.commandhandler;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import pcloudystudio.featurecore.ui.dialog.MobileElementSpyDialog;

import org.eclipse.e4.core.di.annotations.CanExecute;

public class MobileElementSpyHandler {
	@Execute
	public void execute(Shell parentShell) {
		MobileElementSpyDialog mobileSpyDialog = new MobileElementSpyDialog(parentShell, SWT.CENTER);
		mobileSpyDialog.open();
	}

	@CanExecute
	public boolean canExecute() {
		return true;
	}

}