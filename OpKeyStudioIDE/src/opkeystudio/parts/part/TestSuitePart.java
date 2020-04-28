package opkeystudio.parts.part;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.PersistState;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.ArtifactTree;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalListenerDispatcher;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class TestSuitePart {

	@PostConstruct
	public void postConstruct(Composite parent) throws IOException {
		new TestSuiteView(parent, 0);
	}

	@PreDestroy
	public void preDestroy() {
		
	}

	@Focus
	public void onFocus() {
		ArtifactTree tree = (ArtifactTree) SystemRepository.getInstance().getArtifactTreeControl();
		Artifact artifact = getArtifact();
		if (artifact != null) {
			tree.highlightArtifact(artifact.getId());
		}
		OpKeyGlobalListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
	}

	public Artifact getArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		return (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
	}

	@Persist
	public void save() {

	}

	@PersistState
	public void persistState() {
		OpKeyGlobalListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
	}
}
