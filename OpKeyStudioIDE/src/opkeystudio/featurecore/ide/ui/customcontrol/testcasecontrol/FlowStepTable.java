package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class FlowStepTable extends CustomTable {
	private FlowStepTable thisTable = this;

	public FlowStepTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		String[] tableHeaders = { "#", "Keyword", "ORObject", "Input", "Output", "Description" };
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
					if (i == 0) {
						column.setWidth(50);
					} else {
						column.setWidth((table_0.getBounds().width - 50) / 5);
					}
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

	public void setFlowStepsData(List<FlowStep> flowSteps) {
		super.setControlData(flowSteps);
	}

	@SuppressWarnings("unchecked")
	public List<FlowStep> getFlowStepsData() {
		return (List<FlowStep>) super.getControlData();
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(FlowStepTableItem item) {
		FlowStep attrProperty = item.getFlowStepeData();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		TableEditor editor3 = getTableEditor();
		TableEditor editor4 = getTableEditor();
		CustomButton button = new CustomButton(this, SWT.CHECK);
		button.setSelection(attrProperty.isShouldrun());
		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				thisTable.deselectAll();
				thisTable.setSelection(new TableItem[] { item });
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		editor1.setEditor(button, item, 0);
	}

	public void renderFlowSteps() throws JsonParseException, JsonMappingException, SQLException, IOException {
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
		FlowApi.getInstance().initAllFlowInputArguments();
		FlowApi.getInstance().initAllFlowOutputArguments();
		List<FlowStep> flowSteps = FlowApi.getInstance().getAllFlowSteps(artifactId);
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.getKeyword() != null) {
				String orname = "";
				String keyWordName = "";
				if (flowStep.getOrObject().size() > 0) {
					orname = flowStep.getOrObject().get(0).getName();
				}
				if (flowStep.getKeyword() != null) {
					keyWordName = flowStep.getKeyword().getKeywordname();
				}
				FlowStepTableItem flowTableItem = new FlowStepTableItem(this, 0);
				flowTableItem.setText(new String[] { "", keyWordName, orname, "", "", flowStep.getKeyword().getKeyworddescription() });
				flowTableItem.setFlowStepData(flowStep);
				addTableEditor(flowTableItem);
			}
		}
	}

	public FlowStep getSelectedFlowStep() {
		FlowStepTableItem flowTableItem = (FlowStepTableItem) this.getSelection()[0];
		return flowTableItem.getFlowStepeData();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
