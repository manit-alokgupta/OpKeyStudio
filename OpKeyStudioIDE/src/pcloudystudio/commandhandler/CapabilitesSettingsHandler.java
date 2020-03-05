package pcloudystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import pcloudystudio.capability.CapabilitySettings;
import pcloudystudio.mobilespy.dialog.MobileSpyDialog;

public class CapabilitesSettingsHandler {
	@Execute
	public void execute(Shell parentShell) {
		CapabilitySettings capability_Dialog = new CapabilitySettings(parentShell, SWT.CENTER);
		capability_Dialog.open();
	}

	@CanExecute
	public boolean canExecute() {

		return true;
	}
}
