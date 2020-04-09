package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.io.IOException;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.transpiler.GlobalTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalVariablesTranspiler extends AbstractTranspiler {
	public GlobalVariablesTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getTranspiledArtifactsFolder());
	}

	public void transpile() {
		File file = createGlobalVariableFile();
		JavaClassSource classSource = new GlobalTranspiler().getJavaClassOfGlobalVariables();
		classSource.setPackage("allartifacts");
		new TranspilerUtilities().writeCodeToFile(file, classSource);
	}

	public File createGlobalVariableFile() {
		String folderPath = getTranspiledDataFolder() + File.separator + "allartifacts";
		if (!new File(folderPath).exists()) {
			new File(folderPath).mkdir();
		}
		String filePath = folderPath + File.separator + "OpKeyGlobalVariables.java";
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}
