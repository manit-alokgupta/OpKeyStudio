package opkeystudio.featurecore.ide.ui.ui.sourcecodeeditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.sourcecodeeditorcontrol.SourceCodeTree;
import opkeystudio.featurecore.ide.ui.customcontrol.sourcecodeeditorcontrol.SourceCodeTreeItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.execution.ExecutionEngine;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompilerTools;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode.FILE_TYPE;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.IntellisenseTools;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.SourceCodeEditorTools;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Token;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Token.TOKEN_TYPE;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler.TranspileObject;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler.Transpiler;
import opkeystudio.opkeystudiocore.core.utils.ContentAssistData;

public class SourceCodeEditor extends Composite {
	private TestCaseView testCaseView;
	private CTabFolder tabFolder;
	private SourceCodeTree sourceCodeTree;
	private ISourceViewer sourceCodeText;
	private ToolItem runExecutionButton;
	private ToolItem stopExecutionButton;
	private ToolItem compileButton;
	private ToolItem saveAllButton;
	private ToolItem refreshButton;

	private FileNode mainArtifactSourceCodeNode;

	public SourceCodeEditor(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		setTestCaseView(testCaseView);
		init();
	}

	private void init() {
		this.setLayout(new GridLayout(1, false));
		ToolBar sourceCodeToolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		sourceCodeToolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		runExecutionButton = new ToolItem(sourceCodeToolBar, SWT.NONE);
		runExecutionButton.setText("Run");
		ToolItem seperator1 = new ToolItem(sourceCodeToolBar, SWT.SEPARATOR);
		stopExecutionButton = new ToolItem(sourceCodeToolBar, SWT.NONE);
		stopExecutionButton.setText("Stop");
		stopExecutionButton.setEnabled(false);
		ToolItem seperator11 = new ToolItem(sourceCodeToolBar, SWT.SEPARATOR);
		compileButton = new ToolItem(sourceCodeToolBar, SWT.NONE);
		compileButton.setText("Compile");
		ToolItem seperator2 = new ToolItem(sourceCodeToolBar, SWT.SEPARATOR);
		saveAllButton = new ToolItem(sourceCodeToolBar, SWT.NONE);
		saveAllButton.setText("Save All");
		ToolItem seperator3 = new ToolItem(sourceCodeToolBar, SWT.SEPARATOR);
		refreshButton = new ToolItem(sourceCodeToolBar, SWT.NONE);
		refreshButton.setText("Refresh");

		Composite composite_16 = new Composite(this, SWT.NONE);
		composite_16.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_16.setLayout(new GridLayout(2, false));

		sourceCodeTree = new SourceCodeTree(composite_16, SWT.BORDER);
		sourceCodeTree.setHeaderVisible(true);
		sourceCodeTree.setLinesVisible(false);

		GridData gd_sourceCodeTree = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_sourceCodeTree.widthHint = 275;
		sourceCodeTree.setLayoutData(gd_sourceCodeTree);

		tabFolder = new CTabFolder(composite_16, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		runExecutionButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				new CompilerTools().compile(getMainArtifactSourceCodeNode());
				ExecutionEngine executionEngine = new ExecutionEngine();
				executionEngine.executeRun(getMainArtifactSourceCodeNode());
				stopExecutionButton.setEnabled(true);
				runExecutionButton.setEnabled(false);
				Thread t1 = new Thread(new Runnable() {

					@Override
					public void run() {
						while (true) {
							System.out.println("Execution Completed " + executionEngine.isExecutionCompleted());
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (executionEngine.isExecutionCompleted()) {
								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										stopExecutionButton.setEnabled(false);
										runExecutionButton.setEnabled(true);
									}
								});
								break;
							}
						}
					}
				});
				t1.start();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		stopExecutionButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Process executionProcess = ExecutionEngine.getExecutionProcess();
				if (executionProcess != null) {
					executionProcess.destroy();
				}
				stopExecutionButton.setEnabled(false);
				runExecutionButton.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				transpileDatas();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		compileButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				new CompilerTools().compile(getMainArtifactSourceCodeNode());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		saveAllButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				List<FileNode> fileNodes = sourceCodeTree.getAllFileNodes();
				for (FileNode fnode : fileNodes) {
					if (fnode.isModified()) {
						try {
							new CompilerTools().saveSourceCodeFile(fnode);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				toggleSaveAllButton(false);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
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
				tabItem.setImage(scti.getImage());
				tabItem.setControl(sourceCodeText.getTextWidget());
				tabItem.setText(item.getText());
				tabFolder.setSelection(tabItem);
			}
		});
		toggleSaveAllButton(false);
	}

	public void toggleRunButton(boolean status) {
		this.runExecutionButton.setEnabled(status);
	}

	public void toggleCompileButton(boolean status) {
		this.compileButton.setEnabled(status);
	}

	public void toggleSaveAllButton(boolean status) {
		this.saveAllButton.setEnabled(status);
	}

	public void toggleRefreshButton(boolean status) {
		this.refreshButton.setEnabled(status);
	}

	private void renderTreeItemsNode(SourceCodeTreeItem treeItemNode, FileNode node) {
		for (FileNode fileNode : node.getFilesNodes()) {
			SourceCodeTreeItem scti = new SourceCodeTreeItem(treeItemNode, 0);
			scti.setFileNode(fileNode);
			scti.setText(fileNode.getFileName());
			if (fileNode.getFileType() == FILE_TYPE.FOLDER || fileNode.getFileType() == FILE_TYPE.PROJECTFOLDER
					|| fileNode.getFileType() == FILE_TYPE.PACKAGEFOLDER) {
				scti.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/folder.png"));
			}
			if (fileNode.getFileType() == FILE_TYPE.JAVASOURCEFILE) {
				scti.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/javafile.png"));
			}
			renderTreeItemsNode(scti, fileNode);
		}
	}

	private void expandAll(SourceCodeTreeItem treeItem) {
		treeItem.setExpanded(true);
		TreeItem items[] = treeItem.getItems();
		for (TreeItem item : items) {
			item.setExpanded(true);
			expandAll((SourceCodeTreeItem) item);
		}
		this.setRedraw(true);
	}

	private void renderTreeItems(FileNode rootNode) {
		sourceCodeTree.removeAll();
		List<FileNode> fileNodes = rootNode.getFilesNodes();
		for (FileNode fileNode : fileNodes) {
			SourceCodeTreeItem scti = new SourceCodeTreeItem(sourceCodeTree, 0);
			scti.setFileNode(fileNode);
			scti.setText(fileNode.getFileName());
			if (fileNode.getFileType() == FILE_TYPE.FOLDER || fileNode.getFileType() == FILE_TYPE.PROJECTFOLDER
					|| fileNode.getFileType() == FILE_TYPE.PACKAGEFOLDER) {
				scti.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/folder.png"));
			}
			renderTreeItemsNode(scti, fileNode);
			expandAll(scti);
		}
	}

	private String readSourceCode(File file) {
		BufferedReader br;
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				sb.append(line + System.lineSeparator());
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	private void initiateJavaEditor(FileNode fileNode) {
		List<CompileError> compileErrors = fileNode.getCompileErrors();
		for (CompileError ce : compileErrors) {
			System.out.println(ce.getMessage());
		}
		sourceCodeText = new SourceViewer(tabFolder, null, SWT.V_SCROLL | SWT.SCROLL_LINE);
		sourceCodeText.setDocument(new Document());
		ContentAssistData contentAssistData = ContentAssistData.getInstance();
		ContentAssistant assistant = new ContentAssistant();
		assistant.setStatusLineVisible(true);
		assistant.enableColoredLabels(true);
		assistant.setStatusMessage("");
		assistant.enableAutoActivation(true);
		assistant.setEmptyMessage("Nothing Found.");
		assistant.setContentAssistProcessor(new ContentAssistProcessor(contentAssistData),
				IDocument.DEFAULT_CONTENT_TYPE);
		assistant.install(sourceCodeText);

		sourceCodeText.setEditable(true);

		String sourceCode = readSourceCode(fileNode.getFile());
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
				FileNode fileNode = sourceCodeTree.getSelectedFileNode();
				fileNode.setData(sourceCodeText.getTextWidget().getText());
				fileNode.setModified(true);
				toggleSaveAllButton(true);
				if (isWhitespaceString(e.getText())) {
					// contentAssistData.add(findMostRecentWord(e.getOffset() - 1));
				}
			}
		});
		/*
		 * sourceCodeText.getTextWidget().addLineStyleListener(new LineStyleListener() {
		 * public void lineGetStyle(LineStyleEvent e) { System.out.println("Running " +
		 * e.lineText); // Set the line number if (sourceCodeText == null) { return; }
		 * if (sourceCodeText.getTextWidget() == null) { return; } try { e.bulletIndex =
		 * sourceCodeText.getTextWidget().getLineAtOffset(e.lineOffset); } catch
		 * (Exception exception) { exception.printStackTrace(); } StyleRange style = new
		 * StyleRange(); style.metrics = new GlyphMetrics(0, 0,
		 * Integer.toString(sourceCodeText.getTextWidget().getLineCount() + 1).length()
		 * * 12); //e.bullet = new Bullet(ST.BULLET_NUMBER, style); } });
		 */
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
			setMainArtifactSourceCodeNode(rootNode);
			// Add code of rereading the structure
			renderTreeItems(rootNode);
			new CompilerTools().compile(rootNode);
			new IntellisenseTools().executeIntelliSense(rootNode);
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

	public FileNode getMainArtifactSourceCodeNode() {
		return mainArtifactSourceCodeNode;
	}

	public void setMainArtifactSourceCodeNode(FileNode mainArtifactSourceCodeNode) {
		this.mainArtifactSourceCodeNode = mainArtifactSourceCodeNode;
	}

}
