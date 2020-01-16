
package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.featurecore.ide.ui.ui.LoginDialog;

public class LoginHandler {
	@Execute
	public void execute(Shell shell) {
		LoginDialog ldialog = new LoginDialog(shell, SWT.CENTER);
		
		ldialog.open();
		
	}
	
	

}