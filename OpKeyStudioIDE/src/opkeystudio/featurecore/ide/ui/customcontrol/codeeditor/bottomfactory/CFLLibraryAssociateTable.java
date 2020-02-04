package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;

public class CFLLibraryAssociateTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;

	public CFLLibraryAssociateTable(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactory) {
		super(parent, style);
		this.setParentBottomFactoryUI(parentBottomFactory);
		init();
	}

	private void init() {

		String[] headers = new String[] { "#", "FileName", "Type" };
		for (String header : headers) {
			TableColumn column = new TableColumn(this, 0);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < headers.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Table table_0 = (Table) e.getSource();
				int count = 0;
				for (TableColumn column : table_0.getColumns()) {
					if (count == 0) {
						column.setWidth(100);
					} else {
						column.setWidth((table_0.getBounds().width - 100) / 2);
					}
					count++;
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

}
