package opkeystudio.featurecore.project;

enum PROJECTTYPE {
	WEB, MOBILE, DESKTOP
};

public abstract class ProjectImplementation {
	private String workspaceLocation;

	public String getWorkspaceLocation() {
		return workspaceLocation;
	}

	public void setWorkspaceLocation(String workspaceLocation) {
		this.workspaceLocation = workspaceLocation;
	}
}
