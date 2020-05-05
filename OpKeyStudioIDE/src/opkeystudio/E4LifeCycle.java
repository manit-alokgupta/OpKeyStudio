package opkeystudio;

import java.io.File;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;

import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyArtifactPersistListenerDispatcher;
import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordManager;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

/**
 * This is a stub implementation containing e4 LifeCycle annotated
 * methods.<br />
 * There is a corresponding entry in <em>plugin.xml</em> (under the
 * <em>org.eclipse.core.runtime.products' extension point</em>) that references
 * this class.
 **/
public class E4LifeCycle {

	@PostContextCreate
	void postContextCreate(IEclipseContext workbenchContext) {
		Utilities.getInstance().setDefaultInstallDir(new File("").getAbsolutePath());
		Utilities.getInstance().initializeOpKeyStudioPath();
		KeywordManager.getInstance().loadAllKeywords();
		opkeystudio.core.utils.Utilities.getInstance().initCodeEditorSuperConstructor();
	}

	@PreSave
	void preSave(IEclipseContext workbenchContext) {
		System.out.println("Called @@PreSave");
		OpKeyArtifactPersistListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
	}

	@ProcessAdditions
	void processAdditions(IEclipseContext workbenchContext) {
		System.out.println("Called @@ProcessAdditions");
	}

	@ProcessRemovals
	void processRemovals(IEclipseContext workbenchContext) {
		System.out.println("Called @ProcessRemovals");
	}
}
