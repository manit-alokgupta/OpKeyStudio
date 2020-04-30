package opkeystudio.featurecore.ide.ui.customcontrol.artifacttree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.ui.CodeViewTreeUI;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.transpiler.ArtifactTranspiler;

public class CodeViewTree extends CustomTree {
	private List<Artifact> artifacts = new ArrayList<Artifact>();

	private CodeViewTreeUI parentArtifactCodeViewTreeUI;

	public CodeViewTree(Composite parent, int style, CodeViewTreeUI parentArtifactCodeViewTreeUI) {
		super(parent, style);
		this.setParentArtifactCodeViewTreeUI(parentArtifactCodeViewTreeUI);
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

		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				CodeViewTreeItem treeItem = getSelectedArtifactTreeItem();
				if (treeItem == null) {
					return;
				}
				File selectedFile = treeItem.getArtifactFile();
				if (treeItem.isSystemFolder()) {
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
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void openSelectedCodeFile() {
		File selectedCodeFile = getSelectedArtifact();
		if (selectedCodeFile == null) {
			return;
		}
		System.out.println(">> " + selectedCodeFile.getAbsolutePath());
		EPartService partService = Utilities.getInstance().getEpartService();
		MPart part = partService.createPart("opkeystudio.partdescriptor.genericCodeEditor");
		part.setLabel(selectedCodeFile.getName());
		part.setTooltip(selectedCodeFile.getName());
		part.getTransientData().put("opkeystudio.codeFile", selectedCodeFile);
		partService.showPart(part, PartState.ACTIVATE);
	}

	public void deleteSelectedFile() {
		File selectedCodeFile = getSelectedArtifact();
		if (selectedCodeFile == null) {
			return;
		}

		boolean status = new MessageDialogs().openConfirmDialog("OpKey",
				String.format("Do ypu really want to delete '%s'?", selectedCodeFile.getName()));
		if (!status) {
			return;
		}
		if (selectedCodeFile.isFile()) {
			try {
				FileUtils.forceDelete(selectedCodeFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (selectedCodeFile.isDirectory()) {
			try {
				FileUtils.deleteDirectory(selectedCodeFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		renderArtifacts();
	}

	public void renameSelectedFile() {
		File selectedCodeFile = getSelectedArtifact();
		if (selectedCodeFile == null) {
			return;
		}
		String fileName = new MessageDialogs().openInputDialogAandGetValue("OpKey", "Enter New Java File Name",
				"NewClass");
		if (fileName == null) {
			new MessageDialogs().openErrorDialog("OpKey", "Please provide a valid name");
			return;
		}
		if (fileName.trim().isEmpty()) {
			new MessageDialogs().openErrorDialog("OpKey", "Please provide a valid name");
			return;
		}

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

		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().readTextFile(selectedCodeFile);
		JavaClassSource classSource = (JavaClassSource) Roaster.parse(codeData);
		classSource.setName(fileName);
		String parentFolder = selectedCodeFile.getParentFile().getAbsolutePath();
		selectedCodeFile.delete();
		opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.writeToFile(new File(parentFolder + File.separator + fileName + ".java"), classSource.toString());
		renderArtifacts();
	}

	public void createNewJavaFile() {
		File selectedCodeFile = getSelectedArtifact();
		if (selectedCodeFile == null) {
			return;
		}

		String fileName = new MessageDialogs().openInputDialogAandGetValue("OpKey", "Enter New Java File Name",
				"NewClass");
		if (fileName == null) {
			new MessageDialogs().openErrorDialog("OpKey", "Please provide a valid name");
			return;
		}
		if (fileName.trim().isEmpty()) {
			new MessageDialogs().openErrorDialog("OpKey", "Please provide a valid name");
			return;
		}

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
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Set<String> packages = ArtifactTranspiler.getInstance().getAllPackagesNames();
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName(fileName).setPublic();
		class1.addMethod().setName("main").setBody("System.out.println(\"Hello from OpKey E\");").setPublic()
				.setStatic(true).addParameter("String[]", "args");
		for (String packag : packages) {
			class1.addImport(packag + ".*");
		}

		opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().writeToFile(file, class1.toString());
		renderArtifacts();
	}

	public void createNewFolder() {
		File selectedCodeFile = getSelectedArtifact();
		if (selectedCodeFile == null) {
			return;
		}

		String fileName = new MessageDialogs().openInputDialogAandGetValue("OpKey", "Enter New Java File Name",
				"NewFolder");
		if (fileName == null) {
			new MessageDialogs().openErrorDialog("OpKey", "Please provide a valid name");
			return;
		}
		if (fileName.trim().isEmpty()) {
			new MessageDialogs().openErrorDialog("OpKey", "Please provide a valid name");
			return;
		}
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
		renderArtifacts();
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

	public CodeViewTreeUI getParentArtifactCodeViewTreeUI() {
		return parentArtifactCodeViewTreeUI;
	}

	public void setParentArtifactCodeViewTreeUI(CodeViewTreeUI parentArtifactCodeViewTreeUI) {
		this.parentArtifactCodeViewTreeUI = parentArtifactCodeViewTreeUI;
	}
}
