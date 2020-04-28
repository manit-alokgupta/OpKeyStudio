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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryDataRepoUi;
import opkeystudio.featurecore.ide.ui.customcontrol.datarepositorycontrol.DataRepositoryTable;
import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryConstructApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;

public class DataRepositoryView extends SuperComposite {
	private DataRepositoryTable dataRepositoryTable;
	@SuppressWarnings("unused")
	private int colCount = 0;
	@SuppressWarnings("unused")
	private int rowCount = 0;
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
	private ToolItem saveToolItm;
	private ArtifactCodeView codedFunctionView;

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
		TabFolder tabFolder = new TabFolder(this, SWT.BOTTOM);

		Composite drDataHolder = new Composite(tabFolder, SWT.NONE);
		drDataHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		drDataHolder.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Data Repository");
		tabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DR_ICON));
		tabItem.setControl(drDataHolder);

		Composite composite = new Composite(drDataHolder, SWT.BORDER);
		composite.setLayout(new GridLayout(1, false));
		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar.setBounds(0, 0, 87, 23);

		addColmToolItm = new ToolItem(toolBar, SWT.NONE);
		addColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_COLUMN_ICON));
		addColmToolItm.setToolTipText("Add Column");

		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		deleteColmToolItm = new ToolItem(toolBar, SWT.NONE);
		deleteColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_COLUMN_ICON));
		deleteColmToolItm.setToolTipText("Delete Column");

		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.SEPARATOR);

		moveColmLeftToolItm = new ToolItem(toolBar, SWT.NONE);
		moveColmLeftToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_LEFT_ICON));
		moveColmLeftToolItm.setToolTipText("Move Column Left");

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);

		moveColmRightToolItm = new ToolItem(toolBar, SWT.NONE);
		moveColmRightToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_RIGHT_ICON));
		moveColmRightToolItm.setToolTipText("Move Column Right");

		ToolItem toolItem_3 = new ToolItem(toolBar, SWT.SEPARATOR);

		renameColmToolItm = new ToolItem(toolBar, SWT.NONE);
		renameColmToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RENAME_ICON));
		renameColmToolItm.setToolTipText("Rename Column ");

		ToolItem toolItem_4 = new ToolItem(toolBar, SWT.SEPARATOR);

		addRowToolItm = new ToolItem(toolBar, SWT.NONE);
		addRowToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_ROW_ICON));
		addRowToolItm.setToolTipText("Add Row");

		ToolItem toolItem_5 = new ToolItem(toolBar, SWT.SEPARATOR);

		deleteRowToolItm = new ToolItem(toolBar, SWT.NONE);
		deleteRowToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ROW_ICON));
		deleteRowToolItm.setToolTipText("Delete Row");

		ToolItem toolItem_6 = new ToolItem(toolBar, SWT.SEPARATOR);

		moveRowUpToolItm = new ToolItem(toolBar, SWT.NONE);
		moveRowUpToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_UP_ICON));
		moveRowUpToolItm.setToolTipText("Move Row Up");

		ToolItem toolItem_7 = new ToolItem(toolBar, SWT.SEPARATOR);

		moveRowDownToolItm = new ToolItem(toolBar, SWT.NONE);
		moveRowDownToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_DOWN_ICON));
		moveRowDownToolItm.setToolTipText("Move Row Down");

		ToolItem toolItem_10 = new ToolItem(toolBar, SWT.SEPARATOR);

		saveToolItm = new ToolItem(toolBar, SWT.NONE);
		saveToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SAVE_ICON));
		saveToolItm.setToolTipText("Save");

		ToolItem toolItem_12 = new ToolItem(toolBar, SWT.SEPARATOR);

		refreshToolItm = new ToolItem(toolBar, SWT.NONE);
		refreshToolItm.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_ICON));
		refreshToolItm.setToolTipText("Refresh");

		dataRepositoryTable = new DataRepositoryTable(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI, this);
		dataRepositoryTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		dataRepositoryTable.setHeaderVisible(true);
		dataRepositoryTable.setLinesVisible(true);

		bottomFactory = new BottomFactoryDataRepoUi(composite, SWT.BORDER);
		bottomFactory.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		bottomFactory.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		colCount = dataRepositoryTable.getColumnCount();
		rowCount = dataRepositoryTable.getItemCount();
		addButtonListner();

		TabItem sourceCodeTabItem = new TabItem(tabFolder, SWT.NONE);
		sourceCodeTabItem.setText("SourceCode");
		sourceCodeTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		Composite sourceCodeHolder = new Composite(tabFolder, SWT.NONE);
		sourceCodeHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sourceCodeTabItem.setControl(sourceCodeHolder);
		sourceCodeHolder.setLayout(new FillLayout(SWT.HORIZONTAL));

		ArtifactCodeView codedFunctionView = new ArtifactCodeView(sourceCodeHolder, SWT.NONE, this, false);
		setCodedFunctionView(codedFunctionView);
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
				dataRepositoryTable.addDRRow();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		renameColmToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				dataRepositoryTable.renameDRColumn();
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

		saveToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				saveDR();
				dataRepositoryTable.renderAllDRDetails();
				getCodedFunctionView().refreshDRCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshToolItm.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (saveToolItm.getEnabled() == true) {
					boolean status = new MessageDialogs().openConfirmDialog("OpKey", "Do you want to save?");
					if (status) {
						saveDR();
					}
				}
				dataRepositoryTable.renderAllDRDetails();
				getCodedFunctionView().refreshDRCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void saveDR() {
		List<DRColumnAttributes> drColumns = dataRepositoryTable.getDrColumnAttributes();
		new DataRepositoryConstructApi().saveAllDRColumns(drColumns);
		toggleSaveButton(false);
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

	public void toggleSaveButton(boolean status) {
		this.saveToolItm.setEnabled(status);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ArtifactCodeView getCodedFunctionView() {
		return codedFunctionView;
	}

	public void setCodedFunctionView(ArtifactCodeView codedFunctionView) {
		this.codedFunctionView = codedFunctionView;
	}
}
