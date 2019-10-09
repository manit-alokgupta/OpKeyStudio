package opkeystudio.core.utils;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

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
}
