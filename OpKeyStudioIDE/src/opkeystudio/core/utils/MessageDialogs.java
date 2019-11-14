package opkeystudio.core.utils;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class MessageDialogs {

	public String openInputDialogAandGetValue(String dialogTitle, String dialogContent, String defaultValue) {
		InputDialog input = new InputDialog(Display.getCurrent().getActiveShell(), dialogTitle, dialogContent,
				defaultValue, null);

		if (input.open() != InputDialog.OK) {
			return null;
		}
		return input.getValue();
	}

	public boolean openConfirmDialog(String title, String message) {
		boolean result = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), title, message);
		return result;
	}
}
