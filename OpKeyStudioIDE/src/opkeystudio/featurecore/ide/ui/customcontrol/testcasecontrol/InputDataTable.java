package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;

public class InputDataTable extends CustomTable {
	private List<KeyWordInputArgument> keyWordInputArgs;
	private List<FlowInputArgument> flowInputArgs;

	public InputDataTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		String[] tableHeaders = { "Type", "Name", "Provide Data", " " };
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, SWT.LEFT);
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
					column.setWidth((table_0.getBounds().width) / 4);
				}
			}
		});
	}

	public List<KeyWordInputArgument> getKeyWordInputArgs() {
		return keyWordInputArgs;
	}

	public void setKeyWordInputArgs(List<KeyWordInputArgument> keyWordInputArgs) {
		this.keyWordInputArgs = keyWordInputArgs;
	}

	public List<FlowInputArgument> getFlowInputArgs() {
		return flowInputArgs;
	}

	public void setFlowInputArgs(List<FlowInputArgument> flowInputArgs) {
		this.flowInputArgs = flowInputArgs;
	}

	public void renderInputTable() {
		this.removeAll();
		List<FlowInputArgument> flowInputArgs = getFlowInputArgs();
		for (int i = 0; i < getKeyWordInputArgs().size(); i++) {
			KeyWordInputArgument keywordInputArg = getKeyWordInputArgs().get(i);
			if (!keywordInputArg.getDatatype().equals("ORObject")) {
				FlowInputArgument flowInputArg = flowInputArgs.get(i);
				CustomTableItem cti = new CustomTableItem(this, 0);
				cti.setText(new String[] { keywordInputArg.getDatatype(), keywordInputArg.getName(),
						flowInputArg.getStaticvalue() });
			}
		}
	}
}
