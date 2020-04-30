package pcloudystudio.core.utils;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MobileDeviceUtil {
	public static final String ANDROID_DEVICE_VERSION_PROPERTY = "ro.build.version.release";
	public static final String ANDROID_DEVICE_NAME_PROPERTY = "ro.product.model";
	public static final String ANDROID_DEVICE_API_LEVEL_PROPERTY = "ro.build.version.sdk";
	public static final String ANDROID_DEVICE_ABI_PROPERTY = "ro.product.cpu.abi";
	public static final String ANDROID_DEVICE_MANUFACTURER_PROPERTY = "ro.product.manufacturer";

	private static Map<String, String> devicesList;
	private static String devicePropertyOutput;

	public static Map<String, String> getAndroidDevices() throws Exception {
		String adbPath = System.getenv("ANDROID_SDK_HOME");
		if (adbPath == null)
			adbPath = System.getenv("ANDROID_HOME");
		if (adbPath != null) {
			final List<String> deviceIds = new ArrayList<String>();
			adbPath = String.valueOf(adbPath) + File.separator + "platform-tools" + File.separator + "adb";
			String[] cmd = { adbPath, "devices" };
			final ProcessBuilder pb = new ProcessBuilder(cmd);
			Process process = pb.start();
			process.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.toLowerCase().trim().contains("list of devices")
						&& line.toLowerCase().trim().contains("device")) {
					final String deviceId = line.split("\\s")[0];
					deviceIds.add(deviceId);
				}
			}
			br.close();
			devicesList = new HashMap<String, String>();
			for (final String id : deviceIds) {
				cmd = new String[] { adbPath, "-s", id, "shell", "getprop", ANDROID_DEVICE_MANUFACTURER_PROPERTY };
				pb.command(cmd);
				process = pb.start();
				process.waitFor();
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String deviceName = br.readLine();
				br.close();
				cmd = new String[] { adbPath, "-s", id, "shell", "getprop", ANDROID_DEVICE_NAME_PROPERTY };
				pb.command(cmd);
				process = pb.start();
				process.waitFor();
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				deviceName = String.valueOf(deviceName) + " " + br.readLine();
				br.close();
				cmd = new String[] { adbPath, "-s", id, "shell", "getprop", ANDROID_DEVICE_VERSION_PROPERTY };
				pb.command(cmd);
				process = pb.start();
				process.waitFor();
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				deviceName = String.valueOf(deviceName) + " " + br.readLine();
				br.close();
				deviceName = String.valueOf(deviceName) + " (Android)";
				devicesList.put(id, deviceName);
			}
		}
		return devicesList;
	}

	public static String getSelectedAndroidDeviceId(String selectedDeviceName) {
		String deviceID = null;
		if (selectedDeviceName != null || selectedDeviceName != "") {
			for (Entry<String, String> entry : MobileDeviceUtil.devicesList.entrySet()) {
				if (entry.getValue().equals(selectedDeviceName)) {
					deviceID = entry.getKey();
					break;
				}
			}
		}
		return deviceID;
	}

	public static String getDeviceProperty(String deviceID, String propertyName) throws Exception {
		String adbPath = System.getenv("ANDROID_SDK_HOME");
		if (adbPath == null)
			adbPath = System.getenv("ANDROID_HOME");
		if (adbPath != null) {
			adbPath = String.valueOf(adbPath) + File.separator + "platform-tools" + File.separator + "adb";
			String[] cmd = new String[] { adbPath, "-s", deviceID, "shell", "getprop", propertyName };
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.command(cmd);
			Process process = pb.start();
			process.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			devicePropertyOutput = br.readLine();
			br.close();
		}
		return devicePropertyOutput;
	}

}
