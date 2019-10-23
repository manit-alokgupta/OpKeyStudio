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



	public void clearData() {
		this.removeAll();
	}


	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
