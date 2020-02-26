package pcloudystudio.appiumserver;

import java.io.IOException;

public class AppiumServerJava {

	public void startServer() {
		String Port = new AppiumPortIpInfo().getPort();
		String IpAddress = new AppiumPortIpInfo().getHost_Address();
		Runtime runtime = Runtime.getRuntime();
		try {
			// runtime.exec("cmd.exe /c start cmd.exe k\"appium -a 127.0.0.1 -p 4723
			// --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");

			if (Port != null && IpAddress != null) {
				runtime.exec(
						"cmd.exe /c start cmd.exe /k \"appium -a" + " " + IpAddress + " " + "-p" + " " + Port + "\"");
				Thread.sleep(10000);
			} else {
				System.out.println("Server is going to Start on default port and default ip address ");
				runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 \"");
				Thread.sleep(10000);

			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stopServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM node.exe");
			runtime.exec("taskkill /F /IM cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
