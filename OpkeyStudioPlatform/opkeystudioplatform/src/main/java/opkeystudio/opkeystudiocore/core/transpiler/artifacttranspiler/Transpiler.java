package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;

public interface Transpiler {
	public void transpile(Artifact artifact, File outputFile);
}
