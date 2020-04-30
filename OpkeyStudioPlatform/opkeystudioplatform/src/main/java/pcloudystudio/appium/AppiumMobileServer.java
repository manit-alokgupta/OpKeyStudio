package pcloudystudio.appium;

import java.io.IOException;
import java.net.ServerSocket;

public class AppiumMobileServer {
	public static void startServer() {
		String port = AppiumConfiguration.getPort();
		String hostAddress = AppiumConfiguration.getHostAddress();
		Runtime runtime = Runtime.getRuntime();
		try {
			if (port != null && hostAddress != null) {
				runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a" + " " + hostAddress + " " + "-p" + " " + port
						+ " " + "--session-override" + "\"");
				Thread.sleep(4000);
			} else {
				runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override\"");
				Thread.sleep(4000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void stopServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM node.exe");
			runtime.exec("taskkill /F /IM cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Boolean isServerRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;

	}
}
