package opkeystudio.opkeystudiocore.plugindev.controls;

public class ControlsRepository {
	private static ControlsRepository controlRepository = null;
	
	private Object prefernceTabHolder;

	public static ControlsRepository getInstance() {
		if (controlRepository == null) {
			controlRepository = new ControlsRepository();
		}
		return controlRepository;
	}

	public Object getPreferenceTabHolder() {
		return prefernceTabHolder;
	}

	public void setPreferenceTabHolder(Object tabHolder) {
		prefernceTabHolder = tabHolder;
	}
}
