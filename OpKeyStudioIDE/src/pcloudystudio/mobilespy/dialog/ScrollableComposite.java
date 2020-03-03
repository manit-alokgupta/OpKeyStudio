package pcloudystudio.mobilespy.dialog;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.custom.ScrolledComposite;

public class ScrollableComposite extends ScrolledComposite implements MouseWheelListener {

	public ScrollableComposite(final Composite parent, final int style) {
		super(parent, style);
	}

	public void setContent(final Control content) {
		this.removeMouseScrollListenerForCurrentContent();
		super.setContent(content);
	}

	private void removeMouseScrollListenerForCurrentContent() {
		final Control oldContent = this.getContent();
		if (oldContent == null || oldContent.isDisposed()) {
			return;
		}
		oldContent.removeMouseWheelListener((MouseWheelListener) this);
	}

	public void mouseScrolled(final MouseEvent event) {
		int wheelCount;
		for (wheelCount = event.count, wheelCount = (int) Math.ceil(wheelCount / 3.0f); wheelCount < 0; ++wheelCount) {
			this.getVerticalBar().setIncrement(4);
		}
		while (wheelCount > 0) {
			this.getVerticalBar().setIncrement(-4);
			--wheelCount;
		}
	}

	public void dispose() {
		this.removeMouseScrollListenerForCurrentContent();
		super.dispose();
	}
}
