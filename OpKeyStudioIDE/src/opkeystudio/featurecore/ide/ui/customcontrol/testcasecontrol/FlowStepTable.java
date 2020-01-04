package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.io.IOException;
import java.sql.SQLException;
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
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class FlowStepTable extends CustomTable {
	private TestCaseView parentTestCaseView;

	public FlowStepTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public FlowStepTable(Composite parent, int style, TestCaseView parentView) {
		super(parent, style);
		init();
		this.setParentTestCaseView(parentView);
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

	private void addTestCaseTableEditor(FlowStepTableItem item) {
		FlowStep attrProperty = item.getFlowStepeData();
		TableEditor editor1 = getTableEditor();
		CustomButton button = new CustomButton(this, SWT.CHECK);
		button.setSelection(attrProperty.isShouldrun());
		button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				deselectAll();
				System.out.println("Selection "+button.getSelection());
				attrProperty.setShouldrun(button.getSelection());
				attrProperty.setModified(true);
				getParentTestCaseView().toggleSaveButton(true);
				setSelection(new TableItem[] { item });
				notifyListeners(SWT.Selection, null);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
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

	public void renderFlowSteps() throws JsonParseException, JsonMappingException, SQLException, IOException {
		disposeAllTableEditors();
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
		List<FlowStep> flowSteps = null;
		if (artifact.getFile_type_enum() == MODULETYPE.Flow) {
			FlowApi.getInstance().initAllFlowInputArguments();
			FlowApi.getInstance().initAllFlowOutputArguments();
			flowSteps = FlowApi.getInstance().getAllFlowSteps(artifactId);
		}
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			FunctionLibraryApi.getInstance().initAllFlowInputArguments();
			FunctionLibraryApi.getInstance().initAllFlowOutputArguments();
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
		// getParentTestCaseView().getSourceCodeEditor().transpileDatas();
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

	public void moveStepUp(FlowStep fstep1, FlowStep fstep2) {
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

	public void moveStepDown(FlowStep fstep1, FlowStep fstep2) {
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

	public void deleteStep(FlowStep flowStep)
			throws JsonParseException, JsonMappingException, SQLException, IOException {
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
