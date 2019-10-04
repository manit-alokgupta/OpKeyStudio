package opkeystudio.featurecore.ide.ui.customcontrol;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TestCaseViewer extends Table {
	String[] tableHeaders = { "Keyword", "Object", "Input Value", "Output Value" };

	public TestCaseViewer(Composite parent) {
		super(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		init((SashForm) parent);
		addRow();
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

	public void addRow() {
		for (int i = 0; i <100; i++) {
			TableItem ti = new TableItem(this, 0);
			ti.setText(new String[] {String.valueOf(i),"deded","deded","dededed"});
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
