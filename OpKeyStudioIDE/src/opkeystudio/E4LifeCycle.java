package opkeystudio;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import opkeystudio.featurecore.ide.ui.ui.LoginDialog;
import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordManager;

/**
 * This is a stub implementation containing e4 LifeCycle annotated
 * methods.<br />
 * There is a corresponding entry in <em>plugin.xml</em> (under the
 * <em>org.eclipse.core.runtime.products' extension point</em>) that references
 * this class.
 **/
@SuppressWarnings("restriction")
public class E4LifeCycle {

	@PostContextCreate
	void postContextCreate(IEclipseContext workbenchContext) {

		final Shell shell = new Shell(Display.getCurrent().getActiveShell(), SWT.CENTER|SWT.DIALOG_TRIM|SWT.APPLICATION_MODAL);
		LoginDialog ldialog = new LoginDialog(shell, SWT.CENTER);
		ldialog.open();

		try {
			KeywordManager.getInstance().loadAllKeywords();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static Shell getScreenCentredShell() {
//		Display display = PlatformUI.getWorkbench().getDisplay();
//		Shell centreShell = new Shell(display);
//		Point size = centreShell.computeSize(-1, -1);
//		Rectangle screen = display.getMonitors()[0].getBounds();
//		centreShell.setBounds((screen.width - size.x) / 2, (screen.height - size.y) / 2, size.x, size.y);
//		return centreShell;
//	}

	@PreSave
	void preSave(IEclipseContext workbenchContext) {
	}

	@ProcessAdditions
	void processAdditions(IEclipseContext workbenchContext) {
	}

	@ProcessRemovals
	void processRemovals(IEclipseContext workbenchContext) {
	}
}
