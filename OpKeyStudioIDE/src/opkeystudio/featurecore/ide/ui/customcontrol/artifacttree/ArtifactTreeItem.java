package opkeystudio.featurecore.ide.ui.customcontrol.artifacttree;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;

public class ArtifactTreeItem extends CustomTreeItem {
	public ArtifactTreeItem(Tree parent, int style) {
		super(parent, style);
	}

	public ArtifactTreeItem(TreeItem parent, int style) {
		super(parent, style);
	}

	public Artifact getArtifact() {
		return (Artifact) super.getControlData();
	}

	public void setArtifact(Artifact artifact) {
		super.setControlData(artifact);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
