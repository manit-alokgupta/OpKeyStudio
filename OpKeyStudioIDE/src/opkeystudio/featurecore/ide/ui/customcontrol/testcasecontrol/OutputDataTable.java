package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;

public class OutputDataTable extends CustomTable {
	private Keyword keyword;
	private List<FlowOutputArgument> flowOutputArgs;
	private TestCaseView parentTestCaseView;

	public OutputDataTable(Composite parent, int style, TestCaseView parentView) {
		super(parent, style);
		init();
		this.setParentTestCaseView(parentView);
	}

	public OutputDataTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
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
	}

	public List<FlowOutputArgument> getFlowOutputArgs() {
		return flowOutputArgs;
	}

	public void setFlowOutputArgs(List<FlowOutputArgument> flowOutputArgs) {
		this.flowOutputArgs = flowOutputArgs;
	}

	public void renderOutPutTable() {
		this.removeAll();
		for (FlowOutputArgument flowOutPutArg : getFlowOutputArgs()) {
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setText(new String[] { keyword.getOutputtype(), "", "" });
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
}
