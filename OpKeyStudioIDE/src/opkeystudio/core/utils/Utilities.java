package opkeystudio.core.utils;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.InjectionException;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSession;

public class Utilities {
	private static Utilities utils = null;
	private Shell defaultShell;
	private String pluginName;

	public static Utilities getInstance() {
		if (utils == null) {
			utils = new Utilities();
		}
		return utils;
	}

	public void setShellCursor(int i) {
		//defaultShell.setCursor(new Cursor(Display.getCurrent(), i));
	}

	public EPartService getEpartService() {
		Bundle bundle = FrameworkUtil.getBundle(EPartService.class);
		BundleContext bundleContext = bundle.getBundleContext();
		IEclipseContext eclipseCtx = EclipseContextFactory.getServiceContext(bundleContext);
		EPartService partService = (EPartService) eclipseCtx.get(EPartService.class.getName());
		return partService;
	}

	public MPart getActivePart() {
		return getEpartService().getActivePart();
	}

	public Collection<MPart> getAllParts() {
		return getEpartService().getParts();
	}

	public MPart getArtifactMPart(Artifact artifact) {
		Collection<MPart> allParts = getAllParts();
		System.out.println("All Parts " + allParts.size());
		for (MPart mpart : allParts) {
			System.out.println("Mpart Searching");
			Artifact artifact_0 = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
			if (artifact_0 == null) {
				continue;
			}
			if (artifact_0.getId().equals(artifact.getId())) {
				System.out.println("ID FOUND " + artifact_0.getId());
				return mpart;
			}
		}
		return null;
	}

	public MPart getGenericEditorMPart(File artifact) {
		Collection<MPart> allParts = getAllParts();
		System.out.println("All Parts " + allParts.size());
		for (MPart mpart : allParts) {
			System.out.println("Mpart Searching");
			File artifact_0 = (File) mpart.getTransientData().get("opkeystudio.codeFile");
			if (artifact_0 == null) {
				continue;
			}
			if (artifact_0.getAbsolutePath().equals(artifact.getAbsolutePath())) {
				return mpart;
			}
		}
		return null;
	}

