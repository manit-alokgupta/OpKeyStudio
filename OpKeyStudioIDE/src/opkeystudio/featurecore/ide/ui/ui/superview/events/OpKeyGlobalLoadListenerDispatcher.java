package opkeystudio.featurecore.ide.ui.ui.superview.events;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;

public class OpKeyGlobalLoadListenerDispatcher {
	private static OpKeyGlobalLoadListenerDispatcher instance = null;
	private List<Composite> superComposites = new ArrayList<>();

	public static OpKeyGlobalLoadListenerDispatcher getInstance() {
		if (instance == null) {
			instance = new OpKeyGlobalLoadListenerDispatcher();
		}
		return instance;
	}

	public void addSuperComposite(Composite scomposite) {
		this.superComposites.add(scomposite);
	}

	public void clearAllSuperComposites() {
		this.superComposites.clear();
	}

	public void fireAllSuperCompositeGlobalListener() {
		
		for (Composite scomp : this.superComposites) {
			if (scomp.isDisposed()) {
				continue;
			}
			if (scomp instanceof CustomTable) {
				CustomTable table = (CustomTable) scomp;
				table.fireGlobalListener();
			}

			if (scomp instanceof CustomTree) {
				CustomTree table = (CustomTree) scomp;
				table.fireGlobalListener();
			}
		}
	}
}
