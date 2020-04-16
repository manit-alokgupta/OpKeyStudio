package pcloudystudio.core.vncutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class VNCUtils {

	private static VNCUtils obj;
	private static String DeviceidCommand = "adb get-serialno";
	private static String PreCompiledLibDirectory = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator
			+ "VncServer" + File.separator + "vncserver" + File.separator + "PreCompiled_libs"; // "
	
	private static String ResourceDirectory = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator
			+ "VncServer" + File.separator + "vncserver" + File.separator + "Resources"+File.separator ; 
	private static String MobileLibDirectory = "/data/local/tmp/pcloudy-libs";

	private VNCUtils() {
	}

	public static VNCUtils getInstance() {
		if (obj == null)
			return new VNCUtils();
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

	public static String runAdbCommandForMobileId() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String id = "";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(DeviceidCommand);
		process.waitFor();
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		String line;
		while ((line = br.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		br.close();
		if (builder.toString().trim().equals(""))
			System.out.println("No Device Is Connected");

		if (!builder.toString().trim().equals("") || builder != null) {
			id = builder.toString().split("\n")[0];

		}

		System.out.println(id);

		if (!id.trim().equals("")) {

			return id;
		}
		return "No Device Is Connected";

	}

	public static String runAdbCommandForMobileModel(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		String model = "";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec("adb -s " + id + " shell getprop ro.product.model");
		process.waitFor();
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		String line;
		while ((line = br.readLine()) != null) {
			builder.append(line);

			builder.append(System.getProperty("line.separator"));
		}

		br.close();
		if (builder.toString().trim().equals(""))
			System.out.println("No Device Is Connected");

		if (!builder.toString().trim().equals("") || builder != null) {
			model = builder.toString().split("\n")[0].trim();

		}

		System.out.println(model);

		if (!model.trim().equals("")) {

			return model;
		}
		return "No Device Is Connected";

	}

	public static String runAdbCommandForMobileAbi(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		String abi = "";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec("adb -s " + id + "shell getprop ro.product.cpu.abi");
		process.waitFor();
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		String line;
		while ((line = br.readLine()) != null) {
			builder.append(line);

			builder.append(System.getProperty("line.separator"));
		}

		br.close();

		if (builder.toString().trim().equals(""))
			System.out.println("No Device Is Connected");

		if (!builder.toString().trim().equals("") || builder != null) {
			abi = builder.toString().split("\n")[0].trim();

		}

		System.out.println(abi);

		if (!abi.trim().equals("")) {

			return abi;
		}
		return "No Device Is Connected";

	}

	public static String runAdbCommandForMobileSdk(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		String sdk = "";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec("adb -s " + id + " shell getprop ro.build.version.sdk");
		process.waitFor();
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		String line;
		while ((line = br.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		br.close();
		if (builder.toString().trim().equals(""))
			System.out.println("No Device Is Connected");

		if (!builder.toString().trim().equals("") || builder != null) {
			sdk = builder.toString().split("\n")[0].trim();

		}

		System.out.println(sdk);

		if (!sdk.trim().equals("")) {

			return sdk;
		}
		return "No Device Is Connected";

	}

	public static String runAdbCommandForMobileRelease(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		String release = "";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec("adb -s " + id + " shell getprop ro.build.version.release");
		process.waitFor();
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		String line;
		while ((line = br.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		br.close();
		if (builder.toString().trim().equals(""))
			System.out.println("No Device Is Connected");

		if (!builder.toString().trim().equals("") || builder != null) {
			release = builder.toString().split("\n")[0];

		}

		System.out.println(release);

		if (!release.trim().equals("")) {

			return release;
		}
		return "No Device Is Connected";

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
		String command = "adb -s " + id + " push " + VNCUtils.PreCompiledLibDirectory + "/libs/" + abi
				+ "/androidvncserver" + " " + VNCUtils.MobileLibDirectory; // give the source for precompiled directory
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
		String command = "adb -s " + id + " push  " + VNCUtils.PreCompiledLibDirectory + "/screenshot/" + abi
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
		String command = "adb -s " + id + " push  " + VNCUtils.PreCompiledLibDirectory + "/aosp/android-" + sdk + "/"
				+ abi + "/minicap.so" + "   " + MobileLibDirectory;
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
		String command = "cmd.exe /c start cmd.exe /k adb -s " + id
				+ " shell LD_LIBRARY_PATH=/data/local/tmp/pcloudy-libs  exec /data/local/tmp/pcloudy-libs/androidvncserver -s 100";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
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

	public static void installInputServiceApk(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("pushing inputservice.apk");
		String command = "adb -s " + id + " install " + ResourceDirectory + "inputservice.apk";
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
		String command = "java -jar " + ResourceDirectory + "opkeyvnc.jar" + " " + port + " " + deviceName + " " + id
				+ " " + ResourceDirectory;
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

	public static void StartInputService(String id) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("starting input service");
		String command = "adb -s " + id
				+ " shell export CLASSPATH=/data/app/com.pcloudy.inputservice-1/base.apk; exec app_process /system/bin com.pcloudy.inputservice.Agent";
		System.out.println("command is" + " " + command);
		StringBuilder builder = new StringBuilder();
		Thread.sleep(500);
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
}
