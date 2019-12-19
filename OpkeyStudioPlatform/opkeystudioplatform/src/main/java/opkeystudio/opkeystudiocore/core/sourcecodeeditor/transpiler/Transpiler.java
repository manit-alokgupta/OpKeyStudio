package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;

public class Transpiler {
	private static Transpiler transpiler;

	public static Transpiler getTranspiler() {
		if (transpiler == null) {
			transpiler = new Transpiler();
		}
		return transpiler;
	}

	public void transpileGlobalVariables(List<GlobalVariable> globalVariables) {

	}
}
