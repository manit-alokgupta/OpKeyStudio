package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class ExceptionDialog extends Dialog {

	protected Object result;
	protected Shell shlExceptionDialog;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ExceptionDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlExceptionDialog.open();
		shlExceptionDialog.layout();
		Display display = getParent().getDisplay();
		while (!shlExceptionDialog.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlExceptionDialog = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE | SWT.SYSTEM_MODAL);
		shlExceptionDialog.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/exception.png"));
		shlExceptionDialog.setSize(665, 514);
		shlExceptionDialog.setText("Exception Dialog");
		shlExceptionDialog.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlExceptionDialog, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		StyledText excpMsg = new StyledText(composite, SWT.BORDER | SWT.READ_ONLY);
		excpMsg.setText("Exception Message");
		excpMsg.setEditable(false);
		GridData gd_excpMsg = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_excpMsg.heightHint = 51;
		excpMsg.setLayoutData(gd_excpMsg);
		
		StyledText excpDetail = new StyledText(composite, SWT.BORDER | SWT.READ_ONLY);
		excpDetail.setText("Exception Detail");
		excpDetail.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(22, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Button okBtn = new Button(composite_1, SWT.CENTER);
		okBtn.setToolTipText("OK");
		okBtn.setText("OK");
		okBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));
		okBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		okBtn.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/ok.png"));
		
		Button reportBtn = new Button(composite_1, SWT.CENTER);
		reportBtn.setText("Report");
		GridData gd_reportBtn = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_reportBtn.widthHint = 68;
		reportBtn.setLayoutData(gd_reportBtn);
		reportBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		reportBtn.setAlignment(SWT.RIGHT);
		reportBtn.setToolTipText("Report");
		reportBtn.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/report.png"));
		
		Button exitBtn = new Button(composite_1, SWT.CENTER);
		exitBtn.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/exit.png"));
		exitBtn.setToolTipText("Exit");
		exitBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		exitBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		exitBtn.setText("Exit");

	}
}
