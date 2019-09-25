
package opkeystudio.command;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.parts.dialogui.AuthenticateUI;

public class Import {
	@Execute
	public void execute(Shell shell) {
		AuthenticateUI aui = new AuthenticateUI(shell, 1);
		aui.open();
	}

}