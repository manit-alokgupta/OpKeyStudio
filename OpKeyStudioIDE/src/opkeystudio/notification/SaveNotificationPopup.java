package opkeystudio.notification;

import org.eclipse.mylyn.commons.ui.dialogs.AbstractNotificationPopup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class SaveNotificationPopup extends AbstractNotificationPopup {// extends AbstractNotificationPopup

	public SaveNotificationPopup(Display display) {
		super(display);
		setShellStyle(SWT.NO_TRIM);
	}

	protected void createContentArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		container.setLayout(new GridLayout(1, false));

		Label successMsg = new Label(container, SWT.NULL);
		successMsg.setText("Save Successfully!");
		FontData fontData = successMsg.getFont().getFontData()[0];
		Font font = new Font(Display.getCurrent(),
				new FontData(fontData.getName(), fontData.getHeight(), SWT.BOLD | SWT.CENTER));
		successMsg.setFont(font);
		GridData message = new GridData();
		message.widthHint = 400;
		successMsg.setLayoutData(message);
	}

	@Override
	protected String getPopupShellTitle() {
		return "OpKey";
	}
}
