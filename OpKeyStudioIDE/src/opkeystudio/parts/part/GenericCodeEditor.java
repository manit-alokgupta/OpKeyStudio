package opkeystudio.parts.part;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.PersistState;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.ISaveHandler;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.commandhandler.CustomSaveHandler;
import opkeystudio.featurecore.ide.ui.ui.ArtifactCodeView;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyArtifactPersistListenerDispatcher;

public class GenericCodeEditor {

	ArtifactCodeView codeView = null;

	@PostConstruct
	public void postConstruct(Composite parent, MPart part, MWindow window) throws IOException {
		codeView = new ArtifactCodeView(parent, 0, true);
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
		codeView.handleRefreshOnSave();
	}

	@PersistState
	public void persistState() {
		OpKeyArtifactPersistListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
	}
}
