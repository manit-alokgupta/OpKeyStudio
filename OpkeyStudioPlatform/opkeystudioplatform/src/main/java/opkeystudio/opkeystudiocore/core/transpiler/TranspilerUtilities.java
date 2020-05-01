package opkeystudio.opkeystudiocore.core.transpiler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class TranspilerUtilities {
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

	public void processFlowStepsForAppium(List<FlowStep> flowSteps) {
		boolean isMobileKeyword = false;
		List<String> appiumKeywords = getGenericAppiumKeywords();
		List<String> webKeywords = getGenericWebKeywords();
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.getKeyword() != null) {
				String keywordName = flowStep.getKeyword().getName().trim();
				if (appiumKeywords.contains(keywordName)) {
					isMobileKeyword = true;
				}

				if (webKeywords.contains(keywordName)) {
					isMobileKeyword = false;
				}
			}
			flowStep.setAppiumType(isMobileKeyword);
		}
	}
}
