package opkeystudio.opkeystudiocore.core.project.generic;

import java.util.UUID;

public class WorkSpace {
	private String workSpaceId;
	private String workSpaceName;
	private String workSpacePath;

	public WorkSpace(String workspaceName, String workspacePath) {
		setWorkSpaceId(UUID.randomUUID().toString());
	}

	public String getWorkSpaceId() {
		return workSpaceId;
	}

	private void setWorkSpaceId(String workSpaceId) {
		this.workSpaceId = workSpaceId;
	}

	public String getWorkSpaceName() {
		return workSpaceName;
	}

	public void setWorkSpaceName(String workSpaceName) {
		this.workSpaceName = workSpaceName;
	}

	public String getWorkSpacePath() {
		return workSpacePath;
	}

	public void setWorkSpacePath(String workSpacePath) {
		this.workSpacePath = workSpacePath;
	}
}
