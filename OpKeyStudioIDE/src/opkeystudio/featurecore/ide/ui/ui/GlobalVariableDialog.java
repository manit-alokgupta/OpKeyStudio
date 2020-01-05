package opkeystudio.featurecore.ide.ui.ui;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.GlobalVariableTable;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;

public class GlobalVariableDialog extends Dialog {

	
	protected Object result;
	protected Shell shlGlobalVariable;
	private GlobalVariableTable globalVariablesTable;
	private ToolItem addtoolitem;
	private ToolItem deletetoolitem;
	private ToolItem savetoolitem;
	private ToolItem refreshtoolitem;
	private static Text txtSearch;

	public void toggleAddToolItem(boolean status) {
		addtoolitem.setEnabled(status);
	}

	public void toggleDeleteToolItem(boolean status) {
		deletetoolitem.setEnabled(status);
	}

	public void toggleSaveToolItem(boolean status) {
		savetoolitem.setEnabled(status);
	}

	public void toggleRefreshToolItem(boolean status) {
		refreshtoolitem.setEnabled(status);
	}

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

	@SuppressWarnings("unused")
	private void createContents() {
		shlGlobalVariable = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.SYSTEM_MODAL);
		shlGlobalVariable.setSize(800, 600);
		shlGlobalVariable.setText("Global Variable");
		shlGlobalVariable.setLayout(new FillLayout(SWT.HORIZONTAL));

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlGlobalVariable.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlGlobalVariable.setLocation(new Point(locationX, locationY));

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
				globalVariablesTable.addBlankGlobalVariableStep();
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
				globalVariablesTable.deleteGlobalVariableStep();
				toggleSaveToolItem(true);
				toggleDeleteToolItem(false);
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
				globalVariablesTable.saveAll();
				globalVariablesTable.refreshGlobalVariables();
				toggleSaveToolItem(false);
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

				if (savetoolitem.isEnabled()) {
					boolean status = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(),
							"Global Variable Save", "Do you want to save global varaible?");
					if (status) {
						globalVariablesTable.saveAll();
						globalVariablesTable.refreshGlobalVariables();
						toggleSaveToolItem(false);
					}

				}
				toggleSaveToolItem(false);
				globalVariablesTable.refreshGlobalVariables();
			}
		});

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		txtSearch = new Text(composite_1, SWT.BORDER);
		txtSearch.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtSearch.setMessage("Search");

		txtSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				Text text = (Text) e.getSource();
				String searchValue = text.getText();
				if (searchValue.length() >= 1 || searchValue.trim().isEmpty()) {
					filterGlobalVariable(searchValue);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Button clearButton = new Button(composite_1, SWT.NONE);
		clearButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/erase.png"));
		clearButton.setToolTipText("Clear");

		clearButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				txtSearch.setText("");
				String textToSearch = txtSearch.getText();
				filterGlobalVariable(textToSearch);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		globalVariablesTable = new GlobalVariableTable(composite, SWT.BORDER | SWT.FULL_SELECTION, this);
		globalVariablesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		globalVariablesTable.setHeaderVisible(true);
		globalVariablesTable.setLinesVisible(true);
		globalVariablesTable.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 4);
				}
			}
		});

		final TableEditor editor = new TableEditor(globalVariablesTable);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		globalVariablesTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				int selectedIndex = globalVariablesTable.getSelectionIndex();
				if (selectedIndex == -1) {
					toggleDeleteToolItem(false);
					return;
				} else {
					toggleDeleteToolItem(true);
				}
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



		globalVariablesTable.refreshGlobalVariables();

	}

	private void filterGlobalVariable(String searchValue) {
		List<GlobalVariable> globalvariables = globalVariablesTable.getGlobalVariablesData();
		for (GlobalVariable globalVariable : globalvariables) {
			if (globalVariable.getName().trim().toLowerCase().contains(searchValue.trim().toLowerCase())) {
				globalVariable.setVisible(true);
			} else {
				globalVariable.setVisible(false);
			}
		}
	}
}
