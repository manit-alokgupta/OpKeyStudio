package opkeystudio.featurecore.ide.ui.customcontrol;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.opkeystudiocore.core.apis.dto.component.generic.SuperSchema;

public class ArtifactTreeItem extends CustomTreeItem {
	public ArtifactTreeItem(Tree parent, int style) {
		super(parent, style);
	}

	public ArtifactTreeItem(TreeItem parent, int style) {
		super(parent, style);
	}

	public SuperSchema getArtifact() {
		return (SuperSchema) super.getOpKeyData();
	}

	public void setArtifact(SuperSchema artifact) {
		super.setOpKeyData(artifact);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
