package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.io.IOException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

public class ComponentArgumentInputTable extends CustomTable {

	private TestCaseView parentTestCaseView;

	public ComponentArgumentInputTable(Composite parent, int style, TestCaseView parentTestCaseView) {
		super(parent, style);
		this.setParentTestCaseView(parentTestCaseView);
		init();
	}

	private void init() {
		String[] tableHeaders = { "Name", "DataType", "Description" };
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
					column.setWidth((table_0.getBounds().width) / 3);
				}
			}
		});
		renderTable();

		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem item = getSelection()[0];
				CustomTableItem cti = (CustomTableItem) item;
				ComponentInputArgument cia = (ComponentInputArgument) cti.getControlData();
				FlowInputArgument selectedFlowInputArgument = getParentTestCaseView().getInputDataTable()
						.getSelectedFlowInputArgument();
				if (selectedFlowInputArgument == null) {
					return;
				}
				new FlowApiUtilities().setFlowInputData(getParentTestCaseView().getArtifact(),
						selectedFlowInputArgument, cia.getIp_id(), DataSource.ValueFromInputParameter);
				selectedFlowInputArgument.setModified(true);
				try {
					getParentTestCaseView().getInputDataTable()
							.renderInputTable(getParentTestCaseView().getInputDataTable().getFlowStep());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				getParentTestCaseView().toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void renderTable() {
		this.removeAll();
		Artifact artifact = getParentTestCaseView().getArtifact();
		List<ComponentInputArgument> compsinp = new FunctionLibraryApi().getAllComponentInputArgument(artifact.getId());
		for (ComponentInputArgument cia : compsinp) {
			String descp = "";
			if (cia.getDescription() != null) {
				descp = cia.getDescription();
			}
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setText(new String[] { cia.getName(), cia.getType(), descp });
			cti.setControlData(cia);
		}
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

}
