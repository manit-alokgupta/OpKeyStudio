package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
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
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.SourceCodeEditorTools;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Token;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Token.TOKEN_TYPE;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler.Transpiler;

public class SourceCodeEditor extends Composite {
	private TestCaseView testCaseView;
	private TabFolder tabFolder;
	private Tree sourceCodeTree;
	private TextViewer sourceCodeText;

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
				initiateJavaEditor(scti.getCodeData());
				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				tabItem.setControl(sourceCodeText.getControl());
				tabItem.setText(item.getText());
				tabFolder.setSelection(tabItem);
			}
		});
	}

	private void initiateJavaEditor(String sourceCode) {
		sourceCodeText = new TextViewer(tabFolder, SWT.V_SCROLL | SWT.SCROLL_LINE);
		sourceCodeText.setDocument(new Document());
		WordTracker wordTracker = new WordTracker(200);
		wordTracker.add("new");
		wordTracker.add("class");
		ContentAssistant assistant = new ContentAssistant();
		assistant.setStatusLineVisible(true);
		assistant.enableColoredLabels(true);
		assistant.setStatusMessage("");
		assistant.enableAutoActivation(true);
		assistant.setEmptyMessage("Nothing Found.");
		assistant.setContentAssistProcessor(new RecentWordContentAssistProcessor(wordTracker),
				IDocument.DEFAULT_CONTENT_TYPE);
		assistant.install(sourceCodeText);

		sourceCodeText.setEditable(true);
		sourceCodeText.getTextWidget().setText(sourceCode);

		sourceCodeText.getTextWidget().addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// assistant.showPossibleCompletions();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				assistant.showPossibleCompletions();
			}
		});

		sourceCodeText.addTextListener(new ITextListener() {
			public void textChanged(TextEvent e) {
				if (isWhitespaceString(e.getText())) {
					wordTracker.add(findMostRecentWord(e.getOffset() - 1));
				}
			}
		});

		styleText(sourceCodeText.getTextWidget());
	}

	protected String findMostRecentWord(int startSearchOffset) {
		int currOffset = startSearchOffset;
		char currChar;
		String word = "";
		try {
			while (currOffset > 0
					&& !Character.isWhitespace(currChar = sourceCodeText.getDocument().getChar(currOffset))) {
				word = currChar + word;
				currOffset--;
			}
			return word;
		} catch (BadLocationException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected boolean isWhitespaceString(String string) {
		StringTokenizer tokenizer = new StringTokenizer(string);
		// if there is at least 1 token, this string is not whitespace
		return !tokenizer.hasMoreTokens();
	}

	private void styleText(StyledText styledTextControl) {
		StyleRange[] styleRanges = styledTextControl.getStyleRanges();
		for (StyleRange styleRange : styleRanges) {
			styleRange.foreground = new Color(styledTextControl.getDisplay(), 8, 8, 9);
		}
		String code = styledTextControl.getText();
		List<Token> allTokens = SourceCodeEditorTools.getInstance().getTokens(code);
		for (Token token : allTokens) {
			StyleRange styleRange = new StyleRange();
			styleRange.start = token.getTokenStartIndex();
			styleRange.length = token.getTokenEndIndex() - token.getTokenStartIndex();
			styleRange.fontStyle = SWT.BOLD;
			if (token.getTokenType() == TOKEN_TYPE.GENERIC) {
				styleRange.foreground = new Color(styledTextControl.getDisplay(), 159, 54, 54);
			} else if (token.getTokenType() == TOKEN_TYPE.STRING) {
				styleRange.foreground = new Color(styledTextControl.getDisplay(), 64, 124, 185);
			} else {
				styleRange.foreground = new Color(styledTextControl.getDisplay(), 8, 8, 9);
			}
			styledTextControl.setStyleRange(styleRange);
		}
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
