package opkeystudio.featurecore.ide.ui.ui.superview.events;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;

public class OpKeyGlobalListenerDispatcher {
	private static OpKeyGlobalListenerDispatcher instance = null;
	private List<SuperComposite> superComposites = new ArrayList<>();

	public static OpKeyGlobalListenerDispatcher getInstance() {
		if (instance == null) {
			instance = new OpKeyGlobalListenerDispatcher();
		}
		return instance;
	}

	public void addSuperComposite(SuperComposite scomposite) {
		this.superComposites.add(scomposite);
	}
}
