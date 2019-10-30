package opkeystudio.featurecore.ide.ui.customcontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectRepository;

public class ObjectRepositoryTree extends CustomTree {

	public ObjectRepositoryTree(Composite parent, int style) {
		super(parent, style);
		renderObjectRepositories();
	}

	public void setObjectRepositoriesData(List<ObjectRepository> objectRepository) {
		super.setControlData(objectRepository);
	}

	@SuppressWarnings("unchecked")
	public List<ObjectRepository> getObjectRepositoriesData() {
		return (List<ObjectRepository>) super.getControlData();
	}

	public void renderObjectRepositories() {
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.objectrepositoryArtifact");
		System.out.println("Neon "+artifact.getId());
		try {
			new ObjectRepositoryApi().getAllObjects(artifact.getId().trim());
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
