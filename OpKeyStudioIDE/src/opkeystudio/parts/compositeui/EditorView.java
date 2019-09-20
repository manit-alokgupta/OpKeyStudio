package opkeystudio.parts.compositeui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;

public class EditorView extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EditorView(Composite parent, int style) {
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
