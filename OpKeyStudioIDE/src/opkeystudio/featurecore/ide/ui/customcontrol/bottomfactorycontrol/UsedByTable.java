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

import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryDataRepoUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryORUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryTestCaseUi;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;

public class UsedByTable extends CustomTable {

	private boolean paintCalled = false;
	private BottomFactoryTestCaseUi parentBottomFactoryTestCaseUi;
	private BottomFactoryORUi parentBottomFactoryORUi;
	private BottomFactoryDataRepoUi parentBottomFactoryDataRepoUi;
	private BottomFactoryFLUi parentBottomFactoryFLUi;

	public UsedByTable(Composite parent, int style, BottomFactoryTestCaseUi bottomFactoryUi) {
		super(parent, style);
		init();
		this.setParentBottomFactoryTestCaseUi(bottomFactoryUi);
	}

	public UsedByTable(Composite parent, int style, BottomFactoryORUi bottomFactoryUi) {
		super(parent, style);
		init();
		this.setParentBottomFactoryORUi(bottomFactoryUi);
	}

	public UsedByTable(Composite parent, int style, BottomFactoryDataRepoUi bottomFactoryUi) {
		super(parent, style);
		init();
		this.setParentBottomFactoryDataRepoUi(bottomFactoryUi);
	}

	public UsedByTable(Composite parent, int style, BottomFactoryFLUi parentView) {
		super(parent, style);
		init();
		this.setParentBottomFactoryFLUi(parentView);
	}

	private void init() {
		String[] tableHeaders = { "Name", "Full Path", "Description", "Component Type" };
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
					column.setWidth(table_0.getBounds().width / 4);
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

	public BottomFactoryTestCaseUi getParentBottomFactoryTestCaseUi() {
		return parentBottomFactoryTestCaseUi;
	}

	public void setParentBottomFactoryTestCaseUi(BottomFactoryTestCaseUi parentBottomFactoryUi) {
		this.parentBottomFactoryTestCaseUi = parentBottomFactoryUi;
	}

	public BottomFactoryORUi getParentBottomFactoryORUi() {
		return parentBottomFactoryORUi;
	}

	public void setParentBottomFactoryORUi(BottomFactoryORUi parentBottomFactoryUi) {
		this.parentBottomFactoryORUi = parentBottomFactoryUi;
	}

	public BottomFactoryDataRepoUi getParentBottomFactoryDataRepoUi() {
		return parentBottomFactoryDataRepoUi;
	}

	public void setParentBottomFactoryDataRepoUi(BottomFactoryDataRepoUi parentBottomFactoryUi) {
		this.parentBottomFactoryDataRepoUi = parentBottomFactoryUi;
	}

	public BottomFactoryFLUi getParentBottomFactoryFLUi() {
		return parentBottomFactoryFLUi;
	}

	public void setParentBottomFactoryFLUi(BottomFactoryFLUi parentBottomFactoryFLUi) {
		this.parentBottomFactoryFLUi = parentBottomFactoryFLUi;
	}
}
