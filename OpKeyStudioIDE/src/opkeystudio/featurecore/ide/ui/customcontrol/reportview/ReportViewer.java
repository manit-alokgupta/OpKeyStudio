package opkeystudio.featurecore.ide.ui.customcontrol.reportview;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.opkeystudiocore.core.execution.ExecutionSession;

import org.eclipse.swt.layout.FillLayout;

import java.io.File;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;

public class ReportViewer extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	private Browser reportViewBrowser;
	private ExecutionSession executionSession;

	public ReportViewer(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		initUI();
		initExecutionSession();
		displayReport();
	}

	private void initUI() {
		reportViewBrowser = new Browser(this, SWT.BORDER);
	}

	private void displayReport() {
		reportViewBrowser.setUrl(getExecutionSession().getReportFolderDir() + File.separator + "Report.html");
	}

	private void initExecutionSession() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		this.setExecutionSession((ExecutionSession) mpart.getTransientData().get("opkeystudio.executionSessionData"));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ExecutionSession getExecutionSession() {
		return executionSession;
	}

	public void setExecutionSession(ExecutionSession executionSession) {
		this.executionSession = executionSession;
	}
}
