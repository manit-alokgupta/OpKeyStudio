
package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.Utilities;

public class AboutCommand {
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, "About OpKey Studio",
				"OpKey-E is a product of OpKey. Visit https://www.opkey.com/" + System.lineSeparator() + "Version: " + Utilities.getInstance().getVersion());
	}

}