package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.Project;
import opkeystudio.opkeystudiocore.core.apis.restapi.ProjectApi;

public class ProjectDialog extends TitleAreaDialog {
	private Text text;
	private Table table;
	private String[] tableHeaders = { "Mode", "Project" };
	private List<Project> allProjects = new ArrayList<>();

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ProjectDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.DIALOG_TRIM | SWT.SYSTEM_MODAL);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Choose the OpKey Project");
		setTitle("Choose Project");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		text.setToolTipText("Enter Project Name");

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
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
		text.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				filterProjectTable(text.getText());
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

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		button.setText("Go");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		button_1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
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

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(600, 400);
	}

	public List<Project> getAllProjects() {
		return allProjects;
	}

	public void setAllProjects(List<Project> allProjects) {
		this.allProjects = allProjects;
	}
}
