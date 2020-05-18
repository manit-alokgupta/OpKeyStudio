package opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryConstructApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.dtoMaker.ArtifactMaker;
import opkeystudio.opkeystudiocore.core.dtoMaker.DRMaker;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactApi {
	public List<ArtifactDTO> getAllArtificates() {
		String result = QueryExecutor.getInstance()
				.executeQuery("select * from main_artifact_filesystem order by position");
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ArtifactDTO.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<ArtifactDTO>();
	}

	public List<ArtifactDTO> getArtifact(String artifactId) {
		String query = String.format("select * from main_artifact_filesystem where id='%s'", artifactId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ArtifactDTO.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<ArtifactDTO>();
	}

	public List<ArtifactDTO> getAllArtificatesByType(String artifactType) {
		String result = QueryExecutor.getInstance().executeQuery(String.format(
				"select * from main_artifact_filesystem where file_type_enum='%s' order by position", artifactType));
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ArtifactDTO.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<ArtifactDTO>();
	}

	public void deleteArtifact(ArtifactDTO artifact) {
		String query = String.format("delete from main_artifact_filesystem where id='%s'", artifact.getId());
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	public void updateArtifact(ArtifactDTO artifact) {
		String updateQuery = new QueryMaker().createUpdateQuery(artifact, "main_artifact_filesystem",
				String.format("WHERE id='%s'", artifact.getId()));
		QueryExecutor.getInstance().executeUpdateQuery(updateQuery);
	}

	public void createArtifact(ArtifactDTO parentId, String artifactName, MODULETYPE artifactType) {
		ArtifactDTO artifact = new ArtifactMaker().getArtifactObject(parentId, artifactName, artifactType);
		String query = new QueryMaker().createInsertQuery(artifact, "main_artifact_filesystem", "");
		QueryExecutor.getInstance().executeUpdateQuery(query);
		new ArtifactApiUtilities().createMainArtifactClob(artifact);
		new ArtifactApiUtilities().createFlowManualTestCase(artifact);
		if (artifactType == MODULETYPE.DataRepository) {
			List<DRColumnAttributes> drColumnAttributes = new DRMaker().getDefaultDRStructure(artifact);
			for (DRColumnAttributes drColumnAttribute : drColumnAttributes) {
				new DataRepositoryConstructApi().addDRColumn(drColumnAttribute);
				for (DRCellAttributes drCellAttribute : drColumnAttribute.getDrCellAttributes()) {
					new DataRepositoryConstructApi().addDRCell(drCellAttribute);
				}
			}
			GlobalLoader.getInstance().initAllDRColumns();
			GlobalLoader.getInstance().initALLDRCells();
		}
	}
}
