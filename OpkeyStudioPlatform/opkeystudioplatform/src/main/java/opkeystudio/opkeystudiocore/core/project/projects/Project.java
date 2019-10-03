package opkeystudio.opkeystudiocore.core.project.projects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.ValidationException;

import opkeystudio.opkeystudiocore.core.exceptions.ValidationExceptions;
import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;
import opkeystudio.opkeystudiocore.core.project.artificates.RootFolder;
import opkeystudio.opkeystudiocore.core.project.generic.WorkSpace;

public abstract class Project {
	private String projectId;
	private String projectName;
	private WorkSpace workSpace;
	private ProjectType projectType;
	private String projectPath;
	private List<RootFolder> rootFolders = new ArrayList<>();

	public enum ProjectType {
		WEB, SALESFORCE, ORACLEFUSION, WORKDAY
	};

	public Project(String projectName, ProjectType projectType) throws ValidationExceptions {
		if (projectName.trim().isEmpty()) {
			throw new ValidationExceptions("ProjectName Should Not Be Blank");
		}
		setProjectId(UUID.randomUUID().toString());
		setProjectName(projectName);
		setProjectType(projectType);
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

	public ProjectType getProjectType() {
		return this.projectType;
	}

	private void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public WorkSpace getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(WorkSpace workSpace) {
		this.workSpace = workSpace;
		String projectPath = workSpace.getWorkSpacePath() + File.separator + getProjectName();
		setProjectPath(projectPath);
	}

	public List<RootFolder> getRootFolder() {
		return rootFolders;
	}

	public void addRootFolder(RootFolder rootFolder) {
		this.rootFolders.add(rootFolder);
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public void createPoject() throws IOException {
		WorkSpace wspace = getWorkSpace();
		String projectPath = wspace.getWorkSpacePath() + File.separator + getProjectName();
		File projectFolder = new File(projectPath);
		projectFolder.mkdir();
		setProjectPath(projectPath);
		List<RootFolder> rootFolders = getRootFolder();
		for (RootFolder rfolder : rootFolders) {
			rfolder.createArtificate();
			populateArtificates(rfolder);
		}
	}

	private void populateArtificates(Artificate artificate) throws IOException {
		artificate.createArtificate();
		List<Artificate> childArtificates = artificate.getArtificates();
		for (Artificate childArtificate : childArtificates) {
			populateArtificates(childArtificate);
		}
	}

}
