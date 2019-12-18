package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory.BottomFactoryTagApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.BottomFactoryTag;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryDataRepoUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryORUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryTestCaseUi;

public class TagTable extends CustomTable {

	private boolean paintCalled = false;
	private TagTable thisTable = this;
	private BottomFactoryTestCaseUi parentBottomFactoryUI;
	private BottomFactoryORUi parentBottomFactoryORUi;
	private BottomFactoryDataRepoUi parentBottomFactoryDataRepoUi;
	private BottomFactoryFLUi parentBottomFactoryFLUi;

	public TagTable(Composite parent, int style, BottomFactoryTestCaseUi bottomFactoryUI) {
		super(parent, style);
		try {
			init();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thisTable = this;
		this.setParentBottomFactoryUI(bottomFactoryUI);
	}

	public TagTable(Composite parent, int style, BottomFactoryORUi bottomFactoryUi) {
		super(parent, style);
		try {
			init();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thisTable = this;
		this.setParentBottomFactoryORUi(bottomFactoryUi);
	}

	public TagTable(Composite parent, int style, BottomFactoryDataRepoUi bottomFactoryUi) {
		super(parent, style);
		try {
			init();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thisTable = this;
		this.setParentBottomFactoryDataRepoUi(bottomFactoryUi);
	}

	public TagTable(Composite parent, int style, BottomFactoryFLUi parentView) {
		super(parent, style);
		try {
			init();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thisTable = this;
		this.setParentBottomFactoryFLUi(parentView);
	}

	private void init() throws JsonParseException, JsonMappingException, IOException, SQLException {
		String[] tableHeaders = { "Key", "Value" };
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
					column.setWidth(table_0.getBounds().width / 2);
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
				CustomTableItem selectedTableItem = (CustomTableItem) cursor.getRow();
				BottomFactoryTag bottomFactoryTag = (BottomFactoryTag) selectedTableItem.getControlData();
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
						selectedTableItem.setText(selectedColumn, text.getText());
						bottomFactoryTag.setModified(true);
						cursor.getRow().setText(selectedColumn, text.getText());
						if (selectedColumn == 0) {
							bottomFactoryTag.setKey(text.getText());
						}
						if (selectedColumn == 1) {
							bottomFactoryTag.setValue(text.getText());
						}
					}
				});

				controlEditor.setEditor(text);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		renderAllTagData();
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public List<BottomFactoryTag> getTagData() {
		return (List<BottomFactoryTag>) super.getControlData();
	}

	public void setTagData(List<BottomFactoryTag> bottomFactoryTags) {
		super.setControlData(bottomFactoryTags);
	}

	public void deleteTagData(BottomFactoryTag bottomFactoryTag) {
		bottomFactoryTag.setDeleted(true);
	}

	public void refreshAllTagData() {
		this.removeAll();
		List<BottomFactoryTag> bottomFactoryTags = getTagData();
		setTagData(bottomFactoryTags);
		for (BottomFactoryTag bottomFactoryTag : bottomFactoryTags) {
			if (bottomFactoryTag.isDeleted() == false) {
				TagTableItem item = new TagTableItem(this, 0);
				item.setText(
						new String[] { bottomFactoryTag.getKey().toString(), bottomFactoryTag.getValue().toString() });
				item.setTagData(bottomFactoryTag);
			}
		}
		selectRow(0);
	}

	public void renderAllTagData() throws JsonParseException, JsonMappingException, IOException, SQLException {
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
		List<BottomFactoryTag> bottomFactoryTags = new BottomFactoryTagApi().getAllBottomFactoryTag(artifactId);
		setTagData(bottomFactoryTags);
		for (BottomFactoryTag bottomFactoryTag : bottomFactoryTags) {
			if (bottomFactoryTag.isDeleted() == false) {
				System.out.println(bottomFactoryTag.getKey().toString());
				System.out.println(bottomFactoryTag.getValue().toString());
				System.out.println(bottomFactoryTag.getTag_id());
				System.out.println(bottomFactoryTag.getId());
				TagTableItem item = new TagTableItem(this, 0);
				item.setText(
						new String[] { bottomFactoryTag.getKey().toString(), bottomFactoryTag.getValue().toString() });
				item.setTagData(bottomFactoryTag);
			}
		}
		selectRow(0);
	}

	public BottomFactoryTestCaseUi getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(BottomFactoryTestCaseUi parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

	public BottomFactoryORUi getParentBottomFactoryORUI() {
		return parentBottomFactoryORUi;
	}

	public void setParentBottomFactoryORUi(BottomFactoryORUi parentBottomFactoryUi) {
		this.parentBottomFactoryORUi = parentBottomFactoryUi;
	}

	public BottomFactoryORUi getParentBottomFactoryDataRepoUi() {
		return parentBottomFactoryORUi;
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
