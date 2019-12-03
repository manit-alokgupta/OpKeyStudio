
package opkeystudio.parts.part;

import javax.inject.Inject;
import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import javax.annotation.PreDestroy;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

public class ConsoleWindowPart {
	@Inject
	public ConsoleWindowPart() {

	}

	@Inject
	MPart consoleWindowPart;

	@PostConstruct
	public void postConstruct(Composite parent) {
		System.out.println("Console Window Created");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("Console Window Destroyed");
		
	}

	@Focus
	public void onFocus() {
		System.out.println("Console Window Focused");
	}

	@Persist
	public void save() {
		System.out.println("Console Window Save");
	}

}