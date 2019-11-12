package opkeystudio.opkeystudiocore.core.queryMaker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class QueryMaker {
	public void createQueryString(Object object) throws IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> _class = object.getClass();
		Field[] fields = _class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Annotation annotation = field.getAnnotation(DBField.class);
			if (annotation instanceof DBField) {
				String fieldName = field.getName();
				Object fieldValue = field.get(object);
				if (fieldValue != null) {
					System.out.println(fieldName + "=" + fieldValue);
				}
			}
		}
	}

	public static void main(String[] ar) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, SQLException {
		ObjectAttributeProperty oap = new ObjectAttributeProperty();
		oap.setOr_id("123");
		new QueryMaker().createQueryString(oap);
	}
}
