package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class TestObjectTable extends CustomTable {
	private List<ORObject> orobject = new ArrayList<ORObject>();
	private TestCaseView parentTestCaseView;
	private MenuItem openORObjectInNewTabMenuItem;

	public TestObjectTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public TestObjectTable(Composite parent, int style, TestCaseView parentView) {
		super(parent, style);
		init();
		this.setParentTestCaseView(parentView);
	}

	private void init() {
		String[] tableHeaders = { "Name", "Provide Object", "Object Type", "Action" };
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, SWT.LEFT);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}
		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (int i = 0; i < table_0.getColumnCount(); i++) {
					TableColumn column = table_0.getColumn(i);
					column.setWidth((table_0.getBounds().width) / 4);
				}
			}
		});

		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ORObject orobject = getSelectedORObject();
				if (orobject == null) {
					openORObjectInNewTabMenuItem.setEnabled(false);
				} else {
					openORObjectInNewTabMenuItem.setEnabled(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		Menu menu = new Menu(this);
		this.setMenu(menu);
		openORObjectInNewTabMenuItem = new MenuItem(menu, SWT.NONE);
		openORObjectInNewTabMenuItem.setText("Open in New Tab");
		openORObjectInNewTabMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/open.png"));
		openORObjectInNewTabMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ORObject orobject = getSelectedORObject();
				if (orobject == null) {
					return;
				}
				String orId = orobject.getOr_id();
				ArtifactDTO artifact = GlobalLoader.getInstance().getArtifactById(orId);
				if (artifact == null) {
					return;
				}
				Utilities.getInstance().openArtifacts(artifact);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public List<ORObject> getOrobject() {
		return orobject;
	}

	public void setOrobject(List<ORObject> orobject) {
		this.orobject = orobject;
	}

	public void renderORObjectTable(FlowStep flowStep) {
		this.removeAll();
		if (getOrobject().size() > 0) {
			for (ORObject orobject : getOrobject()) {
				CustomTableItem cti = new CustomTableItem(this, 0);
				cti.setText(new String[] { "Object", orobject.getName(), "", "" });
				cti.setControlData(orobject);
				cti.setImage(1, ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TESTOBJECT_ICON));
			}
			return;
		}
		if (flowStep.getKeyword() != null) {
			if (flowStep.getKeyword().isKeywordContainsORObject()) {
				CustomTableItem cti = new CustomTableItem(this, 0);
				cti.setText(new String[] { "Object", "", "", "" });
				cti.setImage(1, ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TESTOBJECT_ICON));
			}
		}
	}

	public ORObject getSelectedORObject() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		CustomTableItem cti = (CustomTableItem) this.getSelection()[0];
		if (cti == null) {
			return null;
		}
		if (cti.getControlData() == null) {
			return null;
		}
		return (ORObject) cti.getControlData();
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

}
