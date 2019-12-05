package opkeystudio.notification;

import org.eclipse.swt.widgets.Display;

public class NotificationQuartzJobHelper {
	private static NotificationQuartzJobHelper notificationQuartzJobHelper;
	private Display currentDisp;

	public static NotificationQuartzJobHelper getInstance() {
		if (notificationQuartzJobHelper == null) {
			notificationQuartzJobHelper = new NotificationQuartzJobHelper();
		}
		return notificationQuartzJobHelper;
	}

	public void setCurrDisplay(Display currentDisp) {
		this.currentDisp = currentDisp;
	}

	public Display getCurrentDisp() {
		return this.currentDisp;
	}
}
