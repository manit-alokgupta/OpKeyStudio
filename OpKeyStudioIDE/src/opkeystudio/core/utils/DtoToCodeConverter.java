package opkeystudio.core.utils;

import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.EditorTools;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class DtoToCodeConverter {
	public JavaClassSource getJavaClassOfGlobalVariables() {
		List<GlobalVariable> globalVariables = GlobalLoader.getInstance().getGlobalVaribles();
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName("OpKeyGlobalVariables").setPublic();
		for (GlobalVariable gv : globalVariables) {
			class1.addField().setName(gv.getName().replaceAll(" ", "_")).setType("String")
					.setStringInitializer(gv.getValue()).setPublic().setStatic(true);
		}
		return class1;
	}

	public JavaClassSource getJavaClassORObjects(Artifact artifact, List<ORObject> orObjects) {
		String classBody = "public class %s{%s}";
		String staticBody = "%s static {%s}";
		String staticVariableBody = "public static ORObject %s;";
		String variableDeclaration = "%s = new ORObject();";

		String methodCall = ".addProperty(\"%s\",\"%s\")";
		String staticVariableDatas = "";
		String variabledeclarationdata = "";
		for (ORObject orobject : orObjects) {
			String variableData = String.format(staticVariableBody, orobject.getVariableName());
			staticVariableDatas += variableData;
		}

		for (ORObject orobject : orObjects) {
			String variableDecalaration = String.format(variableDeclaration, orobject.getVariableName());
			List<ObjectAttributeProperty> attributeProperties = orobject.getObjectAttributesProperty();
			int count = 0;
			for (ObjectAttributeProperty oap : attributeProperties) {
				if (oap.getProperty().toLowerCase().equals("objectimage")) {
					continue;
				}
				String methodBody = String.format(methodCall, oap.getProperty(), oap.getValue());
				if (count == 0) {
					methodBody = orobject.getVariableName() + methodBody;
				}
				variableDecalaration += methodBody;
				count++;
			}
			variableDecalaration += ";";
			variabledeclarationdata += variableDecalaration;
		}

		String staticBodyData = String.format(staticBody, staticVariableDatas, variabledeclarationdata);
		String classBodyData = String.format(classBody, artifact.getArtifactVariableName(), staticBodyData);
		JavaClassSource outClass = (JavaClassSource) Roaster.parse(classBodyData);
		outClass.addImport("com.neon.ORObject");
		System.out.println(outClass.toString());
		return outClass;
	}

}
