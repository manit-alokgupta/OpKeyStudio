package opkeystudio.featurecore.ide.ui.ui;

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

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dto.project.ProjectFile;

public class ProjectOpenDialog extends TitleAreaDialog {

	private Shell parentshell;

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
		setMessage("Select a Project And Select The Artifact to Import");
		setTitle("Import From OpKey SAAS");
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
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		sashForm.setWeights(new int[] { 2, 2 });
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
					column.setWidth(table_0.getBounds().width / 2);
				}
			}
		});

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem = table.getSelection()[0];
				if (tableItem == null) {
					return;
				}
				CustomTableItem cti = (CustomTableItem) tableItem;
				ProjectFile sproject = (ProjectFile) cti.getControlData();
				setSelectedProject(sproject);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		projectSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				filterProjectTable(projectSearch.getText());
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		try {
			renderProjectList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return area;
	}

	private void toggleExportButton(boolean status) {
		this.openButton.setEnabled(status);
	}

	public void renderProjectList() throws IOException {
		table.removeAll();
		List<ProjectFile> projects = new ArrayList<ProjectFile>();
		setAllProjects(projects);
		for (ProjectFile project : projects) {
			CustomTableItem tableItem = new CustomTableItem(table, 0);
			tableItem.setText(new String[] { project.getName() });
			tableItem.setControlData(project);

		}
	}

	public void refreshProject() {
		table.removeAll();
		List<ProjectFile> projects = new ArrayList<ProjectFile>();
		setAllProjects(projects);
		for (ProjectFile project : projects) {
			if (project.isVisible()) {
				CustomTableItem tableItem = new CustomTableItem(table, 0);
				tableItem.setText(new String[] { project.getName() });
				tableItem.setControlData(project);
			}

		}
	}

	public void filterProjectTable(String searchValue) {
		List<ProjectFile> projects = getAllProjects();
		for (ProjectFile project : projects) {
			if (project.getName().trim().toLowerCase().contains(searchValue.trim().toLowerCase())) {
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
		openButton = createButton(parent, IDialogConstants.OK_ID, "Import", true);
		openButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.IMPORT_ICON));
		openButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		toggleExportButton(false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(644, 504);
	}

	public Shell getParentshell() {
		return parentshell;
	}

	public void setParentshell(Shell parentshell) {
		this.parentshell = parentshell;
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
