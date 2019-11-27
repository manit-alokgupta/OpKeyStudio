package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;

public class InputDataTable extends CustomTable {
	private List<KeyWordInputArgument> keyWordInputArgs;
	private List<FlowInputArgument> flowInputArgs;
	private List<ComponentInputArgument> componentInputArgs;
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
				text.setText(flowInputArgument.getStaticvalue());
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
						flowInputArgument.setStaticvalue(text.getText());
						flowInputArgument.setModified(true);
						getParentTestCaseView().toggleSaveButton(true);
						row.setText(selectedColumn, text.getText());
					}
				});

				if (selectedColumn == 2) {
					editor.setEditor(text);
					text.setFocus();
				} else {
					editor.getEditor().dispose();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
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

	public void renderInputTable() {
		this.removeAll();
		List<FlowInputArgument> flowInputArgs = getFlowInputArgs();
		if (getKeyWordInputArgs() != null) {
			for (int i = 0; i < getKeyWordInputArgs().size(); i++) {
				KeyWordInputArgument keywordInputArg = getKeyWordInputArgs().get(i);
				if (!keywordInputArg.getDatatype().equals("ORObject")) {
					FlowInputArgument flowInputArg = flowInputArgs.get(i);
					CustomTableItem cti = new CustomTableItem(this, 0);
					cti.setText(new String[] { keywordInputArg.getDatatype(), keywordInputArg.getName(),
							flowInputArg.getStaticvalue() });
					cti.setControlData(flowInputArg);
				}
			}
		}

		if (getComponentInputArgs() != null) {
			for (int i = 0; i < getComponentInputArgs().size(); i++) {
				ComponentInputArgument keywordInputArg = getComponentInputArgs().get(i);
				FlowInputArgument flowInputArg = flowInputArgs.get(i);
				CustomTableItem cti = new CustomTableItem(this, 0);
				cti.setText(new String[] { keywordInputArg.getType(), keywordInputArg.getName(),
						flowInputArg.getStaticvalue() });
				cti.setControlData(flowInputArg);
			}
		}
	}

	public FlowInputArgument getSelectedFlowInputArgument() {
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
