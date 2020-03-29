package pcloudystudio.core.vncutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class VncUtils {

	private static VncUtils obj;
	private static String DeviceidCommand = "adb get-serialno";
	private static String DeviceModelCommand = "adb shell getprop ro.product.model";
	private static String DeviceSdkCommand = "adb shell getprop ro.build.version.sdk";
	private static String DeviceAbiCommand = "adb shell getprop ro.product.cpu.abi";
	private static String DeviceReleaseCommand = "adb shell getprop ro.build.version.release";
	private static String PreCompiledDirectory = " E:/vncserver/PreCompiled_libs";
	private static String ResourceDirectory = "E:/vncserver/resources/";
	private static String MobilielibDirectory = "/data/local/tmp/pcloudy-libs";

	private VncUtils() {
	}

	public static VncUtils getInstance() {
		if (obj == null)
			return new VncUtils();
		return obj;
	}

	public static boolean checkIfDeviceIsConnected() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb devices";
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

	public static boolean checkIfMobileContainsLibFolder() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb shell ls " + MobilielibDirectory;
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
		if (builder.toString().trim().equals("")) {
			return false;
		} else if (builder.toString().contains("androidvncserver"))
			return true;

		else if (builder.toString().contains("minicap"))
			return true;
		else if (builder.toString().contains("minicap.so"))
			return true;
		else
			return false;
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

	public static String runAdbCommandForMobileModel() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		String model = "";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(DeviceModelCommand);
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

	public static String runAdbCommandForMobileAbi() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		String abi = "";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(DeviceAbiCommand);
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

	public static String runAdbCommandForMobileSdk() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		String sdk = "";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(DeviceSdkCommand);
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

	public static String runAdbCommandForMobileRelease() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		String id = "";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(DeviceReleaseCommand);
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

	public static void MakingRequiredDirectory() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Making lib directory in ");
		String makeDircmd = "adb shell mkdir " + MobilielibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(makeDircmd);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void givePermissionToDirectory() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Granting permission to Lib directory");
		String command = "adb shell chmod 777 " + MobilielibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void pushVncServer(String abi) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("pushing vnc server");
		String command = "adb push" + VncUtils.PreCompiledDirectory + "/libs/" + abi + "/androidvncserver" + " "
				+ VncUtils.MobilielibDirectory; // give the source for precompiled directory
		System.out.println("command is  " + command);
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void grantPermissionToServer() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("giving permision to vnc server");
		String command = "adb shell chmod 777 " + MobilielibDirectory + "/androidvncserver";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void pushScreenshotMinicap(String abi) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("pushing minicap file");
		String command = "adb push  " + VncUtils.PreCompiledDirectory + "/screenshot/" + abi + "/minicap" + "  "
				+ MobilielibDirectory;
		System.out.println("command is  " + command);
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void grantPermissionToMinicap() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("giving permision to vnc minicap");
		String command = "adb shell chmod 777 " + MobilielibDirectory + "/minicap";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void pushScreenshotMinicapSo(String sdk, String abi) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("pushing minicapso file");
		String command = "adb  push  " + VncUtils.PreCompiledDirectory + "/aosp/android-" + sdk + "/" + abi
				+ "/minicap.so" + "   " + MobilielibDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void grantPermissionToMinicapSo() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("giving permision to minicapso");
		String command = "adb shell chmod 777 /data/local/tmp/pcloudy-libs/minicap.so";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			System.out.println(builder);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void startVncServer() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Starting Vnc Server");
		String command = "cmd.exe /c start cmd.exe /k adb shell LD_LIBRARY_PATH=/data/local/tmp/pcloudy-libs  exec /data/local/tmp/pcloudy-libs/androidvncserver -s 100"
				+ "/";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		// process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

	}

	public static void PortForward(String port) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("forwarding port 5801 to port 5901");
		String command = "adb forward tcp:" + port + " " + "tcp:5901";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void pushInputServiceApk() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("pushing inputservice.apk");
		String command = "adb push " + ResourceDirectory + "inputservice.apk";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void pushOpkeyVncJar() throws IOException, InterruptedException {
		System.out.println("pushing pussing opkeyvnc jar");
		String command = "adb push " + ResourceDirectory + "opkeyvnc.jar";
		Runtime runtime = Runtime.getRuntime();
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void LunchVnc(String deviceName, String serialNo, String port)
			throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Launching Vnc");
		String command = "java -jar " + ResourceDirectory + "opkeyvnc.jar" + " " + port + " " + deviceName + " "
				+ serialNo + " " + ResourceDirectory;
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

	}

	public static void StartInputService(String serialNo) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("starting input service");
		String command = "adb -s " + serialNo
				+ " shell export CLASSPATH=/data/app/com.pcloudy.inputservice-1/base.apk; exec app_process /system/bin com.pcloudy.inputservice.Agent";
		StringBuilder builder = new StringBuilder();
		Process process = runtime.exec(command);
		process.waitFor();
		String line;
		BufferedReader stdError = new BufferedReader(
				new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
		while ((line = stdError.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}

		stdError.close();

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

		File tmpDir = new File(PreCompiledDirectory);
		boolean exists = tmpDir.exists();
		boolean isDirectory = tmpDir.isDirectory();
		if (exists && isDirectory) {
			System.out.println(PreCompiledDirectory + " " + "is a directory");
			return true;
		} else
			return false;

	}

	public static boolean checkIfMobileContainsAndroidVncServer() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb shell ls " + MobilielibDirectory;
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

	public static boolean checkIfMobileContainsAndroidMinicapFile() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb shell ls " + MobilielibDirectory;
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

	public static boolean checkIfMobileContainsAndroidMinicapSoFile() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		String command = "adb shell ls " + MobilielibDirectory;
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

	public static void RemoveMobileLibDirectory() throws IOException, InterruptedException {
		System.out.println("removing the directory first");
		Runtime runtime = Runtime.getRuntime();
		String command = "adb shell rm -r " + MobilielibDirectory;
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

	public static boolean VerifyMobileLibDirectoryRemoved() throws IOException, InterruptedException {
		System.out.println("verifying the libdirectory is removed");
		Runtime runtime = Runtime.getRuntime();
		String command = "adb shell ls " + MobilielibDirectory;
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
}
