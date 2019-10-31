package opkeystudio.parts.part;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.ui.ObjectRepositoryView;
import opkeystudio.featurecore.ide.ui.ui.ObjectRepositoryView2;

public class ObjectRepositoryPart {
	@Inject
	MPart projectExplorerPart;

	@PostConstruct
	public void postConstruct(Composite parent) throws IOException {
		new ObjectRepositoryView2(parent, 0);
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
