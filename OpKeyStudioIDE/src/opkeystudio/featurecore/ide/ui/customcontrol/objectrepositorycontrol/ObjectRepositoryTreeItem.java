package opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectRepository;

public class ObjectRepositoryTreeItem extends CustomTreeItem {

	public ObjectRepositoryTreeItem(Tree parent, int style) {
		super(parent, style);
	}

	public ObjectRepositoryTreeItem(TreeItem parent, int style) {
		super(parent, style);
	}
	
	public ObjectRepository getObjectRepository() {
		return (ObjectRepository) super.getOpKeyData();
	}

	public void setArtifact(ObjectRepository objectRepository) {
		super.setOpKeyData(objectRepository);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
