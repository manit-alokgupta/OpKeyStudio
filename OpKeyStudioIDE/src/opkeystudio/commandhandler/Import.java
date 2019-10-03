
package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.featurecore.project.ui.ProjectCreationWizard;

public class Import {
	@Execute
	public void execute(Shell shell) {
		ProjectCreationWizard pwizard = new ProjectCreationWizard(shell, 0);
		pwizard.open();
	}

}