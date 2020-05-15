package opkeystudio.opkeystudiocore.core.apis.dbapi.usedby;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;

public class ArtifactUsedBy {
	public boolean checkIsUsedBy(Artifact artifact) {
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			return new FLUsedBy().isFLIsUsed(artifact);
		}
		return false;
	}
}
