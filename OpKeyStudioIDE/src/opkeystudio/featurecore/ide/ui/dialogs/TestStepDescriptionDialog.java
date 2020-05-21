package opkeystudio.featurecore.ide.ui.dialogs;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.awt.JobAttributes;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;

public class TestStepDescriptionDialog extends TitleAreaDialog {
	protected String dialogTitle;
	protected String dialogMessage;
	protected JobAttributes.DialogType type;
	protected String description;
	protected StyledText textDescription;

	public TestStepDescriptionDialog(Shell parentShell, String defaltContent) {
		super(parentShell);
		this.dialogTitle = "Step Details";
		this.dialogMessage = "Description for Test Step";
		this.description = defaltContent;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.BORDER);
		container.setLayoutData((Object) new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		container.setLayout((Layout) new GridLayout(2, false));
		(this.textDescription = new StyledText(container, SWT.BORDER | SWT.HIGH | SWT.WRAP))
		.setLayoutData((Object) new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.textDescription.setEditable(false);
		return (Control) area;
	}

	@Override
	protected Point getInitialSize() {
		Point initSize = super.getInitialSize();
		return new Point(initSize.x, Math.max(250, initSize.y));
	}

	@Override
	public void create() {
		super.create();
		getShell().setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"));
		this.setTitle(this.dialogTitle);
		this.setMessage(this.dialogMessage, 1);
		this.setInput();
	}

	@Override
	protected void okPressed() {
		this.description = this.textDescription.getText();
		super.okPressed();
	}

	private void setInput() {
		if (this.description != null) {
			this.textDescription.setText(this.description);
		}
	}

	public String getDescription() {
		return (this.description == null) ? "" : this.description;
	}
}
