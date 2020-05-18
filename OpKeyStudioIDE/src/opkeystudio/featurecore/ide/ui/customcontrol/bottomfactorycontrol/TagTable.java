package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryDataRepoUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryORUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryTestCaseUi;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryTestSuiteUi;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.BottomFactoryTag;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

@SuppressWarnings("unused")
public class TagTable extends CustomTable {

	private boolean paintCalled = false;
	private TagTable thisTable = this;
	private BottomFactoryTestCaseUi parentBottomFactoryUI;
	private BottomFactoryORUi parentBottomFactoryORUi;
	private BottomFactoryDataRepoUi parentBottomFactoryDataRepoUi;
	private BottomFactoryFLUi parentBottomFactoryFLUi;
	private BottomFactoryTestSuiteUi parentBottomFactoryTestSuiteUi;
	private List<BottomFactoryTag> bottomFactoryTags = new ArrayList<BottomFactoryTag>();

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

	public TagTable(Composite parent, int style, BottomFactoryTestSuiteUi parentView) {
		super(parent, style);
		try {
			init();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thisTable = this;
		this.setParentBottomFactoryTestSuiteUi(parentView);
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
		return this.bottomFactoryTags;
	}

	public void setTagData(List<BottomFactoryTag> bottomFactoryTags) {
		this.bottomFactoryTags = bottomFactoryTags;
	}

	public void deleteTagData(BottomFactoryTag bottomFactoryTag) {
		bottomFactoryTag.setDeleted(true);
		refreshAllTagData();
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
		ArtifactDTO artifact = (ArtifactDTO) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
	}

	public void moveTagDataUp(BottomFactoryTag bottomFactoryTag1, BottomFactoryTag bottomFactoryTag2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryTag1.getPosition();
		int fpos2 = bottomFactoryTag2.getPosition();

		bottomFactoryTag1.setPosition(fpos2);
		bottomFactoryTag2.setPosition(fpos1);
		refreshAllTagData();
		selectRow(selectedIndex - 1);
		bottomFactoryTag1.setModified(true);
		bottomFactoryTag2.setModified(true);
	}

	public void moveTagDataDown(BottomFactoryTag bottomFactoryTag1, BottomFactoryTag bottomFactoryTag2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryTag1.getPosition();
		int fpos2 = bottomFactoryTag2.getPosition();

		bottomFactoryTag1.setPosition(fpos2);
		bottomFactoryTag2.setPosition(fpos1);
		refreshAllTagData();
		selectRow(selectedIndex + 1);
		bottomFactoryTag1.setModified(true);
		bottomFactoryTag2.setModified(true);
	}

	public BottomFactoryTag getSelectedTagData() {
		TagTableItem item = (TagTableItem) this.getSelection()[0];
		return item.getTagData();
	}

	public BottomFactoryTag getPrevTagData() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == 0) {
			return null;
		}
		selectedIndex = selectedIndex - 1;
		TagTableItem item = (TagTableItem) this.getItem(selectedIndex);
		if (item != null) {
			return item.getTagData();
		}
		return null;
	}

	public BottomFactoryTag getNextTagData() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == this.getItemCount() - 1) {
			return null;
		}
		selectedIndex = selectedIndex + 1;
		TagTableItem item = (TagTableItem) this.getItem(selectedIndex);
		if (item != null) {
			return item.getTagData();
		}
		return null;
	}

	public void addBlankTagData() {
		try {
			MPart mpart = Utilities.getInstance().getActivePart();
			ArtifactDTO artifact = (ArtifactDTO) mpart.getTransientData().get("opkeystudio.artifactData");
			String artifactId = artifact.getId();
			int lastPosition = 0;
			BottomFactoryTag bottomFactoryTag = new BottomFactoryTag();
			System.out.println(getTagData().size());
			if ((getTagData().size()) == 0) {
				lastPosition = (getTagData().size() - 1);

			} else {
				lastPosition = getTagData().get(getTagData().size() - 1).getPosition();
			}
			bottomFactoryTag.setPosition(lastPosition + 1);
			bottomFactoryTag.setTag_id(Utilities.getInstance().getUniqueUUID(""));
			bottomFactoryTag.setP_id_denormalized(ServiceRepository.getInstance().getDefaultProject().getP_id());
			bottomFactoryTag.setId(artifactId);
			bottomFactoryTag.setKey("NewTag-" + (getTagData().size() - 1));
			bottomFactoryTag.setValue("value-" + (getTagData().size() - 1));
			renderAllTagData();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public BottomFactoryTestSuiteUi getParentBottomFactoryTestSuiteUi() {
		return parentBottomFactoryTestSuiteUi;
	}

	public void setParentBottomFactoryTestSuiteUi(BottomFactoryTestSuiteUi parentBottomFactoryTestSuiteUi) {
		this.parentBottomFactoryTestSuiteUi = parentBottomFactoryTestSuiteUi;
	}

}
