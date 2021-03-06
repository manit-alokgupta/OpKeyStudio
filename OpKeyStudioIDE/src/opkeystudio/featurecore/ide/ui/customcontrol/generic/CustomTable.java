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

import opkeystudio.featurecore.ide.ui.ui.superview.events.GlobalLoadListener;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalLoadListenerDispatcher;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyIntellisenseListenerDispatcher;

public class CustomTable extends Table {
	private int selectedRowIndex = -1;
	private Object controlData;
	private int selectedColumn = 0;

	private List<GlobalLoadListener> listeners = new ArrayList<>();
	private List<GlobalLoadListener> intellisenseListners = new ArrayList<GlobalLoadListener>();
	private TableCursor tableCursor;

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
		OpKeyGlobalLoadListenerDispatcher.getInstance().addSuperComposite(this);
		OpKeyIntellisenseListenerDispatcher.getInstance().addSuperComposite(this);
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
			selectTableRow(this.getSelectedRowIndex());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectLastRow() {
		selectTableRow(this.getItemCount() - 1);
	}

	private void selectTableRow(int rowno) {
		try {
			if (rowno == -1 && this.getItemCount() > 0) {
				this.setSelection(rowno + 1);
				this.notifyListeners(SWT.Selection, null);
				return;
			}
			if (rowno + 1 > this.getItemCount()) {
				this.setSelection(rowno - 1);
				this.notifyListeners(SWT.Selection, null);
				return;
			}
			this.setSelection(rowno);
			this.notifyListeners(SWT.Selection, null);
		} catch (Exception e) {

		}
	}

	public void selectNextRow() {
		try {
			if (this.getSelectedRowIndex() == 0 && this.getItemCount() == 1) {
				this.setSelection(this.getSelectedRowIndex());
				this.notifyListeners(SWT.Selection, null);
				return;
			}
			this.setSelection(this.getSelectedRowIndex() + 1);
			this.notifyListeners(SWT.Selection, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void selectDefaultRowByCursor(TableCursor cursor, int column) {
		try {
			if (this.getItemCount() == 0) {
				return;
			}
			if (cursor != null) {
				cursor.setSelection(this.getSelectedRowIndex(), column);
				cursor.notifyListeners(SWT.Selection, null);
				cursor.notifyListeners(SWT.MouseUp, null);
				cursor.notifyListeners(SWT.MouseDown, null);
			}
			this.notifyListeners(SWT.Selection, null);
			this.notifyListeners(SWT.MouseUp, null);
			this.notifyListeners(SWT.MouseDown, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectNextRowByCursor(TableCursor cursor, int column) {
		if (cursor != null) {
			try {
				int rowNo = this.getSelectedRowIndex();
				selectTableRow(rowNo);
				cursor.setSelection(rowNo + 1, column);
				cursor.notifyListeners(SWT.Selection, null);
				this.notifyListeners(SWT.Selection, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void selectColumnByCursor(TableCursor cursor, int columnIndex) {
		if (cursor != null) {
			cursor.setSelection(this.getSelectedRowIndex(), columnIndex);
			cursor.notifyListeners(SWT.Selection, null);
		}
	}

	public int getSelectedColumn() {
		return selectedColumn;
	}

	public void setSelectedColumn(int selectedColumn) {
		this.selectedColumn = selectedColumn;
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

	public void setColumnTextWithCursor(TableCursor cursor, int selectedColumn, String text) {
		if (cursor == null) {
			return;
		}
		if (cursor.getRow() == null) {
			return;
		}
		if (text == null) {
			return;
		}
		cursor.getRow().setText(selectedColumn, text);
	}

	public String getColumnTextWithCursor(TableCursor cursor, int selectedColumn) {
		if (cursor == null) {
			return "";
		}
		if (cursor.getRow() == null) {
			return "";
		}
		String text = cursor.getRow().getText(selectedColumn);
		if (text == null) {
			return "";
		}
		return text;
	}

	public void addOpKeyGlobalLoadListener(GlobalLoadListener listener) {
		listeners.add(listener);
	}

	public void removeOpKeyGlobalLoadListener(GlobalLoadListener listener) {
		listeners.remove(listener);
	}

	public void addIntellisenseLoadListener(GlobalLoadListener listener) {
		intellisenseListners.add(listener);
	}

	public void removeIntellisenseLoadListener(GlobalLoadListener listener) {
		intellisenseListners.remove(listener);
	}

	public void fireGlobalListener() {
		for (GlobalLoadListener listener : this.listeners) {
			listener.handleGlobalEvent();
		}
	}

	public void fireIntellisenseListener() {
		for (GlobalLoadListener listener : this.intellisenseListners) {
			listener.handleGlobalEvent();
		}
	}

	public TableCursor getTableCursor() {
		return tableCursor;
	}

	public void setTableCursor(TableCursor tableCursor) {
		this.tableCursor = tableCursor;
	}
}
