package opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.testsuite.TestSuiteApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;

public class SuiteStepTable extends CustomTable {

	private SuiteStepTable thisTable = this;
	private TestSuiteView parentTestSuiteView;
	private List<Control> allTableEditors = new ArrayList<Control>();

	public SuiteStepTable(Composite parent, int style, TestSuiteView parentView) {
		super(parent, style);
		init();
		this.setParentTestSuiteView(parentView);
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
				TestSuite prevTestSuite = getPrevTestSuite();
				TestSuite selectedTestSuite = getSelectedTestSuite();
				TestSuite nextTestSuite = getNextTestSuite();
				if (selectedTestSuite == null) {
					getParentTestSuiteView().toggleMoveUpButton(false);
					getParentTestSuiteView().toggleMoveDownButton(false);
					getParentTestSuiteView().toggleDeleteButton(false);
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
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void setTestSuiteData(List<TestSuite> testSuite) {
		super.setControlData(testSuite);
	}

	public List<TestSuite> getTestSuiteData() {
		return (List<TestSuite>) super.getControlData();
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(SuiteStepTableItem item) {
		TestSuite attrProperty = item.getTestSuiteData();
		TableEditor editor1 = getTableEditor();
		CustomButton button = new CustomButton(this, SWT.CHECK);
		button.setSelection(attrProperty.isShouldrun());
		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

			}

			@Override
			public void mouseDown(MouseEvent e) {
				thisTable.deselectAll();
				thisTable.setSelection(new TableItem[] { item });
				thisTable.notifyListeners(SWT.Selection, null);

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {

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

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void moveStepUp(TestSuite suiteStep1, TestSuite suiteStep2)
			throws JsonParseException, JsonMappingException, IOException {
		int selectedIndex = this.getSelectionIndex();
		int spos1 = suiteStep1.getPosition();
		int spos2 = suiteStep2.getPosition();

		suiteStep1.setPosition(spos2);
		suiteStep2.setPosition(spos1);
		refreshAllTestSuites();

		selectRow(selectedIndex - 1);
		suiteStep1.setModified(true);
		// getParentTestSuiteView().toggleSaveBtn(true);
	}

	public void moveStepDown(TestSuite suiteStep1, TestSuite suiteStep2)
			throws JsonParseException, JsonMappingException, IOException {
		int selectedIndex = this.getSelectionIndex();
		int spos1 = suiteStep1.getPosition();
		int spos2 = suiteStep2.getPosition();

		suiteStep1.setPosition(spos2);
		suiteStep2.setPosition(spos1);
		refreshAllTestSuites();

		selectRow(selectedIndex + 1);
		suiteStep1.setModified(true);
		suiteStep2.setModified(true);
		// getParentTestSuiteView().toggleSaveBtn(true);
	}

	public void deleteSuiteStep(TestSuite suiteStep) throws JsonParseException, JsonMappingException, IOException {
		suiteStep.setDeleted(true);
		refreshAllTestSuites();
	}

	public void renderAllTestSuites() throws JsonParseException, JsonMappingException, IOException {
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		List<TestSuite> testSuites = new TestSuiteApi().getAllTestSuitesStepsWithArtifact(artifact.getId());
		setTestSuiteData(testSuites);
		for (TestSuite testSuite : testSuites) {
			SuiteStepTableItem suiteStepTableItem = new SuiteStepTableItem(this, 0);
			suiteStepTableItem.setText(new String[] { "", "", testSuite.getArtifact().getName(), "", "" });
			suiteStepTableItem.setTestSuiteData(testSuite);
		}
	}

	public void refreshAllTestSuites() throws JsonParseException, JsonMappingException, IOException {
		this.removeAll();
		List<TestSuite> testSuites = getTestSuiteData();
		Collections.sort(testSuites);
		setTestSuiteData(testSuites);
		for (TestSuite testSuite : testSuites) {
			if (testSuite.isDeleted() == false) {
				SuiteStepTableItem suiteStepTableItem = new SuiteStepTableItem(this, 0);
				suiteStepTableItem.setText(new String[] { "", "", testSuite.getArtifact().getName(), "", "" });
				suiteStepTableItem.setTestSuiteData(testSuite);
			}
		}
	}

	public TestSuite getSelectedTestSuite() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		SuiteStepTableItem suiteStepTableItem = (SuiteStepTableItem) this.getSelection()[0];
		return suiteStepTableItem.getTestSuiteData();
	}

	public TestSuite getPrevTestSuite() {
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

	public TestSuite getNextTestSuite() {
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
