 
package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;

public class ApplicationExit {
	@Execute
	public void execute() {
		System.exit(0);
	}
		
}