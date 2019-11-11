package opkeystudio.opkeystudiocore.core.queryMaker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.StringJoiner;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;

public class QueryMaker {
	String column = "";
	StringJoiner joinColumn = new StringJoiner(", ");
	StringJoiner joinColumnValues = new StringJoiner(", ");
	Object columnValues="";

	public void createQuery() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, SQLException {
		ObjectAttributeProperty oap = new ObjectAttributeProperty();
		oap.setOr_id("123");

		Class<? extends ObjectAttributeProperty> _class = oap.getClass();

		Field[] fieldArray = _class.getDeclaredFields();

		for (Field field : fieldArray) {
			field.setAccessible(true);
			Object value = field.get(oap);

			Annotation annotation = field.getDeclaredAnnotation(DBField.class);

			if (annotation instanceof DBField) {
				String fieldName = field.getName();
				System.out.println("fieldName is: " + fieldName);

				joinColumn.add(fieldName);

				System.out.println("annotation is: " + annotation);
				Class<?> fieldType = field.getType();
				try {
					if (fieldType.equals(String.class)) {
						System.out.println("Type:  " + fieldType.getSimpleName());
						System.out.println("value are: " + value + "\n");
						
					}
					if (fieldType.equals(int.class)) {
						System.out.println("Type:  " + fieldType.getSimpleName());
						System.out.println("value are: " + value + "\n");
					}
					if (fieldType.equals(boolean.class)) {
						System.out.println("Type:  " + fieldType.getSimpleName());
						System.out.println("value are: " + value + "\n");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		column = joinColumn.toString();
		System.out.println(column);
		System.out.println("Enter Table name");
		Scanner sc = new Scanner(System.in);
		String tablename = sc.nextLine();

		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String insert = sqlComm.executeQueryString("insert into" + tablename + "'(" + column + ")'");
		sc.close();
	}

	public static void main(String[] ar) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, SQLException {
		QueryMaker qm = new QueryMaker();
		qm.createQuery();
	}
}
