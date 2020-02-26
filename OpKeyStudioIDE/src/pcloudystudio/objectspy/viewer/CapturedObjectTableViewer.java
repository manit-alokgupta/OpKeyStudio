package pcloudystudio.objectspy.viewer;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import java.util.ArrayList;

import pcloudystudio.objectspy.dialog.MobileElementDialog;
import pcloudystudio.objectspy.element.impl.CapturedMobileElement;
import pcloudystudio.objectspy.element.impl.control.CTableViewer;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import java.util.List;

public class CapturedObjectTableViewer extends CTableViewer {
	private List<CapturedMobileElement> capturedElements;
	private MobileElementDialog dialog;

	public CapturedObjectTableViewer(final Composite parent, final int style, final MobileElementDialog dialog) {
		super(parent, style);
		this.dialog = dialog;
	}

	public List<CapturedMobileElement> getCapturedElements() {
		return this.capturedElements;
	}

	public void setCapturedElements(final List<CapturedMobileElement> capturedElements) {
		this.setInput((Object) (this.capturedElements = capturedElements));
	}

	public CapturedMobileElement getSelectedElement() {
		final IStructuredSelection selection = this.getStructuredSelection();
		if (selection.isEmpty()) {
			return null;
		}
		return (CapturedMobileElement) selection.getFirstElement();
	}

	public IStructuredSelection getStructuredSelection() {
		final IStructuredSelection selection = (IStructuredSelection) this.getSelection();
		return selection;
	}

	public CapturedMobileElement[] getSelectedElements() {
		final IStructuredSelection selection = this.getStructuredSelection();
		if (selection.isEmpty()) {
			return new CapturedMobileElement[0];
		}
		final Object[] rawElements = selection.toArray();
		final CapturedMobileElement[] capturedElements = new CapturedMobileElement[rawElements.length];
		for (int i = 0; i < rawElements.length; ++i) {
			capturedElements[i] = (CapturedMobileElement) rawElements[i];
		}
		return capturedElements;
	}

	public void addMobileElements(final List<CapturedMobileElement> mobileElements) {
		final List<CapturedMobileElement> added = new ArrayList<CapturedMobileElement>();
		for (final CapturedMobileElement eachElement : mobileElements) {
			final int elementIdx = this.capturedElements.indexOf(eachElement);
			if (elementIdx < 0) {
				this.capturedElements.add(eachElement);
				added.add(eachElement);
			} else {
				added.add(this.capturedElements.get(elementIdx));
			}
		}
		this.refresh();
		this.setSelection((ISelection) new StructuredSelection((List) added));
		this.showLastItem();
		this.notifyStateChanged();
	}

	public void removeCapturedElements(final List<CapturedMobileElement> elements) {
		this.capturedElements.removeAll(elements);
		this.refresh();
		this.notifyStateChanged();
	}

	public void removeCapturedElement(final CapturedMobileElement element) {
		this.removeCapturedElement(element, true);
	}

	public void removeCapturedElement(final CapturedMobileElement element, final boolean needRefresh) {
		if (!this.contains(element)) {
			return;
		}
		this.capturedElements.remove(element);
		if (needRefresh) {
			this.refresh();
		}
		this.notifyStateChanged();
	}

	public void checkAllElements(final boolean checked) {
		if (this.capturedElements.isEmpty()) {
			return;
		}
		for (final CapturedMobileElement mobileElement : this.capturedElements) {
			mobileElement.setChecked(checked);
		}
		this.refresh();
		this.notifyStateChanged();
	}

	public boolean contains(final CapturedMobileElement element) {
		return element != null && this.capturedElements.contains(element);
	}

	public boolean isAllElementChecked() {
		for (final CapturedMobileElement mobileElement : this.capturedElements) {
			if (!mobileElement.isChecked()) {
				return false;
			}
		}
		return true;
	}

	public boolean isAnyElementChecked() {
		for (final CapturedMobileElement mobileElement : this.capturedElements) {
			if (mobileElement.isChecked()) {
				return true;
			}
		}
		return false;
	}

	public List<CapturedMobileElement> getAllCheckedElements() {
		final List<CapturedMobileElement> checkedElements = new ArrayList<CapturedMobileElement>();
		for (final CapturedMobileElement mobileElement : this.capturedElements) {
			if (mobileElement.isChecked()) {
				checkedElements.add(mobileElement);
			}
		}
		return checkedElements;
	}

	public void notifyStateChanged() {
		this.dialog.updateCapturedElementSelectingColumnHeader();
	}
}
