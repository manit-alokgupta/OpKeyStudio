package opkeystudio.featurecore.ide.ui.ui.superview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.ui.superview.events.ArtifactPersistListener;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyArtifactPersistListenerDispatcher;

public class SuperComposite extends Composite {
	private List<ArtifactPersistListener> listeners = new ArrayList<>();

	public SuperComposite(Composite parent, int style) {
		super(parent, style);
		OpKeyArtifactPersistListenerDispatcher.getInstance().addSuperComposite(this);
	}

	public void addOpKeyGlobalEventListener(ArtifactPersistListener listener) {
		listeners.add(listener);
	}

	public void removeOpKeyGlobalListener(ArtifactPersistListener listener) {
		listeners.remove(listener);
	}

	public void fireGlobalListener() {
		for (ArtifactPersistListener listener : this.listeners) {
			listener.handleGlobalEvent();
		}
	}
}
