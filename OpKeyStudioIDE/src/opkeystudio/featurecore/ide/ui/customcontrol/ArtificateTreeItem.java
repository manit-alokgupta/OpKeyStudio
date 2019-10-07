package opkeystudio.featurecore.ide.ui.customcontrol;

import java.util.UUID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;

public class ArtificateTreeItem extends TreeItem {
	private Artificate artificate;

	public ArtificateTreeItem(Tree parent, Artificate artificate) {
		super(parent, SWT.NONE);
		setArtificate(artificate);
	}

	public ArtificateTreeItem(TreeItem parent, Artificate artificate) {
		super(parent, SWT.NONE);
		setArtificate(artificate);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Artificate getArtificate() {
		return artificate;
	}

	public void setArtificate(Artificate artificate) {
		this.artificate = artificate;
	}
}
