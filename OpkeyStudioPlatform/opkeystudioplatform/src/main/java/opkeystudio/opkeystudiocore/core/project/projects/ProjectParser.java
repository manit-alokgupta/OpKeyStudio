package opkeystudio.opkeystudiocore.core.project.projects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;
import opkeystudio.opkeystudiocore.core.project.artificates.ArtificateFolder;
import opkeystudio.opkeystudiocore.core.project.artificates.RootFolder;

public class ProjectParser {
	public List<Artificate> parseProjects(String projectPath) {
		File projectFolder = new File(projectPath);
		List<RootFolder> rootFolders = new ArrayList<RootFolder>();
		File[] rootfiles = projectFolder.listFiles();
		for (File rootfile : rootfiles) {
			if (rootfile.isDirectory()) {
				RootFolder rootFolder = new RootFolder(rootfile.getParent(), rootfile.getName());
				List<Artificate> allartificates = scanAllArtificates(rootFolder);
				rootFolder.addArtificates(allartificates);
				rootFolders.add(rootFolder);
			}
		}

		return null;
	}

	private List<Artificate> scanAllArtificates(Artificate artificate) {
		List<Artificate> allartificates = new ArrayList<Artificate>();
		File artificateFile = new File(artificate.getArtificatePath());
		File[] files = artificateFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				ArtificateFolder arfolder = new ArtificateFolder(file.getParent(), file.getName());
			}
		}
		return null;
	}
}
