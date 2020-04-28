package opkeystudio.featurecore.ide.ui.ui.superview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalListener;

public class SuperComposite extends Composite {
	private List<OpKeyGlobalListener> listeners = new ArrayList<>();

	public SuperComposite(Composite parent, int style) {
		super(parent, style);
	}

	public void addOpKeyGlobalEventListener(OpKeyGlobalListener listener) {
		listeners.add(listener);
	}

	public void removeOpKeyGlobalListener(OpKeyGlobalListener listener) {
		listeners.remove(listener);
	}

	public void fireGlobalListener() {
		for (OpKeyGlobalListener listener : this.listeners) {
			listener.handleGlobalEvent();
		}
	}
}
