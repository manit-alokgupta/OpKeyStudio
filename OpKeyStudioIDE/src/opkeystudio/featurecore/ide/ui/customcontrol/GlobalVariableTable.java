package opkeystudio.featurecore.ide.ui.customcontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectAttributeTableItem;
import opkeystudio.featurecore.ide.ui.ui.GlobalVariableDialog;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalVariableTable extends CustomTable {
	private GlobalVariableDialog parentGlobalVariableView;

	public GlobalVariableTable(Composite parent, int style, GlobalVariableDialog gvDialog) {
		super(parent, SWT.NONE);
		setParentGlobalVariableView(gvDialog);
		init();
	}

	private void init() {
		TableCursor cursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(cursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;
		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setGlobalVariablesData(List<GlobalVariable> gvars) {
		super.setControlData(gvars);
	}

	@SuppressWarnings("unchecked")
	public List<GlobalVariable> getGlobalVariablesData() {
		return (List<GlobalVariable>) super.getControlData();
	}

	public void addGlobalVariable(GlobalVariable gv) {
		getGlobalVariablesData().add(gv);
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		editor.horizontalAlignment = SWT.CENTER;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private void addTableEditor(CustomTableItem item) {
		GlobalVariable globalVariable = (GlobalVariable) item.getOpKeyData();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		CustomButton isExternallyUpdatable = new CustomButton(this, SWT.CHECK);
		isExternallyUpdatable.setSelection(globalVariable.isExternallyupdatable());

		isExternallyUpdatable.setControlData(globalVariable);
		isExternallyUpdatable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelection(item);
				CustomButton button = (CustomButton) e.getSource();
				GlobalVariable oatr = (GlobalVariable) button.getControlData();
				oatr.setModified(true);
				oatr.setExternallyupdatable(button.getSelection());
				getParentGlobalVariableView().toggleSaveToolItem(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		editor1.setEditor(isExternallyUpdatable, item, 3);

		allTableEditors.add(editor1.getEditor());
		allTableEditors.add(editor2.getEditor());
	}

	private void disposeAllTableEditors() {
		for (Control editor : allTableEditors) {
			editor.dispose();
		}
	}

	public void refreshGlobalVariables() {
		disposeAllTableEditors();
		this.removeAll();
		try {
			List<GlobalVariable> globalvariables = new GlobalVariableApi().getAllGlobalVariables();
			for (GlobalVariable globalvariable : globalvariables) {
				CustomTableItem ti = new CustomTableItem(this, 0);
				ti.setData(globalvariable);
				ti.setOpKeyData(globalvariable);
				ti.setText(new String[] { globalvariable.getName(), globalvariable.getDatatype(),
						globalvariable.getValue(), "" });
				addTableEditor(ti);
			}
			this.setControlData(globalvariables);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void renderGlobalVariables() {
		disposeAllTableEditors();
		this.removeAll();
		List<GlobalVariable> globalvariables = this.getGlobalVariablesData();
		for (GlobalVariable globalvariable : globalvariables) {
			if (globalvariable.isDeleted() == false) {
				CustomTableItem ti = new CustomTableItem(this, 0);
				ti.setData(globalvariable);
				ti.setOpKeyData(globalvariable);
				ti.setText(new String[] { globalvariable.getName(), globalvariable.getDatatype(),
						globalvariable.getValue(), "" });
				addTableEditor(ti);
			}
		}
	}

	public void addBlankGlobalVariableStep() {
		int lastPosition = getGlobalVariablesData().get(getGlobalVariablesData().size() - 1).getPosition();
		GlobalVariable gv = new GlobalVariable();
		gv.setPosition(lastPosition + 1);
		gv.setGv_id(Utilities.getInstance().getUniqueUUID(""));
		gv.setP_id(ServiceRepository.getInstance().getDefaultProject().getP_id());
		gv.setName("");
		gv.setAdded(true);
		addGlobalVariable(gv);
		renderGlobalVariables();
	}

	public void deleteGlobalVariableStep() {
		int selectedIndex = this.getSelectionIndex();
		getGlobalVariablesData().get(selectedIndex).setDeleted(true);
		renderGlobalVariables();
	}

	public void saveAll() {
		boolean status = MessageDialog.openConfirm(this.getShell(), "Global Variable Save",
				"Do you want to save global varaible?");
		if (!status) {
			refreshGlobalVariables();
			return;
		}

		List<GlobalVariable> allGlobalVariables = getGlobalVariablesData();
		for (GlobalVariable gv : allGlobalVariables) {
			if (gv.isDeleted()) {
				new GlobalVariableApi().deleteGlobalVariable(gv);
			}
		}
		for (GlobalVariable gv : allGlobalVariables) {
			if (gv.isModified()) {
				new GlobalVariableApi().updateGlobalVariable(gv);
			}
		}
		for (GlobalVariable gv : allGlobalVariables) {
			if (gv.isAdded()) {
				new GlobalVariableApi().insertGlobalVaribale(gv);
			}
		}
		refreshGlobalVariables();
	}

	public GlobalVariableDialog getParentGlobalVariableView() {
		return parentGlobalVariableView;
	}

	public void setParentGlobalVariableView(GlobalVariableDialog parentGlobalVariableView) {
		this.parentGlobalVariableView = parentGlobalVariableView;
	}

}
