
package opkeystudio.parts.part;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class MainWorkBenchPart {
	@Inject
	public MainWorkBenchPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("Console Window Destroyed");
//		
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