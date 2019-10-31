package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class ImportDBCommand {

	String[] filterExt = { "*.db;*.sql"};
	@Execute
	public void execute(Shell shell) {

		FileDialog dialog = new FileDialog(shell,SWT.OPEN);

		dialog.setFilterExtensions(filterExt);
		dialog.open();
		String filePath=dialog.getFilterPath()+"\\"+dialog.getFileName();
		
		ServiceRepository.getInstance().setExortedDBFilePath(filePath);
		
		System.out.println(filePath);


	}

}
