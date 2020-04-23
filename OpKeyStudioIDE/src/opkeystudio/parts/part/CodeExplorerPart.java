package opkeystudio.parts.part;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.ui.CodeViewTreeUI;

public class CodeExplorerPart {
	@PostConstruct
	public void postConstruct(Composite parent) {
		new CodeViewTreeUI(parent, 0);

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
