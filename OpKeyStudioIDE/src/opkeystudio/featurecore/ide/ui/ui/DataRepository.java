package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class DataRepository extends Composite {
	private Table excelTable;
	private int colCount = 0;
	private int rowCount = 0;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DataRepository(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar.setBounds(0, 0, 87, 23);

		ToolItem addColmToolItm = new ToolItem(toolBar, SWT.NONE);
		addColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/add_column.png"));
		addColmToolItm.setToolTipText("Add Column");

		ToolItem deleteColmToolItm = new ToolItem(toolBar, SWT.NONE);
		deleteColmToolItm
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/delete_column.png"));
		deleteColmToolItm.setToolTipText("Delete Column");

		ToolItem moveColmLeftToolItm = new ToolItem(toolBar, SWT.NONE);
		moveColmLeftToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_col_left.png"));
		moveColmLeftToolItm.setToolTipText("Move Column Left");

		ToolItem moveColmRightToolItm = new ToolItem(toolBar, SWT.NONE);
		moveColmRightToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_col_right.png"));
		moveColmRightToolItm.setToolTipText("Move Column Right");

		ToolItem renameColmToolItm = new ToolItem(toolBar, SWT.NONE);
		renameColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/rename.png"));
		renameColmToolItm.setToolTipText("Rename Column ");

		ToolItem addRowToolItm = new ToolItem(toolBar, SWT.NONE);
		addRowToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/add_row.png"));
		addRowToolItm.setToolTipText("Add Row");

		ToolItem deleteRowToolItm = new ToolItem(toolBar, SWT.NONE);
		deleteRowToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/delete_row.png"));
		deleteRowToolItm.setToolTipText("Delete Row");

		ToolItem moveRowUpToolItm = new ToolItem(toolBar, SWT.NONE);
		moveRowUpToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_row_up.png"));
		moveRowUpToolItm.setToolTipText("Move Row Up");

		ToolItem moveRowDownToolItm = new ToolItem(toolBar, SWT.NONE);
		moveRowDownToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_row_down.png"));
		moveRowDownToolItm.setToolTipText("Move Row Down");

		ToolItem inportExcelDataToolItm = new ToolItem(toolBar, SWT.NONE);
		inportExcelDataToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/import.png"));
		inportExcelDataToolItm.setToolTipText("Import Data From Excel");

		ToolItem exportExcelDataToolItm = new ToolItem(toolBar, SWT.NONE);
		exportExcelDataToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/export.png"));
		exportExcelDataToolItm.setToolTipText("Export Data In Excel");

		ToolItem saveToolItm = new ToolItem(toolBar, SWT.NONE);
		saveToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/save_icon.png"));
		saveToolItm.setToolTipText("Save");

		ToolItem refreshToolItm = new ToolItem(toolBar, SWT.NONE);
		refreshToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		refreshToolItm.setToolTipText("Refresh");

		ToolItem createBackupToolItm = new ToolItem(toolBar, SWT.NONE);
		createBackupToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/backup_create.png"));
		createBackupToolItm.setToolTipText("Create New Backup");

		excelTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		excelTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		excelTable.setBounds(0, 0, 85, 45);
		excelTable.setHeaderVisible(true);
		excelTable.setLinesVisible(true);

		colCount = excelTable.getColumnCount();
		rowCount = excelTable.getItemCount();

		addColmToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				new TableColumn(excelTable, SWT.NONE).setWidth(100);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addRowToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				rowCount+=1;
				new TableItem(excelTable, SWT.NONE).setText(""+rowCount);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
