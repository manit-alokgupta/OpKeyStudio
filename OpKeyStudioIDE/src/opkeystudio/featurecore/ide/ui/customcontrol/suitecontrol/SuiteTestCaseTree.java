package opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol;

import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuiteStep;
import opkeystudio.opkeystudiocore.core.dtoMaker.SuiteMaker;

public class SuiteTestCaseTree extends ArtifactTree {
	private TestSuiteView parentTestSuiteView;
	private MenuItem addTestCaseMenuItem;

	public SuiteTestCaseTree(Composite parent, int style, TestSuiteView parentTestSuiteView) {
		super(parent, style, parentTestSuiteView);
		this.setParentTestSuiteView(parentTestSuiteView);
		init();
		super.renderArtifacts();
	}

	private void init() {
		Menu menu = new Menu(this);
		addTestCaseMenuItem = new MenuItem(menu, 0);
		addTestCaseMenuItem.setText("Add");
		this.setMenu(menu);
		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = getSelectedArtifact();
				if (artifact == null) {
					addTestCaseMenuItem.setEnabled(false);
					getParentTestSuiteView().getAddTestCaseButton().setEnabled(false);
				} else {
					addTestCaseMenuItem.setEnabled(true);
					getParentTestSuiteView().getAddTestCaseButton().setEnabled(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addTestCaseMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addTestSuite();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		getParentTestSuiteView().getAddTestCaseButton().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addTestSuite();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void addTestSuite() {
		Artifact artifact = getSelectedArtifact();
		TestSuiteStep selectedTestSuite = getParentTestSuiteView().getSuiteStepTable().getSelectedTestSuite();
		List<TestSuiteStep> testSuiteSteps = getParentTestSuiteView().getSuiteStepTable().getTestSuiteData();
		TestSuiteStep testSuiteStep = new SuiteMaker().createTestSuite(artifact, getParentTestSuiteView().getArtifact(),
				selectedTestSuite, testSuiteSteps);

		getParentTestSuiteView().getSuiteStepTable().getTestSuiteData().add(testSuiteStep);
		getParentTestSuiteView().getSuiteStepTable().refreshAllTestSuites();
		getParentTestSuiteView().toggleSaveButton(true);
	}

	public TestSuiteView getParentTestSuiteView() {
		return parentTestSuiteView;
	}

	public void setParentTestSuiteView(TestSuiteView parentTestSuiteView) {
		this.parentTestSuiteView = parentTestSuiteView;
	}

}
