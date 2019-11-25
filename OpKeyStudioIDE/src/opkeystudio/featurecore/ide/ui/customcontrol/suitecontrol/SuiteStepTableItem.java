package opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;

public class SuiteStepTableItem extends CustomTableItem {

	public SuiteStepTableItem(Composite parent, int style) {
		super((Table) parent, style);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public TestSuite getTestSuiteData() {
		return (TestSuite) super.getOpKeyData();
	}

	public void setTestSuiteData(TestSuite objectProperty) {
		super.setOpKeyData(objectProperty);
	}

}
