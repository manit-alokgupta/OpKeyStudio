package opkeystudio.featurecore.ide.ui.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.commandhandler.ProjectImporter;
import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dto.project.ProjectFile;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ProjectOpenDialog extends TitleAreaDialog {

	private Shell shlOpenProject;

	public ProjectOpenDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.DIALOG_TRIM | SWT.SYSTEM_MODAL | SWT.RESIZE);
		setParentshell(parentShell);
	}

	private Text projectSearch;
	private Table table;
	// private Text text_1;
	private org.eclipse.swt.widgets.Button openButton;
	private String[] tableHeaders = { "Project" };
	private List<ProjectFile> allProjects = new ArrayList<>();
	private ProjectFile selectedProject;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		getShell().setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"));
		setMessage("Select a Project to Open");
		setTitle("Open Project");

		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		SashForm sashForm = new SashForm(container, SWT.BORDER | SWT.SMOOTH);

		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(new GridLayout(1, false));

		projectSearch = new Text(composite, SWT.BORDER);
		projectSearch.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		projectSearch.setMessage("Search Project");
		projectSearch.setText("Search Project");
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		sashForm.setWeights(new int[] { 1 });
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(table, 0);
			column.setText(header);
		}
		table.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			table.getColumn(i).pack();
		}

		table.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width);
				}
			}
		});

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem = table.getSelection()[0];
				if (tableItem == null) {
					toggleOpenButton(false);
					return;
				}
				CustomTableItem cti = (CustomTableItem) tableItem;
				ProjectFile sproject = (ProjectFile) cti.getControlData();
				setSelectedProject(sproject);
				toggleOpenButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		projectSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				filterProjectTable(projectSearch.getText());
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		try {
			renderProjectList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return area;
	}

	private void toggleOpenButton(boolean status) {
		this.openButton.setEnabled(status);
	}

	public void renderProjectList() throws IOException {
		table.removeAll();
		List<ProjectFile> projects = new ArrayList<ProjectFile>();
		String projectFolderPath = Utilities.getInstance().getProjectsFolder();
		File[] projectFolders = new File(projectFolderPath).listFiles();
		for (File projectFolder : projectFolders) {
			if (projectFolder.isDirectory()) {
				ProjectFile proFile = new ProjectFile(projectFolder.getName().trim());
				projects.add(proFile);
			}
		}
		setAllProjects(projects);
		for (ProjectFile project : projects) {
			CustomTableItem tableItem = new CustomTableItem(table, 0);
			tableItem.setText(new String[] { project.getProjectName() });
			tableItem.setControlData(project);

		}
	}

	public void refreshProject() {
		table.removeAll();
		List<ProjectFile> projects = getAllProjects();
		for (ProjectFile project : projects) {
			if (project.isVisible()) {
				CustomTableItem tableItem = new CustomTableItem(table, 0);
				tableItem.setText(new String[] { project.getProjectName() });
				tableItem.setControlData(project);
			}

		}
	}

	public void filterProjectTable(String searchValue) {
		List<ProjectFile> projects = getAllProjects();
		for (ProjectFile project : projects) {
			if (project.getProjectName().trim().toLowerCase().contains(searchValue.trim().toLowerCase())) {
				project.setVisible(true);
			} else {
				project.setVisible(false);
			}
		}
		this.refreshProject();
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		openButton = createButton(parent, IDialogConstants.OK_ID, "Open", true);
		openButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.IMPORT_ICON));
		openButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ProjectFile projectFile = getSelectedProject();
				if (ServiceRepository.getInstance().getProjectName() != null) {
					if (ServiceRepository.getInstance().getProjectName().equals(projectFile.getProjectName())) {
						new MessageDialogs().openErrorDialog("OpKey", "Project Already Opened");
						return;
					}
				}
				File projectDir = new File(
						Utilities.getInstance().getProjectsFolder() + File.separator + projectFile.getProjectName());
				File[] files = projectDir.listFiles();
				for (File file : files) {
					if (file.getName().endsWith(".db")) {
						new ProjectImporter().importProjectDB(file.getAbsolutePath());
						break;
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		toggleOpenButton(false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(644, 504);
	}

	public Shell getParentshell() {
		return shlOpenProject;
	}

	public void setParentshell(Shell parentshell) {
		this.shlOpenProject = parentshell;
	}

	public List<ProjectFile> getAllProjects() {
		return allProjects;
	}

	public void setAllProjects(List<ProjectFile> allProjects) {
		this.allProjects = allProjects;
	}

	public ProjectFile getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(ProjectFile selectedProject) {
		this.selectedProject = selectedProject;
	}
}
