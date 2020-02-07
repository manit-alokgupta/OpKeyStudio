package opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol;

import java.io.IOException;
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

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.testsuite.TestSuiteApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuiteStep;

public class SuiteStepTable extends CustomTable {

	private TestSuiteView parentTestSuiteView;
	private List<Control> allTableEditors = new ArrayList<Control>();
	private MenuItem copyMenuItem;
	private MenuItem pasteMenuItem;
	private MenuItem openInNewTab;
	private MenuItem deleteMenuItem;
	private MenuItem moveupMenuItem;
	private MenuItem movedownMenuItem;
	private MenuItem setToRunMenuItem;
	private MenuItem skipfromRunMenuItem;

	public SuiteStepTable(Composite parent, int style, TestSuiteView parentView) {
		super(parent, style);
		init();
		initContextMenu();
		this.setParentTestSuiteView(parentView);
	}

	private void disableMenuItem() {
		copyMenuItem.setEnabled(false);
		pasteMenuItem.setEnabled(false);
		openInNewTab.setEnabled(false);
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
		this.openInNewTab.setEnabled(status);
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

	private void initContextMenu() {
		Menu menu = new Menu(this);

		openInNewTab = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		copyMenuItem = new MenuItem(menu, 0);
		new MenuItem(menu, SWT.SEPARATOR);
		pasteMenuItem = new MenuItem(menu, 0);

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

		openInNewTab.setText("Open In New Tab");
		copyMenuItem.setText("Copy");
		pasteMenuItem.setText("Paste");
		deleteMenuItem.setText("Delete");
		moveupMenuItem.setText("Move Up");
		movedownMenuItem.setText("Move Down");
		setToRunMenuItem.setText("Set to Run");
		skipfromRunMenuItem.setText("Skip from Run");

		copyMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

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
				TestSuiteStep flowStep = getSelectedTestSuite();
				if (flowStep == null) {
					return;
				}
				flowStep.setShouldrun(false);
				flowStep.setModified(true);
				refreshAllTestSuites();
				getParentTestSuiteView().toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		setToRunMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TestSuiteStep flowStep = getSelectedTestSuite();
				if (flowStep == null) {
					return;
				}
				flowStep.setShouldrun(true);
				flowStep.setModified(true);
				refreshAllTestSuites();
				getParentTestSuiteView().toggleSaveButton(true);
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
		openInNewTab.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TestSuiteStep selectedSuiteStep = getSelectedTestSuite();
				Utilities.getInstance().openArtifacts(selectedSuiteStep.getArtifact());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					deleteSuiteStep();
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

		moveupMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				moveStepUp();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		this.setMenu(menu);
		disableMenuItem();
		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TestSuiteStep flowStep = getSelectedTestSuite();
				if (flowStep == null) {
					disableMenuItem();
				} else {
					toggleCopyMenuItem(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void init() {

		String[] tableHeaders = { "#", " ", "Test Case/Gherkin", "Local Data Repository", "Data Repository Tree" };

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
			public void paintControl(PaintEvent e) {
				Table table_0 = (Table) e.getSource();
				for (int i = 0; i < table_0.getColumnCount(); i++) {
					TableColumn column = table_0.getColumn(i);
					if (i == 0) {
						column.setWidth(50);
					} else if (i == 1) {
						column.setWidth(50);
					} else {
						column.setWidth((table_0.getBounds().width - 100) / 3);
					}
				}

			}
		});

		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TestSuiteStep prevTestSuite = getPrevTestSuite();
				TestSuiteStep selectedTestSuite = getSelectedTestSuite();
				TestSuiteStep nextTestSuite = getNextTestSuite();
				if (selectedTestSuite == null) {
					getParentTestSuiteView().toggleMoveUpButton(false);
					getParentTestSuiteView().toggleMoveDownButton(false);
					getParentTestSuiteView().toggleDeleteButton(false);
					toggleSetToRunMenuItem(false);
					toggleAddStepMenuItem(false);
					toggleSkipFromRunMenuItem(false);
				}
				if (prevTestSuite == null) {
					getParentTestSuiteView().toggleMoveUpButton(false);
					getParentTestSuiteView().toggleMoveDownButton(true);
					getParentTestSuiteView().toggleDeleteButton(true);
				}
				if (nextTestSuite == null) {
					getParentTestSuiteView().toggleMoveUpButton(true);
					getParentTestSuiteView().toggleMoveDownButton(false);
					getParentTestSuiteView().toggleDeleteButton(true);
				}
				if (prevTestSuite == null && nextTestSuite == null) {
					getParentTestSuiteView().toggleMoveUpButton(false);
					getParentTestSuiteView().toggleMoveDownButton(false);
					getParentTestSuiteView().toggleDeleteButton(true);
				}
				if (selectedTestSuite != null) {
					toggleSetToRunMenuItem(true);
					toggleAddStepMenuItem(true);
					toggleSkipFromRunMenuItem(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void setTestSuiteData(List<TestSuiteStep> testSuite) {
		super.setControlData(testSuite);
	}

	@SuppressWarnings("unchecked")
	public List<TestSuiteStep> getTestSuiteData() {
		return (List<TestSuiteStep>) super.getControlData();
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(SuiteStepTableItem item) {
		TestSuiteStep testSuiteStep = item.getTestSuiteData();
		TableEditor editor1 = getTableEditor();
		CustomButton button = new CustomButton(this, SWT.CHECK);
		if (testSuiteStep.getArtifact().getFile_type_enum() == MODULETYPE.Flow) {
			item.setImage(0, ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/testcase.png"));
		}
		button.setSelection(testSuiteStep.isShouldrun());
		button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				testSuiteStep.setShouldrun(button.getSelection());
				testSuiteStep.setModified(true);
				deselectAll();
				setSelection(new TableItem[] { item });
				notifyListeners(SWT.Selection, null);
				getParentTestSuiteView().toggleSaveButton(true);
				getParentTestSuiteView().getSuiteStepTable().refreshAllTestSuites();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		editor1.setEditor(button, item, 1);
		this.allTableEditors.add(editor1.getEditor());
	}

	private void disposeAllTableEditors() {
		for (Control controlEditor : this.allTableEditors) {
			controlEditor.dispose();
		}
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void moveStepUp() {
		TestSuiteStep suiteStep1 = getSelectedTestSuite();
		TestSuiteStep suiteStep2 = getPrevTestSuite();
		int selectedIndex = this.getSelectionIndex();
		int spos1 = suiteStep1.getPosition();
		int spos2 = suiteStep2.getPosition();

		suiteStep1.setPosition(spos2);
		suiteStep2.setPosition(spos1);
		refreshAllTestSuites();

		selectRow(selectedIndex - 1);
		suiteStep1.setModified(true);
		getParentTestSuiteView().toggleSaveButton(true);
	}

	public void moveStepDown() {
		TestSuiteStep suiteStep1 = getSelectedTestSuite();
		TestSuiteStep suiteStep2 = getNextTestSuite();
		int selectedIndex = this.getSelectionIndex();
		int spos1 = suiteStep1.getPosition();
		int spos2 = suiteStep2.getPosition();

		suiteStep1.setPosition(spos2);
		suiteStep2.setPosition(spos1);
		refreshAllTestSuites();

		selectRow(selectedIndex + 1);
		suiteStep1.setModified(true);
		suiteStep2.setModified(true);
		getParentTestSuiteView().toggleSaveButton(true);
	}

	public void deleteSuiteStep() throws JsonParseException, JsonMappingException, IOException {
		boolean status = new MessageDialogs().openConfirmDialog("OpKey",
				"Do you want to delete '" + this.getSelectedTestSuite().getArtifact().getName() + "'?");
		if (!status) {
			return;
		}
		TestSuiteStep suiteStep = getSelectedTestSuite();
		suiteStep.setDeleted(true);
		refreshAllTestSuites();
		getParentTestSuiteView().toggleSaveButton(true);
	}

	public void renderAllTestSuites() throws JsonParseException, JsonMappingException, IOException {
		disposeAllTableEditors();
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		List<TestSuiteStep> testSuites = new TestSuiteApi().getAllTestSuitesStepsWithArtifact(artifact.getId());
		setTestSuiteData(testSuites);
		for (TestSuiteStep testSuite : testSuites) {
			SuiteStepTableItem suiteStepTableItem = new SuiteStepTableItem(this, 0);
			suiteStepTableItem.setText(new String[] { "", "", testSuite.getArtifact().getName(), "", "" });
			suiteStepTableItem.setTestSuiteData(testSuite);
			addTableEditor(suiteStepTableItem);
		}
		selectDefaultRow();
	}

	public void refreshAllTestSuites() {
		disposeAllTableEditors();
		this.removeAll();
		List<TestSuiteStep> testSuites = getTestSuiteData();
		Collections.sort(testSuites);
		setTestSuiteData(testSuites);
		for (TestSuiteStep testSuite : testSuites) {
			if (testSuite.isDeleted() == false) {
				SuiteStepTableItem suiteStepTableItem = new SuiteStepTableItem(this, 0);
				suiteStepTableItem.setText(new String[] { "", "", testSuite.getArtifact().getName(), "", "" });
				suiteStepTableItem.setTestSuiteData(testSuite);
				addTableEditor(suiteStepTableItem);
			}
		}
		selectDefaultRow();
	}

	public TestSuiteStep getSelectedTestSuite() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		SuiteStepTableItem suiteStepTableItem = (SuiteStepTableItem) this.getSelection()[0];
		return suiteStepTableItem.getTestSuiteData();
	}

	public TestSuiteStep getPrevTestSuite() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == 0) {
			return null;
		}
		selectedIndex = selectedIndex - 1;
		SuiteStepTableItem suiteStepTableItem = (SuiteStepTableItem) this.getItem(selectedIndex);
		if (suiteStepTableItem != null) {
			return suiteStepTableItem.getTestSuiteData();
		}
		return null;
	}

	public TestSuiteStep getNextTestSuite() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == this.getItemCount() - 1) {
			return null;
		}
		selectedIndex = selectedIndex + 1;
		SuiteStepTableItem suiteStepTableItem = (SuiteStepTableItem) this.getItem(selectedIndex);
		if (suiteStepTableItem != null) {
			return suiteStepTableItem.getTestSuiteData();
		}
		return null;
	}

	public TestSuiteView getParentTestSuiteView() {
		return parentTestSuiteView;
	}

	public void setParentTestSuiteView(TestSuiteView parentTestSuiteView) {
		this.parentTestSuiteView = parentTestSuiteView;
	}

}
