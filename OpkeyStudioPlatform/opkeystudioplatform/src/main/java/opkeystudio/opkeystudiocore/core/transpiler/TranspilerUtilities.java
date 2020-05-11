package opkeystudio.opkeystudiocore.core.transpiler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class TranspilerUtilities {

	private List<String> appiumTypeFunctionLibraries = new ArrayList<String>();
	private static TranspilerUtilities instance;

	public static TranspilerUtilities getInstance() {
		if (instance == null) {
			instance = new TranspilerUtilities();
		}
		return instance;
	}

	public void writeCodeToFile(File file, JavaClassSource classSource) {
		Utilities.getInstance().writeToFile(file, classSource.toString());
	}

	public void addDefaultImports(JavaClassSource classSource) {
		for (String packageName : ArtifactTranspiler.getInstance().getAllPackagesNames()) {
			classSource.addImport(packageName + ".*");
		}
	}

	public void addPackageName(Artifact artifact, JavaClassSource classSource) {
		if (artifact.getPackageName() == null) {
			return;
		}
		if (artifact.getPackageName().trim().isEmpty()) {
			return;
		}
		classSource.setPackage(artifact.getPackageName());
	}

	private List<String> getGenericAppiumKeywords() {
		List<String> keywords = new ArrayList<String>();
		keywords.add("LaunchChromeOnMobile");
		keywords.add("Launch_iOSApplicationOnDevice");
		keywords.add("LaunchBrowserOnMobileDevice");
		keywords.add("LaunchChromeOnMobile");
		keywords.add("LaunchSafariOn_iOS");
		keywords.add("Mobile_LaunchAndroidApplication");
		return keywords;
	}

	private List<String> getGenericWebKeywords() {
		List<String> keywords = new ArrayList<String>();
		keywords.add("OpenBrowser");
		return keywords;
	}

	public void processFlowStepsForAppium(Artifact artifact, List<FlowStep> flowSteps) {
		boolean isMobileKeyword = TranspilerUtilities.getInstance().isFunctionLibraryisAppiumType(artifact.getId());
		List<String> appiumKeywords = getGenericAppiumKeywords();
		List<String> webKeywords = getGenericWebKeywords();
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.isShouldrun() == false) {
				continue;
			}
			if (flowStep.getKeyword() != null) {
				String keywordName = flowStep.getKeyword().getName().trim();
				if (appiumKeywords.contains(keywordName)) {
					isMobileKeyword = true;
				}

				if (webKeywords.contains(keywordName)) {
					isMobileKeyword = false;
				}
				flowStep.setAppiumType(isMobileKeyword);
			}
			if (flowStep.getFunctionLibraryComponent() != null) {
				List<FlowStep> flflowSteps = FunctionLibraryApi.getInstance()
						.getAllFlowSteps(flowStep.getFunctionLibraryComponent().getId());
				for (FlowStep flstep : flflowSteps) {
					if (flstep.getKeyword() != null) {
						String keywordName = flstep.getKeyword().getName().trim();
						if (appiumKeywords.contains(keywordName)) {
							isMobileKeyword = true;
						}

						if (webKeywords.contains(keywordName)) {
							isMobileKeyword = false;
						}
						flstep.setAppiumType(isMobileKeyword);
					}
				}

				if (isMobileKeyword) {
					TranspilerUtilities.getInstance()
							.addAppiumTypeFunctionLibraries(flowStep.getFunctionLibraryComponent().getId());
				} else {
					TranspilerUtilities.getInstance()
							.removeAppiumTypeFunctionLibraries(flowStep.getFunctionLibraryComponent().getId());
				}
			}
		}
	}

	public List<String> getAppiumTypeFunctionLibraries() {
		return appiumTypeFunctionLibraries;
	}

	public void setAppiumTypeFunctionLibraries(List<String> appiumTypeFunctionLibraries) {
		this.appiumTypeFunctionLibraries = appiumTypeFunctionLibraries;
	}

	public void addAppiumTypeFunctionLibraries(String functionLibrrayId) {
		this.getAppiumTypeFunctionLibraries().add(functionLibrrayId);
	}

	public void removeAppiumTypeFunctionLibraries(String functionLibrrayId) {
		if (this.getAppiumTypeFunctionLibraries().contains(functionLibrrayId)) {
			this.getAppiumTypeFunctionLibraries().remove(functionLibrrayId);
		}
	}

	public void clearAppiumTypeFunctionLibraries() {
		this.getAppiumTypeFunctionLibraries().clear();
	}

	public boolean isFunctionLibraryisAppiumType(String artifactId) {
		if (getAppiumTypeFunctionLibraries().contains(artifactId)) {
			return true;
		}
		return false;
	}
}
