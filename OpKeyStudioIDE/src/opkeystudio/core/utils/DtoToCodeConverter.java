package opkeystudio.core.utils;

import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class DtoToCodeConverter {
	public JavaClassSource getJavaClassOfGlobalVariables() {
		List<GlobalVariable> globalVariables = GlobalLoader.getInstance().getGlobalVaribles();
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName("OpKeyGlobalVariables").setPublic();
		for (GlobalVariable gv : globalVariables) {
			class1.addField().setName(gv.getName().replaceAll(" ", "_")).setType("String")
					.setStringInitializer(gv.getValue()).setPublic().setStatic(true);
		}
		return class1;
	}

	public JavaClassSource getJavaClassORObjects(Artifact artifact, List<ORObject> orObjects) {
		String classBody = "public class %s{%s}";
		String staticBody = "%s static {%s}";
		String staticVariableBody = "public static ORObject %s;";
		String variableDeclaration = "%s = new ORObject();";

		String methodCall = ".addProperty(\"%s\",\"%s\")";
		String staticVariableDatas = "";
		String variabledeclarationdata = "";
		for (ORObject orobject : orObjects) {
			String variableData = String.format(staticVariableBody, orobject.getVariableName());
			staticVariableDatas += variableData;
		}

		for (ORObject orobject : orObjects) {
			String variableDecalaration = String.format(variableDeclaration, orobject.getVariableName());
			List<ObjectAttributeProperty> attributeProperties = orobject.getObjectAttributesProperty();
			int count = 0;
			for (ObjectAttributeProperty oap : attributeProperties) {
				if (oap.getProperty().toLowerCase().equals("objectimage")) {
					continue;
				}
				String methodBody = String.format(methodCall, oap.getProperty(), oap.getValue());
				if (count == 0) {
					methodBody = orobject.getVariableName() + methodBody;
				}
				variableDecalaration += methodBody;
				count++;
			}
			variableDecalaration += ";";
			variabledeclarationdata += variableDecalaration;
		}

		String staticBodyData = String.format(staticBody, staticVariableDatas, variabledeclarationdata);
		String classBodyData = String.format(classBody, artifact.getArtifactVariableName(), staticBodyData);
		JavaClassSource outClass = (JavaClassSource) Roaster.parse(classBodyData);
		outClass.addImport("com.opkeystudio.runtime.ORObject");
		return outClass;
	}

	public JavaClassSource getJavaClassDRObjects(Artifact artifact, List<DRColumnAttributes> drColumns) {
		String classBody = "public class %s{%s}";
		String staticBody = "%s static {%s}";

		String methodCall = "addDRCell(\"%s\",\"%s\");";
		String staticVariableDatas = "";
		for (DRColumnAttributes drColumnAttribute : drColumns) {
			String columnName = drColumnAttribute.getName();
			for (DRCellAttributes drCell : drColumnAttribute.getDrCellAttributes()) {
				String drcellValue = drCell.getValue();
				if (drcellValue == null) {
					drcellValue = "";
				}
				String fromatedCall = String.format(methodCall, columnName, drcellValue);
				staticVariableDatas += fromatedCall;
			}
		}

		String staticBodyData = String.format(staticBody, getDRObjectBody(), staticVariableDatas);
		String classBodyData = String.format(classBody, artifact.getArtifactVariableName(), staticBodyData);
		JavaClassSource outClass = (JavaClassSource) Roaster.parse(classBodyData);
		outClass.addImport("java.util.ArrayList");
		outClass.addImport("java.util.HashMap");
		outClass.addImport("java.util.List");
		outClass.addImport("java.util.Map");
		return outClass;
	}

	private String getDRObjectBody() {
		String data = "	private static Map<String, List<String>> drDatas = new HashMap<>();\r\n" + "\r\n"
				+ "	public static void addDRCell(String column, String cellValue) {\r\n"
				+ "		Map<String, List<String>> drDatas = getDrDatas();\r\n"
				+ "		if (drDatas.get(column) != null) {\r\n" + "			drDatas.get(column).add(cellValue);\r\n"
				+ "			return;\r\n" + "		}\r\n"
				+ "		List<String> cellDatas = new ArrayList<String>();\r\n" + "		cellDatas.add(cellValue);\r\n"
				+ "		drDatas.put(column, cellDatas);\r\n" + "	}\r\n" + "\r\n"
				+ "	public static List<String> getDRCells(String column) {\r\n"
				+ "		List<String> drcells = getDrDatas().get(column);\r\n" + "		if (drcells == null) {\r\n"
				+ "			return new ArrayList<String>();\r\n" + "		}\r\n"
				+ "		List<String> filteredDatas = new ArrayList<String>();\r\n"
				+ "		for (String drcell : drcells) {\r\n" + "			if (!drcell.trim().isEmpty()) {\r\n"
				+ "				filteredDatas.add(drcell);\r\n" + "			}\r\n" + "		}\r\n"
				+ "		return filteredDatas;\r\n" + "	}\r\n" + "\r\n"
				+ "	public static String getDRCell(String columnName, int rowNo) {\r\n"
				+ "		return getDRCells(columnName).get(rowNo);\r\n" + "	}\r\n" + "\r\n"
				+ "	public static Map<String, List<String>> getDrDatas() {\r\n" + "		return drDatas;\r\n"
				+ "	}\r\n" + "\r\n" + "	public static void setDrDatas(Map<String, List<String>> drDatas2) {\r\n"
				+ "		drDatas = drDatas2;\r\n" + "	}";
		return data;
	}

}
