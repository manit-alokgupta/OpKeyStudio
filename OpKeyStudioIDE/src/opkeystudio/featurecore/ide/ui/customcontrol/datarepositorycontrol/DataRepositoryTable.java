package opkeystudio.featurecore.ide.ui.customcontrol.datarepositorycontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.featurecore.ide.ui.ui.DataRepositoryView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;

public class DataRepositoryTable extends CustomTable {
	private DataRepositoryView parentDataRepositoryView;
	private List<DRColumnAttributes> drColumnAttributes = new ArrayList<>();

	private DRColumnAttributes selectedDRColumnAttribute;
	private DRCellAttributes selectedDRCEllAttribute;

	public DataRepositoryTable(Composite parent, int style, DataRepositoryView parentView)
			throws JsonParseException, JsonMappingException, IOException {
		super(parent, style);
		this.setParentDataRepositoryView(parentView);
		init();
	}

	private void init() throws JsonParseException, JsonMappingException, IOException {

		renderAllDRDetails();
		TableCursor cursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(cursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;

		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomText text = new CustomText(cursor, 0);
				int selectedColNo = cursor.getColumn();
				int selectedRowNo = getSelectionIndices()[0];

				List<DRColumnAttributes> drColumnAttributes = getDrColumnAttributes();
				DRColumnAttributes drColumnAttreibute = drColumnAttributes.get(selectedColNo);
				DRCellAttributes drCellAttribute = drColumnAttreibute.getDrCellAttributes().get(selectedRowNo);
				setSelectedDRColumnAttribute(drColumnAttreibute);
				setSelectedDRCEllAttribute(drCellAttribute);

				if (getSelectedDRCEllAttribute().getValue() == null) {
					text.setText("");
				} else {
					text.setText(getSelectedDRCEllAttribute().getValue());
				}
				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						text.dispose();

					}

					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub

					}
				});

				text.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						cursor.getRow().setText(selectedColNo, text.getText());
						getSelectedDRCEllAttribute().setValue(text.getText());
						getSelectedDRCEllAttribute().setModified(true);
					}
				});

				controlEditor.setEditor(text);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (int i = 0; i < table_0.getColumnCount(); i++) {
					TableColumn column = table_0.getColumn(i);
					column.setWidth((table_0.getBounds().width - 50) / 5);
				}
			}
		});
	}

	private void initializeHeaders(List<DRColumnAttributes> columnAttributes) {
		this.removeAll();
		for (TableColumn column : this.getColumns()) {
			column.dispose();
		}
		for (DRColumnAttributes header : columnAttributes) {
			TableColumn column = new TableColumn(this, SWT.LEFT);
			column.setText(header.getName());
		}
		for (int i = 0; i < columnAttributes.size(); i++) {
			this.getColumn(i).pack();
		}

	}

	private void renderDRDatas(List<DRColumnAttributes> drDatas) {
		Collections.sort(drDatas);
		initializeHeaders(drDatas);
		setDrColumnAttributes(drDatas);

		int rowCount = 0;
		List<DRCellAttributes> allDrCellAttributes = new ArrayList<DRCellAttributes>();
		for (int columnNo = 0; columnNo < drDatas.size(); columnNo++) {
			DRColumnAttributes drColumnAttribute = drDatas.get(columnNo);
			if (drColumnAttribute.isDeleted()) {
				continue;
			}
			List<DRCellAttributes> drCellAttributes = drColumnAttribute.getDrCellAttributes();
			rowCount = drCellAttributes.size();
			for (int rowNo = 0; rowNo < drCellAttributes.size(); rowNo++) {
				DRCellAttributes drCellAttribute = drCellAttributes.get(rowNo);
				if (drCellAttribute.isDeleted()) {
					continue;
				}
				drCellAttribute.setDrColumnAttribute(drColumnAttribute);
				drCellAttribute.setRowNo(rowNo);
				drCellAttribute.setColumnNo(columnNo);
				allDrCellAttributes.add(drCellAttribute);
			}
		}

		for (int irow = 0; irow < rowCount; irow++) {
			ArrayList<DRCellAttributes> allRowDrCellAttributes = new ArrayList<DRCellAttributes>();
			for (DRCellAttributes drCellAttributes : allDrCellAttributes) {
				if (drCellAttributes.getRowNo() == irow) {
					allRowDrCellAttributes.add(drCellAttributes);
				}
			}

			DataRepositoryTableItem dti = new DataRepositoryTableItem(this, 0);
			dti.setDrCellAttributes(allRowDrCellAttributes);
			for (int i = 0; i < allRowDrCellAttributes.size(); i++) {
				DRCellAttributes drCellAttribute = allRowDrCellAttributes.get(i);
				if (drCellAttribute.getValue() == null) {
					dti.setText(i, "");
				} else {
					dti.setText(i, drCellAttribute.getValue());
				}
			}
		}
	}

	public void renderAllDRDetails() throws JsonParseException, JsonMappingException, IOException {
		Artifact artifact = getParentDataRepositoryView().getArtifact();
		List<DRColumnAttributes> drDatas = new DataRepositoryApi().getAllDRDatas(artifact.getId());
		renderDRDatas(drDatas);
	}

	public void refreshAllDRDetails() throws JsonParseException, JsonMappingException, IOException {
		List<DRColumnAttributes> drDatas = getDrColumnAttributes();
		renderDRDatas(drDatas);
	}

	public void deleteDRColumn() {
		getSelectedDRColumnAttribute().setDeleted(true);
	}

	public DataRepositoryView getParentDataRepositoryView() {
		return parentDataRepositoryView;
	}

	public void setParentDataRepositoryView(DataRepositoryView parentDataRepositoryView) {
		this.parentDataRepositoryView = parentDataRepositoryView;
	}

	public List<DRColumnAttributes> getDrColumnAttributes() {
		return drColumnAttributes;
	}

	public void setDrColumnAttributes(List<DRColumnAttributes> drColumnAttributes) {
		this.drColumnAttributes = drColumnAttributes;
	}

	public DRColumnAttributes getSelectedDRColumnAttribute() {
		return selectedDRColumnAttribute;
	}

	public void setSelectedDRColumnAttribute(DRColumnAttributes selectedDRColumnAttribute) {
		this.selectedDRColumnAttribute = selectedDRColumnAttribute;
	}

	public DRCellAttributes getSelectedDRCEllAttribute() {
		return selectedDRCEllAttribute;
	}

	public void setSelectedDRCEllAttribute(DRCellAttributes selectedDRCEllAttribute) {
		this.selectedDRCEllAttribute = selectedDRCEllAttribute;
	}

}
