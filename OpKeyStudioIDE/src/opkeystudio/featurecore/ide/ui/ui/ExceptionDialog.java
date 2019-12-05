package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

public class ExceptionDialog extends Dialog {

	protected Object result;
	protected Shell shlExceptionDialog;
	private Button copyExcpBtn;
	private StyledText excpMsg;
	private StyledText excpDetail;
	private Button okBtn;
	private Button reportBtn;
	private Button exitBtn;
	String copiedException;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ExceptionDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
 
	/**
	 * Open the dialog.
	 * 
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

		excpMsg = new StyledText(composite, SWT.BORDER | SWT.READ_ONLY);
		excpMsg.setText("Exception Message");
		excpMsg.setEditable(false);
		GridData gd_excpMsg = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_excpMsg.heightHint = 51;
		excpMsg.setLayoutData(gd_excpMsg);

		excpDetail = new StyledText(composite, SWT.BORDER | SWT.READ_ONLY);
		excpDetail.setText("Exception Detail");
		excpDetail.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(22, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		copyExcpBtn = new Button(composite_1, SWT.NONE);
		copyExcpBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		copyExcpBtn.setToolTipText("Copy");
		copyExcpBtn.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/copy.png"));
		copyExcpBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				copiedException = excpDetail.getText().toString();
				new MessageBox(getParent()).setMessage("Exception Copied");
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {

			}
		});
		copyExcpBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		copyExcpBtn.setText("Copy Exception");
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
		
				okBtn = new Button(composite_1, SWT.CENTER);
				okBtn.setToolTipText("OK");
				okBtn.setText("OK");
				okBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 7, 1));
				okBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
				okBtn.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/ok.png"));
				okBtn.addMouseListener(new MouseListener() {

					@Override
					public void mouseUp(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseDown(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseDoubleClick(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				});

		reportBtn = new Button(composite_1, SWT.CENTER);
		reportBtn.setText("Report");
		GridData gd_reportBtn = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_reportBtn.widthHint = 68;
		reportBtn.setLayoutData(gd_reportBtn);
		reportBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		reportBtn.setAlignment(SWT.RIGHT);
		reportBtn.setToolTipText("Report");
		reportBtn.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/report.png"));
		reportBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		exitBtn = new Button(composite_1, SWT.CENTER);
		exitBtn.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/exitExcp.png"));
		exitBtn.setToolTipText("Exit");
		exitBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		exitBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		exitBtn.setText("Exit");
		exitBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				System.exit(0);

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
}
