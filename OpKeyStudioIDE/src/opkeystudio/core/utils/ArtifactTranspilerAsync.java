package opkeystudio.core.utils;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.opkeystudiocore.core.transpiler.ArtifactTranspiler;

public class ArtifactTranspilerAsync {
	public void executeArtifactTranspilerAsync(Shell shell) {
		MessageDialogs msd = new MessageDialogs();
		msd.openProgressDialog(shell, "Converting Artifacts into Source Code", false, new IRunnableWithProgress() {

			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				new ArtifactTranspiler().transpileAllArtifacts();
			}
		});
		msd.closeProgressDialog();
	}
}
