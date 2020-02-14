package opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLibraryMap;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.MainFileStoreDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class CodedFunctionApi {

	public void saveCFLCode(Artifact artifact, CFLCode cflcode) {
		artifact.setModified_on(Utilities.getInstance().getUpdateCurrentDateTime());
		new ArtifactApi().updateArtifact(artifact);
		String query = "";
		if (cflcode.isAdded()) {
			query = new QueryMaker().createInsertQuery(cflcode, "cf_code", "");
			System.out.println(query);
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
		if (cflcode.isModified()) {
			query = new QueryMaker().createUpdateQuery(cflcode, "cf_code",
					String.format("Where CF_ID='%s'", cflcode.getCf_id()));
			System.out.println(query);
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}

		GlobalLoader.getInstance().initAllCFCodes();
		GlobalLoader.getInstance().initAllCFLibraryMap();
		GlobalLoader.getInstance().initAllCFLInputParameters();
		GlobalLoader.getInstance().initAllCFLOutputParameters();
	}

	public List<CFLCode> getCodedFLCodeData(Artifact artifact) {
		List<CFLCode> cflCodes = new ArrayList<CFLCode>();
		List<CFLCode> allcflCodes = GlobalLoader.getInstance().getAllCfCodes();
		for (CFLCode cfcode : allcflCodes) {
			if (cfcode.getCf_id().equals(artifact.getId())) {
				cflCodes.add(cfcode);
			}
		}
		return cflCodes;
	}

	public List<CFLInputParameter> getCodedFLInputParameters(Artifact artifact) {
		String query = String.format("select * from cf_input_parameters WHERE cf_id='%s' ORDER BY position asc;",
				artifact.getId());
		String result = QueryExecutor.getInstance().executeQuery(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, CFLInputParameter.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<CFLInputParameter>();
	}

	public List<CFLOutputParameter> getCodedFLOutputParameters(Artifact artifact) {
		String query = String.format("select * from cf_output_parameters WHERE cf_id='%s' ORDER BY position asc;",
				artifact.getId());
		String result = QueryExecutor.getInstance().executeQuery(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, CFLOutputParameter.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<CFLOutputParameter>();
	}

	public List<CFLibraryMap> getCodedFLLibrary(Artifact artifact) {
		String query = String.format("select * from cf_library_map WHERE cf_id='%s'", artifact.getId());
		String result = QueryExecutor.getInstance().executeQuery(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, CFLibraryMap.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<CFLibraryMap>();
	}

	public List<MainFileStoreDTO> getMainFileStores(CFLibraryMap cfLibraryMap) {
		String query = String.format("SELECT * FROM main_filestore Where f_id='%s'", cfLibraryMap.getF_id());
		String result = QueryExecutor.getInstance().executeQuery(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, MainFileStoreDTO.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<MainFileStoreDTO>();
	}

	public byte[] getLibraryFileData(String fid) {
		String query = String.format("select DATA from main_filestore_data WHERE f_id='%s'", fid);
		return QueryExecutor.getInstance().executeQueryWithByteData(query);
	}

	public void deleteAssociatedLibrary(String fid) {
		String query = String.format("delete from cf_library_map where f_id='%s'", fid);
		String query1 = String.format("delete from main_filestore where f_id='%s'", fid);
		String query2 = String.format("delete from main_filestore_data where f_id='%s'", fid);
		QueryExecutor.getInstance().executeUpdateQuery(query);
		QueryExecutor.getInstance().executeUpdateQuery(query1);
		QueryExecutor.getInstance().executeUpdateQuery(query2);
		GlobalLoader.getInstance().initAllCFLibraryMap();
		GlobalLoader.getInstance().initAllMainFileStoreDTOS();
	}

	public void addLibraryFileInDb(Artifact artifact, File libraryFile) {
		String[] fileData = libraryFile.getName().split("\\.");
		String fileName = fileData[0];
		fileName = fileName + artifact.getId().replaceAll("-", "_");
		String fileExtension = fileData[fileData.length - 1];

		List<MainFileStoreDTO> fileStoreDtos = GlobalLoader.getInstance().getAllMainFileStoreDtos();
		for (MainFileStoreDTO fileStoreDto : fileStoreDtos) {
			if (fileStoreDto.getFilename().toLowerCase().equals(fileName)) {
				fileStoreDto.setUploadedon(Utilities.getInstance().getUpdateCurrentDateTime());
				byte[] bytes = readFileToByteArray(libraryFile);
				String md5 = Utilities.getInstance().getMD5String(bytes);
				fileStoreDto.setMd5_checksum(md5);
				fileStoreDto.setFilelocationtype("Database");
				String fileStoreQuery = new QueryMaker().createUpdateQuery(fileStoreDto, "main_filestore",
						String.format("WHERE F_ID='%s'", fileStoreDto.getF_id()));
				QueryExecutor.getInstance().executeUpdateQuery(fileStoreQuery);
				String f_id = fileStoreDto.getF_id();
				String dbFile = "jdbc:sqlite:" + ServiceRepository.getInstance().getExportedDBFilePath();
				Connection c;
				try {
					c = DriverManager.getConnection(dbFile);
					String sql = "UPDATE main_filestore_data SET DATA=? WHERE F_ID=?";
					PreparedStatement p_stmt = c.prepareStatement(sql);
					try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
						bos.write(readFileToByteArray(libraryFile));
						bos.flush();
						bos.close();

						p_stmt.setBytes(1, bos.toByteArray());
						p_stmt.setString(2, f_id);
						p_stmt.execute();
						c.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GlobalLoader.getInstance().initAllCFLibraryMap();
				GlobalLoader.getInstance().initAllMainFileStoreDTOS();
				return;
			}
		}

		artifact.setModified_on(Utilities.getInstance().getCurrentDateTime());
		new ArtifactApi().updateArtifact(artifact);
		CFLibraryMap cflibraryMap = new CFLibraryMap();
		cflibraryMap.setCf_id(artifact.getId());
		cflibraryMap.setF_id(Utilities.getInstance().getUniqueUUID(""));
		String cflmQuery = new QueryMaker().createInsertQuery(cflibraryMap, "cf_library_map", "");
		QueryExecutor.getInstance().executeUpdateQuery(cflmQuery);

		MainFileStoreDTO mainFileStoreDto = new MainFileStoreDTO();
		mainFileStoreDto.setF_id(cflibraryMap.getF_id());
		mainFileStoreDto.setFilename(fileName);
		mainFileStoreDto.setExtension("." + fileExtension);
		mainFileStoreDto.setUploadedon(Utilities.getInstance().getCurrentDateTime());
		mainFileStoreDto.setSize(String.valueOf(libraryFile.length()));
		mainFileStoreDto.setFilelocationtype("Database");
		byte[] bytes = readFileToByteArray(libraryFile);
		String md5 = Utilities.getInstance().getMD5String(bytes);
		mainFileStoreDto.setMd5_checksum(md5);
		mainFileStoreDto.setTag("CustomKeywordLibrary");
		mainFileStoreDto.setUploadedon_tz(Utilities.getInstance().getCurrentTimeZone());

		String fileStoreQuery = new QueryMaker().createInsertQuery(mainFileStoreDto, "main_filestore", "");
		QueryExecutor.getInstance().executeUpdateQuery(fileStoreQuery);

		String dbFile = "jdbc:sqlite:" + ServiceRepository.getInstance().getExportedDBFilePath();
		Connection c;
		try {
			c = DriverManager.getConnection(dbFile);
			String sql = "INSERT INTO main_filestore_data (f_id, data) VALUES (?, ?)";
			PreparedStatement p_stmt = c.prepareStatement(sql);
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				bos.write(readFileToByteArray(libraryFile));
				bos.flush();
				bos.close();

				p_stmt.setString(1, cflibraryMap.getF_id());
				p_stmt.setBytes(2, bos.toByteArray());
				p_stmt.execute();
				c.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalLoader.getInstance().initAllCFLibraryMap();
		GlobalLoader.getInstance().initAllMainFileStoreDTOS();
	}

	private byte[] readFileToByteArray(File file) {
		FileInputStream fis = null;
		// Creating a byte array using the length of the file
		// file.length returns long which is cast to int
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();

		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return bArray;
	}

	public String getCodedFLCodeWithBody(String className, String usercode, String privatefunctioncode) {
		JavaClassSource _class = Roaster.create(JavaClassSource.class);
		_class.setName(className).setPublic();
		_class.addImport("com.crestech.opkey.plugin.contexts.Context");
		_class.addImport("com.crestech.opkey.plugin.codedfl.KeyValuePair");
		_class.addImport("org.openqa.selenium.By");
		_class.addImport("org.openqa.selenium.WebDriver");
		_class.addInterface("com.crestech.opkey.plugin.CodedFunctionLibrary");
		_class.addMethod().setName("run").setPublic().setBody(usercode);
		return _class.toString();
	}
}
