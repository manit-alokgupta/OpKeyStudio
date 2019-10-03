package opkeystudio;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import opkeystudio.opkeystudiocore.Test;
import opkeystudio.opkeystudioplatform.OpkeystudioplatformApplication;

public class Activator implements BundleActivator {
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("OpKey Studio Started");
		new Test().displayName();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
