package opkeystudio.opkeystudiocore.queryMaker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class QueryMaker {

	public void createQuery() throws NoSuchFieldException, SecurityException {
		ObjectAttributeProperty oap = new ObjectAttributeProperty();
		Class _class = oap.getClass();
		Field[] field = _class.getDeclaredFields();

		for (Field f : field) {
			Annotation annotation = f.getDeclaredAnnotation(DBField.class);
			if (annotation instanceof DBField) {
				System.out.println(f.getName());
				System.out.println(annotation);
				System.out.println(f.getType() + "\n");
			}
		}
	}

	public static void main(String[] ar) throws NoSuchFieldException, SecurityException {
		QueryMaker qm = new QueryMaker();
		qm.createQuery();
	}
}
