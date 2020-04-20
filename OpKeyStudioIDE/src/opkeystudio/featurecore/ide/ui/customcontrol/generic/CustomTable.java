package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class CustomTable extends Table {
	private int selectedRowIndex = 0;
	private Object controlData;
	private int selectedColumn = 0;
	private TableCursor tablecursor;

	public CustomTable(Composite parent, int style) {
		super(parent, style);
		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelectedRowIndex(getSelectionIndex());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public Object getControlData() {
		return controlData;
	}

	public void setControlData(Object controlData) {
		this.controlData = controlData;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}

	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}

	public void selectDefaultRow() {
		try {
			if (this.getItemCount() == 0) {
				return;
			}
			this.setSelection(this.getSelectedRowIndex());
			if (this.getTablecursor() != null) {
				int column = this.getSelectedColumn();
				this.getTablecursor().setSelection(this.getSelectedRowIndex(), column);
				this.getTablecursor().notifyListeners(SWT.Selection, null);
			}
			this.notifyListeners(SWT.Selection, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getSelectedColumn() {
		return selectedColumn;
	}

	public void setSelectedColumn(int selectedColumn) {
		this.selectedColumn = selectedColumn;
	}

	public TableCursor getTablecursor() {
		return tablecursor;
	}

	public void setTablecursor(TableCursor tablecursor) {
		this.tablecursor = tablecursor;
	}

	private List<String> getTableColumnData(int columnIndex) {
		List<String> columnDatas = new ArrayList<String>();
		TableItem[] items = this.getItems();
		for (TableItem item : items) {
			String columnData = item.getText(columnIndex);
			if (columnData == null) {
				continue;
			}
			columnDatas.add(columnData.toLowerCase());
		}
		return columnDatas;
	}

	public String getUniqueColumnData(String stringToSearch, int columnIndex) {
		List<String> columnDatas = getTableColumnData(columnIndex);
		for (int i = 1; i < 1000; i++) {
			String columnData = stringToSearch + i;
			if (!columnDatas.contains(columnData.toLowerCase())) {
				return columnData;
			}
		}
		return "";
	}

	public boolean isColumnDataUnique(String columnData, int columnIndex) {
		if (columnData.trim().isEmpty()) {
			return true;
		}
		List<String> dataList = new ArrayList<String>();
		List<String> columnDatas = getTableColumnData(columnIndex);
		for (String cdata : columnDatas) {
			if (cdata.equals(columnData.toLowerCase())) {
				dataList.add(columnData);
			}
		}
		if (dataList.size() > 1) {
			return false;
		}
		return true;
	}
}
