package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FormData;
import org.eclipse.wb.swt.SWTResourceManager;

public class ObjectRepositoryView2 extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ObjectRepositoryView2(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		CBanner banner = new CBanner(composite, SWT.NONE);

		Composite leftComponent = new Composite(banner, SWT.NONE);
		banner.setLeft(leftComponent);

		Composite leftComponentHolder = new Composite(leftComponent, SWT.NONE);
		leftComponentHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		leftComponentHolder.setBounds(0, 0, 367, 295);

		Composite rightComponent = new Composite(banner, SWT.NONE);
		banner.setRight(rightComponent);

		banner.setRightWidth(200);

		Composite rightComponentHolder = new Composite(rightComponent, SWT.NONE);
		rightComponentHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		rightComponentHolder.setBounds(0, 0, 200, 295);
		banner.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Control leftComponent = (Control) e.getSource();
				System.out.println(leftComponent.getBounds().width);
				System.out.println(leftComponent.getBounds().height);
				leftComponentHolder.setSize(leftComponent.getBounds().width, banner.getBounds().height);
				banner.redraw();
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
