package pcloudystudio.commandhandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import pcloudystudio.core.utils.MobileDeviceUtil;
import pcloudystudio.core.vncutils.AndroidVNCLauncher;

public class AndroidVNCLauncherHandler {
	private Map<String, String> devicesList;
	String deviceName = "";
	String deviceId = "";
	String deviceAbi = "";
	String deviceSdk = "";

	@Execute
	public void execute(Shell parentShell) throws IOException, InterruptedException {
		try {
			devicesList = MobileDeviceUtil.getAndroidDevices();
			Set<Map.Entry<String, String>> set = devicesList.entrySet();
			for (Map.Entry<String, String> entry : set) {
				deviceId = entry.getKey().toString();
				System.out.println("id is " + deviceId);
				break;
			}
			deviceName = MobileDeviceUtil.getDeviceProperty(deviceId, MobileDeviceUtil.ANDROID_DEVICE_NAME_PROPERTY);
			deviceAbi = MobileDeviceUtil.getDeviceProperty(deviceId, MobileDeviceUtil.ANDROID_DEVICE_ABI_PROPERTY);
			deviceSdk = MobileDeviceUtil.getDeviceProperty(deviceId,
					MobileDeviceUtil.ANDROID_DEVICE_API_LEVEL_PROPERTY);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AndroidVNCLauncher starter = new AndroidVNCLauncher();

		java.util.concurrent.Executors.newSingleThreadExecutor().execute(new Runnable() {
			public void run() {
				try {
					starter.startVncServer(deviceId, deviceName, deviceAbi, deviceSdk);

				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		Thread.sleep(5000);

		java.util.concurrent.Executors.newSingleThreadExecutor().execute(new Runnable() {
			public void run() {
				try {
					starter.startMobicast(deviceId, deviceName, deviceAbi, deviceSdk);

				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
}