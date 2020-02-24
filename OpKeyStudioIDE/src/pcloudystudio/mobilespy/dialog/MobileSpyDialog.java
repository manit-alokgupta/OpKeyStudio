package pcloudystudio.mobilespy.dialog;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import pcloudystudio.objectspy.dialog.MobileInspectorController;
import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.tree.MobileElementLabelProvider;
import pcloudystudio.objectspy.element.tree.MobileElementTreeContentProvider;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;

public class MobileSpyDialog extends Dialog {

	protected Object result;
	protected Shell shlSpyMobile;
	private static Table table;
	private MobileInspectorController inspectorController;
	private CheckboxTreeViewer allObjectsCheckboxTreeViewer;
	public TreeMobileElement appRootElement;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public MobileSpyDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		this.inspectorController = new MobileInspectorController();
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlSpyMobile.open();
		shlSpyMobile.layout();
		Display display = getParent().getDisplay();
		while (!shlSpyMobile.isDisposed()) {
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
		shlSpyMobile = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlSpyMobile.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/mobile_spy.png"));
		shlSpyMobile.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlSpyMobile.setSize(1500, 1000);
		shlSpyMobile.setText("Mobile Object Spy");

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlSpyMobile.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlSpyMobile.setLocation(new Point(locationX, locationY));
		shlSpyMobile.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(shlSpyMobile, SWT.NONE);

		ScrolledComposite mobileSnapshotScrolledComposite = new ScrolledComposite(sashForm,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		mobileSnapshotScrolledComposite.setExpandHorizontal(true);
		mobileSnapshotScrolledComposite.setExpandVertical(true);

		ScrolledComposite allObjectsTreeScrolledComposite = new ScrolledComposite(sashForm,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		allObjectsTreeScrolledComposite.setExpandHorizontal(true);
		allObjectsTreeScrolledComposite.setExpandVertical(true);

		MobileElementTreeContentProvider contentProvider = new MobileElementTreeContentProvider();
		MobileElementLabelProvider labelProvider = new MobileElementLabelProvider();

		setTreeRoot(MobileSpyDialog.this, this.inspectorController.getMobileObjectRoot());
		this.allObjectsCheckboxTreeViewer = new CheckboxTreeViewer(allObjectsTreeScrolledComposite, SWT.BORDER);
		Tree tree = this.allObjectsCheckboxTreeViewer.getTree();
		this.allObjectsCheckboxTreeViewer.setContentProvider(contentProvider);
		this.allObjectsCheckboxTreeViewer.setLabelProvider(labelProvider);
		this.allObjectsCheckboxTreeViewer.setInput((Object) new Object[] { this.appRootElement });
		this.allObjectsCheckboxTreeViewer.refresh();
		this.allObjectsCheckboxTreeViewer.expandAll();

		allObjectsTreeScrolledComposite.setContent(tree);
		allObjectsTreeScrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		ScrolledComposite objectPropertiesScrolledComposite = new ScrolledComposite(sashForm,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		objectPropertiesScrolledComposite.setExpandHorizontal(true);
		objectPropertiesScrolledComposite.setExpandVertical(true);

		table = new Table(objectPropertiesScrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");

		TableColumn tblclmnValue = new TableColumn(table, SWT.NONE);
		tblclmnValue.setWidth(100);
		tblclmnValue.setText("Value");
		objectPropertiesScrolledComposite.setContent(table);
		objectPropertiesScrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		sashForm.setWeights(new int[] { 2, 3, 2 });
	}

	static void setTreeRoot(final MobileSpyDialog mobileSpyDialog, final TreeMobileElement appRootElement) {
		mobileSpyDialog.appRootElement = appRootElement;
	}
}
