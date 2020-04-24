package opkeystudio.opkeystudiocore.core.transpiler;

import java.util.ArrayList;
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

public class GlobalTranspiler {
	public JavaClassSource getJavaClassOfGlobalVariables() {
		List<GlobalVariable> globalVariables = GlobalLoader.getInstance().getGlobalVaribles();
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName("OpKeyGlobalVariables").setPublic();
		for (GlobalVariable gv : globalVariables) {
			try {
				class1.addField().setName(gv.getVariableName()).setType("String").setStringInitializer(gv.getValue())
						.setPublic().setStatic(true);
			} catch (Exception e) {
				e.printStackTrace();
				class1.addField().setName("_" + gv.getVariableName()).setType("String")
						.setStringInitializer(gv.getValue()).setPublic().setStatic(true);
			}
		}
		return class1;
	}

	private boolean isVariableAlreadyAdded(List<String> varaiableNames, String varName) {
		if (varaiableNames.contains(varName)) {
			return true;
		}
		return false;
	}

	public JavaClassSource getJavaClassORObjects(Artifact artifact) {
		List<ORObject> orObjects = GlobalLoader.getInstance().getAllOrObjects(artifact.getId());
		for (ORObject object : orObjects) {
			List<ObjectAttributeProperty> attributeProps = GlobalLoader.getInstance()
					.getORObjectAttributeProperty(object.getObject_id());
			object.setObjectAttributesProperty(attributeProps);
		}
		String classBody = "public class %s{%s}";
		String staticBody = "%s static {%s}";
		String staticVariableBody = "public static ORObject %s;";
		String variableDeclaration = "%s = new ORObject();";

		String methodCall = ".addProperty(\"%s\",\"%s\")";
		String staticVariableDatas = "";
		String variabledeclarationdata = "";
		List<String> variableNames = new ArrayList<String>();
		for (ORObject orobject : orObjects) {
			String variableName = orobject.getVariableName();
			int count = 0;
			if (variableNames.contains(variableName)) {
				while (true) {
					count++;
					String varName = variableName + String.valueOf(count);
					boolean contains = isVariableAlreadyAdded(variableNames, varName);
					if (contains == false) {
						// Need to Fix this
						orobject.setVariableName(varName);
						break;
					}
				}
			} else {
				String variableData = String.format(staticVariableBody, orobject.getVariableName());
				staticVariableDatas += variableData;
				variableNames.add(orobject.getVariableName());
			}
		}

		for (ORObject orobject : orObjects) {
			String parentVariableName = null;
			String parentId = orobject.getParent_object_id();
			for (ORObject parentObject : orObjects) {
				if (parentObject.getObject_id().equals(parentId)) {
					parentVariableName = parentObject.getVariableName();
					break;
				}
			}
			String variableDecalaration = String.format(variableDeclaration, orobject.getVariableName());
			List<ObjectAttributeProperty> attributeProperties = orobject.getObjectAttributesProperty();
			int count = 0;
			for (ObjectAttributeProperty oap : attributeProperties) {
				if (oap.isIsused() == false) {
					continue;
				}
				if (oap.getProperty().toLowerCase().equals("objectimage")) {
					continue;
				}
				String value = oap.getValue();
				if (value == null) {
					value = "";
				}
				value = value.replaceAll("\\r?\\n", "");
				String methodBody = String.format(methodCall, oap.getProperty(),
						value.trim().replace("\\", "\\\\").replace("\"", "\\\""));
				if (count == 0) {
					methodBody = orobject.getVariableName() + methodBody;
				}
				variableDecalaration += methodBody;
				count++;
			}
			variableDecalaration += ";";
			variabledeclarationdata += variableDecalaration;
			if (parentVariableName != null) {
				variabledeclarationdata += orobject.getVariableName() + ".setParentORObject(" + parentVariableName
						+ ");";
			}
		}

		String staticBodyData = String.format(staticBody, staticVariableDatas, variabledeclarationdata);
		String classBodyData = String.format(classBody, artifact.getVariableName(), staticBodyData);
		JavaClassSource outClass = (JavaClassSource) Roaster.parse(classBodyData);
		outClass.addImport("com.opkeystudio.runtime.ORObject");
		return outClass;
	}

