package opkeystudio.opkeystudiocore.core.project;

enum PROJECTTYPE {
	WEB, MOBILE, DESKTOP
};

public abstract class ProjectImplementation {
	private String workspaceLocation;
	private String projectName;
	private int projectType;

	public ProjectImplementation(String projectName, int projectType) {
		this.setProjectName(projectName);
		this.setProjectType(projectType);
	}

	public String getWorkspaceLocation() {
		return workspaceLocation;
	}

	public void setWorkspaceLocation(String workspaceLocation) {
		this.workspaceLocation = workspaceLocation;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}
}
