package opkeystudio.opkeystudiocore.plugindev.controls;

import java.util.HashMap;
import java.util.Map;

public class ControlsRepository {
	private static ControlsRepository controlRepository = null;
	private Map<String, Object> globalControlRepository = new HashMap<>();

	public static ControlsRepository getInstance() {
		if (controlRepository == null) {
			controlRepository = new ControlsRepository();
		}
		return controlRepository;
	}

	public void addControlInControlRepository(String controlId, Object control) {
		globalControlRepository.put(controlId, control);
	}

	public Object getControlFromControlRepository(String controlId) {
		return globalControlRepository.get(controlId);
	}
}
