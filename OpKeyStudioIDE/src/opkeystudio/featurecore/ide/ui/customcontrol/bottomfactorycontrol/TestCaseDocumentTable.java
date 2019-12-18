package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryTestCaseUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryTestSuiteUi;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;

public class TestCaseDocumentTable extends CustomTable {

	private boolean paintCalled = false;
	private TestCaseDocumentTable thisTable = this;
	private BottomFactoryTestCaseUi parentBottomFactoryUI;
	private BottomFactoryTestSuiteUi parentBottomFactoryTestSuiteUi;

	public TestCaseDocumentTable(Composite parent, int style, BottomFactoryTestCaseUi bottomFactoryUI) {
		super(parent, style);
		init();
		thisTable = this;
		this.setParentBottomFactoryUI(bottomFactoryUI);
	}

	public TestCaseDocumentTable(Composite parent, int style, BottomFactoryTestSuiteUi parentView) {
		super(parent, style);
		init();
		thisTable = this;
		this.setParentBottomFactoryTestSuiteUi(parentView);
	}

	private void init() {
		String[] tableHeaders = { "Created By", "Created On", "Status" };
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, 0);
			column.setText(header);
			column.setWidth(100);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				if (paintCalled) {
					// return;
				}
				Table table_0 = (Table) e.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 3);
				}
				paintCalled = true;

			}
		});

		TableCursor cursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(cursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;

		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedColumn = cursor.getColumn();
				CustomText text = new CustomText(cursor, 0);
				System.out.println("Column number:-" + cursor.getColumn());
				System.out.println("Row number:-" + cursor.getRow());

				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						text.dispose();

					}

					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub

					}
				});

				text.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						cursor.getRow().setText(selectedColumn, text.getText());

					}
				});

				controlEditor.setEditor(text);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public BottomFactoryTestCaseUi getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(BottomFactoryTestCaseUi parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

	public BottomFactoryTestSuiteUi getParentBottomFactoryTestSuiteUi() {
		return parentBottomFactoryTestSuiteUi;
	}

	public void setParentBottomFactoryTestSuiteUi(BottomFactoryTestSuiteUi parentBottomFactoryTestSuiteUi) {
		this.parentBottomFactoryTestSuiteUi = parentBottomFactoryTestSuiteUi;
	}
}
