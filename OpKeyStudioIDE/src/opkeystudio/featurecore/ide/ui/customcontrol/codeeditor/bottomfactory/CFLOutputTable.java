package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;

public class CFLOutputTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;
	private List<CFLOutputParameter> cflOutputParameters = new ArrayList<CFLOutputParameter>();

	public CFLOutputTable(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactory) {
		super(parent, style);
		setParentBottomFactoryUI(parentBottomFactory);
		init();
	}

	private void init() {

		String[] headers = new String[] { "Name", "Data Type", "Description" };
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
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / headers.length);
				}
			}
		});
	}

	public void renderCFLOutputParameters() {
		this.removeAll();
		List<CFLOutputParameter> outputParameters = getParentBottomFactoryUI().getParentCodedFunctionView()
				.getJavaEditor().getCodedFunctionArtifact().getCflOutputParameters();
		this.setCflOutputParameters(outputParameters);
		for (CFLOutputParameter cfloutputparam : outputParameters) {
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setControlData(cfloutputparam);
			String description = cfloutputparam.getDescription();
			if (description == null) {
				description = "";
			}
			cti.setText(new String[] { cfloutputparam.getName(), cfloutputparam.getType(), description });
		}
	}

	public CodedFunctionBottomFactoryUI getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

	public List<CFLOutputParameter> getCflOutputParameters() {
		return cflOutputParameters;
	}

	public void setCflOutputParameters(List<CFLOutputParameter> cflOutputParameters) {
		this.cflOutputParameters = cflOutputParameters;
	}

}
