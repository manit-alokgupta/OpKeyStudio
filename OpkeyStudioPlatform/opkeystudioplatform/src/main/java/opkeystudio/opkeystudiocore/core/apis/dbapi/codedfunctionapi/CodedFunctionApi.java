package opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLibraryMap;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class CodedFunctionApi {

	public void saveCFLCode(CFLCode cflcode) {
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
	}

	public List<CFLCode> getCodedFLCodeData(Artifact artifact) {
		String query = String.format("SELECT * FROM cf_code WHERE cf_id='%s'", artifact.getId());
		String result = QueryExecutor.getInstance().executeQuery(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, CFLCode.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<CFLCode>();
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
