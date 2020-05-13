package pcloudystudio.featurecore.ui.dialog;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.Map;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import pcloudystudio.spytreecomponents.BasicMobileElement;

public class CustomCheckBoxTree extends CheckboxTreeViewer implements ICheckStateListener, ICheckStateProvider {

	public static Widget itemSelected;

	public CustomCheckBoxTree(Composite parent, int style) {
		super(parent, style);
		ColumnViewerToolTipSupport.enableFor(this);
		initializeTree();
	}

	private void initializeTree() {
		setCheckStateProvider(this);
		addCheckStateListener(this);
	}

	@Override
	public boolean isChecked(Object element) {
		return false;
	}

	@Override
	public boolean isGrayed(Object element) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void checkStateChanged(CheckStateChangedEvent event) {
		Object element = event.getElement();
		MobileElementSpyDialog.clearPropertiesTableData();
		if (!MobileElementSpyDialog.allObjectsCheckboxTreeViewer.getChecked(element)) {
			MobileElementSpyDialog.allObjectsCheckboxTreeViewer.setAllChecked(false);
			MobileElementSpyDialog.allObjectsCheckboxTreeViewer.setChecked(element, false);
			MobileElementSpyDialog.allObjectsCheckboxTreeViewer
			.setSelection((ISelection) new StructuredSelection((Object) element));
			MobileElementSpyDialog.btnAdd.setEnabled(false);
			MobileElementSpyDialog.btnClickAndMoveToNextScreen.setEnabled(false);
			MobileElementSpyDialog.textObjectName.setText("");
			itemSelected = null;
		} else {
			MobileElementSpyDialog.allObjectsCheckboxTreeViewer.setAllChecked(false);
			MobileElementSpyDialog.allObjectsCheckboxTreeViewer.setChecked(element, true);
			MobileElementSpyDialog.allObjectsCheckboxTreeViewer
			.setSelection((ISelection) new StructuredSelection((Object) element));
			Widget item = findItem(element);
			itemSelected = item;
			TreeItem treeItem = (TreeItem) item;
			Object obj = treeItem.getData();
			fillDataInObjectPropertiesTable(obj);
			MobileElementSpyDialog.btnAdd.setEnabled(true);
			MobileElementSpyDialog.btnClickAndMoveToNextScreen.setEnabled(true);
		}
	}

	public static Widget getCheckedItem(Object element) {
		return itemSelected;
	}

	public static void fillDataInObjectPropertiesTable(Object obj) {

		Map<String, String> mobileElementProps = ((BasicMobileElement) obj).getAttributes();

		MobileElementSpyDialog.textObjectName
		.setText((mobileElementProps.get("name") != null && mobileElementProps.get("name").length() > 0)
				? mobileElementProps.get("name")
						: (mobileElementProps.get("resource-id") != null
						&& mobileElementProps.get("resource-id").length() > 0)
						? mobileElementProps.get("resource-id")
								: (mobileElementProps.get("class") != null
								&& mobileElementProps.get("class").length() > 0)
								? mobileElementProps.get("class")
										: "");
		if (mobileElementProps.get("name") != null) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("name", mobileElementProps.get("name"));
		}
		if (mobileElementProps.get("class") != null) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("class", mobileElementProps.get("class"));
		}
		if (mobileElementProps.get("activity") != null && mobileElementProps.get("activity").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("activity", mobileElementProps.get("activity"));
		}
		if (mobileElementProps.get("instance") != null && mobileElementProps.get("instance").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("instance", mobileElementProps.get("instance"));
		}
		if (mobileElementProps.get("text") != null && mobileElementProps.get("text").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("text", mobileElementProps.get("text"));
		}
		if (mobileElementProps.get("resource-id") != null && mobileElementProps.get("resource-id").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("resource-id", mobileElementProps.get("resource-id"));
		}
		if (mobileElementProps.get("package") != null && mobileElementProps.get("package").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("package", mobileElementProps.get("package"));
		}
		if (mobileElementProps.get("content-desc") != null && mobileElementProps.get("content-desc").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("content-desc", mobileElementProps.get("content-desc"));
		}
		if (mobileElementProps.get("checkable") != null && mobileElementProps.get("checkable").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("checkable", mobileElementProps.get("checkable"));
		}
		if (mobileElementProps.get("checked") != null && mobileElementProps.get("checked").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("checked", mobileElementProps.get("checked"));
		}
		if (mobileElementProps.get("clickable") != null && mobileElementProps.get("clickable").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("clickable", mobileElementProps.get("clickable"));
		}
		if (mobileElementProps.get("enabled") != null && mobileElementProps.get("enabled").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("enabled", mobileElementProps.get("enabled"));
		}
		if (mobileElementProps.get("focusable") != null && mobileElementProps.get("focusable").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("focusable", mobileElementProps.get("focusable"));
		}
		if (mobileElementProps.get("focused") != null && mobileElementProps.get("focused").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("focused", mobileElementProps.get("focused"));
		}
		if (mobileElementProps.get("scrollable") != null && mobileElementProps.get("scrollable").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("scrollable", mobileElementProps.get("scrollable"));
		}
		if (mobileElementProps.get("long-clickable") != null && mobileElementProps.get("long-clickable").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("long-clickable",
					mobileElementProps.get("long-clickable"));
		}
		if (mobileElementProps.get("password") != null && mobileElementProps.get("password").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("password", mobileElementProps.get("password"));
		}
		if (mobileElementProps.get("selected") != null && mobileElementProps.get("selected").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("selected", mobileElementProps.get("selected"));
		}
		if (mobileElementProps.get("bounds") != null && mobileElementProps.get("bounds").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("bounds", mobileElementProps.get("bounds"));
		}
		if (mobileElementProps.get("x") != null && mobileElementProps.get("x").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("x", mobileElementProps.get("x"));
		}
		if (mobileElementProps.get("y") != null && mobileElementProps.get("y").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("y", mobileElementProps.get("y"));
		}
		if (mobileElementProps.get("width") != null && mobileElementProps.get("width").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("width", mobileElementProps.get("width"));
		}
		if (mobileElementProps.get("height") != null && mobileElementProps.get("height").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("height", mobileElementProps.get("height"));
		}
		if (mobileElementProps.get("xpath") != null && mobileElementProps.get("xpath").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("xpath", mobileElementProps.get("xpath"));
		}
		if (mobileElementProps.get("xpath-attribute") != null && mobileElementProps.get("xpath-attribute").length() > 0) {
			MobileElementSpyDialog.addTableItemToPropertiesTableData("xpath-attribute", mobileElementProps.get("xpath-attribute"));
		}
	}

}
