package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.custom.StackLayout;

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
		composite.setLayout(null);
		
		CBanner banner = new CBanner(composite, SWT.NONE);
		banner.setBounds(0, 0, 450, 300);
		
		Composite leftComponent = new Composite(banner, SWT.NONE);
		leftComponent.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		banner.setLeft(leftComponent);
		leftComponent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite leftComponentHolder = new Composite(leftComponent, SWT.NONE);
		
		Composite rightComponent = new Composite(banner, SWT.NONE);
		rightComponent.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		banner.setRight(rightComponent);
		rightComponent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite rightComponentHolder = new Composite(rightComponent, SWT.NONE);
		
		banner.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				CBanner banner=(CBanner)e.getSource();
				banner.getLeft().setSize(banner.getBounds().width/2,banner.getBounds().height);
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
