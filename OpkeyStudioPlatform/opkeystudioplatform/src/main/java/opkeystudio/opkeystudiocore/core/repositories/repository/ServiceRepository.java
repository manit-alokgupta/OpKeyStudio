package opkeystudio.opkeystudiocore.core.repositories.repository;

import java.util.HashMap;
import java.util.Map;


public class ServiceRepository {
	private Map<String, Object> serviceRepositoriesVariables = new HashMap<>();
	private static ServiceRepository serviceRepository;

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

	public void setDefaultProjectPath(String path) {
		setServiceRepositoryVariable("opkeystudio.defaultProjectPath", path);
	}

	public String getDefaultProjectPath() {
		Object projectPath = getServiceRepositoryVariable("opkeystudio.defaultProjectPath");
		if (projectPath == null) {
			return null;
		}
		return (String) projectPath;
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
}
