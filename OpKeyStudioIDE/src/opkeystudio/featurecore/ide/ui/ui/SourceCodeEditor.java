package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.sourcecodeeditorcontrol.SourceCodeTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler.Transpiler;

public class SourceCodeEditor extends Composite {
	private TestCaseView testCaseView;
	private TabFolder tabFolder;
	private Tree sourceCodeTree;

	public SourceCodeEditor(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		setTestCaseView(testCaseView);
		init();
	}

	private void init() {
		this.setLayout(new GridLayout(1, false));
		ToolBar sourceCodeToolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		sourceCodeToolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem sourceCodeToolBarItem = new ToolItem(sourceCodeToolBar, SWT.NONE);
		sourceCodeToolBarItem.setText("New Item");

		ToolItem seperator = new ToolItem(sourceCodeToolBar, SWT.SEPARATOR);

		ToolItem sourceCodeToolBarItem_1 = new ToolItem(sourceCodeToolBar, SWT.NONE);
		sourceCodeToolBarItem_1.setText("New Item");

		Composite composite_16 = new Composite(this, SWT.NONE);
		composite_16.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_16.setLayout(new GridLayout(2, false));

		sourceCodeTree = new Tree(composite_16, SWT.BORDER);
		sourceCodeTree.setHeaderVisible(true);
		sourceCodeTree.setLinesVisible(false);

		try {
			renderTreeItems();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GridData gd_sourceCodeTree = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_sourceCodeTree.widthHint = 275;
		sourceCodeTree.setLayoutData(gd_sourceCodeTree);

		tabFolder = new TabFolder(composite_16, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		// SourceViewer viewer = new SourceViewer(tabFolder, null, SWT.NONE);
		// final HConfiguration sourceConf = new
		// HConfiguration(HContentAssistProcessor.PARAM_PROCESSOR);
		// viewer.configure(sourceConf);
		// viewer.setEditable(true);
		// Font font = JFaceResources.getFont(JFaceResources.TEXT_FONT);
		// viewer.getTextWidget().setFont(font);
	}

	private void renderTreeItems() throws JsonParseException, JsonMappingException, SQLException, IOException,
			IllegalArgumentException, IllegalAccessException {
		String[] items = new String[] { "Global Variables", "TestCases", "Object Repositories", "Function Libraries" };
		for (String item : items) {
			TreeItem sourceCodeTreeitem_1 = new TreeItem(sourceCodeTree, SWT.NONE);
			sourceCodeTreeitem_1.setText(item);
		}

		sourceCodeTree.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

			}

			@Override
			public void mouseDown(MouseEvent e) {

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TreeItem item = sourceCodeTree.getSelection()[0];
				if (!(item instanceof SourceCodeTreeItem)) {
					return;
				}
				SourceCodeTreeItem scti = (SourceCodeTreeItem) item;
				System.out.println(scti.getBodyData());

				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				StyledText styledText = new StyledText(tabFolder, SWT.BORDER);
				styledText.setAlwaysShowScrollBars(true);
				tabItem.setControl(styledText);
				styledText.setMouseNavigatorEnabled(true);
				styledText.setText(scti.getBodyData());

				tabItem.setText(item.getText());
				tabFolder.setSelection(tabItem);
			}
		});
	}

	public void transpileDatas() {
		try {
			List<GlobalVariable> globalVariables = new GlobalVariableApi().getAllGlobalVariables();
			List<FlowStep> flowSteps = getTestCaseView().getFlowStepTable().getFlowStepsData();
			List<FlowStep> functionLibraries = getFunctionLibraries(flowSteps);
			List<ORObject> allORObjects = getAllORObjects(flowSteps);

			String gvtranspiledData = Transpiler.getTranspiler().transpileGlobalVariables(globalVariables);
			String ortranspiledData = Transpiler.getTranspiler().transpileORObjects(allORObjects);
			System.out.println(ortranspiledData);
			TreeItem[] treeItems = sourceCodeTree.getItems();
			for (TreeItem treeItem : treeItems) {
				if (treeItem.getText().equals("Global Variables")) {
					SourceCodeTreeItem gvItem = new SourceCodeTreeItem(treeItem, 0);
					gvItem.setFileName("GlobalVariable.java");
					gvItem.setText(gvItem.getFileName());
					gvItem.setBodyData(gvtranspiledData);
				}
				if (treeItem.getText().equals("Object Repositories")) {
					SourceCodeTreeItem gvItem = new SourceCodeTreeItem(treeItem, 0);
					gvItem.setFileName("ORObjects.java");
					gvItem.setText(gvItem.getFileName());
					gvItem.setBodyData(ortranspiledData);
				}
			}
		} catch (SQLException | IOException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	private List<FlowStep> getFunctionLibraries(List<FlowStep> allFlowSteps) {
		List<FlowStep> allFunctionLibraries = new ArrayList<FlowStep>();
		for (FlowStep flowStep : allFlowSteps) {
			if (flowStep.getComponent_id() != null) {
				allFunctionLibraries.add(flowStep);
			}
		}
		return allFunctionLibraries;
	}

	private List<ORObject> getAllORObjects(List<FlowStep> allFlowSteps) {
		List<ORObject> allORObjects = new ArrayList<ORObject>();
		for (FlowStep flowStep : allFlowSteps) {
			allORObjects.addAll(flowStep.getOrObject());
		}
		return allORObjects;
	}

	public TestCaseView getTestCaseView() {
		return testCaseView;
	}

	public void setTestCaseView(TestCaseView testCaseView) {
		this.testCaseView = testCaseView;
	}

}
