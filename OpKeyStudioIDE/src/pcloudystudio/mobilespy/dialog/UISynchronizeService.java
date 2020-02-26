package pcloudystudio.mobilespy.dialog;

import org.eclipse.e4.ui.di.UISynchronize;

public class UISynchronizeService {
	private static UISynchronizeService _instance;
	private UISynchronize sync;

	public static UISynchronizeService getInstance() {
		if (UISynchronizeService._instance == null) {
			UISynchronizeService._instance = new UISynchronizeService();
		}
		return UISynchronizeService._instance;
	}

	public UISynchronize getSync() {
		return this.sync;
	}

	public void setSync(final UISynchronize sync) {
		this.sync = sync;
	}

	public static void syncExec(final Runnable runnable) {
		getInstance().getSync().syncExec(runnable);
	}

	public static void asyncExec(final Runnable runnable) {
		getInstance().getSync().asyncExec(runnable);
	}
}