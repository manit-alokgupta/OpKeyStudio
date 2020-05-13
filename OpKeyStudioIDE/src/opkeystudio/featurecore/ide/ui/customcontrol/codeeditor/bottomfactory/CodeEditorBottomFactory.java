package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.ui.ArtifactCodeView;
import opkeystudio.iconManager.OpKeyStudioIcons;

public class CodeEditorBottomFactory extends Composite {
	private Display display;

	private ArtifactCodeView parentArtifactCodeView;
	private CFLCompilationResultTable compilationResultsTable;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws SQLException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */

	@SuppressWarnings("unused")
	public CodeEditorBottomFactory(Composite parent, int style, ArtifactCodeView parentTestCaseView) {
		super(parent, style);
		setParentArtifactCodeView(parentTestCaseView);
		display = getParent().getDisplay();
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite.setLayout(new GridLayout(1, false));

		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));

		ExpandItem item = new ExpandItem(expandBar, SWT.NONE);
		item.setText("Code Editor");
		item.setHeight(350);
		item.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.BOTTOM_FACTORY_ICON));
		Group grpMenu = new Group(expandBar, SWT.NONE);
		grpMenu.setText("Menu");
		item.setControl(grpMenu);
		grpMenu.setLayout(new GridLayout(1, false));

		TabFolder tabFolder = new TabFolder(grpMenu, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setBounds(0, 0, 120, 43);
		tabFolder.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		TabItem compilationResultsTabItem = new TabItem(tabFolder, SWT.NONE);
		compilationResultsTabItem.setText("Compilation Results");
		compilationResultsTabItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.COMPILATION_ICON));

		Composite compilationResultsComposite = new Composite(tabFolder, SWT.NONE);
		compilationResultsTabItem.setControl(compilationResultsComposite);
		compilationResultsComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		compilationResultsTable = new CFLCompilationResultTable(compilationResultsComposite,
				SWT.BORDER | SWT.FULL_SELECTION, this);
		compilationResultsTable.setHeaderVisible(true);
		compilationResultsTable.setLinesVisible(true);

		expandBar.addListener(SWT.Expand, new Listener() {

			@Override
			public void handleEvent(Event event) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						parent.layout();
					}
				});
			}
		});
		expandBar.addListener(SWT.Collapse, new Listener() {

			@Override
			public void handleEvent(Event event) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						parent.layout();
					}
				});
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public CFLCompilationResultTable getCompilationResultTable() {
		return this.compilationResultsTable;
	}

	public ArtifactCodeView getParentArtifactCodeView() {
		return parentArtifactCodeView;
	}

	public void setParentArtifactCodeView(ArtifactCodeView parentArtifactCodeView) {
		this.parentArtifactCodeView = parentArtifactCodeView;
	}
}
