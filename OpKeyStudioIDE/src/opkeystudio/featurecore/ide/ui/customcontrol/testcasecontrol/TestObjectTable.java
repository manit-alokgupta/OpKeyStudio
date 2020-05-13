package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.utils.DataType.OpKeyDataType;

public class TestObjectTable extends CustomTable {
	private List<ORObject> orobject = new ArrayList<ORObject>();
	private TestCaseView parentTestCaseView;
	private MenuItem openORObjectInNewTabMenuItem;
	private FlowStep flowStep;
	private boolean stopPaintEvent = false;

	public TestObjectTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public TestObjectTable(Composite parent, int style, TestCaseView parentView) {
		super(parent, style);
		init();
		this.setParentTestCaseView(parentView);
	}

	public void clearAllData() {
		this.removeAll();
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
				if (stopPaintEvent) {
					TableColumn column = table_0.getColumn(0);
					column.setWidth((table_0.getBounds().width));
					return;
				}
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
				Artifact artifact = GlobalLoader.getInstance().getArtifactById(orId);
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

	private List<Control> allTableEditors = new ArrayList<Control>();

	private void addInputTableEditor(CustomTableItem item) {
		TableEditor editor1 = getTableEditor();
		CustomButton button = new CustomButton(this, SWT.NONE | SWT.NO_BACKGROUND);
		button.setToolTipText("Remove Object");
		button.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				FlowStep flowStep = getFlowStep();
				flowStep.setOrObject(new ArrayList<ORObject>());
				flowStep.setModified(true);
				for (FlowInputArgument inputARg : flowStep.getFlowInputArgs()) {
					if (inputARg.getStaticobjectid() != null) {
						inputARg.setStaticobjectid(String.valueOf(OpKeyDataType.NULLDATA));
						inputARg.setModified(true);
					}
				}
				getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
				getParentTestCaseView().toggleSaveButton(true);
			}

			@Override
			public void mouseDown(MouseEvent e) {

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				editor1.getEditor().dispose();
			}
		});
		editor1.setEditor(button, item, 3);
		this.allTableEditors.add(editor1.getEditor());
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		// editor.horizontalAlignment = SWT.RIGHT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		editor.minimumHeight = 10;
		return editor;
	}

	private void disposeAllTableEditors() {
		for (Control controlEditor : this.allTableEditors) {
			controlEditor.dispose();
		}
	}

	public void renderORObjectTable(FlowStep flowStep) {
		this.removeAll();
		disposeAllTableEditors();
		this.setFlowStep(flowStep);
		if (getOrobject().size() > 0) {
			for (ORObject orobject : getOrobject()) {
				this.setHeaderVisible(true);
				this.setLinesVisible(true);
				this.setEnabled(true);
				stopPaintEvent = false;
				CustomTableItem cti = new CustomTableItem(this, 0);
				cti.setText(new String[] { "Object", orobject.getName(), "", "" });
				cti.setControlData(orobject);
				cti.setImage(0, ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TESTOBJECT_ICON));
				addInputTableEditor(cti);
			}
			return;
		}
		if (flowStep.getKeyword() != null) {
			if (flowStep.getKeyword().isKeywordContainsORObject()) {
				this.setHeaderVisible(true);
				this.setLinesVisible(true);
				this.setEnabled(true);
				stopPaintEvent = false;
				CustomTableItem cti = new CustomTableItem(this, 0);
				cti.setText(new String[] { "Object", "", "", "" });
				cti.setImage(0, ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TESTOBJECT_ICON));
			} else {
				this.setHeaderVisible(false);
				this.setLinesVisible(false);
				this.setEnabled(false);
				displayTableInfo(new String[] { "This Keyword Does Not Need An Object!" });
			}
		}
	}

	private void displayTableInfo(String[] message) {
		stopPaintEvent = true;
		CustomTableItem item = new CustomTableItem(this, SWT.NONE | SWT.CENTER);
		item.setText(message);
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

	public FlowStep getFlowStep() {
		return flowStep;
	}

	public void setFlowStep(FlowStep flowStep) {
		this.flowStep = flowStep;
	}

}
