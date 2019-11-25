package opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.dtoMaker.ArtifactMaker;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactApi {
	public List<Artifact> getAllArtificates()
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String result = sqlComm.executeQueryString("select * from main_artifact_filesystem order by position");
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Artifact.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	public List<Artifact> getArtifact(String artifactId) throws JsonParseException, JsonMappingException, IOException {
		String query = String.format("select * from main_artifact_filesystem where id='%s'", artifactId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Artifact.class);
		return mapper.readValue(result, type);
	}

	public List<Artifact> getAllArtificatesByType(String artifactType)
			throws JsonParseException, JsonMappingException, IOException {
		String result = QueryExecutor.getInstance().executeQuery(String.format(
				"select * from main_artifact_filesystem where file_type_enum='%s' order by position", artifactType));
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Artifact.class);
		return mapper.readValue(result, type);
	}

	public void deleteArtifact(Artifact artifact) {
		System.out.println("Deleting " + artifact.getId());
		String query = String.format("delete from main_artifact_filesystem where id='%s'", artifact.getId());
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	public void renameArtifact(Artifact artifact) {
		System.out.println("Renaming " + artifact.getName());
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		try {
			sqlComm.connect();
			String updateQuery = new QueryMaker().createUpdateQuery(artifact, "main_artifact_filesystem",
					String.format("WHERE id='%s'", artifact.getId()));
			System.out.println(updateQuery);
			sqlComm.executeUpdate(updateQuery);
			sqlComm.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createArtifact(String parentId, String artifactName, MODULETYPE artifactType) {
		Artifact artifact = new ArtifactMaker().getArtifactObject(parentId, artifactName, artifactType);
		String query = new QueryMaker().createInsertQuery(artifact, "main_artifact_filesystem", "");
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}
}
