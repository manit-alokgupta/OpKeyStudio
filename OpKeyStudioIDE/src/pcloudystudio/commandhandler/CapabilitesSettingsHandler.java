
package pcloudystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import pcloudystudio.capability.CapabilitySettings;

public class CapabilitesSettingsHandler {
	@Execute
	public void execute(Shell parentShell) {
		CapabilitySettings capabilitySettingsDialog = new CapabilitySettings(parentShell, SWT.CENTER);
		capabilitySettingsDialog.open();
	}

}