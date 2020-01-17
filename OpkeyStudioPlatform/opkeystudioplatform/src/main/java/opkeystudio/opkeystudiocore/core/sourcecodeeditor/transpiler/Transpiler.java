package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode.FILE_TYPE;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class Transpiler {
	private TranspileObject transpileObject;

	private void createFileNode(FileNode fileNode) throws IOException {
		if (fileNode.getFileType() == FILE_TYPE.FOLDER || fileNode.getFileType() == FILE_TYPE.PACKAGEFOLDER
				|| fileNode.getFileType() == FILE_TYPE.PROJECTFOLDER) {
			if (!fileNode.getFile().exists()) {
				fileNode.getFile().mkdir();
			}
		}

		if (fileNode.getFileType() == FILE_TYPE.JAVASOURCEFILE || fileNode.getFileType() == FILE_TYPE.XML) {
			if (!fileNode.getFile().exists()) {
				fileNode.getFile().createNewFile();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(fileNode.getFile()));
			bw.write(fileNode.getData());
			bw.flush();
			bw.close();
		}
		for (FileNode fnode : fileNode.getFilesNodes()) {
			createFileNode(fnode);
		}
	}

	@SuppressWarnings("unused")
	public FileNode transpileDatas(TranspileObject transpileObject) {
		this.setTranspileObject(transpileObject);
		String path = Utilities.getInstance().getDefaultSourceCodeDirPath();
		Artifact artifact = transpileObject.getArtifact();
		FileNode rootNode = new FileNode(artifact.getId(),"", FILE_TYPE.PROJECTFOLDER);
		rootNode.setParentPath(path);
		String rootPath = rootNode.getFilePath();
		FileNode binNode = new FileNode("bin",rootPath, FILE_TYPE.PACKAGEFOLDER);
		binNode.setParentPath(rootNode.getFilePath());

		FileNode srcnode = new FileNode("src", rootPath,FILE_TYPE.PACKAGEFOLDER);
		srcnode.setParentPath(rootNode.getFilePath());

		FileNode gvNode = new FileNode("globalvariables",rootPath, FILE_TYPE.PACKAGEFOLDER);
		gvNode.setParentPath(srcnode.getFilePath());

		FileNode tcNode = new FileNode("testcases",rootPath, FILE_TYPE.PACKAGEFOLDER);
		tcNode.setParentPath(srcnode.getFilePath());

		FileNode flNode = new FileNode("functionlibraries",rootPath, FILE_TYPE.PACKAGEFOLDER);
		flNode.setParentPath(srcnode.getFilePath());

		FileNode orNode = new FileNode("objectrepositories",rootPath, FILE_TYPE.PACKAGEFOLDER);
		orNode.setParentPath(srcnode.getFilePath());

		FileNode libNode = new FileNode("libs",rootPath, FILE_TYPE.PACKAGEFOLDER);
		libNode.setParentPath(srcnode.getFilePath());

		srcnode.addFileNodes(gvNode);
		srcnode.addFileNodes(tcNode);
		srcnode.addFileNodes(flNode);
		srcnode.addFileNodes(orNode);
		srcnode.addFileNodes(libNode);

		rootNode.addFileNodes(binNode);
		rootNode.addFileNodes(srcnode);

		List<GlobalVariable> globalVariables = transpileObject.getGlobalVaribales();
		List<FlowStep> flowSteps = transpileObject.getFlowSteps();
		List<FlowStep> functionLibaries = getFunctionLibraries(flowSteps);
		List<ORObject> orobjects = getAllORObjects(flowSteps);
		Set<String> orids = getAllObjectRepositoryIds(flowSteps);

		transpileObject.setOrObjects(orobjects);
		FileNode gvFile = new FileNode("GlobalVariables",rootPath, FILE_TYPE.JAVASOURCEFILE);
		gvFile.setParentPackageName(gvNode.getFileName());
		gvFile.setParentPath(gvNode.getFilePath());

		FileNode orFile = new FileNode("ORObjects",rootPath, FILE_TYPE.JAVASOURCEFILE);
		orFile.setParentPackageName(orNode.getFileName());
		orFile.setParentPath(orNode.getFilePath());

		FileNode tcFile = new FileNode(artifact.getName(),rootPath, FILE_TYPE.JAVASOURCEFILE);
		tcFile.setParentPackageName(tcNode.getFileName());
		tcFile.setParentPath(tcNode.getFilePath());

		String tcDatas = new TranspilerUtilities(this).transpileTestCaseSteps(artifact, flowSteps, tcFile);
		String ordatas = new TranspilerUtilities(this).transpileORObjects(orobjects, orFile);
		String gvdatas = new TranspilerUtilities(this).transpileGlobalVariables(globalVariables, gvFile);

		gvFile.setData(gvdatas);
		orFile.setData(ordatas);
		tcFile.setData(tcDatas);

		for (FlowStep functionLibraryStep : functionLibaries) {
			Artifact flartifact = functionLibraryStep.getFunctionLibraryComponent();
			try {
				List<FlowStep> flflowSteps = FunctionLibraryApi.getInstance().getAllFlowSteps(flartifact.getId());
				FileNode flFile = new FileNode(flartifact.getName(),rootPath, FILE_TYPE.JAVASOURCEFILE);
				flFile.setParentPackageName(flNode.getFileName());
				String flDatas = new TranspilerUtilities(this).transpileTestCaseSteps(flartifact, flflowSteps, flFile);
				flFile.setData(flDatas);
				flFile.setParentPath(flNode.getFilePath());
				flNode.addFileNodes(flFile);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		orNode.addFileNodes(orFile);
		gvNode.addFileNodes(gvFile);
		tcNode.addFileNodes(tcFile);
		try {
			createFileNode(rootNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rootNode;
	}

	private List<FlowStep> getFunctionLibraries(List<FlowStep> allFlowSteps) {
		List<FlowStep> allFunctionLibraries = new ArrayList<FlowStep>();
		for (FlowStep flowStep : allFlowSteps) {
			if (flowStep.getFunctionLibraryComponent() != null) {
				allFunctionLibraries.add(flowStep);
				try {
					List<FlowStep> flowSteps = new FunctionLibraryApi()
							.getAllFlowSteps(flowStep.getFunctionLibraryComponent().getId());
					allFunctionLibraries.addAll(getFunctionLibraries(flowSteps));
				} catch (JsonParseException | JsonMappingException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return allFunctionLibraries;
	}

	private List<ORObject> getAllORObjects(List<FlowStep> allFlowSteps) {
		List<ORObject> allORObjects = new ArrayList<ORObject>();
		for (FlowStep flowStep : allFlowSteps) {
			allORObjects.addAll(flowStep.getOrObject());
		}
		return allORObjects;
	}

	private Set<String> getAllObjectRepositoryIds(List<FlowStep> allFlowSteps) {
		Set<String> objectRepoId = new HashSet<String>();
		for (FlowStep flowStep : allFlowSteps) {
			List<ORObject> orobjects = flowStep.getOrObject();
			for (ORObject orobject : orobjects) {
				objectRepoId.add(orobject.getOr_id());
			}
		}
		return objectRepoId;
	}

	public TranspileObject getTranspileObject() {
		return transpileObject;
	}

	public void setTranspileObject(TranspileObject transpileObject) {
		this.transpileObject = transpileObject;
	}
}
