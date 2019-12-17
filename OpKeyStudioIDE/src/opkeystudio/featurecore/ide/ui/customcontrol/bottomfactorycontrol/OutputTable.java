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

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory.BottomFactoryOutputParemeterApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryOutput;

public class OutputTable extends CustomTable {
	private boolean paintCalled = false;
	private OutputTable thisTable = this;

	private BottomFactoryFLUi parentBottomFactoryFLUi;

	public OutputTable(Composite parent, int style, BottomFactoryFLUi parentView)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		super(parent, style);
		init();
		thisTable = this;
		this.setParentBottomFactoryFLUi(parentView);
	}

	private void init() throws JsonParseException, JsonMappingException, IOException, SQLException {
		String[] tableHeaders = { "Name", "Data Type", "Associated Step Output", "Description" };
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
		renderAllBottomFactoryOutputData();
	}

	public void setBottomFactoryOutputData(List<Fl_BottomFactoryOutput> bottomFactoryOutputs) {
		super.setControlData(bottomFactoryOutputs);
	}

	public List<Fl_BottomFactoryOutput> getBottomFactoryOutputData() {
		return (List<Fl_BottomFactoryOutput>) super.getControlData();
	}

	public void deleteBottomFactoryOutputData(Fl_BottomFactoryOutput bottomFactoryOutput) {
		bottomFactoryOutput.setDeleted(true);
	}

	public void refreshAllBottomFactoryOutputData() {
		this.removeAll();
		List<Fl_BottomFactoryOutput> bottomFactoryOutputs = getBottomFactoryOutputData();
		setBottomFactoryOutputData(bottomFactoryOutputs);
		for (Fl_BottomFactoryOutput fl_BottomFactoryOutput : bottomFactoryOutputs) {
			if (fl_BottomFactoryOutput.isDeleted() == false) {
				OutputTableItem outputTableItem = new OutputTableItem(this, 0);
				outputTableItem.setText(new String[] { fl_BottomFactoryOutput.getName(),
						fl_BottomFactoryOutput.getType(), fl_BottomFactoryOutput.getComponentstep_oa_id(),
						fl_BottomFactoryOutput.getDescription() });
				outputTableItem.setBottomFactoryOutputData(fl_BottomFactoryOutput);
			}
		}
	}

	public void renderAllBottomFactoryOutputData()
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		String artifactId = artifact.getId();
		List<Fl_BottomFactoryOutput> bottomFactoryOutputs = new BottomFactoryOutputParemeterApi()
				.getAllBottomFactoryOutputParameter(artifactId);
		setBottomFactoryOutputData(bottomFactoryOutputs);
		for (Fl_BottomFactoryOutput fl_BottomFactoryOutput : bottomFactoryOutputs) {
			if (fl_BottomFactoryOutput.isDeleted() == false) {
				OutputTableItem outputTableItem = new OutputTableItem(this, 0);
				System.out.println(fl_BottomFactoryOutput.getName().toString());
				System.out.println(fl_BottomFactoryOutput.getType());
				System.out.println(fl_BottomFactoryOutput.getDescription());
				outputTableItem.setText(new String[] { fl_BottomFactoryOutput.getName().toString(),
						fl_BottomFactoryOutput.getType(), fl_BottomFactoryOutput.getComponentstep_oa_id(),
						fl_BottomFactoryOutput.getDescription() });
				outputTableItem.setBottomFactoryOutputData(fl_BottomFactoryOutput);

			}
		}
	}

	private void selectRow(int index) {
		if (this.getItemCount() == 0) {
			return;
		}
		this.setSelection(index);
		this.notifyListeners(SWT.Selection, null);
	}

	public void moveFl_BottomFactoryOutputUp(Fl_BottomFactoryOutput bottomFactoryOutput1,
			Fl_BottomFactoryOutput bottomFactoryOutput2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		refreshAllBottomFactoryOutputData();
		selectRow(selectedIndex - 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);

	}

	public void moveFl_BottomFactoryOutputDown(Fl_BottomFactoryOutput bottomFactoryOutput1,
			Fl_BottomFactoryOutput bottomFactoryOutput2) {
		int selectedIndex = this.getSelectionIndex();
		int fpos1 = bottomFactoryOutput1.getPosition();
		int fpos2 = bottomFactoryOutput2.getPosition();

		bottomFactoryOutput1.setPosition(fpos2);
		bottomFactoryOutput2.setPosition(fpos1);
		refreshAllBottomFactoryOutputData();
		selectRow(selectedIndex + 1);
		bottomFactoryOutput1.setModified(true);
		bottomFactoryOutput2.setModified(true);

	}

	public Fl_BottomFactoryOutput getSelectedOutputParemeter() {
		OutputTableItem OutputTableItem = (OutputTableItem) this.getSelection()[0];
		return OutputTableItem.getBottomFactoryOutputData();
	}

	public Fl_BottomFactoryOutput getPrevOutputParemeter() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == 0) {
			return null;
		}
		selectedIndex = selectedIndex - 1;
		OutputTableItem OutputTableItem = (opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.OutputTableItem) this
				.getItem(selectedIndex);
		if (OutputTableItem != null) {
			return OutputTableItem.getBottomFactoryOutputData();
		}
		return null;
	}

	public Fl_BottomFactoryOutput getNextOutputParemeter() {
		int selectedIndex = this.getSelectionIndices()[0];
		if (selectedIndex == this.getItemCount() - 1) {
			return null;
		}
		selectedIndex = selectedIndex + 1;
		OutputTableItem OutputTableItem = (opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.OutputTableItem) this
				.getItem(selectedIndex);
		if (OutputTableItem != null) {
			return OutputTableItem.getBottomFactoryOutputData();
		}
		return null;
	}

	public BottomFactoryFLUi getParentBottomFactoryFLUi() {
		return parentBottomFactoryFLUi;
	}

	public void setParentBottomFactoryFLUi(BottomFactoryFLUi parentBottomFactoryFLUi) {
		this.parentBottomFactoryFLUi = parentBottomFactoryFLUi;
	}
}
