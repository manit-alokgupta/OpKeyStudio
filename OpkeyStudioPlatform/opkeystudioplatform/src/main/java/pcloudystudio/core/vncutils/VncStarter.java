package pcloudystudio.core.vncutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.IOUtils;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class VncStarter {
	private String deviceId;
	private String deviceAbi;
	private String deviceSdk;
	private String deviceName;
	private String port = "5801";

	public VncStarter() throws IOException, InterruptedException {
		VncUtils.getInstance();
		deviceId = VncUtils.runAdbCommandForMobileId();
		deviceName = VncUtils.runAdbCommandForMobileModel(deviceId);
		deviceAbi = VncUtils.runAdbCommandForMobileAbi(deviceId);
		deviceSdk = VncUtils.runAdbCommandForMobileSdk(deviceId);
	}

	public void startVncServer() throws IOException, InterruptedException {

		if (VncUtils.checkIfDeviceIsConnected(deviceId)) {

			Boolean mobileLibStatus = VncUtils.checkIfMobileContainsLibFolder(deviceId);
			if (!mobileLibStatus) {
				VncUtils.MakingRequiredDirectory(deviceId);

				VncUtils.givePermissionToDirectory(deviceId);

				VncUtils.pushVncServer(deviceId, deviceAbi);

				VncUtils.grantPermissionToServer(deviceId);

				VncUtils.pushScreenshotMinicap(deviceId, deviceAbi);

				VncUtils.grantPermissionToMinicap(deviceId);

				VncUtils.pushScreenshotMinicapSo(deviceId, deviceSdk, deviceAbi);

				VncUtils.grantPermissionToMinicapSo(deviceId);

			} else {
				Boolean androidvncserverStaus = VncUtils.checkIfMobileContainsAndroidVncServer(deviceId);
				if (!androidvncserverStaus) {
					VncUtils.pushVncServer(deviceId, deviceAbi);
					VncUtils.grantPermissionToServer(deviceId);

				}
				Boolean androidvMinicapStaus = VncUtils.checkIfMobileContainsAndroidVncServer(deviceId);
				if (!androidvMinicapStaus) {
					VncUtils.pushScreenshotMinicap(deviceId, deviceAbi);
					VncUtils.grantPermissionToMinicap(deviceId);

				}

				Boolean androidvMinicapSoStaus = VncUtils.checkIfMobileContainsAndroidVncServer(deviceId);
				if (!androidvMinicapSoStaus) {
					VncUtils.pushScreenshotMinicapSo(deviceId, deviceAbi, deviceAbi);
					VncUtils.grantPermissionToMinicapSo(deviceId);

				}

			}

			VncUtils.startVncServer(deviceId);

		}

	}

	public static void Sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startMobicast() throws IOException, InterruptedException {
		if (VncUtils.checkIfDeviceIsConnected(deviceId)) {
			VncUtils.installInputServiceApk(deviceId);

			VncUtils.StartInputService(deviceId);

			VncUtils.PortForward(deviceId, port);

			VncUtils.LunchVnc(deviceId, deviceName, port);
		}
	}

	public void copyZip() {
		URL url;
		String copyToPath = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "VncServer";
		try {
			url = new URL("platform:/plugin/OpKeyStudio/resources/vncserver.zip");
			InputStream inputStream = url.openConnection().getInputStream();
			File targetFile = new File(copyToPath + "\\package.zip");
			java.nio.file.Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			IOUtils.closeQuietly(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
