package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;

public class TestObjectTable extends CustomTable {
	public TestObjectTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		String[] tableHeaders= {"Name","Provide Object","Object Type","Action"};
		for (String header : tableHeaders) {
			TableColumn column=new TableColumn(this,SWT.LEFT);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}
		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (int i = 0; i < table_0.getColumnCount(); i++) {
					TableColumn column = table_0.getColumn(i);
					column.setWidth((table_0.getBounds().width ) / 4);
				}
			}
		});
		
	}

	

}
