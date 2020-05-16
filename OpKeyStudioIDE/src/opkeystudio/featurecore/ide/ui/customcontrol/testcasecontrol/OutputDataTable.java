package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.featurecore.ide.ui.ui.superview.events.GlobalLoadListener;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

public class OutputDataTable extends CustomTable {
	public enum TABLE_TYPE {
		SELECTIONTABLE, INPUTTABLE
	};

	private FlowStep flowStep;
	private Keyword keyword;
	private List<FlowOutputArgument> flowOutputArgs = new ArrayList<FlowOutputArgument>();
	private List<ComponentOutputArgument> componentOutputArgs = new ArrayList<ComponentOutputArgument>();
	private TestCaseView parentTestCaseView;
	private TABLE_TYPE tableType;

	public OutputDataTable(Composite parent, int style, TestCaseView parentView, TABLE_TYPE tableType) {
		super(parent, style);
		this.setParentTestCaseView(parentView);
		setTableType(tableType);
		init();
		initGlobalLoadListener();
	}

	public OutputDataTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void initGlobalLoadListener() {
		this.addOpKeyGlobalLoadListener(new GlobalLoadListener() {

			@Override
			public void handleGlobalEvent() {
				if (getTableType() == TABLE_TYPE.SELECTIONTABLE) {
					renderOutPutTableAll(getFlowStep());
				}
				if (getTableType() == TABLE_TYPE.INPUTTABLE) {
					renderOutPutTableFlowStep(getFlowStep());
				}
			}
		});
	}

	public void clearAllData() {
		this.removeAll();
	}

