package opkeystudio.featurecore.ide.ui.customcontrol;

import java.util.UUID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class ArtificateTreeItem extends TreeItem {
	private String artificateId;
	public ArtificateTreeItem(Tree parent) {
		super(parent, SWT.NONE);
		setArtificateId(UUID.randomUUID().toString());
	}

	public ArtificateTreeItem(TreeItem parent) {
		super(parent, SWT.NONE);
		setArtificateId(UUID.randomUUID().toString());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public String getArtificateId() {
		return artificateId;
	}

	public void setArtificateId(String artificateId) {
		this.artificateId = artificateId;
	}
}
