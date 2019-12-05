package opkeystudio.parts.part;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol.SuiteStepTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.FlowStepTable;
import opkeystudio.featurecore.ide.ui.ui.ArtifactTreeUI;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowConstruct;
import opkeystudio.opkeystudiocore.core.apis.dbapi.testsuite.TestSuiteApi;

public class ProjectExplorerPart {
	@Inject
	MPart projectExplorerPart;

	FlowStepTable flowStepTable;
	SuiteStepTable testSuiteTable;

	@PostConstruct
	public void postConstruct(Composite parent) {
		new ArtifactTreeUI(parent, 0);
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("Console Window Destroyed");
//		boolean status = MessageDialog.openQuestion(Display.getCurrent().getActiveShell(), "OpKey",
//				"Please save before Quiting");
//		if (!status) {
//			System.out.println(status);
//			new TestSuiteApi().saveAllTestSuite(testSuiteTable.getTestSuiteData());
//			new FlowConstruct().saveAllFlowSteps(flowStepTable.getFlowStepsData());
//		}
		System.out.println("OpKey Studio Stoped");
	}

	@Focus
	public void onFocus() {
		System.out.println("Console Window Focused");
	}

	@Persist
	public void save() {
		System.out.println("Console Window Save");
	}
}
