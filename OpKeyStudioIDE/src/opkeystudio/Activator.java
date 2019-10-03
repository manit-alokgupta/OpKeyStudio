package opkeystudio;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import opkeystudio.opkeystudiocore.Test2;
import opkeystudio.opkeystudioplatform.OpkeystudioplatformApplication;

public class Activator implements BundleActivator {
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("OpKey Studio Started");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
