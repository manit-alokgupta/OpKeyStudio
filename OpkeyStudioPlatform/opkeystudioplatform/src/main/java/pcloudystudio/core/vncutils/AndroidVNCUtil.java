package pcloudystudio.core.vncutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class AndroidVNCUtil {

	private static AndroidVNCUtil obj;
	private static Process vncServerProcess = null;
	private static Process opkeyVncJarProcess = null; 


	private static String PreCompiledLibDirectory = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator
			+ "VncServer" + File.separator + "vncserver" + File.separator + "PreCompiled_libs"; // "

	private static String ResourceDirectory = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator
			+ "VncServer" + File.separator + "vncserver" + File.separator + "Resources" + File.separator;
	private static String MobileLibDirectory = "/data/local/tmp/pcloudy-libs";

	private static String adbPath = String.valueOf(System.getenv("ANDROID_HOME")) + File.separator + "platform-tools";

	private AndroidVNCUtil() {
	}

	public static AndroidVNCUtil getInstance() {
		if (obj == null)
			return new AndroidVNCUtil();
		return obj;
	}

	public static boolean checkIfDeviceIsConnected(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb -s " + id + " devices";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			System.out.println(line);
		}

		reader.close();
		String str[] = builder.toString().split(System.getProperty("line.separator"));
		int index = 1;
		if (str.length > index)
			if (str[1] != null) {
				if (str[1].contains("device"))
					return true;
			}

		return false;

	}

	public static boolean checkIfMobileContainsLibFolder(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb -s " + id + " shell ls " + MobileLibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			System.out.println(line);
		}

		reader.close();
		if (builder.toString().contains("androidvncserver"))
			return true;
		else if (builder.toString().contains("minicap"))
			return true;
		else if (builder.toString().contains("minicap.so"))
			return true;
		else {
			RemoveMobileLibDirectory(id);
			return false;
		}
	}

	public static void MakingRequiredDirectory(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Making lib directory in ");
		String makeDircmd = "adb -s " + id + " shell mkdir " + MobileLibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(makeDircmd);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void givePermissionToDirectory(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Granting permission to Lib directory");
		String command = "adb -s " + id + " shell chmod 777 " + MobileLibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void pushVncServer(String id, String abi) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("pushing vnc server");
		String command = "adb -s " + id + " push " + AndroidVNCUtil.PreCompiledLibDirectory + "/libs/" + abi
				+ "/androidvncserver" + " " + AndroidVNCUtil.MobileLibDirectory; // give the source for precompiled
		// directory
		System.out.println("command is  " + command);
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void grantPermissionToServer(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("giving permision to vnc server");
		String command = "adb -s " + id + " shell chmod 777 " + MobileLibDirectory + "/androidvncserver";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void pushScreenshotMinicap(String id, String abi) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("pushing minicap file");
		String command = "adb -s " + id + " push  " + AndroidVNCUtil.PreCompiledLibDirectory + "/screenshot/" + abi
				+ "/minicap" + "  " + MobileLibDirectory;
		System.out.println("command is  " + command);
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void grantPermissionToMinicap(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("giving permision to vnc minicap");
		String command = "adb -s " + id + " shell chmod 777 " + MobileLibDirectory + "/minicap";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void pushScreenshotMinicapSo(String id, String sdk, String abi)
			throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("pushing minicapso file");
		String command = "adb -s " + id + " push  " + AndroidVNCUtil.PreCompiledLibDirectory + "/aosp/android-" + sdk
				+ "/" + abi + "/minicap.so" + "   " + MobileLibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void grantPermissionToMinicapSo(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("giving permision to minicapso");
		String command = "adb -s " + id + " shell chmod 777 /data/local/tmp/pcloudy-libs/minicap.so";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void startVncServer(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Starting Vnc Server");
		String command = "adb -s " + id
				+ " shell LD_LIBRARY_PATH=/data/local/tmp/pcloudy-libs  exec /data/local/tmp/pcloudy-libs/androidvncserver -s 100";
		StringBuilder builder = new StringBuilder();
		vncServerProcess = runtime.exec(command);
		vncServerProcess.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(vncServerProcess.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			System.out.println("line");
		}

	}

	public static void PortForward(String id, String port) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("forwarding port 5801 to port 5901");
		String command = "adb -s " + id + " forward tcp:" + port + " " + "tcp:5901";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void installInputServiceApk(String id, int deviceVersion) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = null;
		if (deviceVersion <= 9) {
			System.out.println("installing inputservice_up_to_version_9.apk");
			command = "adb -s " + id + " install " + ResourceDirectory + "inputservice_up_to_version_9.apk";
		} else if (deviceVersion == 10) {
			System.out.println("pushing inputservice_for_version_10.apk");
			command = "adb -s " + id + " install " + ResourceDirectory + "inputservice_for_version_10.apk";
		}

		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void pushOpkeyVncJar(String id) throws IOException, InterruptedException {
		System.out.println("pushing pussing opkeyvnc jar");
		String command = "adb -s " + id + " push " + ResourceDirectory + "opkeyvnc.jar";
		Runtime runtime = Runtime.getRuntime();
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static void LunchVnc(String id, String deviceName, String port) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Launching Vnc");
		String command = "java -jar " + ResourceDirectory + "opkeyvnc.jar" + " " + '"' + port + '"' + " " + '"'
				+ deviceName + '"' + " " + '"' + id + '"' + " " + adbPath;

		StringBuilder builder = new StringBuilder();
		opkeyVncJarProcess = runtime.exec(command);
		opkeyVncJarProcess.waitFor();
		vncServerProcess.destroy();

		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(opkeyVncJarProcess.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		reader.close();

	}

	public static String getInputServicePath(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("getting input service path");
		String path = null;
		String command = "adb -s " + id + " shell pm path com.pcloudy.services";
		System.out.println("command is" + " " + command);
		Process process = runtime.exec(command);
		process.waitFor();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((path = reader.readLine()) != null) {
			System.out.println(path);
			break;
		}
		reader.close();
		if (path != null) { // path contains package:so removing it.
			path = path.replace("package:", "");
			return path.trim();
		} else
			return "/data/app/com.pcloudy.inputservice-1/base.ap"; // this means device not contains any base.apk
	}

	public static void StartInputService(String id, String baseApkpath) throws IOException, InterruptedException {
		System.out.println("starting input service");
		String command = "adb -s " + id + " shell export CLASSPATH=" + baseApkpath + ";"
				+ " exec app_process /system/bin com.pcloudy.inputservice.Agent";
		System.out.println("command is" + " " + command);
		String[] cmd = { adbPath + File.separator + "adb", command };
		ProcessBuilder pb = new ProcessBuilder(cmd);
		StringBuilder builder = new StringBuilder();
		Process process = pb.start();
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			System.out.println(line);
		}

		reader.close();

	}

	public static boolean ChecKResourcesDirectoryExists() throws IOException, InterruptedException {

		File tmpDir = new File(ResourceDirectory);
		boolean exists = tmpDir.exists();
		boolean isDirectory = tmpDir.isDirectory();
		if (exists && isDirectory) {
			System.out.println(ResourceDirectory + " " + "is a directory");
			return true;
		} else
			return false;
	}

	public static Boolean ChecKPreCompileLibrariesDirectoryExists() throws IOException, InterruptedException {

		File tmpDir = new File(PreCompiledLibDirectory);
		boolean exists = tmpDir.exists();
		boolean isDirectory = tmpDir.isDirectory();
		if (exists && isDirectory) {
			System.out.println(PreCompiledLibDirectory + " " + "is a directory");
			return true;
		} else
			return false;

	}

	public static boolean checkIfMobileContainsAndroidVncServer(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb -s " + id + " shell ls " + MobileLibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			System.out.println(line);
		}

		reader.close();

		if (builder.toString().contains("androidvncserver")
				&& !(builder.toString().contains("No such file or directory")))
			return true;
		else
			return false;

	}

	public static boolean checkIfMobileContainsAndroidMinicapFile(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb -s " + id + " shell ls " + MobileLibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			System.out.println(line);
		}

		reader.close();

		if (builder.toString().contains("minicap") && !(builder.toString().contains("No such file or directory")))
			return true;
		else
			return false;

	}

	public static boolean checkIfMobileContainsAndroidMinicapSoFile(String id)
			throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb -s " + id + " shell ls " + MobileLibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			System.out.println(line);
		}

		reader.close();

		if (builder.toString().contains("minicap.so") && !(builder.toString().contains("No such file or directory")))
			return true;
		else
			return false;

	}

	public static void RemoveMobileLibDirectory(String id) throws IOException, InterruptedException {
		System.out.println("removing the directory first");
		Runtime runtime = Runtime.getRuntime();
		String command = "adb -s " + id + " shell rm -r " + MobileLibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			System.out.println(line);
		}

		reader.close();

	}

	public static boolean VerifyMobileLibDirectoryRemoved(String id) throws IOException, InterruptedException {
		System.out.println("verifying the libdirectory is removed");
		Runtime runtime = Runtime.getRuntime();
		String command = "adb -s " + id + " shell ls " + MobileLibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			System.out.println(line);
		}

		reader.close();
		if (reader.toString().contains("No such file or directory"))
			return true;
		else
			return false;

	}

	public static boolean checkIfDirectoryExist(String path) {

		File file = new File(path);
		if (file.isDirectory())
			return true;
		else
			return false;
	}

	public void closeOpenVncViewer() {
		if (vncServerProcess != null && vncServerProcess.isAlive())
			vncServerProcess.destroy();
		
		if (opkeyVncJarProcess != null && opkeyVncJarProcess.isAlive())
			opkeyVncJarProcess.destroy();

	}
}
