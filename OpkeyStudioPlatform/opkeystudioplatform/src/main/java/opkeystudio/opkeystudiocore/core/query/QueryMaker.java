package opkeystudio.opkeystudiocore.core.query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.collections.DuoList;
import opkeystudio.opkeystudiocore.core.dtoMaker.ArtifactMaker;

public class QueryMaker {
	public enum QUERYTYPE {
		INSERT, UPDATE
	};

	private DuoList<String, String> getQueryString(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> _class = object.getClass();
		Field[] fields = _class.getDeclaredFields();
		DuoList<String, String> duoList = new DuoList<>();
		for (Field field : fields) {
			field.setAccessible(true);
			Annotation annotation = field.getAnnotation(DBField.class);
			if (annotation instanceof DBField) {
				String fieldName = field.getName();
				Object fieldValue = field.get(object);
				if (fieldValue != null) {
					duoList.addFirstValue(fieldName);
					duoList.addSecondValue("'" + String.valueOf(fieldValue) + "'");
				}
			}
		}
		return duoList;
	}

	private String formatString(List<String> allStrings) {
		String output = "";
		for (String str : allStrings) {
			if (!output.isEmpty()) {
				output += ", ";
			}
			output += str;
		}
		return output;
	}

	private String createQuery(Object object, String tableName, String condition, QUERYTYPE queryType)
			throws IllegalArgumentException, IllegalAccessException {
		String conditionString = "";
		if (condition != null) {
			if (!condition.isEmpty()) {
				conditionString = " " + condition.trim();
			}
		}

		if (queryType == QUERYTYPE.INSERT) {
			String queryFormat = "INSERT INTO %s(%s) VALUES(%s)" + conditionString;
			DuoList<String, String> queryObject = getQueryString(object);
			String finalQuery = String.format(queryFormat, tableName, formatString(queryObject.getAllFirstValues()),
					formatString(queryObject.getAllSecondValues()));
			return finalQuery;
		}
		if (queryType == QUERYTYPE.UPDATE) {
			String queryFormat = "UPDATE %s SET %s" + conditionString;
			DuoList<String, String> queryObject = getQueryString(object);
			List<String> allFirstValues = queryObject.getAllFirstValues();
			List<String> allSecondValues = queryObject.getAllSecondValues();
			String outData = "";
			for (int i = 0; i < allFirstValues.size(); i++) {
				String key = allFirstValues.get(i);
				String value = allSecondValues.get(i);
				if (!outData.isEmpty()) {
					outData += ", ";
				}
				outData += key + "=" + value;
			}
			String finalQuery = String.format(queryFormat, tableName, outData);
			return finalQuery;
		}
		return null;
	}

	public String createInsertQuery(Object object, String tableName, String conditionString) {
		try {
			return createQuery(object, tableName, conditionString, QUERYTYPE.INSERT);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String createUpdateQuery(Object object, String tableName, String conditionString) {
		try {
			return createQuery(object, tableName, conditionString, QUERYTYPE.UPDATE);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Artifact arti = new ArtifactMaker().getArtifactObject("1234", "hello_testcase", MODULETYPE.Flow);
		String query = new QueryMaker().createInsertQuery(arti, "main_artifact_filesystem", "ewdwdw");
		System.out.println(query);
	}
}
