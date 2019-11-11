package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class OutputDataTable extends CustomTable {

	public OutputDataTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		String[] tableHeaders= {"Type","Parameter Name","Output Variable"};
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
					column.setWidth((table_0.getBounds().width ) / 3);
				}
			}
		});

		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStepTable table = (FlowStepTable) e.getSource();
				FlowStep flowStep = table.getSelectedFlowStep();
				System.out.println(flowStep.getFlow_stepid());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
	}

}