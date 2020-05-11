package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class CFLTranspiler extends AbstractTranspiler {
	public CFLTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getProjectTranspiledArtifactsFolder());
	}

	@Override
	public void transpile(Artifact artifact) {
		try {
			if (artifact.getFile_type_enum() != MODULETYPE.Component) {
				return;
			}
			
			File file = createArtifactFile(artifact);
			JavaClassSource classSource = getCFLJavaClassSource(artifact);
			new TranspilerUtilities().addPackageName(artifact, classSource);
			new TranspilerUtilities().addDefaultImports(classSource);
			new TranspilerUtilities().writeCodeToFile(file, classSource);
			// new ArtifactParser().parseArtifact(artifact);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JavaClassSource getCFLJavaClassSource(Artifact artofact) {
		List<CFLCode> cflcodes = new CodedFunctionApi().getCodedFLCodeData(artofact);
		List<ComponentInputArgument> componentInputArgs = FlowApi.getInstance().getAllComponentInputArgument(artofact.getId());
		List<ComponentOutputArgument> componentOutputArgs = FlowApi.getInstance().getAllComponentOutputArgument(artofact.getId());	
		if (cflcodes.size() > 0) {
			CFLCode cflcode = cflcodes.get(0);
			String imports = "";
			if (cflcode.getImportpackages() != null) {
				imports = cflcode.getImportpackages();
			}
			String code = new CodedFunctionApi().getCodedFLCodeWithBody(artofact.getVariableName(),
					cflcode.getUsercode(), cflcode.getPrivateuserfunctions(), imports, componentInputArgs, componentOutputArgs);
			return (JavaClassSource) Roaster.parse(code);
		}
		String code = new CodedFunctionApi().getCodedFLCodeWithBody(artofact.getVariableName(), "", "", "", componentInputArgs,
				componentOutputArgs);
		return (JavaClassSource) Roaster.parse(code);
	}
}
