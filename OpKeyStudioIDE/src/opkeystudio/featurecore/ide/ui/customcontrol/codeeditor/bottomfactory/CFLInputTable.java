package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.dtoMaker.CFLDMaker;

public class CFLInputTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;
	private List<CFLInputParameter> cflInputParameters = new ArrayList<CFLInputParameter>();

	public CFLInputTable(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactory) {
		super(parent, style);
		setParentBottomFactoryUI(parentBottomFactory);
		init();
	}

	private void init() {

		String[] headers = new String[] { "Name", "Data Type", "DefaultValue", "Optional", "Description" };
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

	public CodedFunctionBottomFactoryUI getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

	public void renderCFLInputParameters() {
		this.removeAll();
		List<CFLInputParameter> cflInputParameters = getParentBottomFactoryUI().getParentCodedFunctionView()
				.getJavaEditor().getCodedFunctionArtifact().getCflInputParameters();
		this.setCflInputParameters(cflInputParameters);
		for (CFLInputParameter cflinputparam : cflInputParameters) {
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setControlData(cflinputparam);
			String description = cflinputparam.getDescription();
			if (description == null) {
				description = "";
			}
			cti.setText(new String[] { cflinputparam.getName(), cflinputparam.getType(),
					cflinputparam.getDefaultvalue(), "", description });
		}
	}

	public CFLInputParameter getSelectedCFLInputArgument() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		CustomTableItem cti = (CustomTableItem) this.getSelection()[0];
		if (cti == null) {
			return null;
		}
		if (cti.getControlData() == null) {
			return null;
		}
		return (CFLInputParameter) cti.getControlData();
	}

	public void addBlankInputPArameter() {
		String variableName = this.getUniqueColumnData("input-parameter-", 0);
		CodedFunctionArtifact artifact = getParentBottomFactoryUI().getParentCodedFunctionView().getJavaEditor()
				.getCodedFunctionArtifact();
		CFLInputParameter inputParameter = new CFLDMaker().createCFInputParameterDTO(artifact, variableName,
				getSelectedCFLInputArgument(), artifact.getCflInputParameters());
		artifact.getCflInputParameters().add(inputParameter);
		Collections.sort(artifact.getCflInputParameters());
		getParentBottomFactoryUI().getParentCodedFunctionView().toggleSaveButton(true);
		renderCFLInputParameters();
	}

	public List<CFLInputParameter> getCflInputParameters() {
		return cflInputParameters;
	}

	public void setCflInputParameters(List<CFLInputParameter> cflInputParameters) {
		this.cflInputParameters = cflInputParameters;
	}

}
