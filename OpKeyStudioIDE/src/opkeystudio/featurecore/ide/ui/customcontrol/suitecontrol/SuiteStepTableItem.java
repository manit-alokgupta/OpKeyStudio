package opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuiteStep;

public class SuiteStepTableItem extends CustomTableItem {

	public SuiteStepTableItem(Composite parent, int style) {
		super((Table) parent, style);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public TestSuiteStep getTestSuiteData() {
		return (TestSuiteStep) super.getControlData();
	}

	public void setTestSuiteData(TestSuiteStep testSuite) {
		super.setControlData(testSuite);
	}

}
