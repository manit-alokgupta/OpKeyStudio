package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.CodedFunctionBottomFactoryUI;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;

public class CFLCompilationResultTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;

	public CFLCompilationResultTable(Composite parent, int style, CodedFunctionBottomFactoryUI bottomFactoryUi) {
		super(parent, style);
		setParentBottomFactoryUI(bottomFactoryUi);
		init();
	}

	private void init() {
		String[] tableHeaders = new String[] { "Type", "Line No", "Error" };
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, 0);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Table table_0 = (Table) e.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 3);
				}
			}
		});
	}

	public CodedFunctionBottomFactoryUI getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

	public void renderCompilingError(List<CompileError> compileErrors) {
		this.removeAll();
		for (CompileError error : compileErrors) {
			TableItem ti = new TableItem(this, 0);
			ti.setText(new String[] { error.getKind().toString(), String.valueOf(error.getLineNumber()),
					error.getMessage() });
		}
	}

}
