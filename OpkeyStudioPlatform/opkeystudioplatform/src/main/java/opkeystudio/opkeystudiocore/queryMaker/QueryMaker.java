package opkeystudio.opkeystudiocore.queryMaker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class QueryMaker {

	public void createQuery()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ObjectAttributeProperty oap = new ObjectAttributeProperty();
		oap.setOr_id("123");
		Class _class = oap.getClass();
		Field[] fieldArray = _class.getDeclaredFields();
	
		for (Field field : fieldArray) {

			field.setAccessible(true);

			Annotation annotation = field.getDeclaredAnnotation(DBField.class);
			if (annotation instanceof DBField) {

				String fieldName = field.getName();
				System.out.println(fieldName);
				System.out.println(annotation);
				Class fieldType = field.getType();
//				System.out.println(oap.getOr_id());

				try {
					Object value = field.get(_class); 

					if (fieldType.equals(String.class)) {

						
						System.out.println(value.toString());

						System.out.println("String:  " + fieldType.getSimpleName());
					}
					if (fieldType.equals(int.class)) {

						System.out.println("Integer:  " + fieldType.getSimpleName());
					}
					if (fieldType.equals(boolean.class)) {
						System.out.println("Boolean:  " + fieldType.getSimpleName());
					}
				} catch (Exception e) {
					System.out.println(e);
				}

			}
		}
	}

	public static void main(String[] ar)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		QueryMaker qm = new QueryMaker();
		qm.createQuery();
	}
}
