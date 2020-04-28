package opkeystudio.parts.part;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.PersistState;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.reportview.ReportViewer;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalListenerDispatcher;

public class ReportViewerPart {
	@PostConstruct
	public void postConstruct(Composite parent) {
		new ReportViewer(parent, 0);
	}

	@PreDestroy
	public void preDestroy() {

	}

	@Focus
	public void onFocus() {
		OpKeyGlobalListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
	}

	@Persist
	public void save() {

	}

	@PersistState
	public void persistState() {
		OpKeyGlobalListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
	}
}
