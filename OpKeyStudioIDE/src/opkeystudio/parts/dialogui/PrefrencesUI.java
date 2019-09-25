package opkeystudio.parts.dialogui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class PrefrencesUI extends Dialog {

	protected Object result;
	protected Shell shlPreferences;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public PrefrencesUI(Shell parent, int style) {
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
		shlPreferences.open();
		shlPreferences.layout();
		Display display = getParent().getDisplay();
		while (!shlPreferences.isDisposed()) {
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
		shlPreferences = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlPreferences.setSize(450, 482);
		shlPreferences.setText("Preferences");
		shlPreferences.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(shlPreferences, SWT.NONE);


		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("New Item");

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("New Item");

		TabItem tbtmNewItem_4 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_4.setText("New Item");

		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("New Item");

		TabItem tbtmNewItem_3 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_3.setText("New Item");

		TabItem tbtmNewItem_5 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_5.setText("New Item");

	}

}
