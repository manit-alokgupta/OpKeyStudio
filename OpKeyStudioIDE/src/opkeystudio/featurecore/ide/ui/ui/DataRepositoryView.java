package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryDataRepoUi;
import opkeystudio.featurecore.ide.ui.customcontrol.datarepositorycontrol.DataRepositoryTable;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryConstructApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;

public class DataRepositoryView extends Composite {
	private DataRepositoryTable dataRepositoryTable;
	private int colCount = 0;
	private int rowCount = 0;
	private TableColumn newColumn;
	private DRColumnAttributes drCol;
	private BottomFactoryDataRepoUi bottomFactory;
	private ToolItem refreshToolItm;
	private ToolItem addColmToolItm;
	private ToolItem deleteColmToolItm;
	private ToolItem moveColmLeftToolItm;
	private ToolItem moveColmRightToolItm;
	private ToolItem renameColmToolItm;
	private ToolItem addRowToolItm;
	private ToolItem deleteRowToolItm;
	private ToolItem moveRowUpToolItm;
	private ToolItem moveRowDownToolItm;
	private ToolItem exportExcelDataToolItm;
	private ToolItem inportExcelDataToolItm;
	private ToolItem saveToolItm;
	private ToolItem createBackupToolItm;

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

		addColmToolItm = new ToolItem(toolBar, SWT.NONE);
		addColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/add_column.png"));
		addColmToolItm.setToolTipText("Add Column");

		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		deleteColmToolItm = new ToolItem(toolBar, SWT.NONE);
		deleteColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/delete_column.png"));
		deleteColmToolItm.setToolTipText("Delete Column");

		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.SEPARATOR);

		moveColmLeftToolItm = new ToolItem(toolBar, SWT.NONE);
		moveColmLeftToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_col_left.png"));
		moveColmLeftToolItm.setToolTipText("Move Column Left");

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);

		moveColmRightToolItm = new ToolItem(toolBar, SWT.NONE);
		moveColmRightToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_col_right.png"));
		moveColmRightToolItm.setToolTipText("Move Column Right");

		ToolItem toolItem_3 = new ToolItem(toolBar, SWT.SEPARATOR);

		renameColmToolItm = new ToolItem(toolBar, SWT.NONE);
		renameColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/rename.png"));
		renameColmToolItm.setToolTipText("Rename Column ");

		ToolItem toolItem_4 = new ToolItem(toolBar, SWT.SEPARATOR);

		addRowToolItm = new ToolItem(toolBar, SWT.NONE);
		addRowToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/add_row.png"));
		addRowToolItm.setToolTipText("Add Row");

		ToolItem toolItem_5 = new ToolItem(toolBar, SWT.SEPARATOR);

		deleteRowToolItm = new ToolItem(toolBar, SWT.NONE);
		deleteRowToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/delete_row.png"));
		deleteRowToolItm.setToolTipText("Delete Row");

		ToolItem toolItem_6 = new ToolItem(toolBar, SWT.SEPARATOR);

		moveRowUpToolItm = new ToolItem(toolBar, SWT.NONE);
		moveRowUpToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_row_up.png"));
		moveRowUpToolItm.setToolTipText("Move Row Up");

		ToolItem toolItem_7 = new ToolItem(toolBar, SWT.SEPARATOR);

		moveRowDownToolItm = new ToolItem(toolBar, SWT.NONE);
		moveRowDownToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/move_row_down.png"));
		moveRowDownToolItm.setToolTipText("Move Row Down");

		ToolItem toolItem_8 = new ToolItem(toolBar, SWT.SEPARATOR);

		exportExcelDataToolItm = new ToolItem(toolBar, SWT.NONE);
		exportExcelDataToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/import.png"));
		exportExcelDataToolItm.setToolTipText("Export Data From Excel");

		ToolItem toolItem_9 = new ToolItem(toolBar, SWT.SEPARATOR);

		inportExcelDataToolItm = new ToolItem(toolBar, SWT.NONE);
		inportExcelDataToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/export.png"));
		inportExcelDataToolItm.setToolTipText("Import Data In Excel");

		ToolItem toolItem_10 = new ToolItem(toolBar, SWT.SEPARATOR);

		saveToolItm = new ToolItem(toolBar, SWT.NONE);
		saveToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/save.png"));
		saveToolItm.setToolTipText("Save");

		ToolItem toolItem_12 = new ToolItem(toolBar, SWT.SEPARATOR);

		refreshToolItm = new ToolItem(toolBar, SWT.NONE);
		refreshToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		refreshToolItm.setToolTipText("Refresh");

		ToolItem toolItem_11 = new ToolItem(toolBar, SWT.SEPARATOR);

		createBackupToolItm = new ToolItem(toolBar, SWT.NONE);
		createBackupToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/backup_create.png"));
		createBackupToolItm.setToolTipText("Create New Backup");

