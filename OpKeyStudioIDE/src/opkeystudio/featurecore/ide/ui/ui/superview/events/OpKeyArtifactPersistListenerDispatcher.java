package opkeystudio.featurecore.ide.ui.ui.superview.events;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;

public class OpKeyArtifactPersistListenerDispatcher {
	private static OpKeyArtifactPersistListenerDispatcher instance = null;
	private List<SuperComposite> superComposites = new ArrayList<>();

	public static OpKeyArtifactPersistListenerDispatcher getInstance() {
		if (instance == null) {
			instance = new OpKeyArtifactPersistListenerDispatcher();
		}
		return instance;
	}

	public void addSuperComposite(SuperComposite scomposite) {
		this.superComposites.add(scomposite);
	}

	public void clearAllSuperComposites() {
		this.superComposites.clear();
	}

	public void fireAllSuperCompositeGlobalListener() {
		for (SuperComposite scomp : this.superComposites) {
			if (scomp.isDisposed()) {
				continue;
			}
			scomp.fireGlobalListener();
		}
	}
}
