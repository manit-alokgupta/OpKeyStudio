package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.ResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.CopyPasteOperation;
import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.dtoMaker.FlowMaker;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class FlowStepTable extends CustomTable {
	private TestCaseView parentTestCaseView;

	private MenuItem openInNewTabMenuItem;
	private MenuItem copyMenuItem;
	private MenuItem pasteMenuItem;
	private MenuItem addStepMenuItem;
	private MenuItem deleteMenuItem;
	private MenuItem moveupMenuItem;
	private MenuItem movedownMenuItem;
	private MenuItem setToRunMenuItem;
	private MenuItem skipfromRunMenuItem;

	public FlowStepTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public FlowStepTable(Composite parent, int style, TestCaseView parentView) {
		super(parent, style);
		init();
		initContextMenu();
		this.setParentTestCaseView(parentView);
	}

	private void disableMenuItem() {
		openInNewTabMenuItem.setEnabled(false);
		copyMenuItem.setEnabled(false);
		pasteMenuItem.setEnabled(false);
		addStepMenuItem.setEnabled(false);
		deleteMenuItem.setEnabled(false);
		moveupMenuItem.setEnabled(false);
		movedownMenuItem.setEnabled(false);
		setToRunMenuItem.setEnabled(false);
		skipfromRunMenuItem.setEnabled(false);
	}

	public void toggleCopyMenuItem(boolean status) {
		this.copyMenuItem.setEnabled(status);
	}

	public void togglePasteMenuItem(boolean status) {
		this.pasteMenuItem.setEnabled(status);
	}

	public void toggleAddStepMenuItem(boolean status) {
		this.addStepMenuItem.setEnabled(status);
	}

	public void toggleDeleteMenuItem(boolean status) {
		this.deleteMenuItem.setEnabled(status);
	}

	public void toggleMoveUpMenuItem(boolean status) {
		this.moveupMenuItem.setEnabled(status);
	}

	public void toggleMoveDownMenuItem(boolean status) {
		this.movedownMenuItem.setEnabled(status);
	}

	public void toggleSetToRunMenuItem(boolean status) {
		this.setToRunMenuItem.setEnabled(status);
	}

	public void toggleSkipFromRunMenuItem(boolean status) {
		this.skipfromRunMenuItem.setEnabled(status);
	}

	public void toggleOpenInNewTabMenuItem(boolean status) {
		this.openInNewTabMenuItem.setEnabled(status);
	}

	private void initContextMenu() {
		Menu menu = new Menu(this);
		openInNewTabMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		copyMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		pasteMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		addStepMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		deleteMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		moveupMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		movedownMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		setToRunMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		skipfromRunMenuItem = new MenuItem(menu, 0);

		openInNewTabMenuItem.setText("Open In New Tab");
		copyMenuItem.setText("Copy");
		pasteMenuItem.setText("Paste");
		addStepMenuItem.setText("Add Step");
		deleteMenuItem.setText("Delete");
		moveupMenuItem.setText("Move Up");
		movedownMenuItem.setText("Move Down");
		setToRunMenuItem.setText("Set to Run");
		skipfromRunMenuItem.setText("Skip from Run");

		copyMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				FlowStep flowStep = getSelectedFlowStep();
				CopyPasteOperation.getInstance().setFlowStep(flowStep);
				toggleCopyMenuItem(false);
				togglePasteMenuItem(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});

		pasteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStep flowStep = CopyPasteOperation.getInstance().getFlowStep();
				if (flowStep != null) {
					FlowStep replicaFlowStep = new FlowMaker().createFlowStepReplica(
							getParentTestCaseView().getArtifact(), getSelectedFlowStep(), flowStep, getFlowStepsData());
					getFlowStepsData().add(replicaFlowStep);
					refreshFlowSteps();
					getParentTestCaseView().toggleSaveButton(true);
				}
				toggleCopyMenuItem(true);
				togglePasteMenuItem(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		skipfromRunMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStep flowStep = getSelectedFlowStep();
				if (flowStep == null) {
					return;
				}
				flowStep.setShouldrun(false);
				flowStep.setModified(true);
				refreshFlowSteps();
				getParentTestCaseView().toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});

		setToRunMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStep flowStep = getSelectedFlowStep();
				if (flowStep == null) {
					return;
				}
				flowStep.setShouldrun(true);
				flowStep.setModified(true);
				refreshFlowSteps();
				getParentTestCaseView().toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		movedownMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				moveStepDown();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		addStepMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addStep();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		deleteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					deleteStep();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		moveupMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				moveStepUp();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		openInNewTabMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStep flowStep = getSelectedFlowStep();
				if (flowStep == null) {
					return;
				}
				if (flowStep.getFunctionLibraryComponent() == null) {
					return;
				}
				Utilities.getInstance().openArtifacts(flowStep.getFunctionLibraryComponent());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		this.setMenu(menu);
		disableMenuItem();
		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStep flowStep = getSelectedFlowStep();
				if (flowStep == null) {
					disableMenuItem();
				} else {
					toggleCopyMenuItem(true);
					toggleSetToRunMenuItem(true);
					toggleSkipFromRunMenuItem(true);
					if (flowStep.getFunctionLibraryComponent() != null) {
						toggleOpenInNewTabMenuItem(true);
					} else {
						toggleOpenInNewTabMenuItem(false);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private void init() {
		String[] tableHeaders = { "#", "Keyword", "ORObject", "Input", "Output", "Description" };
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, SWT.LEFT);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (int i = 0; i < table_0.getColumnCount(); i++) {
					TableColumn column = table_0.getColumn(i);
					if (i == 0) {
						column.setWidth(50);
					} else {

						column.setWidth((table_0.getBounds().width - 50) / 5);
					}
				}
			}
		});
	}

	public void setFlowStepsData(List<FlowStep> flowSteps) {
		super.setControlData(flowSteps);
	}

	@SuppressWarnings("unchecked")
	public List<FlowStep> getFlowStepsData() {
		if (super.getControlData() == null) {
			return new ArrayList<>();
		}
		return (List<FlowStep>) super.getControlData();
	}

	public void addFlowSteps(FlowStep fs) {
		getFlowStepsData().add(fs);
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private void addStepIcons(FlowStepTableItem flowStepTableItem) {
		FlowStep flowStep = flowStepTableItem.getFlowStepeData();
		if (flowStep.getKeyword() != null) {
			flowStepTableItem.setImage(1,
					ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/keword.png"));
		} else if (flowStep.getFunctionLibraryComponent() != null) {
			flowStepTableItem.setImage(1,
					ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/functionlibrary.png"));
		}
		flowStepTableItem.setImage(2, ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/object.png"));
		flowStepTableItem.setImage(3,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/inputdata.png"));
		flowStepTableItem.setImage(4,
				ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/outputdata.png"));
		flowStepTableItem.setImage(5, ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/about.png"));
	}

	private void addTestCaseTableEditor(FlowStepTableItem item) {
		addStepIcons(item);
		FlowStep attrProperty = item.getFlowStepeData();
		TableEditor editor1 = getTableEditor();
		CustomButton button = new CustomButton(this, SWT.CHECK);
		button.setSelection(attrProperty.isShouldrun());
		button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				deselectAll();
				System.out.println("Selection " + button.getSelection());
				attrProperty.setShouldrun(button.getSelection());
				attrProperty.setModified(true);
				getParentTestCaseView().toggleSaveButton(true);
				setSelection(new TableItem[] { item });
				notifyListeners(SWT.Selection, null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		editor1.setEditor(button, item, 0);
		this.allTableEditors.add(editor1.getEditor());
	}

	private void disposeAllTableEditors() {
		for (Control controlEditor : this.allTableEditors) {
			controlEditor.dispose();
		}
	}

	public void renderFlowSteps() throws SQLException, IOException {
		disposeAllTableEditors();
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		if (artifact == null) {
			return;
		}
		String artifactId = artifact.getId();
		List<FlowStep> flowSteps = null;
		GlobalLoader.getInstance().initAllArguments();
		if (artifact.getFile_type_enum() == MODULETYPE.Flow) {
			flowSteps = FlowApi.getInstance().getAllFlowSteps(artifactId);
		}
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			flowSteps = FunctionLibraryApi.getInstance().getAllFlowSteps(artifactId);
		}
		setFlowStepsData(flowSteps);
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.isDeleted() == false) {
				String orname = "";
				String keyWordName = "";
				if (flowStep.getOrObject().size() > 0) {
					orname = flowStep.getOrObject().get(0).getName();
				}
				String keywordDescription = "";
				if (flowStep.getComment() != null) {
					keywordDescription = flowStep.getComment();
				} else if (flowStep.getComments() != null) {
					keywordDescription = flowStep.getComments();
				}
				if (flowStep.getKeyword() != null) {
					keyWordName = flowStep.getKeyword().getKeywordname();
					FlowStepTableItem flowTableItem = new FlowStepTableItem(this, 0);
					flowTableItem.setText(new String[] { "", keyWordName, orname, "",
							new FlowApiUtilities().getFlowOutPutArgumentsString(flowStep), keywordDescription });
					flowTableItem.setFlowStepData(flowStep);
					addTestCaseTableEditor(flowTableItem);
				}
				if (flowStep.getFunctionLibraryComponent() != null) {
					keyWordName = flowStep.getFunctionLibraryComponent().getName();
					FlowStepTableItem flowTableItem = new FlowStepTableItem(this, 0);
					flowTableItem.setText(new String[] { "", keyWordName, orname, "",
							new FlowApiUtilities().getFlowOutPutArgumentsString(flowStep), keywordDescription });
					flowTableItem.setFlowStepData(flowStep);
					addTestCaseTableEditor(flowTableItem);
				}
			}
		}
		selectDefaultRow();
		getParentTestCaseView().getSourceCodeEditor().transpileDatas();
	}

	public void refreshFlowSteps() {
		disposeAllTableEditors();
		this.removeAll();
		List<FlowStep> flowSteps = getFlowStepsData();
		Collections.sort(flowSteps);
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.isDeleted() == false) {
				String orname = "";
				String keyWordName = "";
				if (flowStep.getOrObject().size() > 0) {
					orname = flowStep.getOrObject().get(0).getName();
				}
				if (flowStep.getKeyword() != null) {
					keyWordName = flowStep.getKeyword().getKeywordname();
					String keywordDescription = "";
					if (flowStep.getComment() != null) {
						keywordDescription = flowStep.getComment();
					} else if (flowStep.getComments() != null) {
						keywordDescription = flowStep.getComments();
					}
					FlowStepTableItem flowTableItem = new FlowStepTableItem(this, 0);
					flowTableItem.setText(new String[] { "", keyWordName, orname, "", "", keywordDescription });
					flowTableItem.setFlowStepData(flowStep);
					addTestCaseTableEditor(flowTableItem);
				}
				if (flowStep.getFunctionLibraryComponent() != null) {
					String keywordDescription = "";
					if (flowStep.getComment() != null) {
						keywordDescription = flowStep.getComment();
					} else if (flowStep.getComments() != null) {
						keywordDescription = flowStep.getComments();
					}
					keyWordName = flowStep.getFunctionLibraryComponent().getName();
					FlowStepTableItem flowTableItem = new FlowStepTableItem(this, 0);
					flowTableItem.setText(new String[] { "", keyWordName, orname, "",
							new FlowApiUtilities().getFlowOutPutArgumentsString(flowStep), keywordDescription });
					flowTableItem.setFlowStepData(flowStep);
					addTestCaseTableEditor(flowTableItem);
				}
				if (flowStep.getKeyword() == null && flowStep.getFunctionLibraryComponent() == null) {
					if (flowStep.isNullKeyword()) {
						keyWordName = "";
						FlowStepTableItem flowTableItem = new FlowStepTableItem(this, 0);
						flowTableItem.setText(new String[] { "", keyWordName, orname, "",
								new FlowApiUtilities().getFlowOutPutArgumentsString(flowStep), "" });
						flowTableItem.setFlowStepData(flowStep);
						addTestCaseTableEditor(flowTableItem);
					}
				}
			}
		}
		selectDefaultRow();
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void moveStepUp() {
		FlowStep fstep1 = getSelectedFlowStep();
		FlowStep fstep2 = getPrevFlowStep();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = fstep1.getPosition();
		int fpos2 = fstep2.getPosition();

		fstep1.setPosition(fpos2);
		fstep2.setPosition(fpos1);
		refreshFlowSteps();
		selectRow(selectedIndex - 1);
		fstep1.setModified(true);
		fstep2.setModified(true);
		getParentTestCaseView().toggleSaveButton(true);

	}

	public void moveStepDown() {
		FlowStep fstep1 = getSelectedFlowStep();
		FlowStep fstep2 = getNextFlowStep();
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = fstep1.getPosition();
		int fpos2 = fstep2.getPosition();

		fstep1.setPosition(fpos2);
		fstep2.setPosition(fpos1);
		refreshFlowSteps();
		selectRow(selectedIndex + 1);
		fstep1.setModified(true);
		fstep2.setModified(true);
		getParentTestCaseView().toggleSaveButton(true);

	}

	public void addStep() {
		Artifact artifact = getParentTestCaseView().getArtifact();
		FlowStep selectedFlowStep = this.getSelectedFlowStep();
		FlowStep flowStep = new FlowMaker().getFlowStepDTO(artifact, selectedFlowStep, null, artifact.getId(),
				this.getFlowStepsData());
		this.getFlowStepsData().add(flowStep);
		this.refreshFlowSteps();
		getParentTestCaseView().toggleSaveButton(true);
	}

	public void deleteStep() throws JsonParseException, JsonMappingException, SQLException, IOException {
		String name = "";
		if (this.getSelectedFlowStep().getKeyword() != null) {
			name = this.getSelectedFlowStep().getKeyword().getKeywordname();
		} else if (this.getSelectedFlowStep().getFunctionLibraryComponent() != null) {
			name = this.getSelectedFlowStep().getFunctionLibraryComponent().getName();
		}
		boolean status = new MessageDialogs().openConfirmDialog("OpKey", "Do you want to delete '" + name + "'?");
		if (!status) {
			return;
		}
		FlowStep flowStep = getSelectedFlowStep();
		flowStep.setDeleted(true);
		refreshFlowSteps();
		getParentTestCaseView().toggleSaveButton(true);
	}

	public FlowStep getSelectedFlowStep() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		FlowStepTableItem flowTableItem = (FlowStepTableItem) this.getSelection()[0];
		return flowTableItem.getFlowStepeData();
	}

	public FlowStep getPrevFlowStep() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == 0) {
			return null;
		}
		selectedIndex = selectedIndex - 1;
		FlowStepTableItem flowTableItem = (FlowStepTableItem) this.getItem(selectedIndex);
		if (flowTableItem != null) {
			return flowTableItem.getFlowStepeData();
		}
		return null;
	}

	public FlowStep getNextFlowStep() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == this.getItemCount() - 1) {
			return null;
		}
		selectedIndex = selectedIndex + 1;
		FlowStepTableItem flowTableItem = (FlowStepTableItem) this.getItem(selectedIndex);
		if (flowTableItem != null) {
			return flowTableItem.getFlowStepeData();
		}
		return null;
	}

	public int addKeyword() {

		int lastPosition = getFlowStepsData().get(getFlowStepsData().size() - 1).getPosition();
		return lastPosition;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

}
