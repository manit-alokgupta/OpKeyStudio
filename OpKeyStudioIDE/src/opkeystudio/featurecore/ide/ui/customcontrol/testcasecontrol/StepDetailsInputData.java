package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentStepInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;

public class StepDetailsInputData extends CustomTable {

	private List<KeyWordInputArgument> keyWordInputArgs;
	private List<FlowInputArgument> flowInputArgs;
	private List<ComponentStepInputArgument> componentInputArgs;
	private TestCaseView parentTestCaseView;
	public StepDetailsInputData(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public StepDetailsInputData(Composite parent, int style, TestCaseView parentView) {
		super(parent, style);
		init();
		this.setParentTestCaseView(parentView);
	}

	private void init() {
		String[] tableHeaders = { "Type", "Name", "Mapped Data" };
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
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	@SuppressWarnings("unused")
	private void addTableEditor(FlowStepTableItem item) {
		FlowStep attrProperty = item.getFlowStepeData();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		TableEditor editor3 = getTableEditor();
		CustomText button = new CustomText(this, SWT.CHECK);

		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		editor1.setEditor(button, item, 2);
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

	public List<ComponentStepInputArgument> getComponentInputArgs() {
		return componentInputArgs;
	}

	public void setComponentInputArgs(List<ComponentStepInputArgument> componentInputArguments) {
		this.componentInputArgs = componentInputArguments;
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
				ComponentStepInputArgument keywordInputArg = getComponentInputArgs().get(i);
				FlowInputArgument flowInputArg = flowInputArgs.get(i);
				CustomTableItem cti = new CustomTableItem(this, 0);
				cti.setText(new String[] { keywordInputArg.getType(), keywordInputArg.getName(),
						flowInputArg.getStaticvalue() });
				cti.setControlData(flowInputArg);
			}
		}
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}
}
