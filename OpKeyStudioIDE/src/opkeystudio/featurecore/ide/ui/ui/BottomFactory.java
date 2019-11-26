package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class BottomFactory extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BottomFactory(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite.setLayout(new GridLayout(6, false));
		
		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setText("1");
		
		ExpandBar expandBar_1 = new ExpandBar(composite, SWT.NONE);
		expandBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		ExpandItem xpndtmNewExpanditem_1 = new ExpandItem(expandBar_1, SWT.NONE);
		xpndtmNewExpanditem_1.setText("2");
		
		ExpandBar expandBar_2 = new ExpandBar(composite, SWT.NONE);
		expandBar_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		ExpandItem xpndtmNewExpanditem_2 = new ExpandItem(expandBar_2, SWT.NONE);
		xpndtmNewExpanditem_2.setText("3");
		
		ExpandBar expandBar_3 = new ExpandBar(composite, SWT.NONE);
		expandBar_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		ExpandItem xpndtmNewExpanditem_3 = new ExpandItem(expandBar_3, SWT.NONE);
		xpndtmNewExpanditem_3.setText("4");
		
		ExpandBar expandBar_4 = new ExpandBar(composite, SWT.NONE);
		expandBar_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		ExpandItem xpndtmNewExpanditem_4 = new ExpandItem(expandBar_4, SWT.NONE);
		xpndtmNewExpanditem_4.setText("5");
		
		ExpandBar expandBar_5 = new ExpandBar(composite, SWT.NONE);
		expandBar_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		ExpandItem xpndtmNewExpanditem_5 = new ExpandItem(expandBar_5, SWT.NONE);
		xpndtmNewExpanditem_5.setText("6");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