//		OleFrame frame = new OleFrame(parent, SWT.NONE);
//		site = new OleClientSite(frame, SWT.NONE, "Excel.Sheet");

		dataRepositoryTable = new DataRepositoryTable(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI, this);
		dataRepositoryTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		dataRepositoryTable.setHeaderVisible(true);
		dataRepositoryTable.setLinesVisible(true);

		bottomFactory = new BottomFactoryDataRepoUi(composite, SWT.BORDER);
		bottomFactory.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		bottomFactory.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		colCount = dataRepositoryTable.getColumnCount();
		rowCount = dataRepositoryTable.getItemCount();
		init();
		addButtonListner();
	}

	private void init() {

	}

	private void addButtonListner() {
		addColmToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				dataRepositoryTable.addDRColumn();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		addRowToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				rowCount += 1;
				new TableItem(dataRepositoryTable, SWT.NONE).setText("" + rowCount);

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
				dataRepositoryTable.deleteDRColumn();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveColmLeftToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				dataRepositoryTable.moveColumnLeft();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveColmRightToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				dataRepositoryTable.moveColumnRight();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteRowToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				dataRepositoryTable.deleteDRRow();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveRowUpToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				dataRepositoryTable.moveRowUp();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		moveRowDownToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				dataRepositoryTable.moveRowDown();
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
				List<DRColumnAttributes> drColumns = dataRepositoryTable.getDrColumnAttributes();
				new DataRepositoryConstructApi().saveAllDRColumns(drColumns);
				toggleSaveButton(false);
				try {
					dataRepositoryTable.renderAllDRDetails();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					dataRepositoryTable.renderAllDRDetails();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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

	public Artifact getArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		return artifact;
	}

	public void toggleRefreshButton(boolean status) {
		this.refreshToolItm.setEnabled(status);
	}

	public void toggleAddColumnButton(boolean status) {
		this.addColmToolItm.setEnabled(status);
	}

	public void toggleDeleteColumnButton(boolean status) {
		this.deleteColmToolItm.setEnabled(status);
	}

	public void toggleMoveColumnLeftButton(boolean status) {
		this.moveColmLeftToolItm.setEnabled(status);
	}

	public void toggleMoveColumnRightButton(boolean status) {
		this.moveColmRightToolItm.setEnabled(status);
	}

	public void toggleRenameColumnButton(boolean status) {
		this.renameColmToolItm.setEnabled(status);
	}

	public void toggleAddRowButton(boolean status) {
		this.addRowToolItm.setEnabled(status);
	}

	public void toggleDeleteRowButton(boolean status) {
		this.deleteRowToolItm.setEnabled(status);
	}

	public void toggleMoveRowUpButton(boolean status) {
		this.moveRowUpToolItm.setEnabled(status);
	}

	public void toggleMoveRowDownButton(boolean status) {
		this.moveRowDownToolItm.setEnabled(status);
	}

	public void toggleExportDataButton(boolean status) {
		this.exportExcelDataToolItm.setEnabled(status);
	}

	public void toggleImportDataButton(boolean status) {
		this.inportExcelDataToolItm.setEnabled(status);
	}

	public void toggleSaveButton(boolean status) {
		this.saveToolItm.setEnabled(status);
	}

	public void toggleCreateBackupButton(boolean status) {
		this.createBackupToolItm.setEnabled(status);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
