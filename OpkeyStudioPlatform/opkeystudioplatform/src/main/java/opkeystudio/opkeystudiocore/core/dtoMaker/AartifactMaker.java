package opkeystudio.opkeystudiocore.core.dtoMaker;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class AartifactMaker {
	public Artifact getArtifactObject(String parentId, String artifactName, MODULETYPE artifactType) {
		Artifact artifact = new Artifact();
		artifact.setFile_type_enum(artifactType);
		artifact.setP_id(ServiceRepository.getInstance().getDefaultProject().getP_id());
		artifact.setName(artifactName);
		artifact.setId(Utilities.getInstance().getUniqueUUID(""));
		artifact.setParentid(parentId);
		return artifact;
	}
}
