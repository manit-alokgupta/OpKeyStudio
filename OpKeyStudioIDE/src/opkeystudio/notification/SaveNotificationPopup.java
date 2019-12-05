package opkeystudio.notification;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * Sample Notification Popup Class
 */
public class SaveNotificationPopup {// extends AbstractNotificationPopup

	/**
	 * @param display
	 */

	/*
	 * public SaveNotificationPopup(Display display) { super(display); }
	 */

	protected void createContentArea(Composite parent) {

		Composite container = new Composite(parent, SWT.NULL);

		GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		container.setLayoutData(data);

		container.setLayout(new GridLayout(1, false));

		Label successMsg = new Label(container, SWT.NULL);
		successMsg.setText("Save Successfully");

		new Label(container, SWT.NONE);
	}

	protected String getPopupShellTitle() {
		return "Save";
	}

}