	public void closeAllMparts() {
		try {
			Collection<MPart> allParts = getAllParts();
			System.out.println("All Parts " + allParts.size());
			for (MPart mpart : allParts) {
				System.out.println("Closing Already Opened Mparts");
				Artifact artifact_0 = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
				if (artifact_0 != null) {
					EPartService partService = Utilities.getInstance().getEpartService();
					partService.hidePart(mpart, true);
				}
				ExecutionSession executionSession = (ExecutionSession) mpart.getTransientData()
						.get("opkeystudio.executionSessionData");
				if (executionSession != null) {
					EPartService partService = Utilities.getInstance().getEpartService();
					partService.hidePart(mpart, true);
				}
			}
		} catch (InjectionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showErrorDialog(Shell shell, String title, String message) {
		MessageDialog.openError(shell, title, message);
	}

	public String getUniqueUUID(String prefix) {
		return prefix + "" + UUID.randomUUID().toString();
	}

	public int getIndexOfItem(String[] arrays, String item) {
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].toLowerCase().equals(item.toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	public void openArtifacts(Artifact artifact) {
		try {
			Utilities.getInstance().defaultShell.setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT));

			System.out.println("Opening Artifact " + artifact.getId());
			MPart mpart = Utilities.getInstance().getArtifactMPart(artifact);
			if (mpart != null) {
				System.out.println("MPART Found");
				EPartService partService = Utilities.getInstance().getEpartService();
				partService.showPart(mpart, PartState.ACTIVATE);
				return;
			}
			if (artifact.getFile_type_enum() == MODULETYPE.ObjectRepository) {
				EPartService partService = Utilities.getInstance().getEpartService();
				MPart part = partService.createPart("opkeystudio.partdescriptor.objectRepository");
				part.setLabel(artifact.getName());
				part.setTooltip(artifact.getName());
				part.getTransientData().put("opkeystudio.artifactData", artifact);
				partService.showPart(part, PartState.ACTIVATE);
			}
			if (artifact.getFile_type_enum() == MODULETYPE.Flow) {
				EPartService partService = Utilities.getInstance().getEpartService();
				MPart part = partService.createPart("opkeystudio.partdescriptor.testCaseViewer");
				part.setLabel(artifact.getName());
				part.setTooltip(artifact.getName());
				part.getTransientData().put("opkeystudio.artifactData", artifact);
				partService.showPart(part, PartState.ACTIVATE);
			}
			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				EPartService partService = Utilities.getInstance().getEpartService();
				MPart part = partService.createPart("opkeystudio.partdescriptor.functionLibraryViewer");
				part.setLabel(artifact.getName());
				part.setTooltip(artifact.getName());
				part.getTransientData().put("opkeystudio.artifactData", artifact);
				partService.showPart(part, PartState.ACTIVATE);
			}
			if (artifact.getFile_type_enum() == MODULETYPE.Suite) {
				EPartService partService = Utilities.getInstance().getEpartService();
				MPart part = partService.createPart("opkeystudio.partdescriptor.testSuiteViewer");
				part.setLabel(artifact.getName());
				part.setTooltip(artifact.getName());
				part.getTransientData().put("opkeystudio.artifactData", artifact);
				partService.showPart(part, PartState.ACTIVATE);
			}

			if (artifact.getFile_type_enum() == MODULETYPE.DataRepository) {
				EPartService partService = Utilities.getInstance().getEpartService();
				MPart part = partService.createPart("opkeystudio.partdescriptor.dataRepoViewer");
				part.setLabel(artifact.getName());
				part.setTooltip(artifact.getName());
				part.getTransientData().put("opkeystudio.artifactData", artifact);
				partService.showPart(part, PartState.ACTIVATE);
			}
			if (artifact.getFile_type_enum() == MODULETYPE.CodedFunction) {
				EPartService partService = Utilities.getInstance().getEpartService();
				MPart part = partService.createPart("opkeystudio.partdescriptor.codeFunctionViewer");
				part.setLabel(artifact.getName());
				part.setTooltip(artifact.getName());
				part.getTransientData().put("opkeystudio.artifactData", artifact);
				partService.showPart(part, PartState.ACTIVATE);
			}

		} finally {
			Utilities.getInstance().defaultShell.setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));
		}
	}

	public void openSelectedFileInGenericCodeEditor(File selectedCodeFile) {
		MPart mpart = Utilities.getInstance().getGenericEditorMPart(selectedCodeFile);
		if (mpart != null) {
			System.out.println("MPART Found");
			EPartService partService = Utilities.getInstance().getEpartService();
			partService.showPart(mpart, PartState.ACTIVATE);
			return;
		}
		EPartService partService = Utilities.getInstance().getEpartService();
		MPart part = partService.createPart("opkeystudio.partdescriptor.genericCodeEditor");
		part.setLabel(selectedCodeFile.getName());
		part.setTooltip(selectedCodeFile.getName());
		part.getTransientData().put("opkeystudio.codeFile", selectedCodeFile);
		partService.showPart(part, PartState.ACTIVATE);
	}

	public void closeArtifactPart(Artifact artifact) {
		MPart mpart = getArtifactMPart(artifact);
		if (mpart == null) {
			return;
		}
		EPartService partService = Utilities.getInstance().getEpartService();
		partService.hidePart(mpart, true);
	}

	public void activateMpart(MPart mpart) {
		EPartService partService = Utilities.getInstance().getEpartService();
		partService.showPart(mpart, PartState.VISIBLE);
	}

	public void renameArtifactLabel(Artifact artifact, String renamedData) {
		MPart mpart = getArtifactMPart(artifact);
		if (mpart == null) {
			return;
		}
		mpart.getTransientData().put("opkeystudio.artifactData", artifact);
		mpart.setLabel(renamedData);
		mpart.setTooltip(renamedData);
	}

	public Shell getDefaultShell() {
		return defaultShell;
	}

	public void setDefaultShell(Shell defaultShell) {
		this.defaultShell = defaultShell;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
}
