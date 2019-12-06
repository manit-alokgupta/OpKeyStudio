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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.FlowStepTable;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowConstruct;

public class TestCasePart {
	@Inject
	MPart projectExplorerPart;
	private TestCaseView parentTestCaseView;

	private FlowStepTable flowStepTable;

	@PostConstruct
	public void postConstruct(Composite parent) throws IOException {
		parentTestCaseView = new TestCaseView(parent, 0);

	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("Console Window Destroyed");

		boolean status = MessageDialog.openQuestion(Display.getCurrent().getActiveShell(), "OpKey",
				"Please save before Quiting");
		if (!status) {
			return;
		}

		parentTestCaseView.saving();
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
