package opkeystudio.opkeystudiocore.core.transpiler;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;

public class ArtifactTranspiler {
	public void transpileAllArtifacts() {
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifacts();
		for (Artifact artifact : artifacts) {
			if (artifact.getParentArtifact() != null) {
				System.out.println("Artifact Parent Name " + artifact.getParentArtifact().getName());
			}
		}
	}
}
