package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dto.ArtifactTreeNode;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.restapi.ArtifactTreeApi;

public class ProjectArtifactTreeUI extends TitleAreaDialog {
	private Tree tree;
	private Button exportButton;
	private Button cancelButton;
	private ArtifactTreeNode selectedArtifactTreeNode;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ProjectArtifactTreeUI(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Select The Artifact You Want To Export");
		setTitle("Select The Artifact ");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		tree = new Tree(container, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

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
			renderArtifactTree();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return area;
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

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		exportButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		exportButton.setText("Export");
		exportButton.setEnabled(false);

		exportButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ArtifactTreeNode selectedArtifact = getSelectedArtifactTreeNode();
				System.out.println("Selected Artifact " + selectedArtifact.getText());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		cancelButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(433, 537);
	}

	public ArtifactTreeNode getSelectedArtifactTreeNode() {
		return selectedArtifactTreeNode;
	}

	public void setSelectedArtifactTreeNode(ArtifactTreeNode selectedArtifactTreeNode) {
		this.selectedArtifactTreeNode = selectedArtifactTreeNode;
	}

}
