package opkeystudio.opkeystudiocore.core.queryMaker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.collections.DuoList;

public class QueryMaker {
	public DuoList<String, String> createQueryString(Object object)
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
					duoList.addSecondValue(String.valueOf(fieldValue));
				}
			}
		}
		return duoList;
	}

	public static void main(String[] ar) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, SQLException {
		ObjectAttributeProperty oap = new ObjectAttributeProperty();
		oap.setOr_id("123");
		DuoList<String, String> allValues = new QueryMaker().createQueryString(oap);
		for(String v1:allValues.getAllFirstValues()) {
			System.out.println(v1);
		}
	}
}
