package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Files;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.CodedFunctionView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;

public class CodedFunctionBottomFactoryUI extends Composite {

	private StyledText consoleLogTextView;

	private CFLLibraryAssociateTable associateLibraries;
	private CFLOrAssociate associateor;
	private CFLDRAssociate associatedr;
	private CFLInputTable inputTable;
	private CFLOutputTable outputTable;
	private TabItem inputTabItem;
	private ToolItem addInputItem;
	private ToolItem deleteInputItem;
	private ToolItem moveUpInputItem;
	private ToolItem moveDownInputItem;
	private ToolItem refreshInputItem;

	private TabItem outputTabItem;
	private ToolItem addOutputItem;
	private ToolItem deleteOutputItem;
	private ToolItem moveUpOutputItem;
	private ToolItem moveDownOutputItem;
	private ToolItem refreshOutputItem;

	private ToolItem refreshORTable;
	private ToolItem refreshDRTable;
	private ToolItem addLibrary;
	private ToolItem deleteLibrary;

	private Display display;

	private CodedFunctionView parentCodedView;
	private CFLCompilationResultTable compilationResultsTable;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws SQLException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unused")
	public CodedFunctionBottomFactoryUI(Composite parent, int style, CodedFunctionView parentTestCaseView) {
		super(parent, style);
		setParentCodedFunctionView(parentTestCaseView);
		display = getParent().getDisplay();
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite.setLayout(new GridLayout(1, false));

		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));

		ExpandItem item = new ExpandItem(expandBar, SWT.NONE);
		item.setText("Coded FL Arguments");
		item.setHeight(200);

		Group grpMenu = new Group(expandBar, SWT.NONE);
		grpMenu.setText("Menu");
		item.setControl(grpMenu);
		grpMenu.setLayout(new GridLayout(1, false));

		TabFolder tabFolder = new TabFolder(grpMenu, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setBounds(0, 0, 120, 43);
		tabFolder.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		TabItem associateLibrariesTagItem = new TabItem(tabFolder, SWT.NONE);
		associateLibrariesTagItem.setText("Associate Libraries");
		associateLibrariesTagItem.setToolTipText("Associate Libraries");

		Composite composite_7 = new Composite(tabFolder, SWT.NONE);
		associateLibrariesTagItem.setControl(composite_7);
		composite_7.setLayout(new GridLayout(1, false));
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_1 = new ToolBar(composite_7, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar_1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		addLibrary = new ToolItem(toolBar_1, SWT.NONE);
		addLibrary.setWidth(27);
		addLibrary.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		addLibrary.setToolTipText("Add 2");
		ToolItem toolItem1 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		deleteLibrary = new ToolItem(toolBar_1, SWT.NONE);
		deleteLibrary.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteLibrary.setToolTipText("Delete");
		deleteLibrary.setEnabled(false);

		associateLibraries = new CFLLibraryAssociateTable(composite_7, SWT.BORDER | SWT.FULL_SELECTION, this);
//		tagsTable = new Table(composite_7, SWT.BORDER | SWT.FULL_SELECTION);
		associateLibraries.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		associateLibraries.setHeaderVisible(true);
		associateLibraries.setLinesVisible(true);

		TabItem associateORTabItem = new TabItem(tabFolder, SWT.NONE);
		associateORTabItem.setText("Associate OR");
		associateORTabItem.setToolTipText("Associate OR");

		Composite associateORHolder = new Composite(tabFolder, SWT.NONE);
		associateORTabItem.setControl(associateORHolder);
		associateORHolder.setLayout(new GridLayout(1, false));
		associateORHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_3 = new ToolBar(associateORHolder, SWT.FLAT | SWT.RIGHT);
		toolBar_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		refreshORTable = new ToolItem(toolBar_3, SWT.NONE);
		refreshORTable.setToolTipText("Refresh");
		refreshORTable.setText("Refresh");
		refreshORTable.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));

		associateor = new CFLOrAssociate(associateORHolder, SWT.BORDER | SWT.FULL_SELECTION, this);
		associateor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		associateor.setHeaderVisible(true);
		associateor.setLinesVisible(true);

		TabItem associateDRTabItem = new TabItem(tabFolder, SWT.NONE);
		associateDRTabItem.setText("Associate DR");
		associateDRTabItem.setToolTipText("Associate DR");

		Composite associateDRHolder = new Composite(tabFolder, SWT.NONE);
		associateDRTabItem.setControl(associateDRHolder);
		associateDRHolder.setLayout(new GridLayout(1, false));
		associateDRHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_DR_3 = new ToolBar(associateDRHolder, SWT.FLAT | SWT.RIGHT);
		toolBar_DR_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_DR_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		refreshDRTable = new ToolItem(toolBar_DR_3, SWT.NONE);
		refreshDRTable.setToolTipText("Refresh");
		refreshDRTable.setText("Refresh");
		refreshDRTable.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));

		associatedr = new CFLDRAssociate(associateDRHolder, SWT.BORDER | SWT.FULL_SELECTION, this);
		associatedr.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		associatedr.setHeaderVisible(true);
		associatedr.setLinesVisible(true);

		/*
		 * inputTabItem = new TabItem(tabFolder, SWT.NONE);
		 * inputTabItem.setText("Input"); inputTabItem.setToolTipText("Output");
		 * 
		 * Composite composite_11 = new Composite(tabFolder, SWT.NONE);
		 * inputTabItem.setControl(composite_11); composite_11.setLayout(new
		 * GridLayout(1, false));
		 * 
		 * ToolBar inputTabToolBar = new ToolBar(composite_11, SWT.FLAT | SWT.RIGHT);
		 * inputTabToolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
		 * 1, 1));
		 * inputTabToolBar.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		 * 
		 * addInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		 * addInputItem.setToolTipText("Add");
		 * addInputItem.setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/add_icon.png"));
		 * 
		 * ToolItem toolItem = new ToolItem(inputTabToolBar, SWT.SEPARATOR);
		 * 
		 * deleteInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		 * deleteInputItem.setToolTipText("Delete");
		 * deleteInputItem.setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/delete_icon.png")); deleteInputItem.setEnabled(false);
		 * 
		 * ToolItem toolItem_1 = new ToolItem(inputTabToolBar, SWT.SEPARATOR);
		 * 
		 * moveUpInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		 * moveUpInputItem.setToolTipText("Move Up");
		 * moveUpInputItem.setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/moveup_icon.png")); moveUpInputItem.setEnabled(false);
		 * 
		 * ToolItem toolItem_2 = new ToolItem(inputTabToolBar, SWT.SEPARATOR);
		 * 
		 * moveDownInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		 * moveDownInputItem.setToolTipText("Move Down"); moveDownInputItem
		 * .setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/movedown_icon.png"));
		 * moveDownInputItem.setEnabled(false);
		 * 
		 * ToolItem toolItem_3 = new ToolItem(inputTabToolBar, SWT.SEPARATOR);
		 * 
		 * refreshInputItem = new ToolItem(inputTabToolBar, SWT.NONE);
		 * refreshInputItem.setToolTipText("Refresh"); refreshInputItem
		 * .setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/refresh_icon.png"));
		 * 
		 * inputTable = new CFLInputTable(composite_11, SWT.BORDER | SWT.FULL_SELECTION,
		 * this); // inputTable = new Table(composite_11, SWT.BORDER |
		 * SWT.FULL_SELECTION); inputTable.setLayoutData(new GridData(SWT.FILL,
		 * SWT.FILL, true, true, 1, 1)); inputTable.setHeaderVisible(true);
		 * inputTable.setLinesVisible(true);
		 */

		/*
		 * outputTabItem = new TabItem(tabFolder, SWT.NONE);
		 * outputTabItem.setText("Output"); outputTabItem.setToolTipText("Output");
		 * 
		 * Composite composite_12 = new Composite(tabFolder, SWT.NONE);
		 * outputTabItem.setControl(composite_12); composite_12.setLayout(new
		 * GridLayout(1, false));
		 * 
		 * ToolBar outputTabToolBar = new ToolBar(composite_12, SWT.FLAT | SWT.RIGHT);
		 * outputTabToolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
		 * 1, 1));
		 * outputTabToolBar.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		 * 
		 * addOutputItem = new ToolItem(outputTabToolBar, SWT.NONE);
		 * addOutputItem.setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/add_icon.png")); addOutputItem.setToolTipText("Add");
		 * 
		 * ToolItem toolItem_4 = new ToolItem(outputTabToolBar, SWT.SEPARATOR);
		 * 
		 * deleteOutputItem = new ToolItem(outputTabToolBar, SWT.NONE); deleteOutputItem
		 * .setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/delete_icon.png"));
		 * deleteOutputItem.setToolTipText("Delete");
		 * deleteOutputItem.setEnabled(false);
		 * 
		 * ToolItem toolItem_5 = new ToolItem(outputTabToolBar, SWT.SEPARATOR);
		 * 
		 * moveUpOutputItem = new ToolItem(outputTabToolBar, SWT.NONE); moveUpOutputItem
		 * .setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/moveup_icon.png"));
		 * moveUpOutputItem.setToolTipText("Move Up");
		 * moveUpOutputItem.setEnabled(false);
		 * 
		 * ToolItem toolItem_6 = new ToolItem(outputTabToolBar, SWT.SEPARATOR);
		 * 
		 * moveDownOutputItem = new ToolItem(outputTabToolBar, SWT.NONE);
		 * moveDownOutputItem .setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/movedown_icon.png"));
		 * moveDownOutputItem.setToolTipText("Move Down");
		 * moveDownOutputItem.setEnabled(false);
		 * 
		 * ToolItem toolItem_7 = new ToolItem(outputTabToolBar, SWT.SEPARATOR);
		 * 
		 * refreshOutputItem = new ToolItem(outputTabToolBar, SWT.NONE);
		 * refreshOutputItem.setToolTipText("Refresh"); refreshOutputItem
		 * .setImage(ResourceManager.getPluginImage("OpKeyStudio",
		 * "icons/testcase_icons/refresh_icon.png"));
		 * 
		 * outputTable = new CFLOutputTable(composite_12, SWT.BORDER |
		 * SWT.FULL_SELECTION, this); // outputTable = new Table(composite_12,
		 * SWT.BORDER | SWT.FULL_SELECTION); outputTable.setLayoutData(new
		 * GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		 * outputTable.setHeaderVisible(true); outputTable.setLinesVisible(true);
		 */
		TabItem compilationResultsTabItem = new TabItem(tabFolder, SWT.NONE);
		compilationResultsTabItem.setText("Compilation Results");

		Composite compilationResultsComposite = new Composite(tabFolder, SWT.NONE);
		compilationResultsTabItem.setControl(compilationResultsComposite);
		compilationResultsComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		compilationResultsTable = new CFLCompilationResultTable(compilationResultsComposite,
				SWT.BORDER | SWT.FULL_SELECTION, this);
		compilationResultsTable.setHeaderVisible(true);
		compilationResultsTable.setLinesVisible(true);

		TabItem consoleLogTabItem = new TabItem(tabFolder, SWT.NONE);
		consoleLogTabItem.setText("Console");

		Composite consoleLogComposite = new Composite(tabFolder, SWT.NONE);
		consoleLogTabItem.setControl(consoleLogComposite);
		consoleLogComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		consoleLogTextView = new StyledText(consoleLogComposite, SWT.BORDER | SWT.V_SCROLL);

		consoleLogTextView.setEditable(false);

		expandBar.addListener(SWT.Expand, new Listener() {

			@Override
			public void handleEvent(Event event) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						parent.layout();
					}
				});
			}
		});
		expandBar.addListener(SWT.Collapse, new Listener() {

			@Override
			public void handleEvent(Event event) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						parent.layout();
					}
				});
			}
		});

		/*
		 * outputTable.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } }); inputTable.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 */

		addButtonListeners();
	}

	public void toggleMoveUpButton(boolean status) {
		moveUpInputItem.setEnabled(status);
		moveUpOutputItem.setEnabled(status);
	}

	public void toggleMoveDownButton(boolean status) {
		moveDownInputItem.setEnabled(status);
		moveDownOutputItem.setEnabled(status);
	}

	public void toggleRefreshInputButton(boolean status) {
		refreshInputItem.setEnabled(status);
	}

	public void toggleDeleteButton(boolean status) {
		deleteLibrary.setEnabled(status);
	}

	public void addButtonListeners() {
		/*
		 * addOutputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 * 
		 * addInputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 * 
		 * moveUpInputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 * 
		 * moveDownInputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 * 
		 * deleteOutputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } }); deleteInputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * toggleDeleteButton(false); }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 * 
		 * moveUpOutputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 * 
		 * moveDownOutputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 * 
		 * deleteOutputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 * 
		 * refreshOutputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 * 
		 * refreshInputItem.addSelectionListener(new SelectionListener() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) {
		 * 
		 * } });
		 */

		refreshORTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				associateor.renderORNodes();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshDRTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				associatedr.renderDRNodes();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		addLibrary.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] filterExt = { "*.jar" };
				FileDialog dialog = new FileDialog(parentCodedView.getShell(), SWT.OPEN);
				dialog.setFilterExtensions(filterExt);
				dialog.open();
				String filePath = dialog.getFilterPath() + "\\" + dialog.getFileName();
				if (filePath != null) {
					String associatedLibsDir = getParentCodedFunctionView().getArtifactAssociatedLibraryPath();
					File libraryToAssociate = new File(filePath);
					File destinationDir = new File(associatedLibsDir + File.separator + libraryToAssociate.getName());
					try {
						FileUtils.copyFile(libraryToAssociate, destinationDir);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					new CodedFunctionApi().addLibraryFileInDb(getParentCodedFunctionView().getArtifact(),
							libraryToAssociate);
					associateLibraries.renderAssociatedLibraries();
					getParentCodedFunctionView().refreshIntellisense(false);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		associateLibraries.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomTableItem item = associateLibraries.getSelectedLibrary();
				if (item != null) {
					deleteLibrary.setEnabled(true);
				} else {
					deleteLibrary.setEnabled(false);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteLibrary.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Deleting...");
				CustomTableItem item = associateLibraries.getSelectedLibrary();
				if (item != null) {
					File file = (File) item.getControlData();
					System.out.println("Deleting File "+file.getAbsolutePath());
					if (file.exists()) {
						Files.delete(file);
						associateLibraries.renderAssociatedLibraries();
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void refreshBottomFactory() throws JsonParseException, JsonMappingException, IOException, SQLException {

	}

	public void save() {

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public CodedFunctionView getParentCodedFunctionView() {
		return parentCodedView;
	}

	public void setParentCodedFunctionView(CodedFunctionView parentTestCaseView) {
		this.parentCodedView = parentTestCaseView;
	}

	public CFLCompilationResultTable getCompilationResultTable() {
		return this.compilationResultsTable;
	}

	public StyledText getConsoleLogTextView() {
		return consoleLogTextView;
	}

	public void setConsoleLogTextView(StyledText consoleLogTextView) {
		this.consoleLogTextView = consoleLogTextView;
	}
}
