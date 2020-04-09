package opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class ObjectRepositoryTreeItem extends CustomTreeItem {

	public ObjectRepositoryTreeItem(Tree parent, int style) {
		super(parent, style);
	}

	public ObjectRepositoryTreeItem(TreeItem parent, int style) {
		super(parent, style);
	}

	public ORObject getObjectRepository() {
		return (ORObject) super.getControlData();
	}

	public void setArtifact(ORObject objectRepository) {
		super.setControlData(objectRepository);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
