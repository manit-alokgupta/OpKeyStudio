package pcloudystudio.mobilespy.spytree;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.Map;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import pcloudystudio.mobilespy.dialog.MobileSpyDialog;
import pcloudystudio.objectspy.element.impl.BasicMobileElement;

public class CustomCheckBoxTree extends CheckboxTreeViewer implements ICheckStateListener, ICheckStateProvider {

	public CustomCheckBoxTree(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		ColumnViewerToolTipSupport.enableFor(this);
		initializeTree();
	}

	private void initializeTree() {
		setCheckStateProvider(this);
		addCheckStateListener(this);
	}

	@Override
	public boolean isChecked(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGrayed(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkStateChanged(CheckStateChangedEvent event) {
		// TODO Auto-generated method stub
		Object element = event.getElement();

		Widget item = findItem(element);

		if (!(item instanceof TreeItem)) {
			System.out.println("Given Item is not an instance of TreeItem!");
			return;
		} else {
			MobileSpyDialog.clearPropertiesTableData();
			TreeItem treeItem = (TreeItem) item;
			Object obj = treeItem.getData();

			final Map<String, String> mobileElementProps = ((BasicMobileElement) obj).getAttributes();
			if (mobileElementProps.get("class") != null) {
				MobileSpyDialog.addTableItemToPropertiesTableData("class", mobileElementProps.get("class"));
			}
			if (mobileElementProps.get("instance") != null && mobileElementProps.get("instance").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("instance", mobileElementProps.get("instance"));
			}
			if (mobileElementProps.get("text") != null && mobileElementProps.get("text").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("text", mobileElementProps.get("text"));
			}
			if (mobileElementProps.get("resource-id") != null && mobileElementProps.get("resource-id").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("resource-id", mobileElementProps.get("resource-id"));
			}
			if (mobileElementProps.get("package") != null && mobileElementProps.get("package").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("package", mobileElementProps.get("package"));
			}
			if (mobileElementProps.get("content-desc") != null && mobileElementProps.get("content-desc").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("content-desc",
						mobileElementProps.get("content-desc"));
			}
			if (mobileElementProps.get("checkable") != null && mobileElementProps.get("checkable").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("checkable", mobileElementProps.get("checkable"));
			}
			if (mobileElementProps.get("checked") != null && mobileElementProps.get("checked").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("checked", mobileElementProps.get("checked"));
			}
			if (mobileElementProps.get("clickable") != null && mobileElementProps.get("clickable").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("clickable", mobileElementProps.get("clickable"));
			}
			if (mobileElementProps.get("enabled") != null && mobileElementProps.get("enabled").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("enabled", mobileElementProps.get("enabled"));
			}
			if (mobileElementProps.get("focusable") != null && mobileElementProps.get("focusable").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("focusable", mobileElementProps.get("focusable"));
			}
			if (mobileElementProps.get("focused") != null && mobileElementProps.get("focused").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("focused", mobileElementProps.get("focused"));
			}
			if (mobileElementProps.get("scrollable") != null && mobileElementProps.get("scrollable").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("scrollable", mobileElementProps.get("scrollable"));
			}
			if (mobileElementProps.get("long-clickable") != null
					&& mobileElementProps.get("long-clickable").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("long-clickable",
						mobileElementProps.get("long-clickable"));
			}
			if (mobileElementProps.get("password") != null && mobileElementProps.get("password").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("password", mobileElementProps.get("password"));
			}
			if (mobileElementProps.get("selected") != null && mobileElementProps.get("selected").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("selected", mobileElementProps.get("selected"));
			}
			if (mobileElementProps.get("bounds") != null && mobileElementProps.get("bounds").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("bounds", mobileElementProps.get("bounds"));
			}
			if (mobileElementProps.get("x") != null && mobileElementProps.get("x").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("x", mobileElementProps.get("x"));
			}
			if (mobileElementProps.get("y") != null && mobileElementProps.get("y").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("y", mobileElementProps.get("y"));
			}
			if (mobileElementProps.get("width") != null && mobileElementProps.get("width").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("width", mobileElementProps.get("width"));
			}
			if (mobileElementProps.get("height") != null && mobileElementProps.get("height").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("height", mobileElementProps.get("height"));
			}
			if (mobileElementProps.get("xpath") != null && mobileElementProps.get("xpath").length() > 0) {
				MobileSpyDialog.addTableItemToPropertiesTableData("xpath", mobileElementProps.get("xpath"));
			}
		}
	}

}
