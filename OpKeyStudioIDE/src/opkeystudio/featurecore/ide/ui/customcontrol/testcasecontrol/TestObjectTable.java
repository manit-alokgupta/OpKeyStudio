package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class TestObjectTable extends CustomTable {
	private List<ORObject> orobject;
	private TestCaseView parentTestCaseView;
	public TestObjectTable(Composite parent, int style,TestCaseView parentView) {
		super(parent, style);
		init();
		this.setParentTestCaseView(parentView);
	}

	private void init() {
		String[] tableHeaders = { "Name", "Provide Object", "Object Type", "Action" };
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, SWT.LEFT);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}
	}

	public List<ORObject> getOrobject() {
		return orobject;
	}

	public void setOrobject(List<ORObject> orobject) {
		this.orobject = orobject;
	}

	public void renderORObjectTable() {
		this.removeAll();
		for (ORObject orobject : getOrobject()) {
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setText(new String[] { "Object", orobject.getName(), "", "" });
		}
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}
}
