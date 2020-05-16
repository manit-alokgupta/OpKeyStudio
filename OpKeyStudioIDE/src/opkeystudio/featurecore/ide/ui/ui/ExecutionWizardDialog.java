package opkeystudio.featurecore.ide.ui.ui;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.exceptions.SetupConfigurationException;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSession;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;
import pcloudystudio.appium.AppiumConfiguration;
import pcloudystudio.appium.MobileDesiredCapabilities;
import pcloudystudio.core.utils.MobileDeviceUtil;
import pcloudystudio.core.utils.notification.CustomNotificationUtil;
import pcloudystudio.core.vncutils.AndroidVNCLauncher;
import pcloudystudio.pcloudystudio.core.execution.MobileDeviceExecutionDetail;
import pcloudystudio.resources.constant.ImageConstants;

public class ExecutionWizardDialog extends TitleAreaDialog {
	private Combo pluginSelectionDropDown;
	private Combo androidDeviceSelectionDropDown;
	private Button runButton;
	private TestCaseView parentTestCaseView;
	private TestSuiteView parentTestSuiteView;
	private ArtifactCodeView parentArtifactCodeView;
	private boolean executingFromTestCaseView;
	private boolean executingFromTestSuiteView;
	private boolean executingFromGenericEditor;
	private Text sessionNameTextField;
	private Text buildNameTextField;
	private Label lblDeviceSelection;

	private ExecutionSession executionSession;
	private Composite container;
	private Composite area;
	private Button btnRefreshDeviceList;
	private boolean isAppiumPluginExecution;
	private boolean isCFLArtifact;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public ExecutionWizardDialog(Shell parentShell, TestCaseView parentTestCaseView) {
		super(parentShell);
		this.setParentTestCaseView(parentTestCaseView);
		this.setExecutingFromTestCaseView(true);
		setHelpAvailable(false);
		initExecutionSession(parentTestCaseView.getCurrentArtifact());
	}

	public ExecutionWizardDialog(Shell parentShell, TestSuiteView parentTestSuiteView) {
		super(parentShell);
		this.setParentTestSuiteView(parentTestSuiteView);
		this.setExecutingFromTestSuiteView(true);
		setHelpAvailable(false);
		initExecutionSession(parentTestSuiteView.getCurrentArtifact());
	}

	public ExecutionWizardDialog(Shell parentShell, ArtifactCodeView parentArtifactCodeView,
			boolean executingFromGenericEditor) {
		super(parentShell);
		this.setParentArtifactCodeView(parentArtifactCodeView);
		this.setExecutingFromGenericEditor(executingFromGenericEditor);
		setHelpAvailable(false);
		initExecutionSession(parentArtifactCodeView.getCodeViewFile());
	}

	public ExecutionWizardDialog(Shell parentShell, ArtifactCodeView parentArtifactCodeView,
			boolean executingFromGenericEditor, boolean isCflEditor) {
		super(parentShell);
		setCFLArtifact(true);
		this.setParentArtifactCodeView(parentArtifactCodeView);
		setHelpAvailable(false);
		initExecutionSession(parentArtifactCodeView.getCurrentArtifact());
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Use this wizard to execute your Web or Mobile Tests");
		area = (Composite) super.createDialogArea(parent);
		container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(15, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 15, 1));
		lblNewLabel.setText("Session Name:");

