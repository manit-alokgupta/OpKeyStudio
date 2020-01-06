package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class CustomTable extends Table {
	private int selectedRowIndex;
	private Object controlData;

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
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(this.getSelectedRowIndex());
		this.notifyListeners(SWT.Selection, null);
	}

}
