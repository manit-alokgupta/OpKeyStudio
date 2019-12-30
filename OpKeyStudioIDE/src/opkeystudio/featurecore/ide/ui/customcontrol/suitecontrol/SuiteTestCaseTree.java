package opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;

public class SuiteTestCaseTree extends ArtifactTree {
	private TestSuiteView parentTestSuiteView;

	public SuiteTestCaseTree(Composite parent, int style, TestSuiteView parentTestSuiteView) {
		super(parent, style, parentTestSuiteView);
		this.setParentTestSuiteView(parentTestSuiteView);
		try {
			super.renderArtifacts();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TestSuiteView getParentTestSuiteView() {
		return parentTestSuiteView;
	}

	public void setParentTestSuiteView(TestSuiteView parentTestSuiteView) {
		this.parentTestSuiteView = parentTestSuiteView;
	}

}
