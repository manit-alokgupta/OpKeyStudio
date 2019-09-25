package opkeystudio.parts.compositeui;

import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class EditorViewCompositeUI extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EditorViewCompositeUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(152, 81, 64, 64);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TextViewer textViewer = new TextViewer(composite, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
