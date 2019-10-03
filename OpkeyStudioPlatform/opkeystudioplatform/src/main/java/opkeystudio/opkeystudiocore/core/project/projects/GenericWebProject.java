package opkeystudio.opkeystudiocore.core.project.projects;

import java.io.IOException;

import opkeystudio.opkeystudiocore.core.project.artificates.RootFolder;
import opkeystudio.opkeystudiocore.core.project.generic.WorkSpace;

public class GenericWebProject extends Project {

	public GenericWebProject(String workspacePath, String projectName) {
		super(projectName, ProjectType.WEB);
		WorkSpace wspace = new WorkSpace("", workspacePath);
		setWorkSpace(wspace);
	}

	public void populateProject() throws IOException {
		Project project = new GenericWebProject(getWorkSpace().getWorkSpacePath(), getProjectName());
		RootFolder rootFolder1 = new RootFolder(project.getProjectPath(), "TestCase");
		RootFolder rootFolder2 = new RootFolder(project.getProjectPath(), "ObjectRepository");
		RootFolder rootFolder3 = new RootFolder(project.getProjectPath(), "TestSuites");
		RootFolder rootFolder4 = new RootFolder(project.getProjectPath(), "FunctionLibrary");
		project.addRootFolder(rootFolder1);
		project.addRootFolder(rootFolder2);
		project.addRootFolder(rootFolder3);
		project.addRootFolder(rootFolder4);
		project.createPoject();
	}
}
