package opkeystudio.featurecore.ide.ui.customcontrol.datarepositorycontrol;

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
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.featurecore.ide.ui.ui.DataRepositoryView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.dtoMaker.DRMaker;

public class DataRepositoryTable extends CustomTable {
	private DataRepositoryView parentDataRepositoryView;
	private List<DRColumnAttributes> drColumnAttributes = new ArrayList<>();

	private DRColumnAttributes selectedDRColumnAttribute;
	private DRCellAttributes selectedDRCEllAttribute;
	private int selectedDRRow;
	private TableCursor cursor;

	public DataRepositoryTable(Composite parent, int style, DataRepositoryView parentView) {
		super(parent, style);
		this.setParentDataRepositoryView(parentView);
		init();
	}

	private void init() {
		this.setHeaderBackground(SWTResourceManager.getColor(248, 248, 245));
		getParentDataRepositoryView().toggleMoveColumnLeftButton(false);
		getParentDataRepositoryView().toggleMoveColumnRightButton(false);
		getParentDataRepositoryView().toggleMoveRowDownButton(false);
		getParentDataRepositoryView().toggleMoveRowUpButton(false);
		getParentDataRepositoryView().toggleAddColumnButton(false);
		getParentDataRepositoryView().toggleAddRowButton(false);
		getParentDataRepositoryView().toggleDeleteColumnButton(false);
		getParentDataRepositoryView().toggleDeleteRowButton(false);
		getParentDataRepositoryView().toggleRenameColumnButton(false);
		getParentDataRepositoryView().toggleSaveButton(false);

		cursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(cursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;
		setTableCursor(cursor);
		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				disposeControlEditor(controlEditor);
				CustomText text = new CustomText(cursor, 0);
				int selectedColNo = cursor.getColumn();
				setSelectedColumn(selectedColNo);
				int selectedRowNo = getSelectionIndices()[0];
				setSelectedDRRow(selectedRowNo);
				List<DRCellAttributes> drCellAttribtes = getSelectedRowDRCells();
				DRCellAttributes drCellAttribute = drCellAttribtes.get(selectedColNo);
				DRColumnAttributes drColumnAttreibute = drCellAttribute.getDrColumnAttribute();
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
						if (cursor.getRow() == null) {
							return;
						}
						cursor.getRow().setText(selectedColNo, text.getText());
						getSelectedDRCEllAttribute().setValue(text.getText());
						getSelectedDRCEllAttribute().setModified(true);
						getParentDataRepositoryView().toggleSaveButton(true);
					}
				});

				if (selectedColNo == 0) {
					getParentDataRepositoryView().toggleMoveColumnLeftButton(false);
					getParentDataRepositoryView().toggleMoveColumnRightButton(true);
				} else if (selectedColNo == getColumnCount() - 1) {
					getParentDataRepositoryView().toggleMoveColumnLeftButton(true);
					getParentDataRepositoryView().toggleMoveColumnRightButton(false);
				} else {
					getParentDataRepositoryView().toggleMoveColumnLeftButton(true);
					getParentDataRepositoryView().toggleMoveColumnRightButton(true);
				}
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

		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				DataRepositoryTableItem selectedDrti = getSelectedDataRepositoryItem();
				DataRepositoryTableItem previousDrti = getPreviousDataRepositoryItem();
				DataRepositoryTableItem nextDrti = getNextDataRepositoryItem();
				if (previousDrti == null) {
					getParentDataRepositoryView().toggleMoveRowDownButton(true);
					getParentDataRepositoryView().toggleMoveRowUpButton(false);
				}
				if (nextDrti == null) {
					getParentDataRepositoryView().toggleMoveRowDownButton(false);
					getParentDataRepositoryView().toggleMoveRowUpButton(true);
				}
				if (previousDrti == null && nextDrti == null) {
					getParentDataRepositoryView().toggleMoveRowDownButton(false);
					getParentDataRepositoryView().toggleMoveRowUpButton(false);
				}
				if (previousDrti != null && nextDrti != null) {
					getParentDataRepositoryView().toggleMoveRowDownButton(true);
					getParentDataRepositoryView().toggleMoveRowUpButton(true);
				}
				if (selectedDrti == null) {
					getParentDataRepositoryView().toggleMoveColumnLeftButton(false);
					getParentDataRepositoryView().toggleMoveColumnRightButton(false);
					getParentDataRepositoryView().toggleMoveRowDownButton(false);
					getParentDataRepositoryView().toggleMoveRowUpButton(false);
					getParentDataRepositoryView().toggleAddColumnButton(false);
					getParentDataRepositoryView().toggleAddRowButton(false);
					getParentDataRepositoryView().toggleRenameColumnButton(false);
				}

				if (selectedDrti != null) {
					getParentDataRepositoryView().toggleDeleteColumnButton(true);
					getParentDataRepositoryView().toggleDeleteRowButton(true);
					getParentDataRepositoryView().toggleAddColumnButton(true);
					getParentDataRepositoryView().toggleAddRowButton(true);
					getParentDataRepositoryView().toggleRenameColumnButton(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		renderAllDRDetails();
	}

	private void disposeControlEditor(ControlEditor editor) {
		if (editor != null) {
			if (editor.getEditor() != null) {
				editor.getEditor().dispose();
			}
		}
	}

	private int getColumnIndex(TableColumn[] columns, TableColumn column) {
		if (columns == null) {
			return -1;
		}
		if (columns.length == 0) {
			return -1;
		}
		for (int i = 0; i < columns.length; i++) {
			if (columns[i] == column) {
				return i;
			}
		}
		return -1;
	}

	private void initializeHeaders(List<DRColumnAttributes> columnAttributes) {
		this.removeAll();
		for (TableColumn column : this.getColumns()) {
			column.dispose();
		}
		for (DRColumnAttributes header : columnAttributes) {
			if (header.isDeleted() == false) {
				TableColumn column = new TableColumn(this, SWT.LEFT | SWT.FULL_SELECTION);
				column.setText(header.getName());
				column.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						TableColumn[] columns = getColumns();
						int selectedIndex = getColumnIndex(columns, column);
						System.out.println(">>Column Index " + selectedIndex);
						selectColumnByCursor(cursor, selectedIndex);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub

					}
				});
			}
		}
		for (int i = 0; i < this.getColumns().length; i++) {
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
			Collections.sort(drCellAttributes);
			rowCount = drCellAttributes.size();
			for (int rowNo = 0; rowNo < drCellAttributes.size(); rowNo++) {
				DRCellAttributes drCellAttribute = drCellAttributes.get(rowNo);

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
				if (drCellAttribute.isDeleted()) {
					dti.dispose();
					continue;
				}
				if (drCellAttribute.getValue() == null) {
					dti.setText(i, "");
				} else {
					dti.setText(i, drCellAttribute.getValue());
				}
			}
		}
	}

	public void renderAllDRDetails() {
		Artifact artifact = getParentDataRepositoryView().getArtifact();
		List<DRColumnAttributes> drDatas = new DataRepositoryApi().getAllDRDatas(artifact.getId());
		renderDRDatas(drDatas);
		selectDefaultRow();
	}

	public void refreshAllDRDetails() {
		List<DRColumnAttributes> drDatas = getDrColumnAttributes();
		renderDRDatas(drDatas);
		selectDefaultRow();
	}

	public void addDRRow() {
		setSelectedRowIndex(getSelectedRowIndex() + 1);
		new DRMaker().addDRRow(getParentDataRepositoryView().getArtifact(), this.getSelectedDRRow(),
				getDrColumnAttributes());
		refreshAllDRDetails();
	}

	public void renameDRColumn() {
		DRColumnAttributes drColumn = getSelectedDRColumnAttribute();
		if (drColumn == null) {
			getParentDataRepositoryView().toggleRenameColumnButton(false);
			return;
		}
		String data = new MessageDialogs().openInputDialogAandGetValue("Enter New ColumnName",
				"Enter New DR Column Name", drColumn.getName());
		if (data == null) {
			return;
		}
		if (data.trim().isEmpty()) {
			return;
		}

		boolean isDRColumnExists = isDRColumnAlreadyExist(data);
		if (isDRColumnExists) {
			new MessageDialogs().openErrorDialog("OpKey",
					String.format("Unable to rename Column name '%s' already exist", data));
			return;
		}
		drColumn.setName(data);
		drColumn.setModified(true);
		refreshAllDRDetails();
		getParentDataRepositoryView().toggleSaveButton(true);
	}

	private boolean isDRColumnAlreadyExist(String columnName) {
		List<DRColumnAttributes> columnAttributes = this.getDrColumnAttributes();
		for (DRColumnAttributes column : columnAttributes) {
			if (column.getName() == null) {
				continue;
			}
			if (column.getName().trim().toLowerCase().equals(columnName.trim().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public void addDRColumn() {
		String data = new MessageDialogs().openInputDialogAandGetValue("Enter ColumnName", "Enter the DR Column Name",
				"Column-" + this.getColumnCount());
		if (data == null) {
			return;
		}
		if (data.trim().isEmpty()) {
			return;
		}
		boolean isColumnNameExist = isDRColumnAlreadyExist(data);
		if (isColumnNameExist) {
			new MessageDialogs().openErrorDialog("OpKey",
					String.format("Column Name '%s' already exist. Please provide different name", data));
			return;
		}
		DRColumnAttributes drColumn = new DRMaker().createDRColumnWithCells(getParentDataRepositoryView().getArtifact(),
				getSelectedDRColumnAttribute(), getItemCount(), getDrColumnAttributes());
		drColumn.setName(data);
		System.out.println("DR Cell Size " + drColumn.getDrCellAttributes().size());
		getDrColumnAttributes().add(drColumn);
		refreshAllDRDetails();
		getParentDataRepositoryView().toggleSaveButton(true);
	}

	public void deleteDRColumn() {
		getSelectedDRColumnAttribute().setDeleted(true);
		List<DRCellAttributes> drCellAttributes = getSelectedDRColumnAttribute().getDrCellAttributes();
		for (DRCellAttributes drCellAttribute : drCellAttributes) {
			drCellAttribute.setDeleted(true);
		}
		this.refreshAllDRDetails();
		getParentDataRepositoryView().toggleSaveButton(true);
	}

	public void deleteDRRow() {
		boolean status = new MessageDialogs().openConfirmDialog("OpKey", "Do you really want to delete this row?");
		if (status == false) {
			return;
		}
		List<DRCellAttributes> drcellattributes = getSelectedRowDRCells();
		for (DRCellAttributes drCellAttribute : drcellattributes) {
			drCellAttribute.setDeleted(true);
		}
		this.refreshAllDRDetails();
		getParentDataRepositoryView().toggleSaveButton(true);
	}

	public void moveRowDown() {
		List<DRCellAttributes> selectedDRCells = getSelectedRowDRCells();
		List<DRCellAttributes> nextDRCells = getNextRowDRCells();

		for (int i = 0; i < selectedDRCells.size(); i++) {
			DRCellAttributes selectedDRCell = selectedDRCells.get(i);
			DRCellAttributes nextDRCell = nextDRCells.get(i);
			int selectedDRCellPosition = selectedDRCell.getPosition();
			int nextDRCellPosition = nextDRCell.getPosition();

			selectedDRCell.setPosition(nextDRCellPosition);
			nextDRCell.setPosition(selectedDRCellPosition);

			selectedDRCell.setModified(true);
			nextDRCell.setModified(true);
			this.setSelectedRowIndex(this.getSelectionIndex() + 1);
		}

		refreshAllDRDetails();
		getParentDataRepositoryView().toggleSaveButton(true);
	}

	public void moveRowUp() {
		List<DRCellAttributes> selectedDRCells = getSelectedRowDRCells();
		List<DRCellAttributes> previousDRCells = getPreviousRowDRCells();

		for (int i = 0; i < selectedDRCells.size(); i++) {
			DRCellAttributes selectedDRCell = selectedDRCells.get(i);
			DRCellAttributes previousDRCell = previousDRCells.get(i);
			int selectedDRCellPosition = selectedDRCell.getPosition();
			int nextDRCellPosition = previousDRCell.getPosition();

			selectedDRCell.setPosition(nextDRCellPosition);
			previousDRCell.setPosition(selectedDRCellPosition);

			selectedDRCell.setModified(true);
			previousDRCell.setModified(true);
			this.setSelectedRowIndex(this.getSelectionIndex() - 1);
		}
		refreshAllDRDetails();
		getParentDataRepositoryView().toggleSaveButton(true);
	}

	public void moveColumnRight() {
		setSelectedColumn(getSelectedColumn() + 1);
		DRColumnAttributes selectedDRColumn = getSelectedDRColumnAttribute();
		DRColumnAttributes nextDRColumn = getNextDRColumnAttribute();

		int selectedDRColumnPosition = selectedDRColumn.getPosition();
		int nextDRColumnPosition = nextDRColumn.getPosition();

		selectedDRColumn.setPosition(nextDRColumnPosition);
		nextDRColumn.setPosition(selectedDRColumnPosition);

		selectedDRColumn.setModified(true);
		nextDRColumn.setModified(true);
		refreshAllDRDetails();
		getParentDataRepositoryView().toggleSaveButton(true);
	}

	public void moveColumnLeft() {

		setSelectedColumn(getSelectedColumn() - 1);
		DRColumnAttributes selectedDRColumn = getSelectedDRColumnAttribute();
		DRColumnAttributes previousDRColumn = getPreviousDRColumnAttribute();

		int selectedDRColumnPosition = selectedDRColumn.getPosition();
		int previousDRColumnPosition = previousDRColumn.getPosition();

		selectedDRColumn.setPosition(previousDRColumnPosition);
		previousDRColumn.setPosition(selectedDRColumnPosition);

		selectedDRColumn.setModified(true);
		previousDRColumn.setModified(true);
		refreshAllDRDetails();
		getParentDataRepositoryView().toggleSaveButton(true);
	}

	public List<DRCellAttributes> getSelectedRowDRCells() {
		DataRepositoryTableItem tableItem = getSelectedDataRepositoryItem();
		if (tableItem == null) {
			return null;
		}
		if (tableItem.getDrCellAttributes() == null) {
			return null;
		}
		return tableItem.getDrCellAttributes();
	}

	public List<DRCellAttributes> getPreviousRowDRCells() {
		DataRepositoryTableItem tableItem = getPreviousDataRepositoryItem();
		if (tableItem == null) {
			return null;
		}
		if (tableItem.getDrCellAttributes() == null) {
			return null;
		}
		return tableItem.getDrCellAttributes();
	}

	public List<DRCellAttributes> getNextRowDRCells() {
		DataRepositoryTableItem tableItem = getNextDataRepositoryItem();
		if (tableItem == null) {
			return null;
		}
		if (tableItem.getDrCellAttributes() == null) {
			return null;
		}
		return tableItem.getDrCellAttributes();
	}

	public DataRepositoryTableItem getSelectedDataRepositoryItem() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		DataRepositoryTableItem tableItem = (DataRepositoryTableItem) this.getSelection()[0];
		return tableItem;
	}

	public DataRepositoryTableItem getPreviousDataRepositoryItem() {
		int selectedIndex = this.getSelectionIndex();
		if (selectedIndex == -1) {
			return null;
		}
		if (selectedIndex == 0) {
			return null;
		}
		return (DataRepositoryTableItem) this.getItem(selectedIndex - 1);
	}

	public DataRepositoryTableItem getNextDataRepositoryItem() {
		int selectedIndex = this.getSelectionIndex();
		if (selectedIndex == -1) {
			return null;
		}
		if (selectedIndex == this.getItemCount() - 1) {
			return null;
		}
		return (DataRepositoryTableItem) this.getItem(selectedIndex + 1);
	}

	public DRColumnAttributes getPreviousDRColumnAttribute() {
		DRColumnAttributes selectedDRColumnAttribute = getSelectedDRColumnAttribute();
		if (selectedDRColumnAttribute == null) {
			return null;
		}
		List<DRColumnAttributes> filteredDRColumnAttributes = new ArrayList<DRColumnAttributes>();
		for (DRColumnAttributes drColumnAttribute : getDrColumnAttributes()) {
			if (drColumnAttribute.isDeleted() == false) {
				filteredDRColumnAttributes.add(drColumnAttribute);
			}
		}
		int selectedIndex = filteredDRColumnAttributes.indexOf(selectedDRColumnAttribute);
		if (selectedIndex == 0) {
			return null;
		}
		return filteredDRColumnAttributes.get(selectedIndex - 1);
	}

	public DRColumnAttributes getNextDRColumnAttribute() {
		DRColumnAttributes selectedDRColumnAttribute = getSelectedDRColumnAttribute();
		if (selectedDRColumnAttribute == null) {
			return null;
		}
		List<DRColumnAttributes> filteredDRColumnAttributes = new ArrayList<DRColumnAttributes>();
		for (DRColumnAttributes drColumnAttribute : getDrColumnAttributes()) {
			if (drColumnAttribute.isDeleted() == false) {
				filteredDRColumnAttributes.add(drColumnAttribute);
			}
		}
		int selectedIndex = filteredDRColumnAttributes.indexOf(selectedDRColumnAttribute);
		if (selectedIndex == filteredDRColumnAttributes.size() - 1) {
			return null;
		}
		return filteredDRColumnAttributes.get(selectedIndex + 1);
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

	public int getSelectedDRRow() {
		return selectedDRRow;
	}

	public void setSelectedDRRow(int selectedDRRow) {
		this.selectedDRRow = selectedDRRow;
	}

}
