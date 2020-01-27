package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.CodedFunctionBottomFactoryUI;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;

public class CFLInputTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;

	public CFLInputTable(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactory) {
		super(parent, style);
		setParentBottomFactoryUI(parentBottomFactory);
	}

	public CodedFunctionBottomFactoryUI getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

}
