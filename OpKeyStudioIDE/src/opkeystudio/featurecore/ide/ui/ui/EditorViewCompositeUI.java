package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;

import opkeystudio.featurecore.ide.ui.customcontrol.TestCaseViewer;
import org.eclipse.wb.swt.ResourceManager;

public class EditorViewCompositeUI extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EditorViewCompositeUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(152, 81, 64, 64);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER);
		tabFolder.setTabPosition(SWT.BOTTOM);
		tabFolder.setSelectionBackground(
				Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		CTabItem tbtmNewItem = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase.gif"));
		tbtmNewItem.setText("TestCase");
		
		tabFolder.setSelection(tbtmNewItem);
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite_1, SWT.VERTICAL);
		ToolBar toolBar = new ToolBar(sashForm, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		new TestCaseViewer(sashForm);
		CTabItem tbtmNewItem_1 = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Source Code");

		TextViewer textViewer = new TextViewer(tabFolder, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();
		tbtmNewItem_1.setControl(styledText);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
