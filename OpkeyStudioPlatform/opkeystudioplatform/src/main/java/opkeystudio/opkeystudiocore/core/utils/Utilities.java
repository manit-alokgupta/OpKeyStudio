package opkeystudio.opkeystudiocore.core.utils;

import java.io.File;

public class Utilities {
	private static Utilities util;

	public static Utilities getInstance() {
		if (util == null) {
			util = new Utilities();
		}
		return util;
	}

	public String getDefaultWorkSpacePath() {
		String path = System.getProperty("user.dir") + File.separator + "OpKeyStudio" + File.separator + "workspace";
		new File(path).mkdir();
		return path;
	}
}
