package opkeystudio.core.utils;

import java.util.Collection;
import java.util.UUID;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;

public class Utilities {
	private static Utilities utils = null;

	public static Utilities getInstance() {
		if (utils == null) {
			utils = new Utilities();
		}
		return utils;
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
			System.out.println("Mpart Searching");
			Artifact artifact_0 = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
			if (artifact_0 == null) {
				return null;
			}
			System.out.println("ID FOUND "+artifact_0.getId());
			if (artifact_0.getId().equals(artifact.getId())) {
				return mpart;
			}
		}
		return null;
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
}
