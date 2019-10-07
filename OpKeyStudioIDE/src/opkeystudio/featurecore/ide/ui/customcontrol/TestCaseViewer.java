package opkeystudio.featurecore.ide.ui.customcontrol;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import opkeystudio.opkeystudiocore.core.models.model.ModelGroup;
import opkeystudio.opkeystudiocore.core.models.testcasemodel.InputValue;
import opkeystudio.opkeystudiocore.core.models.testcasemodel.KeyWord;
import opkeystudio.opkeystudiocore.core.models.testcasemodel.ORObject;
import opkeystudio.opkeystudiocore.core.models.testcasemodel.OutputValue;
import opkeystudio.opkeystudiocore.core.models.testcasemodel.TestCaseGroup;
import opkeystudio.opkeystudiocore.core.models.testcasemodel.TestCaseStep;

public class TestCaseViewer extends Table {
	String[] tableHeaders = { "Keyword", "Object", "Input Value", "Output Value" };
	private ModelGroup modelGroup;

	public TestCaseViewer(Composite parent) {
		super(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		init((SashForm) parent);
		TestCaseGroup tcGroup = new TestCaseGroup();
		KeyWord keyword = new KeyWord("Click", "Method_Click");
		ORObject object = new ORObject();
		InputValue inv = new InputValue();
		OutputValue outp = new OutputValue();

		TestCaseStep tcstep = new TestCaseStep(keyword, object, inv, outp);
		tcGroup.addTestCaseStep(tcstep);
		TestCaseStep tcstep_1 = new TestCaseStep(keyword, object, inv, outp);
		tcGroup.addTestCaseStep(tcstep_1);
		setModelGroup(tcGroup);
		renderModel();
	}

	private void init(SashForm sashform) {
		setLinesVisible(true);
		setHeaderVisible(true);
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 4);
				}
			}
		});

		addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Table table = (Table) e.getSource();
				table.getSelectionIndex();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, 0);
			column.setText(header);
		}

		pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			getColumn(i).pack();
		}
		sashform.setWeights(new int[] { 1, 10 });
		setSize(computeSize(SWT.DEFAULT, 200));

		Menu menu = new Menu(this);
		setMenu(menu);

		MenuItem mntmEdit = new MenuItem(menu, SWT.NONE);
		mntmEdit.setText("Edit");

		MenuItem mntmDelete = new MenuItem(menu, SWT.NONE);
		mntmDelete.setText("Delete");
	}

	public void renderModel() {
		TestCaseGroup tcgroup = (TestCaseGroup) getModelGroup();
		for (TestCaseStep tcstep : tcgroup.getAllTestCaseSteps()) {
			TableItem ti = new TableItem(this, 0);
			ti.setText(new String[] { tcstep.getKeyword().getKeywordName(), "deded", "deded", "dededed" });
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ModelGroup getModelGroup() {
		return modelGroup;
	}

	public void setModelGroup(ModelGroup modelGroup) {
		this.modelGroup = modelGroup;
	}
}
