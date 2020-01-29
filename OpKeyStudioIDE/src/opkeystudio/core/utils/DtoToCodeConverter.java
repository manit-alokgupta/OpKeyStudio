package opkeystudio.core.utils;

import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class DtoToCodeConverter {
	public JavaClassSource getJavaClassOfGlobalVariables() {
		List<GlobalVariable> globalVariables = GlobalLoader.getInstance().getGlobalVaribles();
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName("GlobalVariables").setPublic();
		for (GlobalVariable gv : globalVariables) {
			class1.addField().setName(gv.getName().replaceAll(" ", "_")).setType("String")
					.setStringInitializer(gv.getValue()).setPublic().setStatic(true);
		}
		return class1;
	}

	public JavaClassSource getJavaClassORObjects(Artifact artifact, List<ORObject> orObjects) {
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName(artifact.getArtifactVariableName()).setPublic();
		return class1;
	}

}
