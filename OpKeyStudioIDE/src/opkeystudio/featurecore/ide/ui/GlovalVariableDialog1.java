package opkeystudio.featurecore.ide.ui;

import java.awt.Checkbox;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.GlobalVariableTable;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import org.eclipse.wb.swt.SWTResourceManager;

public class GlovalVariableDialog1 extends Dialog {
	String[] tableHeaders = { "Name", "Data Type", "Value", "Externally Updatable" };
	protected Object result;
	protected Shell shlGlobalVaraible;
	private GlobalVariableTable table;
	private ToolItem savetoolitem;
	private ToolItem deletetoolitem;
	private ToolItem addtoolitem;
	private ToolItem refreshtoolitem;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public GlovalVariableDialog1(Shell parent, int style) {
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
	static int EDITABLECOLUMN = 0;

	private void createContents() {
		shlGlobalVaraible = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlGlobalVaraible.setSize(529, 383);
		shlGlobalVaraible.setText("Global Varaible");
		FillLayout fl_shlGlobalVaraible = new FillLayout(SWT.HORIZONTAL);
		fl_shlGlobalVaraible.marginWidth = 5;
		fl_shlGlobalVaraible.marginHeight = 5;
		shlGlobalVaraible.setLayout(fl_shlGlobalVaraible);

		SashForm sashForm = new SashForm(shlGlobalVaraible, SWT.SMOOTH | SWT.VERTICAL);

		ToolBar toolBar = new ToolBar(sashForm, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		addtoolitem = new ToolItem(toolBar, SWT.NONE);
		addtoolitem.setText("Add");
		addtoolitem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/create.png"));
		addtoolitem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.addBlankGlobalVariableStep();
			}
		});
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		deletetoolitem = new ToolItem(toolBar, SWT.NONE);
		deletetoolitem.setText("Delete");
		deletetoolitem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/delete.png"));
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
		savetoolitem.setText("Save");
		savetoolitem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/save.png"));
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
		refreshtoolitem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		refreshtoolitem.setText("Refresh");

		refreshtoolitem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				table.refreshGlobalVariables();
			}
		});
		table = new GlobalVariableTable(sashForm, SWT.FULL_SELECTION);
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

		sashForm.setWeights(new int[] {1, 11});
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