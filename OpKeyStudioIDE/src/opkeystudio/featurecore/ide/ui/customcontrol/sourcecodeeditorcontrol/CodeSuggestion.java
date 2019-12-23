package opkeystudio.featurecore.ide.ui.customcontrol.sourcecodeeditorcontrol;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

public class CodeSuggestion extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CodeSuggestion(Composite parent, int style) {
		super(parent, style);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(231, 93, 75, 25);
		btnNewButton.setText("New Button");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
