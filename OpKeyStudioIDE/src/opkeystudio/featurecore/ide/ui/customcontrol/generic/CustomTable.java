package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

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
				System.out.println("Selection ");
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
				System.out.println("Selecting Column " + column);
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

}
