package opkeystudio.featurecore.globalvariables.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.ToolItem;

import opkeystudio.opkeystudiocore.core.apis.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;

public class GlovalVariableDialog extends Dialog {
	String[] tableHeaders = { "Name", "Data Type", "Value", "Externally Updatable" };
	protected Object result;
	protected Shell shlGlobalVaraible;
	private Table table;

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

	private void renderGlobalVaribles() {
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(table, 0);
			column.setText(header);
		}
		table.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			table.getColumn(i).pack();
		}

		try {
			List<GlobalVariable> globalvariables = new GlobalVariableApi().getAllGlobalVariables();
			for (GlobalVariable globalvariable : globalvariables) {
				TableItem ti = new TableItem(table, 0);
				ti.setData(globalvariable);
				ti.setText(new String[] { globalvariable.getName(), globalvariable.getDatatype(),
						globalvariable.getValue(), String.valueOf(globalvariable.getExternallyupdatable()) });
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlGlobalVaraible = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlGlobalVaraible.setSize(450, 300);
		shlGlobalVaraible.setText("Global Varaible");
		shlGlobalVaraible.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(shlGlobalVaraible, SWT.VERTICAL);

		ToolBar toolBar = new ToolBar(sashForm, SWT.FLAT | SWT.RIGHT);

		ToolItem addtoolitem = new ToolItem(toolBar, SWT.NONE);
		addtoolitem.setText("Add");

		ToolItem deletetoolitem = new ToolItem(toolBar, SWT.NONE);
		deletetoolitem.setText("Delete");

		ToolItem savetoolitem = new ToolItem(toolBar, SWT.NONE);
		savetoolitem.setText("Save");

		ToolItem refreshtoolitem = new ToolItem(toolBar, SWT.NONE);
		refreshtoolitem.setText("Refresh");

		refreshtoolitem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				renderGlobalVaribles();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		table.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 4);
				}
			}
		});
		sashForm.setWeights(new int[] { 1, 10 });
		renderGlobalVaribles();
	}
}
