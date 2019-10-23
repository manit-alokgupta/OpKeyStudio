package opkeystudio.opkeystudiocore.core.apis;

import java.io.IOException;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.ArtificateTreeNode;
import opkeystudio.opkeystudiocore.core.apis.dto.Project;

public class Main {

	public static void main(String[] args) throws IOException {
		new AuthenticateApi().loginToOpKey("admin", "admin");
		List<Project> projects = new ProjectApi().getAssignedProjects();
		System.out.println(projects.get(0).getP_ID());
		new ProjectApi().selectProject(projects.get(0).getP_ID());
		List<ArtificateTreeNode> nodes= new ArtificateTreeApi().getArtificateNodes("00000000-0000-0000-0000-000000000000");
		System.out.println(nodes.get(0).getText());
		new AuthenticateApi().logout();
	}

}
