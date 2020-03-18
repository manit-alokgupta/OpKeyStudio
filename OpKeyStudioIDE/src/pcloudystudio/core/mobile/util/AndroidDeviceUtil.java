package pcloudystudio.core.mobile.util;

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

public class AndroidDeviceUtil {
	private static Map<String, String> devicesList;
	private static String deviceName;

	public static Map<String, String> getAndroidDevices() throws Exception {
		String adbPath = System.getenv("ANDROID_SDK_HOME");
		if (adbPath == null)
			System.getenv("ANDROID_HOME");
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
				cmd = new String[] { adbPath, "-s", id, "shell", "getprop", "ro.product.manufacturer" };
				pb.command(cmd);
				process = pb.start();
				process.waitFor();
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String deviceName = br.readLine();
				br.close();
				cmd = new String[] { adbPath, "-s", id, "shell", "getprop", "ro.product.model" };
				pb.command(cmd);
				process = pb.start();
				process.waitFor();
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				deviceName = String.valueOf(deviceName) + " " + br.readLine();
				br.close();
				cmd = new String[] { adbPath, "-s", id, "shell", "getprop", "ro.build.version.release" };
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

	public static String getDeviceName(String deviceID) throws Exception {
		String adbPath = System.getenv("ANDROID_SDK_HOME");
		if (adbPath == null)
			System.getenv("ANDROID_HOME");
		if (adbPath != null) {
			adbPath = String.valueOf(adbPath) + File.separator + "platform-tools" + File.separator + "adb";
			String[] cmd = new String[] { adbPath, "-s", deviceID, "shell", "getprop", "ro.product.model" };
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.command(cmd);
			Process process = pb.start();
			process.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			deviceName = br.readLine();
			br.close();
		}
		return deviceName;
	}

	public static String getSelectedAndroidDeviceId(String selectedDeviceName) {
		String deviceID = null;
		if (selectedDeviceName != null || selectedDeviceName != "") {
			for (Entry<String, String> entry : AndroidDeviceUtil.devicesList.entrySet()) {
				if (entry.getValue().equals(selectedDeviceName)) {
					deviceID = entry.getKey();
					break;
				}
			}
		}
		return deviceID;
	}
}
