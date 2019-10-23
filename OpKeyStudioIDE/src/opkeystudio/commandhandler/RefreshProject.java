
package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

public class RefreshProject {
	@Execute
	public void execute(Shell shell) {
		refreshProjectTree();
	}

	public void refreshProjectTree() {
	}

}