 
package opkeystudio.command;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class Import {
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openConfirm(shell, "OpKey", "Do You Want Import?");
	}
		
}