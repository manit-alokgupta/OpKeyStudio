package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;

public class CFLInputTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;

	public CFLInputTable(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactory) {
		super(parent, style);
		setParentBottomFactoryUI(parentBottomFactory);
		init();
	}

	private void init() {
		renderCFLInputParameters();
	}

	public CodedFunctionBottomFactoryUI getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

	public void renderCFLInputParameters() {
		Artifact artifact = getParentBottomFactoryUI().getParentCodedFunctionView().getArtifact();
	//	List<CFL> new CodedFunctionApi().getCodedFLInputParameters(artifact);
	}

}
