package opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class CodedFunctionApi {
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

	public String getCodedFLCodeWithBody(String className, String usercode, String privatefunctioncode) {
		String startCode = "// ### Import packages from your associated Libraries here\r\n"
				+ "//#BeginRegion-ImportSection\r\n" + "\r\n" + "//#EndRegion-ImportSection\r\n"
				+ "import com.crestech.opkey.plugin.contexts.Context;\r\n"
				+ "import com.crestech.opkey.plugin.codedfl.KeyValuePair;\r\n" + "import org.openqa.selenium.By;\r\n"
				+ "import org.openqa.selenium.WebDriver;\r\n" + "\r\n" + "public class " + className
				+ " implements com.crestech.opkey.plugin.CodedFunctionLibrary {\r\n" + "\r\n"
				+ "	public void run() throws Exception {" + usercode + "\n		//#EndRegion-UserCode\r\n" + "	}\n"
				+ "	//#BeginRegion-PrivateFunctions\r\n"
				+ "	// #### You may write multiple private functions over here. These functions can be called from this Coded Function.\r\n"
				+ "	/*" + "\n" + privatefunctioncode + "\n	//#EndRegion-PrivateFunctions\r\n" + "}";

		return startCode;
	}
}
