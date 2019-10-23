package opkeystudio.featurecore.globalvariables.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;

public class GlovalVariableDialog extends Dialog {
	String[] tableHeaders = { "Keyword", "Object", "Input Value", "Output Value" };
	protected Object result;
	protected Shell shlGlobalVaraible;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public GlovalVariableDialog(Shell parent, int style) {
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
		shlGlobalVaraible.open();
		shlGlobalVaraible.layout();
		Display display = getParent().getDisplay();
		while (!shlGlobalVaraible.isDisposed()) {
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
		shlGlobalVaraible = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlGlobalVaraible.setSize(450, 300);
		shlGlobalVaraible.setText("Global Varaible");

		ToolBar toolBar = new ToolBar(shlGlobalVaraible, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(0, 0, 444, 23);

		Table table = new Table(shlGlobalVaraible, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 23, 444, 238);
		table.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 4);
				}
			}
		});
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(table, 0);
			column.setText(header);
		}

		table.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			table.getColumn(i).pack();
		}
		TableItem ti = new TableItem(table, 0);
		ti.setText(new String[] { "deded", "deded", "ddede", "ded" });

	}
}
