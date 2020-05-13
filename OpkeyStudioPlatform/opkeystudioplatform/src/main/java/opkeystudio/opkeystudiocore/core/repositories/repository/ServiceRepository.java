package opkeystudio.opkeystudiocore.core.repositories.repository;

import java.util.HashMap;
import java.util.Map;

import opkeystudio.opkeystudiocore.core.apis.dto.project.Project;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ServiceRepository {

	private String projectName;

	private Map<String, Object> serviceRepositoriesVariables = new HashMap<>();
	private static ServiceRepository serviceRepository;
	private boolean dbContainsSecuredData = true;

	public static ServiceRepository getInstance() {
		if (serviceRepository == null) {
			serviceRepository = new ServiceRepository();
		}
		return serviceRepository;
	}

	private void setServiceRepositoryVariable(String key, Object object) {
		this.serviceRepositoriesVariables.put(key, object);
	}

	private Object getServiceRepositoryVariable(String key) {
		return this.serviceRepositoriesVariables.get(key);
	}

	public void setProjectTreeObject(Object treeobject) {
		setServiceRepositoryVariable("opkeystudio.projecttree", treeobject);
	}

	public Object getProjectTreeObject() {
		return getServiceRepositoryVariable("opkeystudio.projecttree");
	}

	public void setTestCaseSelectedSteps(int index) {
		setServiceRepositoryVariable("opkeystudio.mainworkbench.selectedtcstep", index);
	}

	public int getTestCaseSelectedSteps() {
		return (int) getServiceRepositoryVariable("opkeystudio.mainworkbench.selectedtcstep");
	}

	public void setOpKeyHostUrl(String hostUrl) {
		setServiceRepositoryVariable("opkeystudio.opkeyapihost", hostUrl);
	}

	public String getOpKeyHostUrl() {
		return (String) getServiceRepositoryVariable("opkeystudio.opkeyapihost");
	}

	public void setOpKeyHostAuthToken(String token) {
		setServiceRepositoryVariable("opkeystudio.opkeyapitoken", token);
	}

	public String getOpKeyHostAuthToken() {
		return (String) getServiceRepositoryVariable("opkeystudio.opkeyapitoken");
	}

	public void setOpKeyHostSessionId(String sessionId) {
		setServiceRepositoryVariable("opkeystudio.opkeyapisessionid", sessionId);
	}

	public String getOpKeyHostSessionId() {
		return (String) getServiceRepositoryVariable("opkeystudio.opkeyapisessionid");
	}

	public void setOpKeyImportSessionId(String sessionId) {
		setServiceRepositoryVariable("opkeystudio.opkeyimportsessionid", sessionId);
	}

	public String getOpKeyImportSessionId() {
		return (String) getServiceRepositoryVariable("opkeystudio.opkeyimportsessionid");
	}

	public void setExortedDBFilePath(String path) {
		setServiceRepositoryVariable("opkeystudio.exporteddbfilepath", path);
	}

	public String getExportedDBFilePath() {
		return (String) getServiceRepositoryVariable("opkeystudio.exporteddbfilepath");
	}

	public void setDefaultProject(Project project) {
		setServiceRepositoryVariable("opkeystudio.defaultProject", project);
	}

	public Project getDefaultProject() {
		return (Project) getServiceRepositoryVariable("opkeystudio.defaultProject");
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		projectName = projectName.replaceAll(" ", "OPKEYNEONSPACE112463743");
		projectName = Utilities.getInstance().removeSpecialCharacters(projectName);
		projectName = projectName.replaceAll("OPKEYNEONSPACE112463743", " ");
		this.projectName = projectName;
	}

	public String formatProjectName(String projectName) {
		projectName = projectName.replaceAll(" ", "OPKEYNEONSPACE112463743");
		projectName = Utilities.getInstance().removeSpecialCharacters(projectName);
		projectName = projectName.replaceAll("OPKEYNEONSPACE112463743", " ");
		return projectName;
	}

	public boolean isDbContainsSecuredData() {
		return dbContainsSecuredData;
	}

	public void setDbContainsSecuredData(boolean dbContainsSecuredData) {
		this.dbContainsSecuredData = dbContainsSecuredData;
	}
}
