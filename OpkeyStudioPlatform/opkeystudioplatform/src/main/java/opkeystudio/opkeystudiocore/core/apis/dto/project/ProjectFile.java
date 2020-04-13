package opkeystudio.opkeystudiocore.core.apis.dto.project;

import java.io.File;

public class ProjectFile extends File {
	private boolean visible;

	public ProjectFile(String pathname) {
		super(pathname);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
