package opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;

public class SuiteTestCaseTree extends ArtifactTree {
	private TestSuiteView parentTestSuiteView;
	private MenuItem addTestCaseMenuItem;

	public SuiteTestCaseTree(Composite parent, int style, TestSuiteView parentTestSuiteView) {
		super(parent, style, parentTestSuiteView);
		this.setParentTestSuiteView(parentTestSuiteView);
		init();
		try {
			super.renderArtifacts();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				} else {
					addTestCaseMenuItem.setEnabled(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public TestSuiteView getParentTestSuiteView() {
		return parentTestSuiteView;
	}

	public void setParentTestSuiteView(TestSuiteView parentTestSuiteView) {
		this.parentTestSuiteView = parentTestSuiteView;
	}

}
