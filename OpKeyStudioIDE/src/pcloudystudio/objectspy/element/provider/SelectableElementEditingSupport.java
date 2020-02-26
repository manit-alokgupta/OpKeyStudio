package pcloudystudio.objectspy.element.provider;

import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.swt.widgets.Composite;

import pcloudystudio.objectspy.element.impl.CapturedMobileElement;
import pcloudystudio.objectspy.element.impl.support.TypeCheckedEditingSupport;
import pcloudystudio.objectspy.viewer.CapturedObjectTableViewer;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;

public class SelectableElementEditingSupport extends TypeCheckedEditingSupport<CapturedMobileElement>
{
    public SelectableElementEditingSupport(final ColumnViewer viewer) {
        super(viewer);
    }
    
    protected Class<CapturedMobileElement> getElementType() {
        return CapturedMobileElement.class;
    }
    
    protected CellEditor getCellEditorByElement(final CapturedMobileElement element) {
        return (CellEditor)new CheckboxCellEditor((Composite)this.getViewer().getControl());
    }
    
    protected boolean canEditElement(final CapturedMobileElement element) {
        return true;
    }
    
    protected Object getElementValue(final CapturedMobileElement element) {
        return element.isChecked();
    }
    
    protected void setElementValue(final CapturedMobileElement element, final Object value) {
        if (!(value instanceof Boolean)) {
            return;
        }
        final boolean newSelection = (boolean)value;
        if (element.isChecked() != newSelection) {
            element.setChecked(newSelection);
            this.getViewer().refresh((Object)element);
            this.getViewer().notifyStateChanged();
        }
    }
    
    public CapturedObjectTableViewer getViewer() {
        return (CapturedObjectTableViewer)super.getViewer();
    }
}
