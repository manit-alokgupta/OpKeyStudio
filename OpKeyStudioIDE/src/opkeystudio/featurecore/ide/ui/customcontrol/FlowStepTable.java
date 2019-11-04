package opkeystudio.featurecore.ide.ui.customcontrol;

import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class FlowStepTable extends CustomTable {

	public FlowStepTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		String[] tableHeaders = { "Keyword", "ORObject" };
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
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 2);
				}
			}
		});
	}

	public void setFlowStepsData(List<FlowStep> flowSteps) {
		super.setControlData(flowSteps);
	}

	@SuppressWarnings("unchecked")
	public List<FlowStep> getFlowStepsData() {
		return (List<FlowStep>) super.getControlData();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
