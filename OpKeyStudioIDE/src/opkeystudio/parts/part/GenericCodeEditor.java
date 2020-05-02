package opkeystudio.parts.part;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.PersistState;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.ui.ArtifactCodeView;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyArtifactPersistListenerDispatcher;

public class GenericCodeEditor {

	@PostConstruct
	public void postConstruct(Composite parent) throws IOException {
		new ArtifactCodeView(parent, 0, true);
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

	@PersistState
	public void persistState() {
		OpKeyArtifactPersistListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
	}
}
