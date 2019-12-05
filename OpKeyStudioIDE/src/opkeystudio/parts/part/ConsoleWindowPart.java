
package opkeystudio.parts.part;

import javax.inject.Inject;
import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import javax.annotation.PreDestroy;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.dialogs.MessageDialog;

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
//		boolean status = MessageDialog.openQuestion(Display.getCurrent().getActiveShell(), "OpKey",
//				"Please save before Quiting");
//		if (!status) {
//			System.out.println(status);
////			postConstruct(this);
//			return;
//		}
		
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