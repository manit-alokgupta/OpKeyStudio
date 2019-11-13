
package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;


import opkeystudio.featurecore.ide.ui.ui.GlobalVariableDialog;



public class GlobalVariableHandler {
	@Execute
	public void execute(Shell shell) {
		GlobalVariableDialog gdialog = new GlobalVariableDialog(shell, SWT.CENTER);
		gdialog.open();
	}

}