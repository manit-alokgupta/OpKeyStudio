package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.datarepositorycontrol.DataRepositoryTable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;

public class DataRepositoryView extends Composite {
	private DataRepositoryTable excelTable;
	private int colCount = 0;
	private int rowCount = 0;
	private TableColumn newColumn;
	private DRColumnAttributes drCol;
	// private XSSFWorkbook wb;
	private OleClientSite site;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unused")
	public DataRepositoryView(Composite parent, int style)
			throws JsonParseException, JsonMappingException, IOException {
		super(parent, SWT.BORDER);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar.setBounds(0, 0, 87, 23);

		ToolItem addColmToolItm = new ToolItem(toolBar, SWT.NONE);
		addColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/add_column.png"));
		addColmToolItm.setToolTipText("Add Column");

		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem deleteColmToolItm = new ToolItem(toolBar, SWT.NONE);
		deleteColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/delete_column.png"));
		deleteColmToolItm.setToolTipText("Delete Column");

		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem moveColmLeftToolItm = new ToolItem(toolBar, SWT.NONE);
		moveColmLeftToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_col_left.png"));
		moveColmLeftToolItm.setToolTipText("Move Column Left");

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem moveColmRightToolItm = new ToolItem(toolBar, SWT.NONE);
		moveColmRightToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_col_right.png"));
		moveColmRightToolItm.setToolTipText("Move Column Right");

		ToolItem toolItem_3 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem renameColmToolItm = new ToolItem(toolBar, SWT.NONE);
		renameColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/rename.png"));
		renameColmToolItm.setToolTipText("Rename Column ");

		ToolItem toolItem_4 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem addRowToolItm = new ToolItem(toolBar, SWT.NONE);
		addRowToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/add_row.png"));
		addRowToolItm.setToolTipText("Add Row");

		ToolItem toolItem_5 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem deleteRowToolItm = new ToolItem(toolBar, SWT.NONE);
		deleteRowToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/delete_row.png"));
		deleteRowToolItm.setToolTipText("Delete Row");

		ToolItem toolItem_6 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem moveRowUpToolItm = new ToolItem(toolBar, SWT.NONE);
		moveRowUpToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_row_up.png"));
		moveRowUpToolItm.setToolTipText("Move Row Up");

		ToolItem toolItem_7 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem moveRowDownToolItm = new ToolItem(toolBar, SWT.NONE);
		moveRowDownToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_row_down.png"));
		moveRowDownToolItm.setToolTipText("Move Row Down");

		ToolItem toolItem_8 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem exportExcelDataToolItm = new ToolItem(toolBar, SWT.NONE);
		exportExcelDataToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/import.png"));
		exportExcelDataToolItm.setToolTipText("Export Data From Excel");

		ToolItem toolItem_9 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem inportExcelDataToolItm = new ToolItem(toolBar, SWT.NONE);
		inportExcelDataToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/export.png"));
		inportExcelDataToolItm.setToolTipText("Import Data In Excel");

		ToolItem toolItem_10 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem saveToolItm = new ToolItem(toolBar, SWT.NONE);
		saveToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/save.png"));
		saveToolItm.setToolTipText("Save");

		ToolItem toolItem_12 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem refreshToolItm = new ToolItem(toolBar, SWT.NONE);
		refreshToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		refreshToolItm.setToolTipText("Refresh");

		ToolItem toolItem_11 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem createBackupToolItm = new ToolItem(toolBar, SWT.NONE);
		createBackupToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/backup_create.png"));
		createBackupToolItm.setToolTipText("Create New Backup");

//		OleFrame frame = new OleFrame(parent, SWT.NONE);
//		site = new OleClientSite(frame, SWT.NONE, "Excel.Sheet");

		excelTable = new DataRepositoryTable(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI, this);
		excelTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		excelTable.setHeaderVisible(true);
		excelTable.setLinesVisible(true);

		colCount = excelTable.getColumnCount();
		rowCount = excelTable.getItemCount();

		addColmToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				newColumn = new TableColumn(excelTable, SWT.NONE);
				newColumn.setWidth(100);
				newColumn.setText("Column " + colCount);
				colCount += 1;

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		addRowToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				rowCount += 1;
				new TableItem(excelTable, SWT.NONE).setText("" + rowCount);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		exportExcelDataToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String fileName = new MessageDialogs().openInputDialogAandGetValue("File Name",
						"Please Enter File Name", "NewFile");
				// dumpWorkbookToAFile(wb, fileName);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		renameColmToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

//				String columnName = new MessageDialogs().openInputDialogAandGetValue("File Name",
//						"Please Enter File Name", "NewFile");
				System.out.println();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteColmToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveColmLeftToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveColmRightToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteRowToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveRowUpToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveRowDownToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		inportExcelDataToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		saveToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				saving();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		createBackupToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

//	@SuppressWarnings({ "deprecation", "unused" })
//	private XSSFWorkbook createWorkbookFromTable(TableViewer table) {
//		// create a workbook
//		wb = new XSSFWorkbook();
//
//		// add a worksheet
//		XSSFSheet sheet = wb.createSheet("My Table Data");
//
//		// shade the background of the header row
//		XSSFCellStyle headerStyle = wb.createCellStyle();
//		headerStyle.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
//
//		headerStyle.setAlignment(HorizontalAlignment.CENTER);
//
//		Table headerRow = table.getTable();
//		TableColumn[] columns = headerRow.getColumns();
//		int rowIndex = 0;
//		int cellIndex = 0;
//		XSSFRow header = sheet.createRow((short) rowIndex++);
//		for (TableColumn column : columns) {
//			String columnName = column.getText();
//			XSSFCell cell = header.createCell(cellIndex++);
//			cell.setCellValue(column.getText());
//			cell.setCellStyle(headerStyle);
//		}
//		// add data rows
//		TableItem[] items = table.getTable().getItems();
//		for (TableItem item : items) {
//			// create a new row
//			XSSFRow row = sheet.createRow((short) rowIndex++);
//			cellIndex = 0;
//
//			for (int i = 0; i < columns.length; i++) {
//				// create a new cell
////				String columnName = tableColumnNames[i];
//				XSSFCell cell = row.createCell(cellIndex++);
//
//				// set the horizontal alignment (default to RIGHT)
//				XSSFCellStyle cellStyle = wb.createCellStyle();
//				cellStyle.setAlignment(HorizontalAlignment.RIGHT);
//				cell.setCellStyle(cellStyle);
//
//				// set the cell's value
//				String text = item.getText(i);
//				cell.setCellValue(text);
//			}
//		}
//
//		for (int i = 0; i < columns.length; i++) {
//			sheet.autoSizeColumn((short) i);
//		}
//
//		return wb;
//	}

	/*
	 * public void dumpWorkbookToAFile(XSSFWorkbook wb, String filename) { try {
	 * FileOutputStream fos = new FileOutputStream(filename); wb.write(fos);
	 * fos.close();
	 * MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
	 * "Save Workbook Successful", "Workbook saved to the file:\n\n" + filename);
	 * 
	 * } catch (IOException ioe) { ioe.printStackTrace(); String msg =
	 * ioe.getMessage();
	 * MessageDialog.openError(Display.getCurrent().getActiveShell(),
	 * "Save Workbook Failed", "Could not save workbook to the file:\n\n" + msg); }
	 * }
	 */

	public void saving() {

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
