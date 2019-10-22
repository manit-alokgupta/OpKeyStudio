package opkeystudio.featurecore.ide.ui.customcontrol;

import java.io.IOException;

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

import opkeystudio.core.utils.Utilities;
import opkeystudio.opkeystudiocore.core.models.partObject.WorkBenchPartObject;
import opkeystudio.opkeystudiocore.core.models.testcasemodel.TestCaseModelGroup;
import opkeystudio.opkeystudiocore.core.models.testcasemodel.TestCaseStep;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class TestCaseViewer extends Table {
	String[] tableHeaders = { "Keyword", "Object", "Input Value", "Output Value" };

	public TestCaseViewer(Composite parent) {
		super(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		init((SashForm) parent);
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
				ServiceRepository.getInstance().setTestCaseSelectedSteps(table.getSelectionIndex());
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

		//renderModel();
	}

	public void renderModel() {
		WorkBenchPartObject wbpo = Utilities.getInstance().getActivePartWorkBenchObject();
		TestCaseModelGroup tcgroup = (TestCaseModelGroup) wbpo.getArtificate().getModelGroup();
		for (TestCaseStep tcstep : tcgroup.getAllTestCaseSteps()) {
				TableItem ti = new TableItem(this, 0);
				ti.setData(tcstep);
				ti.setText(new String[] { tcstep.getKeyword().getKeywordName(), "", "", "" });
		}
		this.select(0);
	}

	public void clearData() {
		this.removeAll();
	}

	public void deleteSelectedStep() throws CloneNotSupportedException, IOException {
		int selectedIndex = ServiceRepository.getInstance().getTestCaseSelectedSteps();
		if (selectedIndex > -1) {
			WorkBenchPartObject wbpo = Utilities.getInstance().getActivePartWorkBenchObject();
			TestCaseModelGroup tcgroup = (TestCaseModelGroup) wbpo.getArtificate().getModelGroup();
			TestCaseModelGroup clonedtcgroup=(TestCaseModelGroup) opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().cloneObject(tcgroup, TestCaseModelGroup.class);
			clonedtcgroup.getAllTestCaseSteps().remove(selectedIndex);
			clearData();
			renderModel();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
