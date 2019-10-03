package opkeystudio.opkeystudiocore.core.project.projects;

import java.util.UUID;

import opkeystudio.opkeystudiocore.core.project.generic.WorkSpace;

public abstract class Project {
	private String projectId;
	private String projectName;
	private WorkSpace workSpace;

	public Project(String projectName) {
		setProjectId(UUID.randomUUID().toString());
		setProjectName(projectName);
	}

	public String getProjectId() {
		return projectId;
	}

	private void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	private void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public WorkSpace getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(WorkSpace workSpace) {
		this.workSpace = workSpace;
	}
}
