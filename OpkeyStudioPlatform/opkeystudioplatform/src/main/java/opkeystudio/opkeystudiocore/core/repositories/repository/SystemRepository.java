package opkeystudio.opkeystudiocore.core.repositories.repository;

import java.util.HashMap;
import java.util.Map;

public class SystemRepository {
	private Map<String, Object> systemRepositoryVariable = new HashMap<>();
	private static SystemRepository systemRepository;
	private boolean transpilerServiceRunning = false;

	public static SystemRepository getInstance() {
		if (systemRepository == null) {
			systemRepository = new SystemRepository();
		}
		return systemRepository;
	}

	public void setArtifactTreeControl(Object object) {
		this.systemRepositoryVariable.put("opkeystudio.artifacttreecontrol", object);
	}

	public Object getArtifactTreeControl() {
		return this.systemRepositoryVariable.get("opkeystudio.artifacttreecontrol");
	}

	public void setCodeViewTreeControl(Object object) {
		this.systemRepositoryVariable.put("opkeystudio.CodeViewtreecontrol", object);
	}

	public Object getCodeViewTreeControl() {
		return this.systemRepositoryVariable.get("opkeystudio.CodeViewtreecontrol");
	}

	public boolean isTranspilerServiceRunning() {
		return transpilerServiceRunning;
	}

	public void setTranspilerServiceRunning(boolean transpilerServiceRunning) {
		this.transpilerServiceRunning = transpilerServiceRunning;
	}

}
