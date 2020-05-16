package opkeystudio.featurecore.ide.ui.customcontrol.artifacttree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.ui.CodeViewTreeUI;
import opkeystudio.featurecore.ide.ui.ui.superview.events.GlobalLoadListener;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;
import opkeystudio.opkeystudiocore.core.transpiler.ArtifactTranspiler;

public class CodeViewTree extends CustomTree {
	private List<Artifact> artifacts = new ArrayList<Artifact>();

	private CodeViewTreeUI parentArtifactCodeViewTreeUI;

	public CodeViewTree(Composite parent, int style, CodeViewTreeUI parentArtifactCodeViewTreeUI) {
		super(parent, style);
		this.setParentArtifactCodeViewTreeUI(parentArtifactCodeViewTreeUI);
		SystemRepository.getInstance().setCodeViewTreeControl(this);
		init();
		initCodeViewTreeLoader();
	}

	private void initCodeViewTreeLoader() {
		this.addOpKeyGlobalLoadListener(new GlobalLoadListener() {

			@Override
			public void handleGlobalEvent() {
				renderCodeViewTree("");
			}
		});
	}

	private void init() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				CodeViewTreeItem treeItem = getSelectedArtifactTreeItem();
				if (treeItem == null) {
					return;
				}
				getParentArtifactCodeViewTreeUI().toggleRefreshMenuItem(true);
				File selectedFile = treeItem.getArtifactFile();
				if (treeItem.isSystemFolder()) {
					getParentArtifactCodeViewTreeUI().toggleOpenMenuItem(false);
					getParentArtifactCodeViewTreeUI().toggleNewToolbarItem(false);
					getParentArtifactCodeViewTreeUI().toggleNewToolbarMenuItem(false);
					getParentArtifactCodeViewTreeUI().toggleDeleteToolbarItem(false);
					getParentArtifactCodeViewTreeUI().toggleRenameToolbarItem(false);
					return;
				}
				if (selectedFile.isDirectory() || selectedFile.isFile()) {
					getParentArtifactCodeViewTreeUI().toggleDeleteToolbarItem(true);
					getParentArtifactCodeViewTreeUI().toggleRenameToolbarItem(true);
				}

