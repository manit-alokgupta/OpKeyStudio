package opkeystudio.featurecore.ide.ui.ui.sourcecodeeditor;

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
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.widgets.TreeItem;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.sourcecodeeditorcontrol.SourceCodeTreeItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.SourceCodeEditorTools;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Token;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Token.TOKEN_TYPE;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler.TranspileObject;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler.Transpiler;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler.TranspilerUtilities;

public class SourceCodeEditor extends Composite {
	private TestCaseView testCaseView;
	private CTabFolder tabFolder;
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

		GridData gd_sourceCodeTree = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_sourceCodeTree.widthHint = 275;
		sourceCodeTree.setLayoutData(gd_sourceCodeTree);

		tabFolder = new CTabFolder(composite_16, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	private void renderTreeItemsNode(SourceCodeTreeItem treeItemNode, FileNode node) {
		for (FileNode fileNode : node.getFilesNodes()) {
			SourceCodeTreeItem scti = new SourceCodeTreeItem(treeItemNode, 0);
			scti.setFileNode(fileNode);
			scti.setText(fileNode.getFileName());
			renderTreeItemsNode(scti, fileNode);
		}
	}

	private void renderTreeItems(FileNode rootNode) {
		List<FileNode> fileNodes = rootNode.getFilesNodes();
		for (FileNode fileNode : fileNodes) {
			SourceCodeTreeItem scti = new SourceCodeTreeItem(sourceCodeTree, 0);
			scti.setFileNode(fileNode);
			scti.setText(fileNode.getFileName());
			renderTreeItemsNode(scti, fileNode);
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
				initiateJavaEditor(scti.getFileNode());
				CTabItem tabItem = new CTabItem(tabFolder, SWT.CLOSE);
				tabItem.setControl(sourceCodeText.getControl());
				tabItem.setText(item.getText());
				tabFolder.setSelection(tabItem);
			}
		});
	}

	private void initiateJavaEditor(FileNode fileNode) {
		sourceCodeText = new TextViewer(tabFolder, SWT.V_SCROLL | SWT.SCROLL_LINE);
		sourceCodeText.setDocument(new Document());
		ContentAssistData wordTracker = new ContentAssistData(200);
		wordTracker.add("new");
		wordTracker.add("class");
		ContentAssistant assistant = new ContentAssistant();
		assistant.setStatusLineVisible(true);
		assistant.enableColoredLabels(true);
		assistant.setStatusMessage("");
		assistant.enableAutoActivation(true);
		assistant.setEmptyMessage("Nothing Found.");
		assistant.setContentAssistProcessor(new ContentAssistProcessor(wordTracker), IDocument.DEFAULT_CONTENT_TYPE);
		assistant.install(sourceCodeText);

		sourceCodeText.setEditable(true);
		sourceCodeText.getTextWidget().setText("");

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

		sourceCodeText.getTextWidget().addLineStyleListener(new LineStyleListener() {
			public void lineGetStyle(LineStyleEvent e) {
				// Set the line number
				e.bulletIndex = sourceCodeText.getTextWidget().getLineAtOffset(e.lineOffset);
				StyleRange style = new StyleRange();
				style.metrics = new GlyphMetrics(0, 0,
						Integer.toString(sourceCodeText.getTextWidget().getLineCount() + 1).length() * 12);
				e.bullet = new Bullet(ST.BULLET_NUMBER, style);

			}
		});
	}

	public void transpileDatas() {
		try {
			List<GlobalVariable> globalVariables = new GlobalVariableApi().getAllGlobalVariables();
			List<FlowStep> flowSteps = getTestCaseView().getFlowStepTable().getFlowStepsData();
			Artifact artifact = getTestCaseView().getArtifact();
			TranspileObject transpileObject = new TranspileObject();
			transpileObject.setArtifact(artifact);
			transpileObject.setGlobalVaribales(globalVariables);
			transpileObject.setFlowSteps(flowSteps);

			FileNode rootNode = new Transpiler().transpileDatas(transpileObject);
			renderTreeItems(rootNode);
		} catch (SQLException | IOException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public TestCaseView getTestCaseView() {
		return testCaseView;
	}

	public void setTestCaseView(TestCaseView testCaseView) {
		this.testCaseView = testCaseView;
	}

}
