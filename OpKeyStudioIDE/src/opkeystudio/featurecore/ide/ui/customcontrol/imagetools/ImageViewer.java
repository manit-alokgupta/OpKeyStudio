package opkeystudio.featurecore.ide.ui.customcontrol.imagetools;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;

public class ImageViewer extends Dialog {

	protected Object result;
	protected Shell shell;
	private Label lblNewLabel;

	private Image image;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ImageViewer(Shell parent, Image image, int style) {
		super(parent, style);
		setText("Image Viewer");
		setImage(image);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */

	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shell.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shell.setLocation(new Point(locationX, locationY));
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(new Color(lblNewLabel.getDisplay(), 0, 0, 0));
		lblNewLabel.setImage(getImage());
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
