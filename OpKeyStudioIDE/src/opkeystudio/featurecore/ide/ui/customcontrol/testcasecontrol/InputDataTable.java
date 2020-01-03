package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

public class InputDataTable extends CustomTable {
	private List<KeyWordInputArgument> keyWordInputArgs = new ArrayList<KeyWordInputArgument>();
	private List<FlowInputArgument> flowInputArgs = new ArrayList<FlowInputArgument>();
	private List<ComponentInputArgument> componentInputArgs = new ArrayList<>();
	private TestCaseView parentTestCaseView;

	public InputDataTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public InputDataTable(Composite parent, int style, TestCaseView parentView) {
		super(parent, style);
		init();
		addControlEditor();
		this.setParentTestCaseView(parentView);
	}

	public InputDataTable getCurrentInstance() {
		return this;
	}

	private void init() {
		String[] tableHeaders = { "Type", "Name", "Provide Data", " " };
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
					column.setWidth((table_0.getBounds().width) / 4);
				}
			}
		});
	}

	public void addControlEditor() {
		TableCursor cursor = new TableCursor(this, 0);
		ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomTableItem row = (CustomTableItem) cursor.getRow();
				FlowInputArgument flowInputArgument = (FlowInputArgument) row.getControlData();
				int selectedColumn = cursor.getColumn();
				Text text = new Text(cursor, 0);
				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						text.dispose();
					}

					@Override
					public void focusGained(FocusEvent e) {

					}
				});

				text.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						if (getParentTestCaseView().getArtifact().getFile_type_enum() == MODULETYPE.Component) {
							flowInputArgument.setArg_datasource(DataSource.StaticValue);
						} else {
							flowInputArgument.setDatasource(DataSource.StaticValue);
						}

						flowInputArgument.setStaticvalue(text.getText());
						flowInputArgument.setModified(true);
						getParentTestCaseView().toggleSaveButton(true);
						row.setText(selectedColumn, text.getText());
					}
				});

				if (selectedColumn == 2) {
					if (flowInputArgument.getStaticvalue() != null) {
						text.setText(flowInputArgument.getStaticvalue());
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
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private void addInputTableEditor(CustomTableItem item)
			throws JsonParseException, JsonMappingException, IOException {
		FlowInputArgument flowInputArgument = (FlowInputArgument) item.getControlData();
		DataSource dataSourceType = null;
		if (getParentTestCaseView().getArtifact().getFile_type_enum() == MODULETYPE.Component) {
			dataSourceType = flowInputArgument.getArg_datasource();
		} else {
			dataSourceType = flowInputArgument.getDatasource();
		}
		if (dataSourceType == DataSource.ValueFromGlobalVariable) {
			String gv_id = flowInputArgument.getGlobalvariable_id();
			GlobalVariable globalVar = new GlobalVariableApi().getGlobalVariable(gv_id);
			TableEditor editor1 = getTableEditor();
			CustomButton button = new CustomButton(this, SWT.NONE | SWT.NO_BACKGROUND);
			button.setText(globalVar.getName());
			button.setToolTipText(globalVar.getName());
			button.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/global_variable.png"));
			button.setBackground(new Color(button.getDisplay(), 255, 255, 255));
			button.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseDown(MouseEvent e) {
					getParentTestCaseView().getDatasTabHolder().setSelection(2);
				}

				@Override
				public void mouseDoubleClick(MouseEvent e) {
					editor1.getEditor().dispose();
				}
			});
			editor1.setEditor(button, item, 2);
			this.allTableEditors.add(editor1.getEditor());
		}

		if (dataSourceType == DataSource.ValueFromDataRepository) {
			String dataRepositoryColumnId = flowInputArgument.getDatarepositorycolumnid();
			DRColumnAttributes drColumn = new DataRepositoryApi().getDRColumn(dataRepositoryColumnId);
			TableEditor editor1 = getTableEditor();
			CustomButton button = new CustomButton(this, SWT.NONE);
			button.setText(drColumn.getName());
			button.setToolTipText(drColumn.getName());
			button.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/global_variable.png"));
			button.setBackground(new Color(button.getDisplay(), 255, 255, 255));
			button.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseDown(MouseEvent e) {
					getParentTestCaseView().getDatasTabHolder().setSelection(0);
				}

				@Override
				public void mouseDoubleClick(MouseEvent e) {
					editor1.getEditor().dispose();
				}
			});
			editor1.setEditor(button, item, 2);
			this.allTableEditors.add(editor1.getEditor());
		}

		if (dataSourceType == DataSource.ValueFromOutputArgument) {
			String flow_step_oa_id = flowInputArgument.getFlow_step_oa_id();
			if (flow_step_oa_id == null) {
				return;
			}
			List<FlowOutputArgument> flowOutPutArguments = new FlowApiUtilities()
					.getAllFlowOutPutArgument(flow_step_oa_id);
			if (flowOutPutArguments.size() == 0) {
				return;
			}
			FlowOutputArgument flowOutputArgument = flowOutPutArguments.get(0);
			TableEditor editor1 = getTableEditor();
			CustomButton button = new CustomButton(this, SWT.NONE);
			button.setText(flowOutputArgument.getOutputvariablename());
			button.setToolTipText(flowOutputArgument.getOutputvariablename());
			button.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/global_variable.png"));
			button.setBackground(new Color(button.getDisplay(), 255, 255, 112));
			button.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseDown(MouseEvent e) {
					getParentTestCaseView().getDatasTabHolder().setSelection(1);
				}

				@Override
				public void mouseDoubleClick(MouseEvent e) {
					editor1.getEditor().dispose();
				}
			});
			editor1.setEditor(button, item, 2);
			this.allTableEditors.add(editor1.getEditor());
		}

	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void disposeAllTableEditors() {
		for (Control controlEditor : this.allTableEditors) {
			controlEditor.dispose();
		}
	}

	public List<KeyWordInputArgument> getKeyWordInputArgs() {
		return keyWordInputArgs;
	}

	public void setKeyWordInputArgs(List<KeyWordInputArgument> keyWordInputArgs) {
		this.keyWordInputArgs = keyWordInputArgs;
	}

	public List<FlowInputArgument> getFlowInputArgs() {
		return flowInputArgs;
	}

	public void setFlowInputArgs(List<FlowInputArgument> flowInputArgs) {
		this.flowInputArgs = flowInputArgs;
	}

	public void renderInputTable() throws JsonParseException, JsonMappingException, IOException {
		disposeAllTableEditors();
		this.removeAll();
		List<FlowInputArgument> flowInputArgs = getFlowInputArgs();
		if (getKeyWordInputArgs().size() > 0) {
			for (int i = 0; i < getKeyWordInputArgs().size(); i++) {
				KeyWordInputArgument keywordInputArg = getKeyWordInputArgs().get(i);
				List<KeyWordInputArgument> filteredKeywordInputArgs = new ArrayList<KeyWordInputArgument>();
				for (int i1 = 0; i1 < getKeyWordInputArgs().size(); i1++) {
					KeyWordInputArgument inputArgument = getKeyWordInputArgs().get(i1);
					// if (!inputArgument.getDatatype().equals("ORObject")) {
					filteredKeywordInputArgs.add(inputArgument);
					// }
				}

				List<FlowInputArgument> filteredFlowInputArgs = new ArrayList<FlowInputArgument>();
				for (int i1 = 0; i1 < flowInputArgs.size(); i1++) {
					FlowInputArgument inputArgument = flowInputArgs.get(i1);
					// if (inputArgument.getStaticobjectid() == null) {
					filteredFlowInputArgs.add(inputArgument);
					// }
				}

				System.out.println("KeyInputArgs " + filteredKeywordInputArgs.size() + "    " + "FlowInputArgs "
						+ filteredFlowInputArgs.size());
				if (filteredKeywordInputArgs.size() == filteredFlowInputArgs.size()) {
					if (!keywordInputArg.getDatatype().equals("ORObject")) {

						FlowInputArgument flowInputArg = flowInputArgs.get(i);
						CustomTableItem cti = new CustomTableItem(this, 0);
						cti.setText(new String[] { keywordInputArg.getDatatype(), keywordInputArg.getName(),
								flowInputArg.getStaticvalue() });
						cti.setControlData(flowInputArg);
						addInputTableEditor(cti);
					}
				}
			}
		}

		// Display FL in TestCase or Function Library
		if (getComponentInputArgs().size() > 0) {
			for (int i = 0; i < getComponentInputArgs().size(); i++) {
				List<ComponentInputArgument> filteredComponentInputArgs = new ArrayList<ComponentInputArgument>();
				for (int i1 = 0; i1 < getComponentInputArgs().size(); i1++) {
					ComponentInputArgument inputArgument = getComponentInputArgs().get(i1);
					filteredComponentInputArgs.add(inputArgument);
				}

				List<FlowInputArgument> filteredFlowInputArgs = new ArrayList<FlowInputArgument>();
				for (int i1 = 0; i1 < flowInputArgs.size(); i1++) {
					FlowInputArgument inputArgument = flowInputArgs.get(i1);
					// if (inputArgument.getStaticobjectid() == null) {
					filteredFlowInputArgs.add(inputArgument);
					// }
				}

				if (filteredComponentInputArgs.size() == filteredFlowInputArgs.size()) {
					ComponentInputArgument keywordInputArg = getComponentInputArgs().get(i);
					FlowInputArgument flowInputArg = flowInputArgs.get(i);
					CustomTableItem cti = new CustomTableItem(this, 0);
					cti.setText(new String[] { keywordInputArg.getType(), keywordInputArg.getName(),
							flowInputArg.getStaticvalue() });
					cti.setControlData(flowInputArg);
					addInputTableEditor(cti);
				}
			}
		}
		selectDefaultRow();
	}

	public FlowInputArgument getSelectedFlowInputArgument() {
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		CustomTableItem selectedTableItem = (CustomTableItem) this.getSelection()[0];
		return (FlowInputArgument) selectedTableItem.getControlData();
	}

	public List<ComponentInputArgument> getComponentInputArgs() {
		return componentInputArgs;
	}

	public void setComponentInputArgs(List<ComponentInputArgument> componentInputArgs) {
		this.componentInputArgs = componentInputArgs;
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

}
