package opkeystudio;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public class Main {

	public static void main(String[] args) {
		JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		FieldSource<JavaClassSource> fieldSource = javaClass.addField().setName("home").setType("ORObject").setPublic()
				.setStatic(true);
		MethodSource<JavaClassSource> methodSource = javaClass.addMethod().setName("init").setPublic();
		methodSource.setBody("System.out.println(\"Hello\");");
		System.out.println(javaClass.toString());
	}

}
