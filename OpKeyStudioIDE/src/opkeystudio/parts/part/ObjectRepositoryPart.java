package opkeystudio.parts.part;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.ui.ObjectRepositoryView;

public class ObjectRepositoryPart {
	@PostConstruct
	public void postConstruct(Composite parent) throws IOException {
		new ObjectRepositoryView(parent, 0);
	}

	@PreDestroy
	public void preDestroy() {

	}

	@Focus
	public void onFocus() {
		
	}

	@Persist
	public void save() {
		
	}
}
