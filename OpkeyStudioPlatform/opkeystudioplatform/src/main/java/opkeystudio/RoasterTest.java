package opkeystudio;

import java.io.Serializable;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

public class RoasterTest {

	public static void main(String[] args) {
		final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		javaClass.setName("Person");

		javaClass.addInterface(Serializable.class);
		javaClass.addField()
		  .setName("serialVersionUID")
		  .setType("long")
		  .setLiteralInitializer("1L")
		  .setPublic()
		  .setStatic(true)
		  .setFinal(true);

		
		System.out.println(javaClass.toString());
	}

}
