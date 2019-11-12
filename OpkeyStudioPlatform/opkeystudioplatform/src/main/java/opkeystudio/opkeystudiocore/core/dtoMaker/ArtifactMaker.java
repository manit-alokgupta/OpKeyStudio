package opkeystudio.opkeystudiocore.core.dtoMaker;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactMaker {
	public Artifact getArtifactObject(String parentId, String artifactName, MODULETYPE artifactType) {
		Artifact artifact = new Artifact();
		artifact.setFile_type_enum(artifactType);
		// artifact.setP_id(ServiceRepository.getInstance().getDefaultProject().getP_id());
		artifact.setP_id(Utilities.getInstance().getUniqueUUID(""));
		artifact.setCreated_on(Utilities.getInstance().getCurrentDateTime());
		artifact.setCreated_on_tz("ABC");
		artifact.setCreated_by("dededed");
		artifact.setName(artifactName);
		artifact.setId(Utilities.getInstance().getUniqueUUID(""));
		artifact.setParentid(parentId);
		return artifact;
	}
}
