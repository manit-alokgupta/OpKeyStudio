package opkeystudio.core.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.UUID;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.InjectionException;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.ArtifactCodeEditor;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
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
		defaultShell.setCursor(new Cursor(Display.getCurrent(), i));
	}

	public void setShellCursor(Shell shell, int i) {
		shell.setCursor(new Cursor(shell.getDisplay(), i));
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
		for (MPart mpart : allParts) {
			Artifact artifact_0 = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
			if (artifact_0 == null) {
				continue;
			}
			if (artifact_0.getId().equals(artifact.getId())) {
				return mpart;
			}
		}
		return null;
	}

	public MPart getArtifactMPart(File codeViewFile) {
		Collection<MPart> allParts = getAllParts();
		for (MPart mpart : allParts) {
			File artifact_0 = (File) mpart.getTransientData().get("opkeystudio.codeFile");
			if (artifact_0 == null) {
				continue;
			}
			if (artifact_0.getAbsolutePath().equals(codeViewFile.getAbsolutePath())) {
				return mpart;
			}
		}
		return null;
	}

	public MPart getGenericEditorMPart(File artifact) {
		Collection<MPart> allParts = getAllParts();
		for (MPart mpart : allParts) {
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

	public void closeActivePart() {
		MPart part = getActivePart();
		if (part == null) {
			EPartService partService = Utilities.getInstance().getEpartService();
			partService.hidePart(part, true);
		}
	}

	public void closeAllMparts() {
		try {
			Collection<MPart> allParts = getAllParts();
			for (MPart mpart : allParts) {
				Artifact artifact_0 = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
				if (artifact_0 != null) {
					EPartService partService = Utilities.getInstance().getEpartService();
					partService.hidePart(mpart, true);
				}
				ExecutionSession executionSession = (ExecutionSession) mpart.getTransientData().get("opkeystudio.executionSessionData");
				if (executionSession != null) {
					EPartService partService = Utilities.getInstance().getEpartService();
					partService.hidePart(mpart, true);
				}

				Object codeEditorPart = mpart.getTransientData().get("opkeystudio.codeFile");
				if (codeEditorPart != null) {
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

			MPart mpart = Utilities.getInstance().getArtifactMPart(artifact);
			if (mpart != null) {
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
		try {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_WAIT);
			MPart mpart = getArtifactMPart(artifact);
			if (mpart == null) {
				return;
			}
			mpart.getTransientData().put("opkeystudio.artifactData", artifact);
			mpart.setLabel(renamedData);
			mpart.setTooltip(renamedData);
		} finally {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_ARROW);
		}
	}

	public void updateCodeViewFile(File oldCodeViewFile, File newCodeViewFile) {
		try {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_WAIT);
			MPart mpart = getArtifactMPart(oldCodeViewFile);
			if (mpart == null) {
				return;
			}
			mpart.getTransientData().put("opkeystudio.codeFile", newCodeViewFile);
			mpart.setLabel(newCodeViewFile.getName());
			mpart.setTooltip(newCodeViewFile.getName());
		} finally {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_ARROW);
		}
	}

	public void initAllPluginFeatures() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialogs msd = new MessageDialogs();
				msd.openProgressDialog(null, "Loading OpKey Studio's Features", false, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						new ArtifactCodeEditor();
						try {
							GlobalLoader.getInstance().initAllClassInfoDTOS();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				msd.closeProgressDialog();
			}
		});

	}

	public void refreshArtifactTree() {
		GlobalLoader.getInstance().initAllArtifacts();
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

	public String getVersion() {
		return Platform.getProduct().getDefiningBundle().getVersion() + "_2020.05.16_Evening";
	}
}
