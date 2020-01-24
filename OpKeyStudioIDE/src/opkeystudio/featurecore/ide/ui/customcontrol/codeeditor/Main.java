package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.io.File;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<File> pluginBaseLibs = EditorTools.getPluginsLibraries("Web");
		for (File file : pluginBaseLibs) {
			System.out.println(file.getAbsolutePath());
		}
	}

}
