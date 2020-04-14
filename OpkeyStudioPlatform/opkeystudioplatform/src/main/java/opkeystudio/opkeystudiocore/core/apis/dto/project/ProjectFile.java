package opkeystudio.opkeystudiocore.core.apis.dto.project;

public class ProjectFile {
	private boolean visible = true;
	private String projectName;

	public ProjectFile(String projectName) {
		this.setProjectName(projectName);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
