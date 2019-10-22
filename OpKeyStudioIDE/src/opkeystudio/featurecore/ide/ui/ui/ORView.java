package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class ORView extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_4;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ORView(Composite parent, int style) {
		super(parent, style);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(238, 84, 76, 21);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(238, 136, 76, 21);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(10, 84, 75, 25);
		btnNewButton.setText("New Button");
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(10, 136, 76, 21);
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setBounds(125, 132, 75, 25);
		btnNewButton_1.setText("New Button");
		
		text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(124, 193, 76, 21);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
