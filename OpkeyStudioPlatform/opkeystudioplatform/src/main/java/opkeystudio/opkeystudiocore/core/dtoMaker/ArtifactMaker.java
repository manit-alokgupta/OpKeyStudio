package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dto.ArtifactStates;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactMaker {
	public Artifact getArtifactObject(Artifact parent, String artifactName, MODULETYPE artifactType) {
		String createdBy = Utilities.getInstance().getUniqueUUID("");
		String modifiedBy = Utilities.getInstance().getUniqueUUID("");
		String parentId = null;
		if (parent != null) {
			createdBy = parent.getCreated_by();
			modifiedBy = parent.getModified_by();
			parentId = parent.getId();
		}
		Artifact artifact = new Artifact();
		artifact.setFile_type_enum(artifactType);
		artifact.setP_id(ServiceRepository.getInstance().getDefaultProject().getP_id());
		artifact.setCreated_on(Utilities.getInstance().getCurrentDateTime());
		artifact.setCreated_on_tz(Utilities.getInstance().getCurrentTimeZone());
		artifact.setModified_on(Utilities.getInstance().getUpdateCurrentDateTime());
		artifact.setModified_on_tz(Utilities.getInstance().getCurrentTimeZone());
		artifact.setCreated_by(createdBy);
		artifact.setModified_by(modifiedBy);
		artifact.setName(artifactName);
		artifact.setId(Utilities.getInstance().getUniqueUUID(""));
		artifact.setParentid(parentId);

		List<ArtifactStates> states = new ArtifactApiUtilities().getArtifactsStates("Draft");
		artifact.setState_id(states.get(0).getState_id());
		return artifact;
	}
}