				if (selectedFile.isDirectory()) {
					getParentArtifactCodeViewTreeUI().toggleNewToolbarItem(true);
					getParentArtifactCodeViewTreeUI().toggleNewToolbarMenuItem(true);
					getParentArtifactCodeViewTreeUI().toggleOpenMenuItem(false);
				} else if (selectedFile.isFile()) {
					getParentArtifactCodeViewTreeUI().toggleNewToolbarItem(false);
					getParentArtifactCodeViewTreeUI().toggleNewToolbarMenuItem(false);
					getParentArtifactCodeViewTreeUI().toggleOpenMenuItem(true);
				}
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
		Utilities.getInstance().openSelectedFileInGenericCodeEditor(selectedCodeFile);
	}

	public void deleteSelectedFile() {
		try {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_WAIT);
			File selectedCodeFile = getSelectedArtifact();
			if (selectedCodeFile == null) {
				return;
			}

			boolean status = new MessageDialogs().openConfirmDialog("OpKey",
					String.format("Do you really want to delete '%s'?", selectedCodeFile.getName()));
			if (!status) {
				return;
			}
			if (selectedCodeFile.isFile()) {
				try {
					FileUtils.forceDelete(selectedCodeFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (selectedCodeFile.isDirectory()) {
				try {
					FileUtils.deleteDirectory(selectedCodeFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			renderCodeViewTree("");
		} finally {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_ARROW);
		}

	}

	public void renameSelectedFile() {
		try {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_WAIT);
			File selectedCodeFile = getSelectedArtifact();
			if (selectedCodeFile == null) {
				return;
			}
			String fileName = new MessageDialogs().openInputDialogAandGetValue("OpKey", "Enter New Java File Name",
					selectedCodeFile.getName().split("\\.")[0]);
			if (fileName == null) {
				return;
			}
			if (fileName.trim().isEmpty()) {
				new MessageDialogs().openErrorDialog("OpKey", "Please provide a valid name");
				return;
			}

			fileName = fileName.replaceAll(" ", "");
			fileName = fileName.split("\\.")[0];
			boolean cond1 = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
					.isStringContainsSpecialCharacters(fileName);
			boolean cond2 = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
					.isStringStartsWithNumbers(fileName);

			if (cond1) {
				new MessageDialogs().openErrorDialog("OpKey", "File Name must not contain special characters");
				return;
			}

			if (cond2) {
				new MessageDialogs().openErrorDialog("OpKey", "File Name must not starts with number");
				return;
			}
			String parentFolder = selectedCodeFile.getParentFile().getAbsolutePath();
			File newFile = new File(parentFolder + File.separator + fileName + ".java");
			Utilities.getInstance().updateCodeViewFile(selectedCodeFile, newFile);
			try {
				String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
						.readTextFile(selectedCodeFile);
				JavaClassSource classSource = (JavaClassSource) Roaster.parse(codeData);
				classSource.setName(fileName);
				selectedCodeFile.delete();
				opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().writeToFile(newFile,
						classSource.toString());
				renderCodeViewTree("");
			} catch (Exception e) {
				if (selectedCodeFile.isDirectory()) {
					new MessageDialogs().openErrorDialog("OpKey",
							String.format("Can't Rename Package Folder '%s'. ", fileName));
					return;
				}
				new MessageDialogs().openErrorDialog("OpKey",
						String.format("Unable to rename file to name '%s'. " + e.getMessage(), fileName));
				return;
			}
		} finally {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_ARROW);
		}

	}

	public void createNewJavaFile() {
		File selectedCodeFile = getSelectedArtifact();
		if (selectedCodeFile == null) {
			return;
		}

		String fileName = new MessageDialogs().openInputDialogAandGetValue("OpKey", "Enter New Java File Name",
				"NewClass");
		if (fileName == null) {
			return;
		}
		if (fileName.trim().isEmpty()) {
			new MessageDialogs().openErrorDialog("OpKey", "Please provide a valid name");
			return;
		}

		fileName = fileName.replaceAll(" ", "");
		boolean cond1 = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.isStringContainsSpecialCharacters(fileName);
		boolean cond2 = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.isStringStartsWithNumbers(fileName);

		if (cond1) {
			new MessageDialogs().openErrorDialog("OpKey", "File Name must not contain special characters");
			return;
		}

		if (cond2) {
			new MessageDialogs().openErrorDialog("OpKey", "File Name must not starts with number");
			return;
		}
		File file = new File(selectedCodeFile.getAbsolutePath() + File.separator + fileName + ".java");
		if (file.exists()) {
			new MessageDialogs().openErrorDialog("OpKey", "File Name must be unique");
			return;
		}

		List<String> packagesName = new ArrayList<String>();
		CodeViewTreeItem treeitem = this.getSelectedArtifactTreeItem();
		while (treeitem.getParentItem() != null) {
			if (treeitem.isSystemFolder()) {
				break;
			}
			packagesName.add(treeitem.getText());
			treeitem = (CodeViewTreeItem) treeitem.getParentItem();
		}

		Collections.reverse(packagesName);
		String packageName = "";
		for (String pname : packagesName) {
			if (!packageName.isEmpty()) {
				packageName += ".";
			}
			packageName += pname;
		}
		try {
			Set<String> packages = ArtifactTranspiler.getInstance().getAllPackagesNames();
			JavaClassSource class1 = Roaster.create(JavaClassSource.class);
			class1.setName(fileName).setPublic();
			class1.addMethod().setName("main").setBody("System.out.println(\"Hello from OpKey E\");")
					.addThrows("Exception").setPublic().setStatic(true).addParameter("String[]", "args");
			for (String packag : packages) {
				class1.addImport(packag + ".*");
			}

			class1.setPackage(packageName);

			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().writeToFile(file, class1.toString());
			renderCodeViewTree("");
			Utilities.getInstance().openSelectedFileInGenericCodeEditor(file);
		} catch (Exception e) {
			new MessageDialogs().openErrorDialog("OpKey",
					String.format("Unable to create file with name '%s'. Please provide a different name", fileName));
			return;
		}

	}

	public void createNewFolder() {
		File selectedCodeFile = getSelectedArtifact();
		if (selectedCodeFile == null) {
			return;
		}

		String fileName = new MessageDialogs().openInputDialogAandGetValue("OpKey", "Enter New Java File Name",
				"NewFolder");
		if (fileName == null) {
			return;
		}
		if (fileName.trim().isEmpty()) {
			new MessageDialogs().openErrorDialog("OpKey", "Please provide a valid name");
			return;
		}
		fileName = fileName.replaceAll(" ", "");
		boolean cond1 = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.isStringContainsSpecialCharacters(fileName);
		boolean cond2 = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.isStringStartsWithNumbers(fileName);

		if (cond1) {
			new MessageDialogs().openErrorDialog("OpKey", "Folder Name must not contain special characters");
			return;
		}

		if (cond2) {
			new MessageDialogs().openErrorDialog("OpKey", "Folder Name must not starts with number");
			return;
		}

		File file = new File(selectedCodeFile.getAbsolutePath() + File.separator + fileName);
		if (file.exists()) {
			new MessageDialogs().openErrorDialog("OpKey", "Folder Name must be unique");
			return;
		}
		file.mkdir();
		renderCodeViewTree("");
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
			artTreeItem.setImage(
					ResourceManager.getPluginImage("org.eclipse.jdt.ui", "/icons/full/obj16/packagefolder_obj.png"));
			return;
		}
		if (artifactFile.isDirectory()) {
			artTreeItem.setImage(
					ResourceManager.getPluginImage("org.eclipse.jdt.ui", "/icons/full/obj16/package_obj.png"));
			return;
		}

		if (artifactFile.isFile()) {
			if (artifactFile.getName().toLowerCase().endsWith(".java")) {
				artTreeItem.setImage(
						ResourceManager.getPluginImage("org.eclipse.jdt.ui", "/icons/full/obj16/jcu_obj.gif"));
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

	public void renderCodeViewTree(String fileName) {
		if (ServiceRepository.getInstance().getExportedDBFilePath() == null) {
			return;
		}
		String projectFolderPath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getProjectArtifactCodesFolder();

		File codeFolder = new File(projectFolderPath);
		if (!codeFolder.exists()) {
			codeFolder.mkdir();
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

		File[] files = codeFolder.listFiles();
		for (File file : files) {
			renderFiles(srcNode, file, fileName);
		}
		expandAll(rootNode);
	}

	private void renderFiles(CodeViewTreeItem parentNode, File rootFile, String fileName) {
		if (rootFile.isDirectory()) {
			CodeViewTreeItem ctreeitem = new CodeViewTreeItem(parentNode, 0);
			ctreeitem.setArtifactFile(rootFile);
			ctreeitem.setText(rootFile.getName());
			addIcon(ctreeitem);
			for (File file : rootFile.listFiles()) {
				renderFiles(ctreeitem, file, fileName);
			}
		} else {
			if (rootFile.getName().toLowerCase().endsWith(".class")) {
				return;
			}
			if (rootFile.getName().toLowerCase().contains(fileName.toLowerCase())) {
				CodeViewTreeItem ctreeitem = new CodeViewTreeItem(parentNode, 0);
				ctreeitem.setArtifactFile(rootFile);
				ctreeitem.setText(rootFile.getName());
				addIcon(ctreeitem);
			}
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
		renderCodeViewTree(searchValue);
	}

	public CodeViewTreeUI getParentArtifactCodeViewTreeUI() {
		return parentArtifactCodeViewTreeUI;
	}

	public void setParentArtifactCodeViewTreeUI(CodeViewTreeUI parentArtifactCodeViewTreeUI) {
		this.parentArtifactCodeViewTreeUI = parentArtifactCodeViewTreeUI;
	}
}
