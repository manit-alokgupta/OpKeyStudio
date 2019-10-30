package opkeystudio.featurecore.ide.ui.customcontrol;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectRepository;

public class ObjectRepositoryTree extends CustomTree {

	public ObjectRepositoryTree(Composite parent, int style) {
		super(parent, style);
	}
	public void setObjectRepositoriesData(List<ObjectRepository> objectRepository) {
		super.setControlData(objectRepository);
	}

	@SuppressWarnings("unchecked")
	public List<ObjectRepository> getObjectRepositoriesData() {
		return (List<ObjectRepository>) super.getControlData();
	}
}
