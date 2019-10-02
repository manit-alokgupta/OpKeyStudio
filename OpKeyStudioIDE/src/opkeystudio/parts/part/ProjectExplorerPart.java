package opkeystudio.parts.part;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.parts.compositeui.TreeViewCompositeUI;

public class ProjectExplorerPart {
	@Inject
	MPart projectExplorerPart;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		new TreeViewCompositeUI(parent, 0);
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
