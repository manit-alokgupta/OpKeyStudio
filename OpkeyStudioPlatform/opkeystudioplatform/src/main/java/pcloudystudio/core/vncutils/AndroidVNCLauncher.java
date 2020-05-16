package pcloudystudio.core.vncutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class AndroidVNCLauncher {

	private String port = "5801";
	private String VncDirectory = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "VncServer"
			+ File.separator + "vncserver" + File.separator + "PreCompiled_libs";
	private String zipPath = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "VncServer"
			+ File.separator + "vncserver.zip";
	private String zipExtractPath = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "VncServer";

	public AndroidVNCLauncher() throws IOException, InterruptedException {
		AndroidVNCUtil.getInstance();

	}

	public void startVncServer(String deviceId, String deviceName, String deviceAbi, String deviceSdk)
			throws IOException, InterruptedException {

		Boolean directoryStatus = AndroidVNCUtil.checkIfDirectoryExist(VncDirectory);
		if (!directoryStatus) {
			try {
				copyZip();
				// Thread.sleep(2000);
				Utilities.getInstance().extractZipFolder(zipPath, zipExtractPath);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (AndroidVNCUtil.checkIfDeviceIsConnected(deviceId)) {

			Boolean mobileLibStatus = AndroidVNCUtil.checkIfMobileContainsLibFolder(deviceId);
			if (!mobileLibStatus) {
				AndroidVNCUtil.MakingRequiredDirectory(deviceId);

				AndroidVNCUtil.givePermissionToDirectory(deviceId);

				AndroidVNCUtil.pushVncServer(deviceId, deviceAbi);

				AndroidVNCUtil.grantPermissionToServer(deviceId);

				AndroidVNCUtil.pushScreenshotMinicap(deviceId, deviceAbi);

				AndroidVNCUtil.grantPermissionToMinicap(deviceId);

				AndroidVNCUtil.pushScreenshotMinicapSo(deviceId, deviceSdk, deviceAbi);

				AndroidVNCUtil.grantPermissionToMinicapSo(deviceId);

			} else {
				Boolean androidvncserverStaus = AndroidVNCUtil.checkIfMobileContainsAndroidVncServer(deviceId);
				if (!androidvncserverStaus) {
					AndroidVNCUtil.pushVncServer(deviceId, deviceAbi);
					AndroidVNCUtil.grantPermissionToServer(deviceId);

				}
				Boolean androidvMinicapStaus = AndroidVNCUtil.checkIfMobileContainsAndroidMinicapFile(deviceId);
				if (!androidvMinicapStaus) {
					AndroidVNCUtil.pushScreenshotMinicap(deviceId, deviceAbi);
					AndroidVNCUtil.grantPermissionToMinicap(deviceId);

				}

				Boolean androidvMinicapSoStaus = AndroidVNCUtil.checkIfMobileContainsAndroidMinicapSoFile(deviceId);
				if (!androidvMinicapSoStaus) {
					AndroidVNCUtil.pushScreenshotMinicapSo(deviceId, deviceSdk, deviceAbi);
					AndroidVNCUtil.grantPermissionToMinicapSo(deviceId);

				}

			}

			AndroidVNCUtil.startVncServer(deviceId);

		}

	}

	public static void Sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startMobicast(String deviceId, int deviceVersion, String deviceName, String deviceAbi, String deviceSdk)
			throws IOException, InterruptedException {

		if (AndroidVNCUtil.checkIfDeviceIsConnected(deviceId)) {
			AndroidVNCUtil.installInputServiceApk(deviceId, deviceVersion);
            String path=AndroidVNCUtil.getInputServicePath(deviceId) ;
			AndroidVNCUtil.StartInputService(deviceId,path);

			AndroidVNCUtil.PortForward(deviceId, port);

			AndroidVNCUtil.LunchVnc(deviceId, deviceName, port);
		}
	}

	public void copyZip() {
		String copyToPath = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "VncServer";
		try {
			File sourceVncZip = Utilities.getInstance().getVncZipFile();
			
			File targetFile = new File(copyToPath + "\\vncserver.zip");
			java.nio.file.Files.copy(sourceVncZip.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
