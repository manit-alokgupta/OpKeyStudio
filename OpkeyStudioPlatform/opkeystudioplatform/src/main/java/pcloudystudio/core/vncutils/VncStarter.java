package pcloudystudio.core.vncutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.IOUtils;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class VNCStarter {
	private String deviceId;
	private String deviceAbi;
	private String deviceSdk;
	private String deviceName;
	private String port = "5801";
	private String VncDirectory = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "VncServer"
			+ File.separator + "vncserver" + File.separator + "PreCompiled_libs";
	private String zipPath = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "VncServer"
			+ File.separator + "vncserver.zip";
	private String zipExtractPath = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "VncServer";

	public VNCStarter() throws IOException, InterruptedException {
		VNCUtils.getInstance();
		deviceId = VNCUtils.runAdbCommandForMobileId();
		deviceName = VNCUtils.runAdbCommandForMobileModel(deviceId);
		deviceAbi = VNCUtils.runAdbCommandForMobileAbi(deviceId);
		deviceSdk = VNCUtils.runAdbCommandForMobileSdk(deviceId);
	}

	public void startVncServer() throws IOException, InterruptedException {

		Boolean directoryStatus = VNCUtils.checkIfDirectoryExist(VncDirectory);
		if (!directoryStatus) {
			try {
				copyZip();
				//Thread.sleep(2000);
				Utilities.getInstance().extractZipFolder(zipPath, zipExtractPath);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (VNCUtils.checkIfDeviceIsConnected(deviceId)) {

			Boolean mobileLibStatus = VNCUtils.checkIfMobileContainsLibFolder(deviceId);
			if (!mobileLibStatus) {
				VNCUtils.MakingRequiredDirectory(deviceId);

				VNCUtils.givePermissionToDirectory(deviceId);

				VNCUtils.pushVncServer(deviceId, deviceAbi);

				VNCUtils.grantPermissionToServer(deviceId);

				VNCUtils.pushScreenshotMinicap(deviceId, deviceAbi);

				VNCUtils.grantPermissionToMinicap(deviceId);

				VNCUtils.pushScreenshotMinicapSo(deviceId, deviceSdk, deviceAbi);

				VNCUtils.grantPermissionToMinicapSo(deviceId);

			} else {
				Boolean androidvncserverStaus = VNCUtils.checkIfMobileContainsAndroidVncServer(deviceId);
				if (!androidvncserverStaus) {
					VNCUtils.pushVncServer(deviceId, deviceAbi);
					VNCUtils.grantPermissionToServer(deviceId);

				}
				Boolean androidvMinicapStaus = VNCUtils.checkIfMobileContainsAndroidVncServer(deviceId);
				if (!androidvMinicapStaus) {
					VNCUtils.pushScreenshotMinicap(deviceId, deviceAbi);
					VNCUtils.grantPermissionToMinicap(deviceId);

				}

				Boolean androidvMinicapSoStaus = VNCUtils.checkIfMobileContainsAndroidVncServer(deviceId);
				if (!androidvMinicapSoStaus) {
					VNCUtils.pushScreenshotMinicapSo(deviceId, deviceAbi, deviceAbi);
					VNCUtils.grantPermissionToMinicapSo(deviceId);

				}

			}

			VNCUtils.startVncServer(deviceId);

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
		 
		if (VNCUtils.checkIfDeviceIsConnected(deviceId)) {
			VNCUtils.installInputServiceApk(deviceId);

			VNCUtils.StartInputService(deviceId);

			VNCUtils.PortForward(deviceId, port);

			VNCUtils.LunchVnc(deviceId, deviceName, port);
		}
	}

	public void copyZip() {
		URL url;
		String copyToPath = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "VncServer";
		try {
			url = new URL("platform:/plugin/OpKeyStudio/resources/vncserver.zip");
			InputStream inputStream = url.openConnection().getInputStream();
			File targetFile = new File(copyToPath + "\\vncserver.zip");
			java.nio.file.Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			IOUtils.closeQuietly(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
