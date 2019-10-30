package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class ImportDBCommand {

	@Execute
	public void execute(Shell shell) {

		FileDialog dialog = new FileDialog(shell);
		dialog.open();
//		String[] extension=dialog.getFilterExtensions();
//		System.out.println(extension);

		String fileName = dialog.getFileName();

//		System.out.println(dialog.getFilterExtensions().toString());

		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		
		System.out.println(extension);
		if(extension.equals("sql") || extension.equals("sqllite") || extension.equals("db"))
		{
			System.out.println(dialog.getFilterPath()+"\\"+dialog.getFileName());
		}
		else
		{
			System.err.println("File not supported");
		}

	}

}
