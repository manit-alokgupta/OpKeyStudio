package opkeystudio.opkeystudiocore.core.opkeycore;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO.MODULETYPE;

public class Artifact {
	
	private ArtifactDTO _artifactDTO;
	
	//Load Artifact
	public Artifact(ArtifactDTO artifactDTO,MODULETYPE moduleType) {
		_artifactDTO = artifactDTO;
		_artifactDTO.setFile_type_enum(moduleType);
	}

	public ArtifactDTO myDTO() {
		return _artifactDTO;
	}
	
	public String CreatedBy() {
		return _artifactDTO.getCreated_by();
	}

	public void setCreated_by(String created_by) {
		_artifactDTO.setCreated_by(created_by);
	}

	public MODULETYPE getModuleType() {
		return _artifactDTO.getFile_type_enum();
	}

	public String getName() {
		return _artifactDTO.getName();
	}

	public void setName(String name) {
		_artifactDTO.setName(name);
	}

	public String getModified_by() {
		return _artifactDTO.getModified_by();
	}

	public void setModified_by(String modified_by) {
		_artifactDTO.setModified_by(modified_by);
	}

	public String getId() {
		return _artifactDTO.getId();
	}

	public void setId(String id) {
		_artifactDTO.setId(id);
	}

	public int getPosition() {
		return _artifactDTO.getPosition();
	}

	public void setPosition(int position) {
		_artifactDTO.setPosition(position);
	}

	public String getProject_id() {
		return _artifactDTO.getP_id();
	}

	public void setProject_id(String p_id) {
		_artifactDTO.setP_id(p_id);
	}

	public String getParentid() {
		return _artifactDTO.getParentid();
	}

	public void setParentid(String parentid) {
		_artifactDTO.setParentid(parentid);
	}

	public boolean getArtifactisDeleted() {
		return _artifactDTO.isIsdeleted();
	}
}
