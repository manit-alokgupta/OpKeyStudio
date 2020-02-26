package pcloudystudio.objectspy.element.impl.support;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

public abstract class TypeCheckedEditingSupport<T> extends EditingSupport {
	protected int columnIndex;

	public TypeCheckedEditingSupport(final ColumnViewer viewer) {
		super(viewer);
	}

	protected abstract Class<T> getElementType();

	private boolean isElementInstanceOf(final Object element) {
		final Class<?> clazz = this.getElementType();
		return clazz != null && clazz.isInstance(element);
	}

	protected CellEditor getCellEditor(final Object element) {
		if (this.isElementInstanceOf(element)) {
			return this.getCellEditorByElement((T) element);
		}
		return this.defaultCellEditorIfNotInstanceOf();
	}

	protected CellEditor defaultCellEditorIfNotInstanceOf() {
		return null;
	}

	protected abstract CellEditor getCellEditorByElement(final T p0);

	protected boolean canEdit(final Object element) {
		if (this.isElementInstanceOf(element)) {
			return this.canEditElement((T) element);
		}
		return this.defaultCanEditValueIfNotInstanceOf();
	}

	protected boolean defaultCanEditValueIfNotInstanceOf() {
		return false;
	}

	protected abstract boolean canEditElement(final T p0);

	protected Object getValue(final Object element) {
		if (this.isElementInstanceOf(element)) {
			return this.getElementValue((T) element);
		}
		return this.defaultDisplayValueIfNotInstanceOf();
	}

	protected abstract Object getElementValue(final T p0);

	protected Object defaultDisplayValueIfNotInstanceOf() {
		return null;
	}

	protected void setValue(final Object element, final Object value) {
		if (this.isElementInstanceOf(element)) {
			this.setElementValue((T) element, value);
		}
	}

	protected abstract void setElementValue(final T p0, final Object p1);

	protected void initializeCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
		this.columnIndex = cell.getColumnIndex();
		super.initializeCellEditorValue(cellEditor, cell);
	}

	protected void saveCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
		this.columnIndex = cell.getColumnIndex();
		super.saveCellEditorValue(cellEditor, cell);
	}

	protected Composite getComposite() {
		return (Composite) this.getViewer().getControl();
	}
}
