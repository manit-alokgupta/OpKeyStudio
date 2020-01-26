package opkeystudio.core.utils;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

public class DtoToCodeConverter {
	public JavaClassSource getJavaClassOfGlobalVariables() {
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName("GlobalVariables").setPublic();
		class1.addField().setName("data1").setType("String").setStringInitializer("Hello").setPublic().setStatic(true);
		return class1;
	}

	public static void main(String[] args) {
		JavaClassSource jcs = new DtoToCodeConverter().getJavaClassOfGlobalVariables();
		System.out.println(jcs.toString());
	}
}
