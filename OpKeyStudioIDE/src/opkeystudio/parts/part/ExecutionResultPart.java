package opkeystudio.parts.part;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.ISaveHandler;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.commandhandler.CustomSaveHandler;
import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.ArtifactTree;
import opkeystudio.featurecore.ide.ui.ui.ExecutionResultView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class ExecutionResultPart {
	@PostConstruct
	public void postConstruct(Composite parent, MPart part, MWindow window) throws IOException {
		new ExecutionResultView(parent, 0);
		window.getContext().set(ISaveHandler.class, new CustomSaveHandler());
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
	}

	public Artifact getArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		return (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
	}

	@Persist
	public void save() {

	}
}