	public JavaClassSource getJavaClassDRObjects(Artifact artifact) {
		List<DRColumnAttributes> drColumns = GlobalLoader.getInstance().getAllDRColumns(artifact.getId());
		for (DRColumnAttributes drColumn : drColumns) {
			List<DRCellAttributes> drCells = GlobalLoader.getInstance().getDRColumnCells(drColumn.getColumn_id());
			drColumn.setDrCellAttributes(drCells);
		}
		String classBody = "public class %s{%s}";
		String staticBody = "%s static {%s}";

		String methodCall = "addDRCell(\"%s\",\"%s\");";
		String staticVariableDatas = "";
		for (DRColumnAttributes drColumnAttribute : drColumns) {
			String columnName = drColumnAttribute.getVariableName();
			for (DRCellAttributes drCell : drColumnAttribute.getDrCellAttributes()) {
				String drcellValue = drCell.getValue();
				if (drcellValue == null) {
					drcellValue = "";
				}
				drcellValue = drcellValue.replaceAll("\\r?\\n", "");
				drcellValue = drcellValue.trim().trim().replace("\\", "\\\\").replace("\"", "\\\"");
				String fromatedCall = String.format(methodCall, columnName, drcellValue);
				staticVariableDatas += fromatedCall;
			}
		}

		String staticBodyData = String.format(staticBody, getDRObjectBody(), staticVariableDatas);
		String classBodyData = String.format(classBody, artifact.getVariableName(), staticBodyData);
		JavaClassSource outClass = (JavaClassSource) Roaster.parse(classBodyData);
		outClass.addImport("java.util.ArrayList");
		outClass.addImport("java.util.HashMap");
		outClass.addImport("java.util.List");
		outClass.addImport("java.util.Map");
		outClass.addImport("java.util.Set");
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
				+ "	public static void updateDRCell(String columnName, int rowNo, String value) {\r\n"
				+ "		List<String> cells = getDRCells(columnName);\r\n" + "		if (cells.size() == 0) {\r\n"
				+ "			return;\r\n" + "		}\r\n" + "		getDrDatas().get(columnName).set(rowNo, value);\r\n"
				+ "	}\r\n" + "\r\n" + "	public static List<String> getAllDRColumns() {\r\n"
				+ "		List<String> allColumns = new ArrayList<String>();\r\n"
				+ "		Set<String> columns = getDrDatas().keySet();\r\n" + "		for (String column : columns) {\r\n"
				+ "			allColumns.add(column);\r\n" + "		}\r\n" + "		return allColumns;\r\n" + "	}\r\n"
				+ "\r\n" + "	public static int getAllDRColumnCount() {\r\n"
				+ "		return getDrDatas().keySet().size();\r\n" + "	}\r\n" + "\r\n"
				+ "	public static int getAllDRRowCount() {\r\n" + "		List<String> columns = getAllDRColumns();\r\n"
				+ "		for (String column : columns) {\r\n"
				+ "			List<String> cells = getDRCells(column);\r\n" + "			return cells.size();\r\n"
				+ "		}\r\n" + "		return 0;\r\n" + "	}\r\n" + "\r\n"
				+ "	public static List<String> getDRRowValues(int rowno) {\r\n"
				+ "		List<String> allRowsValue = new ArrayList<String>();\r\n"
				+ "		List<String> columns = getAllDRColumns();\r\n" + "		for (String column : columns) {\r\n"
				+ "			List<String> cells = getDRCells(column);\r\n" + "			if (cells.size() > 0) {\r\n"
				+ "				allRowsValue.add(cells.get(rowno));\r\n" + "			}\r\n" + "		}\r\n"
				+ "		return allRowsValue;\r\n" + "	}\r\n" + "\r\n"
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
