package pcloudystudio.objectspy.treeviewer;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import pcloudystudio.objectspy.dialog.MobileObjectSpyDialog;
import pcloudystudio.objectspy.element.tree.MobileElementLabelProvider;
import pcloudystudio.objectspy.element.tree.MobileElementTreeContentProvider;

public class TreeViewer {

	private static int compositeWidth = 500;
	private static int compositeHeight = 250;

	public TreeViewer(Display display, Shell shell) {
		addWidgetsToShell(display, shell);
	}

	private static void addWidgetsToShell(Display display, Shell shell) {

		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBackground(new Color(shell.getDisplay(), 102, 153, 153));
		composite.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().hint(compositeWidth, compositeHeight).applyTo(composite);
		Label lblAllObjects = new Label(composite, SWT.NONE);
		lblAllObjects.setBounds(10, 194, 87, 17);
		lblAllObjects.setText("ALL OBJECTS");
		lblAllObjects.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));

		MobileElementTreeContentProvider contentProvider = new MobileElementTreeContentProvider();
		MobileElementLabelProvider labelProvider = new MobileElementLabelProvider();

		CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(composite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(treeViewer.getTree());
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setAutoExpandLevel(30);
		treeViewer.setInput((Object) new Object[] { MobileObjectSpyDialog.appRootElement });

	}

}
