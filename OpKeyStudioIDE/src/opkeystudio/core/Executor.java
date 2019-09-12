package opkeystudio.core;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;

public class Executor {
	@Execute
	public void execute(IEclipseContext context) {
		System.out.println("Neon Started");
	}
}
