package pcloudystudio.appiumserver;

import java.io.IOException;
import java.net.ServerSocket;

public class AppiumServer {
	public static void startServer() {
		String port = new AppiumPortIpInfo().getPort();
		String hostAddress = new AppiumPortIpInfo().getHost_Address();
		Runtime runtime = Runtime.getRuntime();
		try {
			if (port != null && hostAddress != null) {

				if (isServerRunning(Integer.parseInt(port))) {
					Sleep(1000);
					System.out.println("Shutting Down The Server");
					stopServer();
					Sleep(1000);
				}

				System.out.println("Starting server according to given host: " + hostAddress + ", and port: " + port);
				runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a" + " " + hostAddress + " " + "-p" + " " + port
						+ " " + "--session-override" + "\"");
				Sleep(4000);
			} else {
				if (isServerRunning(4723)) {   // default port
					Sleep(1000);
					System.out.println("Shutting Down The Server");
					stopServer();
					Sleep(1000);
				}

				System.out.println("Starting server on default host: 127.0.0.1, and port: 4723");
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
			System.out.println("Given Port is in Use");
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;

	}

	public static void Sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

}
