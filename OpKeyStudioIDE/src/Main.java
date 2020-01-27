import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
		Class _class = Test.class;
		Method[] method = _class.getDeclaredMethods();
		for (Method m : method) {
			System.out.println(m.getReturnType().getTypeName());
		}
	}

}
