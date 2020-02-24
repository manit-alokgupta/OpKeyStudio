
package pcloudystudio.mobilespy.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import pcloudystudio.mobilespy.dialog.MobileSpyDialog;

import org.eclipse.e4.core.di.annotations.CanExecute;

public class MobileSpyHandler {
	@Execute
	public void execute(Shell parentShell) {
		MobileSpyDialog mobileSpyDialog = new MobileSpyDialog(parentShell, SWT.CENTER);
		mobileSpyDialog.open();
	}

	@CanExecute
	public boolean canExecute() {

		return true;
	}

}