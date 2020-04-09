import java.io.File;
import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordLoader;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;

public class Converter {
	private String codeFormat = "\npublic %s %s(%s) {\n%s\n}\n";

	public void convertDBToCode(String dbFilePath) {
		List<Keyword> allKeywords = new KeywordLoader().loadKeywords(dbFilePath);
		List<KeyWordInputArgument> allKeywordInputArguments = new KeywordLoader()
				.loadAllKeywordInputArguments(dbFilePath);
		for (Keyword keyword : allKeywords) {
			List<KeyWordInputArgument> keywordInputArguments = new ArrayList<KeyWordInputArgument>();
			for (KeyWordInputArgument keyWordInputArgument : allKeywordInputArguments) {
				if (keyword.getKeywordid().equals(keyWordInputArgument.getKeywordid())) {
					keywordInputArguments.add(keyWordInputArgument);
				}
			}
			keyword.setPluginName(new File(dbFilePath).getName().replaceAll(".db", ""));
			keyword.setKeywordInputArguments(keywordInputArguments);
		}

		for (Keyword keyword : allKeywords) {
			String keywordName = keyword.getName();
			if (keywordName.startsWith("Mobile_")) {
				continue;
			}
			String outputType = keyword.getOutputtype();
			String associatedMethod = keyword.getAssociatedmethod();
			outputType = formatDataType(outputType);
			List<KeyWordInputArgument> inputArguments = keyword.getKeywordInputArguments();
			String argumentsCode = getInputArgumentsCode(inputArguments);
			String keywordBody = getKeywordBody("System.out.println(\">>Keyword Called " + keywordName + "\");",
					"//" + associatedMethod, getReturnTypeData(outputType));
			String keyWordCode = getKeywordCode(keywordName, outputType, argumentsCode, keywordBody);
			System.out.println(keyWordCode);
		}

		System.out.println("Size " + allKeywords.size());
	}

	private String getKeywordCode(String keywordName, String outputType, String argumentNames, String keywordBody) {
		return String.format(this.codeFormat, outputType, keywordName, argumentNames, keywordBody);
	}

	private String getInputArgumentsCode(List<KeyWordInputArgument> inputArguments) {
		String code = "";
		for (int i = 0; i < inputArguments.size(); i++) {
			KeyWordInputArgument inputArgument = inputArguments.get(i);
			if (!code.isEmpty()) {
				code += ", ";
			}
			String dataType = formatDataType(inputArgument.getDatatype());
			code += dataType + " arg" + i;
		}
		return code;
	}

	private String formatDataType(String dataType) {
		if (dataType.equals("Boolean")) {
			return "boolean";
		}
		if (dataType.equals("Integer")) {
			return "int";
		}
		if (dataType.equals("Double")) {
			return "double";
		}
		if (dataType.equals("Long")) {
			return "long";
		}
		return dataType;
	}

	private String getKeywordBody(String... codes) {
		String output = "";
		for (int i = 0; i < codes.length; i++) {
			output += "\n" + codes[i] + "\n";
		}
		return output;
	}

	private String getReturnTypeData(String dataType) {
		if (dataType.equals("boolean")) {
			return "return false;";
		}
		if (dataType.equals("int")) {
			return "return 0;";
		}
		if (dataType.equals("long")) {
			return "return 0;";
		}
		if (dataType.equals("double")) {
			return "return 0;";
		}
		return "return \"\";";
	}
}
