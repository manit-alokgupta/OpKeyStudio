package opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;

public class SuiteTestCaseTree extends CustomTree {
	private TestSuiteView parentTestSuiteView;

	public SuiteTestCaseTree(Composite parent, int style, TestSuiteView parentTestSuiteView) {
		super(parent, style);
		this.setParentTestSuiteView(parentTestSuiteView);
	}

	public TestSuiteView getParentTestSuiteView() {
		return parentTestSuiteView;
	}

	public void setParentTestSuiteView(TestSuiteView parentTestSuiteView) {
		this.parentTestSuiteView = parentTestSuiteView;
	}

}
