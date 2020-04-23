package opkeystudio.featurecore.ide.ui.customcontrol;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import pcloudystudio.spytreecomponents.control.CTreeViewer;

public class CodeViewTree extends CustomTree {
	private List<Artifact> artifacts = new ArrayList<Artifact>();

	public CodeViewTree(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

			}

			@Override
			public void mouseDown(MouseEvent e) {

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				openSelectedCodeFile();
			}

		});
	}

	public void openSelectedCodeFile() {
		File selectedCodeFile = getSelectedArtifact();
		if (selectedCodeFile == null) {
			return;
		}
		System.out.println(">> " + selectedCodeFile.getAbsolutePath());
	}

	public void setArtifactsData(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public List<Artifact> getArtifactsData() {
		return this.artifacts;
	}

	private void addIcon(CodeViewTreeItem artTreeItem) {
		File artifactFile = artTreeItem.getArtifactFile();
		if (artifactFile == null) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
			return;
		}
		if (artifactFile.isDirectory()) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
			return;
		}

		if (artifactFile.isFile()) {
			if (artifactFile.getName().toLowerCase().endsWith(".java")) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
			}
			return;
		}
	}

	public void expandAll(CodeViewTreeItem treeItem) {
		treeItem.setExpanded(true);
		TreeItem items[] = treeItem.getItems();
		for (TreeItem item : items) {
			item.setExpanded(true);
			expandAll((CodeViewTreeItem) item);
		}
		this.setRedraw(true);
	}

	public void renderArtifacts() {
		if (ServiceRepository.getInstance().getExportedDBFilePath() == null) {
			return;
		}
		this.removeAll();
		CodeViewTreeItem rootNode = new CodeViewTreeItem(this, 0);
		rootNode.setText("Code WorkSpace");
		rootNode.setExpanded(true);
		rootNode.setSystemFolder(true);
		addIcon(rootNode);

		CodeViewTreeItem srcNode = new CodeViewTreeItem(rootNode, 0);
		srcNode.setText("src");
		srcNode.setExpanded(true);
		srcNode.setSystemFolder(true);
		addIcon(srcNode);
		String transpileDirpath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getTranspiledArtifactsFolder();
		File transpileDirFolder = new File(transpileDirpath);
		File[] files = transpileDirFolder.listFiles();
		for (File file : files) {
			renderFiles(srcNode, file);
		}
		expandAll(rootNode);
	}

	private void renderFiles(CodeViewTreeItem parentNode, File rootFile) {
		if (rootFile.isDirectory()) {
			CodeViewTreeItem ctreeitem = new CodeViewTreeItem(parentNode, 0);
			ctreeitem.setArtifactFile(rootFile);
			ctreeitem.setText(rootFile.getName());
			addIcon(ctreeitem);
			for (File file : rootFile.listFiles()) {
				renderFiles(ctreeitem, file);
			}
		} else {
			CodeViewTreeItem ctreeitem = new CodeViewTreeItem(parentNode, 0);
			ctreeitem.setArtifactFile(rootFile);
			ctreeitem.setText(rootFile.getName());
			addIcon(ctreeitem);
		}
	}

	public CodeViewTreeItem getSelectedArtifactTreeItem() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		return (CodeViewTreeItem) this.getSelection()[0];
	}

	public File getSelectedArtifact() {
		CodeViewTreeItem treeItem = getSelectedArtifactTreeItem();
		if (treeItem == null) {
			return null;
		}
		return treeItem.getArtifactFile();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void filterArtifactTree(String searchValue) {
		List<Artifact> artifacts = this.getArtifactsData();
		for (Artifact artifact : artifacts) {
			if (artifact.getFile_type_enum() != MODULETYPE.Folder) {
				if (artifact.getName().trim().toLowerCase().contains(searchValue.trim().toLowerCase())) {
					artifact.setVisible(true);
				} else {
					artifact.setVisible(false);
				}
			}
		}
	}
}
