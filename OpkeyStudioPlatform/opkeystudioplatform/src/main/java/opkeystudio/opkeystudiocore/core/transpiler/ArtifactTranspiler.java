package opkeystudio.opkeystudiocore.core.transpiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.DRTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.FLTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.GlobalVariablesTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.ORTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.SuiteTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.TCTranspiler;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactTranspiler {

	private Set<String> allPackagesNames = new HashSet<String>();
	private static ArtifactTranspiler artifactTranspiler;

	public static ArtifactTranspiler getInstance() {
		if (artifactTranspiler == null) {
			artifactTranspiler = new ArtifactTranspiler();
		}
		return artifactTranspiler;
	}

	public void setPackageProperties() {
		ArtifactTranspiler.getInstance().getAllPackagesNames().clear();
		List<Artifact> allArtifacts = GlobalLoader.getInstance().getAllArtifacts();
		for (Artifact artifact : allArtifacts) {
			if (artifact.getFile_type_enum() == MODULETYPE.Folder) {
				continue;
			}
			int count = 0;
			String packagePath = "allartifacts";
			String packageName = "allartifacts";
			Artifact tempArtfact = artifact;
			List<String> variableNames = new ArrayList<String>();
			while (tempArtfact != null) {
				if (count == 0) {
					count++;
					tempArtfact = tempArtfact.getParentArtifact();
					continue;
				}

				variableNames.add(tempArtfact.getVariableName());
				tempArtfact = tempArtfact.getParentArtifact();
			}

			Collections.reverse(variableNames);
			for (String varName : variableNames) {
				if (!packagePath.isEmpty()) {
					packagePath += File.separator;
				}
				if (!packageName.isEmpty()) {
					packageName += ".";
				}
				if (checkPackageNameIsValid(varName) == false) {
					varName = "opkeyPackage" + varName;
				}
				packagePath += varName;
				packageName += varName;
			}
			artifact.setPackagePath(packagePath.toLowerCase());
			artifact.setPackageName(packageName.toLowerCase());
			ArtifactTranspiler.getInstance().addPackageName(artifact.getPackageName());
		}
		ArtifactTranspiler.getInstance().addPackageName("allartifacts");
		ArtifactTranspiler.getInstance().addPackageName("com.opkey.OpKeyGenericPlugin");
		ArtifactTranspiler.getInstance().addPackageName("com.opkey.SystemPlugin");
		ArtifactTranspiler.getInstance().addPackageName("com.ssts.reporting");
	}

	private boolean checkPackageNameIsValid(String packagename) {
		try {
			Integer.parseInt(packagename);
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Integer.parseInt(String.valueOf(packagename.charAt(0)));
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public void transpileAllArtifacts() {
		resetTranspiledArtifactsFolder();
		new GlobalVariablesTranspiler().transpile();
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifacts();
		for (Artifact artifact : artifacts) {
			new ORTranspiler().transpile(artifact);
			new DRTranspiler().transpile(artifact);
			new TCTranspiler().transpile(artifact);
			new FLTranspiler().transpile(artifact);
			new SuiteTranspiler().transpile(artifact);
		}
	}

	private void resetTranspiledArtifactsFolder() {
		try {
			FileUtils.deleteDirectory(
					new File(Utilities.getInstance().getTranspiledArtifactsFolder() + File.separator + "allartifacts"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Set<String> getAllPackagesNames() {
		return allPackagesNames;
	}

	public void addPackageName(String packageName) {
		this.allPackagesNames.add(packageName);
	}

	public void setAllPackagesNames(Set<String> allPackagesNames) {
		this.allPackagesNames = allPackagesNames;
	}
}
