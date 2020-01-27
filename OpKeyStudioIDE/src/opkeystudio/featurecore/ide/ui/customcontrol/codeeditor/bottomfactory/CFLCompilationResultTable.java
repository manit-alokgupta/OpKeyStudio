package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.CodedFunctionBottomFactoryUI;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;

public class CFLCompilationResultTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;

	public CFLCompilationResultTable(Composite parent, int style, CodedFunctionBottomFactoryUI bottomFactoryUi) {
		super(parent, style);
		setParentBottomFactoryUI(bottomFactoryUi);
	}

	public CodedFunctionBottomFactoryUI getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

}
