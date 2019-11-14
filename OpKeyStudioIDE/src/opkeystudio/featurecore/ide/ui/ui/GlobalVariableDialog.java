package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.GlobalVariableTable;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;

import org.eclipse.wb.swt.ResourceManager;

public class GlobalVariableDialog extends Dialog {

	String[] tableHeaders = { "Name", "Data Type", "Value", "Externally Updatable" };
	protected Object result;
	protected Shell shlGlobalVariable;
	private GlobalVariableTable table;
//	private Table table;
	private ToolItem addtoolitem;
	private ToolItem deletetoolitem;
	private ToolItem savetoolitem;
	private ToolItem refreshtoolitem;

	/**
	 * Create the dialog. 
	 * 
	 * @param parent
	 * @param style
	 */
	public GlobalVariableDialog(Shell parent, int style) {
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
		shlGlobalVariable.open();
		shlGlobalVariable.layout();
		Display display = getParent().getDisplay();
		while (!shlGlobalVariable.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */

	static int EDITABLECOLUMN = 0;

	private void createContents() {
		shlGlobalVariable = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.SYSTEM_MODAL);
		shlGlobalVariable.setSize(546, 443);
		shlGlobalVariable.setText("Global Variable");
		shlGlobalVariable.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shlGlobalVariable, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		addtoolitem = new ToolItem(toolBar, SWT.NONE);
		addtoolitem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		addtoolitem.setText("Add");

		addtoolitem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.addBlankGlobalVariableStep();
			}
		});

		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		deletetoolitem = new ToolItem(toolBar, SWT.NONE);
		deletetoolitem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deletetoolitem.setText("Delete");
		deletetoolitem.setEnabled(false);
		deletetoolitem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.deleteGlobalVariableStep();
				savetoolitem.setEnabled(true);
			}
		});

		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.SEPARATOR);

		savetoolitem = new ToolItem(toolBar, SWT.NONE);
		savetoolitem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/save_icon.png"));
		savetoolitem.setText("Save");
		savetoolitem.setEnabled(false);
		savetoolitem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.saveAll();
				savetoolitem.setEnabled(false);
			}
		});

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);

		refreshtoolitem = new ToolItem(toolBar, SWT.NONE);
		refreshtoolitem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		refreshtoolitem.setText("Refresh");
		refreshtoolitem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.refreshGlobalVariables();
			}
		});

		table = new GlobalVariableTable(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
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
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				int selectedIndex = table.getSelectionIndex();
				if (selectedIndex == -1) {
					deletetoolitem.setEnabled(false);
					return;
				} else {
					deletetoolitem.setEnabled(true);
				}
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
							EDITABLECOLUMN = col;
						}
					}
				}

				if (item == null) {
					return;
				}
				GlobalVariable gVar = (GlobalVariable) item.getData();
				String selectedColumn = tableHeaders[EDITABLECOLUMN];
				if (selectedColumn.equals("Externally Updatable")) {

				}
				Text newEditor = new Text(table, SWT.NONE);
				newEditor.setText(item.getText(EDITABLECOLUMN));
				newEditor.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						savetoolitem.setEnabled(true);
						gVar.setModified(true);
						Text text = (Text) editor.getEditor();
						editor.getItem().setText(EDITABLECOLUMN, text.getText());
						if (selectedColumn.equals("Name")) {
							gVar.setName(text.getText());
						}
						if (selectedColumn.equals("Data Type")) {
							gVar.setDatatype(text.getText());
						}
						if (selectedColumn.equals("Value")) {
							gVar.setValue(text.getText());
						}
						if (selectedColumn.equals("Externally Updatable")) {
							if (text.getText().equals("true")) {
								gVar.setExternallyupdatable(true);
							} else {
								gVar.setExternallyupdatable(false);
							}
						}
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

		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(table, 0);
			column.setText(header);
		}
		table.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			table.getColumn(i).pack();
		}

		table.refreshGlobalVariables();

	}
}