	private void initForSelectionTable() {
		String[] tableHeaders = { "Output Variable Name", "Description" };
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
					column.setWidth((table_0.getBounds().width) / 2);
				}
			}
		});

		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (getSelection() == null) {
					return;
				}
				if (getSelection().length == 0) {
					return;
				}
				if (getSelection()[0] == null) {
					return;
				}

				CustomTableItem selectedItem = (CustomTableItem) getSelection()[0];
				FlowOutputArgument flowOutPitArgument = (FlowOutputArgument) selectedItem.getControlData();
				String flowOutputArgument = null;
				if (getParentTestCaseView().getArtifact().getFile_type_enum() == MODULETYPE.Component) {
					flowOutputArgument = flowOutPitArgument.getComponentstep_oa_id();
				}
				if (parentTestCaseView.getArtifact().getFile_type_enum() == MODULETYPE.Flow) {
					flowOutputArgument = flowOutPitArgument.getFlow_step_oa_id();
				}

				FlowInputArgument selectedFlowInputArgument = getParentTestCaseView().getInputDataTable()
						.getSelectedFlowInputArgument();
				if (selectedFlowInputArgument == null) {
					return;
				}
				new FlowApiUtilities().setFlowInputData(getParentTestCaseView().getArtifact(),
						selectedFlowInputArgument, flowOutputArgument, DataSource.ValueFromOutputArgument);

				selectedFlowInputArgument.setModified(true);
				try {
					getParentTestCaseView().getInputDataTable()
							.renderInputTable(getParentTestCaseView().getSelectedFlowStep());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				getParentTestCaseView().toggleSaveButton(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void init() {
		if (getTableType() == TABLE_TYPE.SELECTIONTABLE) {
			initForSelectionTable();
			return;
		}
		String[] tableHeaders = { "Type", "Parameter Name", "Output Variable" };
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
					column.setWidth((table_0.getBounds().width) / 3);
				}
			}
		});
		addControlEditor();
	}

	public void addControlEditor() {
		TableCursor cursor = new TableCursor(this, 0);
		ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		cursor.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				CustomTableItem row = (CustomTableItem) cursor.getRow();
				FlowOutputArgument flowOutputArgument = (FlowOutputArgument) row.getControlData();
				int selectedColumn = cursor.getColumn();
				Text text = new Text(cursor, 0);
				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						boolean unique = getParentTestCaseView().validateFlowOutPutVariableName(flowOutputArgument);
						if (unique == false) {
							String variableName = flowOutputArgument.getOutputvariablename();
							flowOutputArgument.setOutputvariablename("");
							renderOutPutTableFlowStep(getFlowStep());
							new MessageDialogs().openErrorDialog(getParentTestCaseView().getShell(), "OpKey",
									String.format("Output Variable Name '%s' Already Exists.", variableName));
						}
						text.dispose();
					}

					@Override
					public void focusGained(FocusEvent e) {

					}
				});

				text.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						flowOutputArgument.setOutputvariablename(text.getText());
						flowOutputArgument.setModified(true);
						getParentTestCaseView().toggleSaveButton(true);
						row.setText(selectedColumn, text.getText());
					}
				});

				if (selectedColumn == 2) {
					if (flowOutputArgument.getOutputvariablename() != null) {
						text.setText(flowOutputArgument.getOutputvariablename());
						editor.setEditor(text);
						text.setFocus();
					} else {
						text.setText("");
						editor.setEditor(text);
						text.setFocus();
					}
				} else {
					if (editor != null) {
						if (editor.getEditor() != null) {
							editor.getEditor().dispose();
						}
					}
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
	}

	public List<FlowOutputArgument> getFlowOutputArgs() {
		return flowOutputArgs;
	}

	public void setFlowOutputArgs(List<FlowOutputArgument> flowOutputArgs) {
		this.flowOutputArgs = flowOutputArgs;
	}

	private void initOutputTableArguments(FlowStep flowStep) {
		this.setFlowStep(flowStep);
		if (flowStep.getKeyword() != null) {
			setKeyword(flowStep.getKeyword());
		} else {
			setKeyword(null);
		}

		if (flowStep.getFunctionLibraryComponent() != null) {
			setComponentOutputArgs(flowStep.getFunctionLibraryComponent().getComponentOutputArguments());
		}
		setFlowOutputArgs(flowStep.getFlowOutputArgs());
	}

	public void renderOutPutTableFlowStep(FlowStep flowStep) {
		if (flowStep == null) {
			return;
		}
		this.initOutputTableArguments(flowStep);
		this.removeAll();
		for (int i = 0; i < flowOutputArgs.size(); i++) {
			FlowOutputArgument flowOutPutArg = getFlowOutputArgs().get(i);
			CustomTableItem cti = new CustomTableItem(this, 0);
			String outputType = "String";
			if (flowStep.getKeyword() != null) {
				outputType = flowStep.getKeyword().getOutputtype();
			}

			String parameterName = "Output";
			if (flowStep.getFunctionLibraryComponent() != null) {
				ComponentOutputArgument componentOut = getComponentOutputArgs().get(i);
				parameterName = componentOut.getName();
			}
			cti.setText(new String[] { outputType, parameterName, flowOutPutArg.getOutputvariablename() });
			cti.setControlData(flowOutPutArg);
			cti.setImage(1, ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OUTPUTDATA_ICON));
		}
	}

	private List<FlowOutputArgument> getAllFlowOutputArgumentsOFArtifact(FlowStep currentflowStep) {
		List<FlowOutputArgument> allFlowOutputArguments = new ArrayList<FlowOutputArgument>();
		List<FlowStep> flowSteps = getParentTestCaseView().getFlowStepTable().getFlowStepsData();
		for (FlowStep flowStep : flowSteps) {
			if (flowStep == currentflowStep) {
				continue;
			}
			allFlowOutputArguments.addAll(flowStep.getFlowOutputArgs());
		}
		return allFlowOutputArguments;
	}

	public void renderOutPutTableAll(FlowStep flowStep) {
		if (flowStep == null) {
			return;
		}
		this.removeAll();
		this.setFlowStep(flowStep);
		List<FlowOutputArgument> flowOutPutArgs = getAllFlowOutputArgumentsOFArtifact(flowStep);
		for (FlowOutputArgument flowOutPutArg : flowOutPutArgs) {
			if (flowOutPutArg.getOutputvariablename() != null) {
				CustomTableItem cti = new CustomTableItem(this, 0);
				cti.setText(new String[] { flowOutPutArg.getOutputvariablename(), "" });
				cti.setControlData(flowOutPutArg);
				cti.setImage(0, ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OUTPUTDATA_ICON));
			}
		}
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

	public TABLE_TYPE getTableType() {
		return tableType;
	}

	public void setTableType(TABLE_TYPE tableType) {
		this.tableType = tableType;
	}

	public FlowStep getFlowStep() {
		return flowStep;
	}

	public void setFlowStep(FlowStep flowStep) {
		this.flowStep = flowStep;
	}

	public List<ComponentOutputArgument> getComponentOutputArgs() {
		return componentOutputArgs;
	}

	public void setComponentOutputArgs(List<ComponentOutputArgument> componentOutputArgs) {
		this.componentOutputArgs = componentOutputArgs;
	}
}
