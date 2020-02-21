
package pcloudystudio.objectspy.commandhandler;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import pcloudystudio.objectspy.dialog.MobileObjectSpyDialog;

public class AndroidDeviceSpyHandler {
	@Execute
	public void execute(Shell shell) {
		MobileObjectSpyDialog mosDialog = new MobileObjectSpyDialog(shell, SWT.CENTER);
		mosDialog.open();
	}
}