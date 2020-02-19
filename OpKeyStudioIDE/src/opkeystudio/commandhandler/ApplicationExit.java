
package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;

import opkeystudio.core.utils.MessageDialogs;

public class ApplicationExit {
	@Execute
	public void execute() {
		boolean status = new MessageDialogs().openConfirmDialog("OpKey", "Do you really want to exit?");
		if (status) {
			System.exit(0);
		}
	}

}