package pcloudystudio.commandhandler;

import java.io.IOException;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import pcloudystudio.core.vncutils.VncStarter;

public class VncHandler {
	@Execute
	public void execute(Shell parentShell) throws IOException, InterruptedException {
		VncStarter starter = new VncStarter();

		java.util.concurrent.Executors.newSingleThreadExecutor().execute(new Runnable() {
			public void run() {
				try {
					starter.startVncServer();

				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		Thread.sleep(5000);
		java.util.concurrent.Executors.newSingleThreadExecutor().execute(new Runnable() {
			public void run() {
				try {
					starter.startMobicast();

				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
}
