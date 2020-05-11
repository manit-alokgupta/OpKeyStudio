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
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class InputDataTable extends CustomTable {
	private FlowStep flowStep;
	private List<KeyWordInputArgument> keyWordInputArgs = new ArrayList<KeyWordInputArgument>();
	private List<FlowInputArgument> flowInputArgs = new ArrayList<FlowInputArgument>();
	private List<ComponentInputArgument> componentInputArgs = new ArrayList<>();
	private List<CFLInputParameter> cflInputArguments = new ArrayList<CFLInputParameter>();
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
		
		cursor.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				CustomTableItem row = (CustomTableItem) cursor.getRow();
				FlowInputArgument flowInputArgument = (FlowInputArgument) row.getControlData();
				String dataType = "";
				if (flowInputArgument.getKeywordInputArgument() != null) {
					dataType = flowInputArgument.getKeywordInputArgument().getDatatype();
				}
				if (flowInputArgument.getComponentInputArgument() != null) {
					dataType = flowInputArgument.getComponentInputArgument().getType();
				}
				System.out.println(">>DataType " + dataType);
				boolean isNumberType = isDataTypeIntegerType(dataType);
				boolean isBooleanType = isDataTypeBooleanrType(dataType);
				int selectedColumn = cursor.getColumn();

				if (selectedColumn == 2) {

					if (isBooleanType) {
						disposeControlEditor(editor);
						Button checkedButton = new Button(cursor, SWT.CHECK);
						checkedButton.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								String status = convertBooleanToString(checkedButton.getSelection());
								new FlowApiUtilities().setFlowInputData(getParentTestCaseView().getArtifact(),
										flowInputArgument, status, DataSource.StaticValue);
								getParentTestCaseView().toggleSaveButton(true);
								row.setText(selectedColumn, status);
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								// TODO Auto-generated method stub

							}
						});
						if (flowInputArgument.getStaticvalue() != null) {
							boolean checkedStatus = convertStringToBoolean(flowInputArgument.getStaticvalue());
							checkedButton.setSelection(checkedStatus);
							editor.setEditor(checkedButton);
						} else {
							checkedButton.setSelection(false);
							editor.setEditor(checkedButton);
						}
					} else {
						disposeControlEditor(editor);
						Text text = new Text(cursor, 0);
						text.addVerifyListener(new VerifyListener() {

							@Override
							public void verifyText(VerifyEvent e) {
								if (isNumberType) {
									final String oldS = text.getText();
									String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);
									System.out.println(">> Text " + newS);
									if (newS.trim().isEmpty()) {
										return;
									}
									boolean isNumber = true;
									try {
										Float.parseFloat(newS);
									} catch (NumberFormatException ex) {
										isNumber = false;
									}

									try {
										Integer.parseInt(newS);
									} catch (NumberFormatException ex) {
										isNumber = false;
									}

									try {
										Double.parseDouble(newS);
									} catch (NumberFormatException ex) {
										isNumber = false;
									}

									if (!isNumber) {
										e.doit = false;
									}
								}
							}
						});

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
								new FlowApiUtilities().setFlowInputData(getParentTestCaseView().getArtifact(),
										flowInputArgument, text.getText(), DataSource.StaticValue);
								getParentTestCaseView().toggleSaveButton(true);
								row.setText(selectedColumn, text.getText());
							}
						});
						if (flowInputArgument.getStaticvalue() != null) {
							text.setText(flowInputArgument.getStaticvalue());
							editor.setEditor(text);
							text.setFocus();
						} else {
							text.setText("");
							editor.setEditor(text);
							text.setFocus();
						}
					}
				} else {
					disposeControlEditor(editor);
				}				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
			}
		});
	}

	private void disposeControlEditor(ControlEditor editor) {
		if (editor != null) {
			if (editor.getEditor() != null) {
				editor.getEditor().dispose();
			}
		}
	}

	private String convertBooleanToString(boolean status) {
		if (status) {
			return "true";
		}
		return "false";
	}

	private boolean convertStringToBoolean(String status) {
		if (status.toLowerCase().equals("true")) {
			return true;
		}
		return false;
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private boolean isDataTypeIntegerType(String dataType) {
		if (dataType.equals("Integer")) {
			return true;
		}
		if (dataType.equals("Float")) {
			return true;
		}
		if (dataType.equals("Double")) {
			return true;
		}
		return false;
	}

	private boolean isDataTypeBooleanrType(String dataType) {
		if (dataType.equals("Boolean")) {
			return true;
		}
		return false;
	}

	private void addInputTableEditor(CustomTableItem item)
			throws JsonParseException, JsonMappingException, IOException {
		item.setImage(1, ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.INPUTDATA_ICON));
		FlowInputArgument flowInputArgument = (FlowInputArgument) item.getControlData();
		DataSource dataSourceType = null;
		String dataType = "";
		if (flowInputArgument.getKeywordInputArgument() != null) {
			dataType = flowInputArgument.getKeywordInputArgument().getDatatype();
		}
		if (flowInputArgument.getComponentInputArgument() != null) {
			dataType = flowInputArgument.getComponentInputArgument().getType();
		}
		System.out.println(">>DataType " + dataType);
		if (getParentTestCaseView().getArtifact().getFile_type_enum() == MODULETYPE.Component) {
			dataSourceType = flowInputArgument.getArg_datasource();
		} else {
			dataSourceType = flowInputArgument.getDatasource();
		}

		if (isConstructFlowIFKeyword(getFlowStep())) {
			int index = flowInputArgument.getIndex();
			if (index == 1 || index == 5 || index == 9 || index == 13 || index == 17) {
				String[] items = { "", "=", "<", ">", "<>" };
				TableEditor editor1 = getTableEditor();
				Combo combo = new Combo(this, SWT.READ_ONLY);
				combo.setItems(items);
				int selectIndex = Utilities.getInstance().getIndexOfItem(items, flowInputArgument.getStaticvalue());
				if (selectIndex > -1) {
					combo.select(selectIndex);
				}
				combo.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						int index = combo.getSelectionIndex();
						String selecedData = items[index];
						flowInputArgument.setStaticvalue(selecedData);
						flowInputArgument.setModified(true);
						getParentTestCaseView().toggleSaveButton(true);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub

					}
				});
				editor1.setEditor(combo, item, 2);
				this.allTableEditors.add(editor1.getEditor());
				return;
			}

			if (index == 3 || index == 7 || index == 11 || index == 15 || index == 19) {
				String[] items = { "", "AND", "OR" };
				TableEditor editor1 = getTableEditor();
				Combo combo = new Combo(this, SWT.READ_ONLY);
				combo.setItems(items);
				int selectIndex = Utilities.getInstance().getIndexOfItem(items, flowInputArgument.getStaticvalue());
				if (selectIndex > -1) {
					combo.select(selectIndex);
				}

				combo.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						int index = combo.getSelectionIndex();
						String selecedData = items[index];
						flowInputArgument.setStaticvalue(selecedData);
						flowInputArgument.setModified(true);
						getParentTestCaseView().toggleSaveButton(true);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub

					}
				});
				editor1.setEditor(combo, item, 2);
				this.allTableEditors.add(editor1.getEditor());
				return;
			}
		}
		if (dataSourceType == DataSource.ValueFromGlobalVariable) {
			String gv_id = flowInputArgument.getGlobalvariable_id();
			GlobalVariable globalVar = new GlobalVariableApi().getGlobalVariable(gv_id);
			TableEditor editor1 = getTableEditor();
			CustomButton button = new CustomButton(this, SWT.NONE | SWT.NO_BACKGROUND);
			button.setText(globalVar.getName());
			button.setToolTipText(globalVar.getName());
			button.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.GLOBAL_VARIABLE_ICON));
			button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
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
			button.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DR_COLUMN_ICON));
			button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
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
			System.out.println("Called ValueFromOutputArgument");
			String flow_step_oa_id = null;
			if (getParentTestCaseView().getArtifact().getFile_type_enum() == MODULETYPE.Component) {
				flow_step_oa_id = flowInputArgument.getComponentstep_oa_id();
			} else if (getParentTestCaseView().getArtifact().getFile_type_enum() == MODULETYPE.Flow) {
				flow_step_oa_id = flowInputArgument.getFlow_step_oa_id();
			}
			if (flow_step_oa_id == null) {
				return;
			}
			List<FlowOutputArgument> flowOutPutArguments = new ArrayList<FlowOutputArgument>();
			if (getParentTestCaseView().getArtifact().getFile_type_enum() == MODULETYPE.Component) {
				flowOutPutArguments = new FlowApiUtilities().getAllComponentOutPutArgument(flow_step_oa_id);
			} else if (getParentTestCaseView().getArtifact().getFile_type_enum() == MODULETYPE.Flow) {
				flowOutPutArguments = new FlowApiUtilities().getAllFlowOutPutArgument(flow_step_oa_id);
			}
			if (flowOutPutArguments.size() == 0) {
				return;
			}
			FlowOutputArgument flowOutputArgument = flowOutPutArguments.get(0);
			TableEditor editor1 = getTableEditor();
			CustomButton button = new CustomButton(this, SWT.NONE);
			button.setText(flowOutputArgument.getOutputvariablename());
			button.setToolTipText(flowOutputArgument.getOutputvariablename());
			button.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OUTPUTDATA_ICON));
			button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
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

		if (dataSourceType == DataSource.ValueFromInputParameter)

		{
			String ipId = flowInputArgument.getIp_id();
			List<ComponentInputArgument> compsinp = new FunctionLibraryApi()
					.getAllComponentInputArgument(getParentTestCaseView().getArtifact().getId());
			for (ComponentInputArgument cia : compsinp) {
				if (cia.getIp_id().equals(ipId)) {
					TableEditor editor1 = getTableEditor();
					CustomButton button = new CustomButton(this, SWT.NONE);
					button.setText(cia.getName());
					button.setToolTipText(cia.getName());
					button.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.INPUTDATA_ICON));
					button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
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
					break;
				}
			}
		}
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		editor.minimumHeight = 10;
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

	private void initInputTableArguments(FlowStep flowStep) {
		this.setFlowStep(flowStep);
		if (flowStep.getKeyword() != null) {
			setKeyWordInputArgs(flowStep.getKeyword().getKeywordInputArguments());
		} else {
			setKeyWordInputArgs(new ArrayList<>());
		}
		if (flowStep.getFunctionLibraryComponent() != null) {
			setComponentInputArgs(flowStep.getFunctionLibraryComponent().getComponentInputArguments());
		} else {
			setComponentInputArgs(new ArrayList<>());
		}

		if (flowStep.getCodedFunctionArtifact() != null) {
			setCflInputArguments(flowStep.getCodedFunctionArtifact().getCflInputParameters());
		} else {
			setCflInputArguments(new ArrayList<CFLInputParameter>());
		}
		setFlowInputArgs(flowStep.getFlowInputArgs());
	}

	private boolean isConstructFlowIFKeyword(FlowStep flowStep) {
		if (flowStep.getKeyword() != null) {
			Keyword keyword = flowStep.getKeyword();
			if (keyword.getName().toLowerCase().equals("if")) {
				return true;
			}
		}
		return false;
	}

	private void addIndexInFlowInputArgumentOfConstructFlowIFKeyword(FlowStep flowStep) {
		List<FlowInputArgument> flowInputArguments = flowStep.getFlowInputArgs();
		for (int i = 0; i < flowInputArguments.size(); i++) {
			flowInputArguments.get(i).setIndex(i);
		}
	}

	public void renderInputTable(FlowStep flowStep) throws JsonParseException, JsonMappingException, IOException {
		this.initInputTableArguments(flowStep);
		disposeAllTableEditors();
		this.removeAll();
		if (isConstructFlowIFKeyword(flowStep)) {
			addIndexInFlowInputArgumentOfConstructFlowIFKeyword(flowStep);
		}
		List<FlowInputArgument> flowInputArgs = getFlowInputArgs();
		if (getKeyWordInputArgs().size() > 0) {
			for (int i = 0; i < getKeyWordInputArgs().size(); i++) {
				KeyWordInputArgument keywordInputArg = getKeyWordInputArgs().get(i);
				List<KeyWordInputArgument> filteredKeywordInputArgs = new ArrayList<KeyWordInputArgument>();
				for (int i1 = 0; i1 < getKeyWordInputArgs().size(); i1++) {
					KeyWordInputArgument inputArgument = getKeyWordInputArgs().get(i1);
					filteredKeywordInputArgs.add(inputArgument);
				}

				List<FlowInputArgument> filteredFlowInputArgs = new ArrayList<FlowInputArgument>();
				for (int i1 = 0; i1 < flowInputArgs.size(); i1++) {
					FlowInputArgument inputArgument = flowInputArgs.get(i1);
					filteredFlowInputArgs.add(inputArgument);
				}
				if (filteredKeywordInputArgs.size() == filteredFlowInputArgs.size()) {
					if (!keywordInputArg.getDatatype().equals("ORObject")) {
						FlowInputArgument flowInputArg = flowInputArgs.get(i);
						CustomTableItem cti = new CustomTableItem(this, 0);
						String valueData = flowInputArg.getStaticvalue();
						if (valueData == null) {
							valueData = "";
							if (keywordInputArg.getDatatype().toString().equals("Boolean")) {
								valueData = "false";
							}
						}
						if (keywordInputArg.getDatatype().toString().equals("Boolean")) {
							if (valueData != null) {
								if (valueData.equals("0")) {
									valueData = "false";
									flowInputArg.setStaticvalue(valueData);
								}
								if (valueData.equals("1")) {
									valueData = "true";
									flowInputArg.setStaticvalue(valueData);
								}
								if (valueData.equals("False")) {
									valueData = "false";
									flowInputArg.setStaticvalue(valueData);
								}
								if (valueData.equals("True")) {
									valueData = "true";
									flowInputArg.setStaticvalue(valueData);
								}
							}
						}
						cti.setText(new String[] { keywordInputArg.getDatatype().toString(), keywordInputArg.getName(),
								valueData });
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
					filteredFlowInputArgs.add(inputArgument);
				}

				if (filteredComponentInputArgs.size() == filteredFlowInputArgs.size()) {
					ComponentInputArgument componentInputArg = getComponentInputArgs().get(i);
					FlowInputArgument flowInputArg = flowInputArgs.get(i);
					CustomTableItem cti = new CustomTableItem(this, 0);
					String inputValue = flowInputArg.getStaticvalue();
					if (inputValue == null) {
						if (componentInputArg.getDefaultvalue() != null) {
							inputValue = componentInputArg.getDefaultvalue();
						}
					}
					cti.setText(new String[] { componentInputArg.getType(), componentInputArg.getName(), inputValue });
					cti.setControlData(flowInputArg);
					addInputTableEditor(cti);
				}
			}
		}

		// Display CFL in TestCase and FL
		if (getCflInputArguments().size() > 0) {
			for (int i = 0; i < getCflInputArguments().size(); i++) {
				List<CFLInputParameter> filteredComponentInputArgs = new ArrayList<CFLInputParameter>();
				for (int i1 = 0; i1 < getCflInputArguments().size(); i1++) {
					CFLInputParameter inputArgument = getCflInputArguments().get(i1);
					filteredComponentInputArgs.add(inputArgument);
				}

				List<FlowInputArgument> filteredFlowInputArgs = new ArrayList<FlowInputArgument>();
				for (int i1 = 0; i1 < flowInputArgs.size(); i1++) {
					FlowInputArgument inputArgument = flowInputArgs.get(i1);
					filteredFlowInputArgs.add(inputArgument);
				}

				if (filteredComponentInputArgs.size() == filteredFlowInputArgs.size()) {
					CFLInputParameter cflInputArgument = getCflInputArguments().get(i);
					FlowInputArgument flowInputArg = flowInputArgs.get(i);
					CustomTableItem cti = new CustomTableItem(this, 0);
					String inputValue = flowInputArg.getStaticvalue();
					if (inputValue == null) {
						if (cflInputArgument.getDefaultvalue() != null) {
							inputValue = cflInputArgument.getDefaultvalue();
						}
					}
					cti.setText(new String[] { cflInputArgument.getType(), cflInputArgument.getName(), inputValue });
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

	public FlowStep getFlowStep() {
		return flowStep;
	}

	public void setFlowStep(FlowStep flowStep) {
		this.flowStep = flowStep;
	}

	public List<CFLInputParameter> getCflInputArguments() {
		return cflInputArguments;
	}

	public void setCflInputArguments(List<CFLInputParameter> cflInputArguments) {
		this.cflInputArguments = cflInputArguments;
	}

}
