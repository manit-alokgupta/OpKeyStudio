package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;

public class TestObjectTree extends CustomTree {
	private TestCaseView parentTestCaseView;

	public TestObjectTree(Composite parent, int style) {
		super(parent, style);
	}

	public TestObjectTree(Composite parent, int style, TestCaseView parentTestCaseView) {
		super(parent, style);
		this.setParentTestCaseView(parentTestCaseView);
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

}
