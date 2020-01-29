package opkeystudio.core.utils;

import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;

public class DtoToCodeConverter {
	public JavaClassSource getJavaClassOfGlobalVariables() {
		List<GlobalVariable> globalVariables = GlobalLoader.getInstance().getGlobalVaribles();
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName("GlobalVariables").setPublic();
		for (GlobalVariable gv : globalVariables) {
			class1.addField().setName(gv.getName()).setType("String").setStringInitializer(gv.getValue()).setPublic()
					.setStatic(true);
		}
		return class1;
	}
}
