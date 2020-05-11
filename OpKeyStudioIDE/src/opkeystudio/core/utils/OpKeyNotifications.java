package opkeystudio.core.utils;

import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class OpKeyNotifications {
	public void showNotification(String titleText, String infoText, String content) {
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				final Display display =	Display.getDefault();
				final Shell shell = new Shell(display);
				shell.setText("PopupDialog");
				shell.setLayout(new GridLayout());
				shell.setSize(400, 300);

				int shellStyle = PopupDialog.INFOPOPUPRESIZE_SHELLSTYLE;
				boolean takeFocusOnOpen = true;
				boolean persistSize = true;
				boolean persistLocation = true;
				boolean showDialogMenu = true;
				boolean showPersistActions = true;
				PopupDialog dialog = new PopupDialog(shell, shellStyle, takeFocusOnOpen, persistSize, persistLocation,
						showDialogMenu, showPersistActions, titleText, infoText) {

					@Override
					protected Control createDialogArea(Composite parent) {
						Composite composite = (Composite) super.createDialogArea(parent);
						Label text = new Label(composite, SWT.SINGLE);
						text.setText(content);
						return composite;
					}

				};

				dialog.open();	
			}
		});
	}
}
