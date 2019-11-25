package opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;

public class SuiteStepTable extends CustomTable {

	private SuiteStepTable thisTable = this;
	private TestSuiteView parentTestSuiteView;
	private List<Control> allTableEditors = new ArrayList<Control>();

	public SuiteStepTable(Composite parent, int style) {
		super(parent, style);
	}

	public void init() {

		String[] tableHeaders = { "#", " ", "Test Case/Gherkin", "Local Data Repository", "Data Repository Tree" };

		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, SWT.LEFT);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Table table_0 = (Table) e.getSource();
				for (int i = 0; i < table_0.getColumnCount(); i++) {
					TableColumn column = table_0.getColumn(i);
					if (i == 0) {
						column.setWidth(50);
					} else {
						column.setWidth((table_0.getBounds().width - 50) / 4);
					}
				}

			}
		});
	}

	public void setTestSuiteData(List<TestSuite> testSuite) {
		super.setControlData(testSuite);
	}

	public List<TestSuite> getTestSuiteData() {
		return (List<TestSuite>) super.getControlData();
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(SuiteStepTableItem item) {
		TestSuite attrProperty = item.getTestSuiteData();
		TableEditor editor1 = getTableEditor();
		CustomButton button = new CustomButton(this, SWT.CHECK);
		button.setSelection(attrProperty.isShouldrun());
		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				thisTable.deselectAll();
				thisTable.setSelection(new TableItem[] { item });
				thisTable.notifyListeners(SWT.Selection, null);

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		editor1.setEditor(button, item, 0);
		this.allTableEditors.add(editor1.getEditor());
	}

	private void disposeAllTableEditors() {
		for (Control controlEditor : this.allTableEditors) {
			controlEditor.dispose();
		}
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void moveStepUp(TestSuite suiteStep1, TestSuite suiteStep2) {
		int selectedIndex = this.getSelectionIndex();
		int spos1 = suiteStep1.getPosition();
		int spos2 = suiteStep2.getPosition();

		suiteStep1.setPosition(spos2);
		suiteStep2.setPosition(spos1);

		selectRow(selectedIndex - 1);
		suiteStep1.setModified(true);
		suiteStep2.setModified(true);
	}

	public void moveStepDown(TestSuite suiteStep1, TestSuite suiteStep2) {

	}

	public void deleteSuiteStep(TestSuite suiteStep) {
		suiteStep.setDeleted(true);
	}

}
