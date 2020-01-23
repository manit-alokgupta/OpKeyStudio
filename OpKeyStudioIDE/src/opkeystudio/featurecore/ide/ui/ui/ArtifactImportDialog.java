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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dto.ArtifactTreeNode;
import opkeystudio.opkeystudiocore.core.apis.dto.Project;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.restapi.ArtifactTreeApi;
import opkeystudio.opkeystudiocore.core.apis.restapi.ProjectApi;

public class ArtifactImportDialog extends TitleAreaDialog {
	private Text projectSearch;
	private Table table;
	private Tree tree;
	private Text text_1;
	private org.eclipse.swt.widgets.Button exportButton;
	private String[] tableHeaders = { "Mode", "Project" };
	private List<Project> allProjects = new ArrayList<>();
	private Project selectedProject;
	private ArtifactTreeNode selectedArtifactTreeNode;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ArtifactImportDialog(Shell parentShell) {
		super(parentShell);
	}

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

		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new GridLayout(1, false));

		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_1.setMessage("Search Artifact");
		tree = new Tree(composite_1, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
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
				Project sproject = (Project) cti.getControlData();
				setSelectedProject(sproject);
				try {
					new ProjectApi().selectProject(sproject.getP_ID());
					renderArtifactTree();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

		tree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ArtifactTreeNode atn = getSelectedArtifactNode();
				if (atn == null) {
					toggleExportButton(false);
				} else {
					toggleExportButton(true);
					setSelectedArtifactTreeNode(atn);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
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
		this.exportButton.setEnabled(status);
	}

	private void addIcon(CustomTreeItem artTreeItem) {
		ArtifactTreeNode atn = (ArtifactTreeNode) artTreeItem.getControlData();
		if (atn == null) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/folder.png"));
		} else if (atn.getType() == Artifact.MODULETYPE.Folder) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/folder.png"));
		} else if (atn.getType() == Artifact.MODULETYPE.Flow) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/testcase.png"));
		} else if (atn.getType() == Artifact.MODULETYPE.ObjectRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/or.png"));
		} else if (atn.getType() == Artifact.MODULETYPE.Suite) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/testsuite.png"));
		} else if (atn.getType() == Artifact.MODULETYPE.DataRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/note.png"));
		} else if (atn.getType() == Artifact.MODULETYPE.Component) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/fl.png"));
		} else if (atn.getType() == Artifact.MODULETYPE.CodedFunction) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/fl.png"));
		}
	}

	public void renderArtifactTree() throws IOException {
		tree.removeAll();
		List<ArtifactTreeNode> treeNodes = new ArtifactTreeApi().getRootArtificateFolder();
		for (ArtifactTreeNode atreeNode : treeNodes) {
			if (!atreeNode.getParent().equals("#")) {
				if (!atreeNode.getText().equals("Loading_eea00542-8578-4d09-be64-96e744db3596")) {
					CustomTreeItem cti = new CustomTreeItem(tree, 0);
					cti.setText(atreeNode.getText());
					cti.setControlData(atreeNode);
					addIcon(cti);
				}
			}
		}
	}

	public void renderProjectList() throws IOException {
		table.removeAll();
		List<Project> projects = new ProjectApi().getAssignedProjects();
		setAllProjects(projects);
		for (Project project : projects) {
			CustomTableItem tableItem = new CustomTableItem(table, 0);
			tableItem.setText(new String[] { project.getProjectMode_ENUM(), project.getName() });
			tableItem.setControlData(project);

		}
	}

	public void refreshProject() {
		table.removeAll();
		List<Project> projects = getAllProjects();
		setAllProjects(projects);
		for (Project project : projects) {
			if (project.isVisible()) {
				CustomTableItem tableItem = new CustomTableItem(table, 0);
				tableItem.setText(new String[] { project.getProjectMode_ENUM(), project.getName() });
				tableItem.setControlData(project);
			}

		}
	}

	public void filterProjectTable(String searchValue) {
		List<Project> projects = getAllProjects();
		for (Project project : projects) {
			if (project.getName().trim().toLowerCase().contains(searchValue.trim().toLowerCase())) {
				project.setVisible(true);
			} else {
				project.setVisible(false);
			}
		}
		this.refreshProject();
	}

	private ArtifactTreeNode getSelectedArtifactNode() {
		if (tree.getSelection() == null) {
			return null;
		}
		if (tree.getSelection().length == 0) {
			return null;
		}
		CustomTreeItem cti = (CustomTreeItem) tree.getSelection()[0];
		if (cti == null) {
			return null;
		}
		return (ArtifactTreeNode) cti.getControlData();

	}

	public List<Project> getAllProjects() {
		return allProjects;
	}

	public void setAllProjects(List<Project> allProjects) {
		this.allProjects = allProjects;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public ArtifactTreeNode getSelectedArtifactTreeNode() {
		return selectedArtifactTreeNode;
	}

	public void setSelectedArtifactTreeNode(ArtifactTreeNode selectedArtifactTreeNode) {
		this.selectedArtifactTreeNode = selectedArtifactTreeNode;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		exportButton = createButton(parent, IDialogConstants.OK_ID, "Export", true);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(644, 504);
	}
}
