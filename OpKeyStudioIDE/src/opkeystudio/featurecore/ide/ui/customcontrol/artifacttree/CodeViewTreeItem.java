package opkeystudio.featurecore.ide.ui.customcontrol.artifacttree;

import java.io.File;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;

public class CodeViewTreeItem extends CustomTreeItem {
	private File artifactFile;
	private boolean systemFolder = false;

	public CodeViewTreeItem(Tree parent, int style) {
		super(parent, style);
	}

	public CodeViewTreeItem(TreeItem parent, int style) {
		super(parent, style);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public File getArtifactFile() {
		return artifactFile;
	}

	public void setArtifactFile(File artifactFile) {
		this.artifactFile = artifactFile;
	}

	public boolean isSystemFolder() {
		return systemFolder;
	}

	public void setSystemFolder(boolean systemFolder) {
		this.systemFolder = systemFolder;
	}
}
