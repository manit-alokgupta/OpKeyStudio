package opkeystudio.opkeystudiocore.core.repositories.repository;

import java.util.HashMap;
import java.util.Map;

import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;

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

	public void setDefaultArtificate(Artificate artificate) {
		setServiceRepositoryVariable("opkeystudio.defaultArtificate", artificate);
	}

	public Artificate getDefaultArtificate() {
		Object artificateObject = getServiceRepositoryVariable("opkeystudio.defaultArtificate");
		if (artificateObject == null) {
			return null;
		}
		return (Artificate) artificateObject;
	}
}