		sessionNameTextField = new Text(container, SWT.BORDER);
		sessionNameTextField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 15, 1));
		sessionNameTextField.setText(getExecutionSession().getSessionName());
		sessionNameTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				getExecutionSession().setSessionName(sessionNameTextField.getText());
				checkIfRunButtonBeEnabled();
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		sessionNameTextField.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				restrictInputString(e, "sessionNameTextField");
			}
		});
		sessionNameTextField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (sessionNameTextField.getText().equals("")) {
					runButton.setEnabled(false);
					checkIfRunButtonBeEnabled(); // CustomNotificationUtil.openInformationNotification("OpKey", "Session
					// Name Cannot Be Empty");
					sessionNameTextField.setFocus();
				} else {
					if (pluginSelectionDropDown.getText().equals("Web") && !buildNameTextField.getText().equals(""))

						runButton.setEnabled(true);
				}
			}

		});

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 15, 1));
		lblNewLabel_1.setText("Build Name:");

		buildNameTextField = new Text(container, SWT.BORDER);
		buildNameTextField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 15, 1));
		buildNameTextField.setText(getExecutionSession().getBuildName());
		buildNameTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				getExecutionSession().setBuildName(buildNameTextField.getText());

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		buildNameTextField.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				restrictInputString(e, "buildNameTextField");
			}
		});
		buildNameTextField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (buildNameTextField.getText().equals("")) {
					runButton.setEnabled(false);
					checkIfRunButtonBeEnabled();// CustomNotificationUtil.openInformationNotification("OpKey", "Build
					// Name Cannot Be Empty");
					buildNameTextField.setFocus();
				} else {
					if (pluginSelectionDropDown.getText().equals("Web") && !sessionNameTextField.getText().equals(""))

						runButton.setEnabled(true);
				}
			}

		});

		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 15, 1));
		lblNewLabel_2.setText("Plugin:");
		isAppiumPluginExecution = false;
		pluginSelectionDropDown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		pluginSelectionDropDown.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 15, 1));
		pluginSelectionDropDown.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					opkeystudio.core.utils.Utilities.getInstance().setShellCursor(pluginSelectionDropDown.getShell(),
							SWT.CURSOR_WAIT);

					checkIfRunButtonBeEnabled();
					int index = pluginSelectionDropDown.getSelectionIndex();
					if (index == 0) {
						return;
					}
					String pluginName = pluginSelectionDropDown.getItem(index);
					getExecutionSession().setPluginName(pluginName);

					if (pluginName.contentEquals("Appium")) {
						initDeviceNames();
						lblDeviceSelection.setVisible(true);
						btnRefreshDeviceList.setVisible(true);
						androidDeviceSelectionDropDown.setVisible(true);
						isAppiumPluginExecution = true;
					} else {
						lblDeviceSelection.setVisible(false);
						btnRefreshDeviceList.setVisible(false);
						androidDeviceSelectionDropDown.setVisible(false);
						isAppiumPluginExecution = false;
					}

					checkIfRunButtonBeEnabled();
				} finally {
					opkeystudio.core.utils.Utilities.getInstance().setShellCursor(pluginSelectionDropDown.getShell(),
							SWT.CURSOR_ARROW);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		lblDeviceSelection = new Label(container, SWT.NONE);
		lblDeviceSelection.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 15, 1));
		lblDeviceSelection.setVisible(false);
		lblDeviceSelection.setText("Device:");

		androidDeviceSelectionDropDown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		androidDeviceSelectionDropDown.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 14, 1));
		androidDeviceSelectionDropDown.setVisible(false);

		btnRefreshDeviceList = new Button(container, SWT.NONE);
		btnRefreshDeviceList.setToolTipText("Refresh");
		btnRefreshDeviceList.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnRefreshDeviceList.setImage(ImageConstants.IMG_16_REFRESH_Button);
		btnRefreshDeviceList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					opkeystudio.core.utils.Utilities.getInstance().setShellCursor(btnRefreshDeviceList.getShell(),
							SWT.CURSOR_WAIT);

					initDeviceNames();
				} finally {

					opkeystudio.core.utils.Utilities.getInstance().setShellCursor(btnRefreshDeviceList.getShell(),
							SWT.CURSOR_ARROW);

				}
			}
		});
		btnRefreshDeviceList.setVisible(false);

		androidDeviceSelectionDropDown.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkIfRunButtonBeEnabled();
				int index = androidDeviceSelectionDropDown.getSelectionIndex();
				if (index == 0) {
					return;
				} else {
					String deviceID = MobileDeviceUtil
							.getSelectedAndroidDeviceId(androidDeviceSelectionDropDown.getText());
					getMobileDeviceExecutionDetail().setDeviceId(deviceID);
					try {
						getMobileDeviceExecutionDetail().setDeviceName(MobileDeviceUtil.getDeviceProperty(deviceID,
								MobileDeviceUtil.ANDROID_DEVICE_NAME_PROPERTY));
						getMobileDeviceExecutionDetail().setDeviceVersion(MobileDeviceUtil.getDeviceProperty(deviceID,
								MobileDeviceUtil.ANDROID_DEVICE_VERSION_PROPERTY));
						getMobileDeviceExecutionDetail().setDeviceAPILevel(MobileDeviceUtil.getDeviceProperty(deviceID,
								MobileDeviceUtil.ANDROID_DEVICE_API_LEVEL_PROPERTY));
						getMobileDeviceExecutionDetail().setDeviceABI(MobileDeviceUtil.getDeviceProperty(deviceID,
								MobileDeviceUtil.ANDROID_DEVICE_ABI_PROPERTY));
					} catch (Exception ex) {
						CustomNotificationUtil.openErrorNotificationDialog("OpKey", ex.getMessage());
					}

					ExecutionSession session = getExecutionSession();
					session.addPluginSetting("DeviceID", getMobileDeviceExecutionDetail().getDeviceId());
					session.addPluginSetting("DeviceName", getMobileDeviceExecutionDetail().getDeviceName());
					session.addPluginSetting("DeviceVersion", getMobileDeviceExecutionDetail().getDeviceVersion());
					session.addPluginSetting("DeviceApiLevel", getMobileDeviceExecutionDetail().getDeviceAPILevel());
					session.addPluginSetting("DeviceABI", getMobileDeviceExecutionDetail().getDeviceABI());
					session.addPluginSetting("appiumHost", AppiumConfiguration.getHostAddress());
					session.addPluginSetting("appiumPort", AppiumConfiguration.getPort());
					session.addPluginSetting("appiumDir", AppiumConfiguration.getAppiumDirectory());

					MobileDevice device = new MobileDevice();
					device.setDisplayName(getMobileDeviceExecutionDetail().getDeviceName());
					device.setIPAddress("");
					device.setOperatingSystem("Android");
					device.setSerialNumber(getMobileDeviceExecutionDetail().getDeviceId());
					device.setVersion(getMobileDeviceExecutionDetail().getDeviceVersion());
					session.setMobileDevice(device);
					
					LinkedHashMap<String, String> capabilities = new LinkedHashMap<String, String>();
					try {
						if (MobileDesiredCapabilities.getMapOfCapabilities() != null) {
							for (String capabilityName : MobileDesiredCapabilities.getMapOfCapabilities().keySet()) {
								capabilities.put(capabilityName,
										MobileDesiredCapabilities.getMapOfCapabilities().get(capabilityName));
								System.out.println(">> Capability added: " + capabilityName);
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					System.out.println(">> Capabilities Size: " + capabilities.size());
					session.setMobileCapabilities(capabilities);
					System.out.println(
							">> Capabilities Size in Execution Session: " + session.getMobileCapabilities().size());
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		setTitle("Execution Wizard");
		try {
			initPluginNames();
		} catch (SetupConfigurationException e1) {
			CustomNotificationUtil.openErrorNotificationDialog("OpKey", e1.getMessage());
		}
		return area;
	}

	private void initExecutionSession(Artifact artifact) {
		ExecutionSession eSession = new ExecutionSession(artifact.getName() + "_", "Build_");
		eSession.setArtifact(artifact);
		eSession.setCflArtifact(isCFLArtifact);
		setExecutionSession(eSession);
	}

	private void initExecutionSession(File artifactFile) {
		System.out.println("Artifact File path " + artifactFile.getAbsolutePath());
		String artifactPath = artifactFile.getAbsolutePath();
		artifactPath = artifactPath.replaceAll("\\\\", "OPKEY_SLASH");

		String codeDir = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().getProjectsFolder();
		codeDir = codeDir + File.separator + ServiceRepository.getInstance().getProjectName();
		codeDir = codeDir + File.separator + "codes" + File.separator;

		String artifactPackageClass = codeDir.replaceAll("\\\\", "OPKEY_SLASH").replaceAll("/", "OPKEY_SLASH");

		String packageClassName = artifactPath.replaceAll(artifactPackageClass, "");
		packageClassName = packageClassName.split("\\.")[0];
		packageClassName = packageClassName.replaceAll("OPKEY_SLASH", ".");
		System.out.println("Package Name " + packageClassName);
		ExecutionSession eSession = new ExecutionSession(artifactFile.getName() + "_", "Build_");
		eSession.setArtifactFilePackageClass(packageClassName);
		eSession.setArtifactCodeDirPath(codeDir);

		String packagePath = packageClassName.replaceAll("\\.", "\\\\") + ".java";
		eSession.setArtifactJavaFilePath(packagePath);
		setExecutionSession(eSession);
	}

	private void initPluginNames() throws SetupConfigurationException {
		pluginSelectionDropDown.removeAll();
		pluginSelectionDropDown.add("Select Plugin");
		String pluginDir = Utilities.getInstance().getDefaultPluginsDir();
		File pluginDirObject = new File(pluginDir);
		if (pluginDirObject.listFiles() == null)
			throw new SetupConfigurationException("Plugin Dir is Empty: " + pluginDirObject.getAbsolutePath());

		for (File file : pluginDirObject.listFiles()) {
			if (file.isDirectory()) {
				String pluginXmlFile = file.getAbsolutePath() + File.separator + "plugin.xml";
				if (!new File(pluginXmlFile).exists()) {
					continue;
				}
				pluginSelectionDropDown.add(file.getName());
			}
		}
		pluginSelectionDropDown.select(0);
	}

	private void initDeviceNames() {

		runButton.setEnabled(false);
		androidDeviceSelectionDropDown.removeAll();
		androidDeviceSelectionDropDown.add("Select Device");
		try {
			Map<String, String> devicesList = MobileDeviceUtil.getAndroidDevices();
			if (devicesList.size() > 0) {
				for (Map.Entry<String, String> deviceEntry : devicesList.entrySet())
					androidDeviceSelectionDropDown.add(deviceEntry.getValue());
			}
			androidDeviceSelectionDropDown.select(0);
		} catch (Exception ex) {
			if (ex.getMessage() == null)
				CustomNotificationUtil.openErrorNotificationDialog("OpKey", "No device connected!" + "\n"
						+ "Make sure ANORID_HOME or ANORID_SDK_HOME is set in Environment Variable for Android SDK!");
			else
				CustomNotificationUtil.openErrorNotificationDialog("OpKey", "No device connected!");
		}

	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		runButton = createButton(parent, IDialogConstants.OK_ID, "Run", true);
		runButton.setEnabled(false);

		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				new MessageDialogs().executeDisplayAsync(new Runnable() {

					@Override
					public void run() {

						try {

							executeSession();
							// start VNC if Appium
							if (isAppiumPluginExecution) {
								try {
									startVnc();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
							container.dispose();
							area.dispose();
							close();

						} catch (IOException ex) {

							CustomNotificationUtil.openErrorNotificationDialog("OpKey", ex.getMessage());
						}

					}
				});

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				e.doit = false;
			}
		});

		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

		cancelButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private void executeSession() throws IOException {

		ExecutionSession executionSession = getExecutionSession();
		File newSessionFolder = new File(Utilities.getInstance().getSessionsFolder(),
				executionSession.getSessionName());
		if (newSessionFolder.exists())
			throw new IOException("Session '" + executionSession.getSessionName() + "' already exists at "
					+ newSessionFolder.getParentFile().getAbsolutePath());

		EPartService partService = opkeystudio.core.utils.Utilities.getInstance().getEpartService();
		MPart part = partService.createPart("opkeystudio.partdescriptor.executionResultPart");
		part.setLabel(executionSession.getSessionName());
		part.setTooltip(executionSession.getSessionName());
		part.getTransientData().put("opkeystudio.executionSessionData", executionSession);
		partService.showPart(part, PartState.ACTIVATE);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(650, 540);
	}

	public TestSuiteView getParentTestSuiteView() {
		return parentTestSuiteView;
	}

	public void setParentTestSuiteView(TestSuiteView parentTestSuiteView) {
		this.parentTestSuiteView = parentTestSuiteView;
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

	public boolean isExecutingFromTestCaseView() {
		return executingFromTestCaseView;
	}

	public void setExecutingFromTestCaseView(boolean executingFromTestCaseView) {
		this.executingFromTestCaseView = executingFromTestCaseView;
	}

	public boolean isExecutingFromTestSuiteView() {
		return executingFromTestSuiteView;
	}

	public void setExecutingFromTestSuiteView(boolean executingFromTestSuiteView) {
		this.executingFromTestSuiteView = executingFromTestSuiteView;
	}

	public ExecutionSession getExecutionSession() {
		return executionSession;
	}

	public void setExecutionSession(ExecutionSession executionSession) {
		this.executionSession = executionSession;
	}

	public MobileDeviceExecutionDetail getMobileDeviceExecutionDetail() {
		return MobileDeviceExecutionDetail.getInstance();
	}

	public void startVnc() throws IOException, InterruptedException {
		AndroidVNCLauncher starter = new AndroidVNCLauncher();
		java.util.concurrent.Executors.newSingleThreadExecutor().execute(new Runnable() {
			public void run() {
				try {
					starter.startVncServer(getMobileDeviceExecutionDetail().getDeviceId(),
							getMobileDeviceExecutionDetail().getDeviceName(),
							getMobileDeviceExecutionDetail().getDeviceABI(),
							getMobileDeviceExecutionDetail().getDeviceAPILevel());

				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		java.util.concurrent.Executors.newSingleThreadExecutor().execute(new Runnable() {
			public void run() {
				try {
					int version = 0;
					if (getMobileDeviceExecutionDetail().getDeviceVersion().indexOf('.') != -1)
						version = Integer.valueOf(getMobileDeviceExecutionDetail().getDeviceVersion().substring(0,
								getMobileDeviceExecutionDetail().getDeviceVersion().indexOf('.')));
					else
						version = Integer.valueOf(getMobileDeviceExecutionDetail().getDeviceVersion());
					starter.startMobicast(getMobileDeviceExecutionDetail().getDeviceId(), version,
							getMobileDeviceExecutionDetail().getDeviceName(),
							getMobileDeviceExecutionDetail().getDeviceABI(),
							getMobileDeviceExecutionDetail().getDeviceAPILevel());
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public ArtifactCodeView getParentArtifactCodeView() {
		return parentArtifactCodeView;
	}

	public void setParentArtifactCodeView(ArtifactCodeView parentArtifactCodeView) {
		this.parentArtifactCodeView = parentArtifactCodeView;
	}

	public boolean isExecutingFromGenericEditor() {
		return executingFromGenericEditor;
	}

	public void setExecutingFromGenericEditor(boolean executingFromGenericEditor) {
		this.executingFromGenericEditor = executingFromGenericEditor;
	}

	public boolean isCFLArtifact() {
		return isCFLArtifact;
	}

	public void setCFLArtifact(boolean isCFLArtifact) {
		this.isCFLArtifact = isCFLArtifact;
	}

	protected void restrictInputString(VerifyEvent event, String field) {
		Boolean isAllowed = false;
		int textLength = 0;
		if (field.equals("sessionNameTextField"))
			textLength = sessionNameTextField.getText().length();

		if (field.equals("buildNameTextField"))
			textLength = buildNameTextField.getText().length();

		if (textLength <= 100) {
			isAllowed = true;
			event.doit = true;
		} else {

			if (event.keyCode == 8 && textLength >= 100) { // keycode 8 represents backspace
				isAllowed = true;
				event.doit = true;
				return;
			}

			isAllowed = false;
		}

		if (!isAllowed) {
			event.doit = false;
			return;
		}
	}

	private void checkIfRunButtonBeEnabled() {

		File newSessionFolder = new File(Utilities.getInstance().getSessionsFolder(), sessionNameTextField.getText());
		if (!sessionNameTextField.getText().equals("")) {
			if (newSessionFolder.exists()) {
				this.setErrorMessage("Session '" + executionSession.getSessionName() + "' already exists at "
						+ newSessionFolder.getParentFile().getAbsolutePath());
				runButton.setEnabled(false);
				return;
			}
		} else {
			this.setErrorMessage("SessionName Cannot Be Empty");
			runButton.setEnabled(false);
			return;
		}

		if (buildNameTextField.getText().equals("")) {
			runButton.setEnabled(false);
			this.setErrorMessage("Build Name Cannot Be Empty.");
			return;
		}

		int index = pluginSelectionDropDown.getSelectionIndex();
		if (index == 0) {
			runButton.setEnabled(false);
			this.setErrorMessage("Plugin not selected.");
			return;
		}

		if (androidDeviceSelectionDropDown.getVisible() && androidDeviceSelectionDropDown.getSelectionIndex() == 0) {
			runButton.setEnabled(false);
			this.setErrorMessage("Select a MobileDevice");
			return;

		}
		this.setErrorMessage(null);

		runButton.setEnabled(true);
	}
}