package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dto.ArtifactTreeNode;
import opkeystudio.opkeystudiocore.core.apis.restapi.ArtifactTreeApi;

public class ProjectArtifactTreeUI extends TitleAreaDialog {
	private Tree tree;

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

		try {
			renderArtifactTree();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return area;
	}

	public void renderArtifactTree() throws IOException {
		List<ArtifactTreeNode> treeNodes = new ArtifactTreeApi().getRootArtificateFolder();
		for (ArtifactTreeNode atreeNode : treeNodes) {
			if (!atreeNode.getParent().equals("#")) {
				if (!atreeNode.getText().equals("Loading_eea00542-8578-4d09-be64-96e744db3596")) {
					CustomTreeItem cti = new CustomTreeItem(tree, 0);
					cti.setText(atreeNode.getText());
					cti.setControlData(atreeNode);
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
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(433, 537);
	}

}
