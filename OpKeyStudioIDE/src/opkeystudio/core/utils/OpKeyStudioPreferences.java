package opkeystudio.core.utils;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

public class OpKeyStudioPreferences {
	private static OpKeyStudioPreferences studiopreference;

	public static OpKeyStudioPreferences getPreferences() {
		if (studiopreference == null) {
			studiopreference = new OpKeyStudioPreferences();
		}
		return studiopreference;
	}

	public void addBasicSettings(String key, String value) {
		IEclipsePreferences preferences = InstanceScope.INSTANCE
				.getNode("opkeystudio.core.utils.OpKeyStudioPreferences");

		org.osgi.service.prefs.Preferences sub1 = preferences.node("basicSettings");
		sub1.put(key, value);
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getBasicSettings(String key) {
		IEclipsePreferences preferences = InstanceScope.INSTANCE
				.getNode("opkeystudio.core.utils.OpKeyStudioPreferences");

		org.osgi.service.prefs.Preferences sub1 = preferences.node("basicSettings");
		return sub1.get(key, null);
	}
	
}
