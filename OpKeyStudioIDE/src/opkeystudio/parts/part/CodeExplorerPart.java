package opkeystudio.parts.part;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.ISaveHandler;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.commandhandler.CustomSaveHandler;
import opkeystudio.featurecore.ide.ui.ui.CodeViewTreeUI;

public class CodeExplorerPart {
	@PostConstruct
	public void postConstruct(Composite parent, MPart part, MWindow window) {
		new CodeViewTreeUI(parent, 0);
		window.getContext().set(ISaveHandler.class, new CustomSaveHandler());

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
