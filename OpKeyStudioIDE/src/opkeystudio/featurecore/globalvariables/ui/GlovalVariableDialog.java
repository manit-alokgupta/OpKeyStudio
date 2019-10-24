package opkeystudio.featurecore.globalvariables.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.ToolItem;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
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
						globalvariable.getValue(), String.valueOf(globalvariable.isExternallyupdatable()) });
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create contents of the dialog.
	 */
	static int EDITABLECOLUMN = 0;

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

		final TableEditor editor = new TableEditor(table);
		// The editor must have the same size as the cell and must
		// not be any smaller than 50 pixels.
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		// editing the second column

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				Control oldEditor = editor.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}

				Table table = (Table) e.getSource();
				TableItem item = table.getSelection()[0];

				Point pt = new Point(e.x, e.y);
				if (item != null) {
					for (int col = 0; col < table.getColumnCount(); col++) {
						Rectangle rect = item.getBounds(col);
						if ((pt.x > rect.x && pt.x < rect.x + rect.width)
								&& (pt.y > rect.y && pt.y < rect.y + rect.height)) {
							System.out.println("item clicked.");
							System.out.println("column is " + col);
							EDITABLECOLUMN = col;
						}
					}
				}

				if (item == null) {
					return;
				}
				Text newEditor = new Text(table, SWT.NONE);
				newEditor.setText(item.getText(EDITABLECOLUMN));
				newEditor.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						Text text = (Text) editor.getEditor();
						editor.getItem().setText(EDITABLECOLUMN, text.getText());
					}
				});
				newEditor.selectAll();
				newEditor.setFocus();
				editor.setEditor(newEditor, item, EDITABLECOLUMN);
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

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		sashForm.setWeights(new int[] { 1, 10 });
		renderGlobalVaribles();
	}
}
