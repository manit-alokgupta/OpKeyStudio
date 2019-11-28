package opkeystudio.featurecore.ide.ui.customcontrol.datarepositorycontrol;

import java.io.IOException;
import java.util.List;

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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.featurecore.ide.ui.ui.DataRepositoryView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;

public class DataRepositoryTable extends CustomTable {

	private boolean paintCalled = false;
	private DataRepositoryTable thisTable;
	private DataRepositoryView parentDataRepositoryView;
	private DRColumnAttributes drCol;
	List<DRColumnAttributes> drColumnAtt;
	List<DRCellAttributes> drCellAtt;

	public DataRepositoryTable(Composite parent, int style, DataRepositoryView parentView)
			throws JsonParseException, JsonMappingException, IOException {
		super(parent, style);

		init();
		thisTable = this;
		this.setParentDataRepositoryView(parentView);
	}

	private void init() throws JsonParseException, JsonMappingException, IOException {

		renderAllDRDetails();
		TableColumn column = new TableColumn(this, 0);
		column.setText(" ");
		column.setWidth(40);
		column.setResizable(false);

		TableCursor cursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(cursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;
		for (DRColumnAttributes drColumnAttributes : drColumnAtt) {

			System.out.println(drColumnAttributes.getName());
			System.out.println(drColumnAttributes.getColumn_id());
			TableColumn columnNext = new TableColumn(this, 0);
			columnNext.setText(drColumnAttributes.getName());
			columnNext.setWidth(100);

//			for (DRCellAttributes drCellAttributes : drCellAtt) {
//				DataRepositoryTableItem dataRepositoryTableItem = new DataRepositoryTableItem(this, 0);
//				if (drColumnAttributes.getColumn_id() == drCellAttributes.getColumn_id()) {
////					cursor.getRow().setText(drCellAttributes.getValue());
//					dataRepositoryTableItem.setText(new String[] { drCellAttributes.getValue() });
//
//				}
//			}
		}

		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedColumn = cursor.getColumn();
				CustomText text = new CustomText(cursor, 0);
				System.out.println("Column number:-" + cursor.getColumn());
				System.out.println("Row number:-" + cursor.getRow());

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
						cursor.getRow().setText(selectedColumn, text.getText());

					}
				});

				controlEditor.setEditor(text);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

//		renderAllCellDetails();
	}

	public void renderAllDRDetails() throws JsonParseException, JsonMappingException, IOException {

		drColumnAtt = new DataRepositoryApi().getAllColumnsValues();
		drCellAtt = new DataRepositoryApi().getAllCellValues();

		for (DRColumnAttributes drColumnAttributes : drColumnAtt) {
			DataRepositoryTableItem dataRepositoryTableItem = new DataRepositoryTableItem(this, 0);
			dataRepositoryTableItem.setDRColumnData(drColumnAttributes);

		}
	}

	public void renderAllCellDetails() throws JsonParseException, JsonMappingException, IOException {
		List<DRCellAttributes> drCellAtt = new DataRepositoryApi().getAllCellValues();

		for (DRCellAttributes drCellAttributes : drCellAtt) {
			DataRepositoryTableItem dataRepositoryTableItem = new DataRepositoryTableItem(this, 0);
			dataRepositoryTableItem.setDRCellData(drCellAttributes);
			System.out.println(drCellAttributes.getPosition());

		}
	}

	public DataRepositoryView getParentDataRepositoryView() {
		return parentDataRepositoryView;
	}

	public void setParentDataRepositoryView(DataRepositoryView parentDataRepositoryView) {
		this.parentDataRepositoryView = parentDataRepositoryView;
	}

}
