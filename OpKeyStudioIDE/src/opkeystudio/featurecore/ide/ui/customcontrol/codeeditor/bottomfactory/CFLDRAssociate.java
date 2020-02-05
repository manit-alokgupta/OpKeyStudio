package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.core.utils.DtoToCodeConverter;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.EditorTools;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;

public class CFLDRAssociate extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;

	public CFLDRAssociate(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		super(parent, style);
		this.setParentBottomFactoryUI(parentBottomFactoryUI);
		init();
	}

	private void init() {

		String[] headers = new String[] { "#", "Data Repository" };
		for (String header : headers) {
			TableColumn column = new TableColumn(this, 0);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < headers.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Table table_0 = (Table) e.getSource();
				int count = 0;
				for (TableColumn column : table_0.getColumns()) {
					if (count == 0) {
						column.setWidth(100);
					} else {
						column.setWidth(table_0.getBounds().width - 100);
					}
					count++;
				}
			}
		});
		renderDRNodes();
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		editor.horizontalAlignment = SWT.CENTER;
		editor.verticalAlignment = SWT.CENTER;
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		return editor;
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private void addTableEditor(CustomTableItem item) {
		String dataLibraryPath = getParentBottomFactoryUI().getParentCodedFunctionView()
				.getArtifactOpkeyDataLibraryPath();
		List<File> files = new EditorTools(getParentBottomFactoryUI().getParentCodedFunctionView())
				.getAllFiles(new File(dataLibraryPath), ".class");
		boolean flag = false;
		Artifact artifact = (Artifact) item.getControlData();
		String artifactVariableName = artifact.getArtifactVariableName() + ".class";
		for (File file : files) {
			System.out.println(file.getName() + "     " + artifactVariableName);
			if (file.getName().equals(artifactVariableName)) {
				flag = true;
				break;
			}
		}
		TableEditor editor1 = getTableEditor();
		CustomButton associateOR = new CustomButton(this, SWT.CHECK | SWT.CENTER);
		associateOR.setSelection(flag);
		associateOR.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setSelection(item);
				Artifact artifact = (Artifact) item.getControlData();
				if (associateOR.getSelection() == true) {
					List<DRColumnAttributes> allDRColumns = GlobalLoader.getInstance()
							.getAllDRColumns(artifact.getId());
					for (DRColumnAttributes drColumn : allDRColumns) {
						List<DRCellAttributes> drCells = GlobalLoader.getInstance()
								.getDRColumnCells(drColumn.getColumn_id());
						drColumn.setDrCellAttributes(drCells);
					}

					JavaClassSource classSource = new DtoToCodeConverter().getJavaClassDRObjects(artifact,
							allDRColumns);
					String dataLibraryPath = getParentBottomFactoryUI().getParentCodedFunctionView()
							.getArtifactOpkeyDataLibraryPath();
					File file = new File(
							dataLibraryPath + File.separator + artifact.getArtifactVariableName() + ".java");
					BufferedWriter bw;
					try {
						bw = new BufferedWriter(new FileWriter(file));
						bw.write(classSource.toString());
						bw.flush();
						bw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					List<CompileError> errors = getParentBottomFactoryUI().getParentCodedFunctionView().getJavaEditor()
							.compileAllOpKeyLibs();

					System.out.println("Errors Found " + errors.size());
					getParentBottomFactoryUI().getParentCodedFunctionView().refreshIntellisense();
				}
				if (associateOR.getSelection() == false) {
					File file1 = new File(
							dataLibraryPath + File.separator + artifact.getArtifactVariableName() + ".class");
					File file2 = new File(
							dataLibraryPath + File.separator + artifact.getArtifactVariableName() + ".java");
					if (file1.exists()) {
						try {
							Files.delete(file1.toPath());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					if (file2.exists()) {
						try {
							Files.delete(file2.toPath());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					List<CompileError> errors = getParentBottomFactoryUI().getParentCodedFunctionView().getJavaEditor()
							.compileAllOpKeyLibs();

					System.out.println("Errors Found " + errors.size());
					getParentBottomFactoryUI().getParentCodedFunctionView().refreshIntellisense();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		editor1.setEditor(associateOR, item, 0);
		allTableEditors.add(editor1.getEditor());
	}

	private void disposeAllTableEditors() {
		for (Control editor : allTableEditors) {
			editor.dispose();
		}
	}

	public void renderDRNodes() {
		disposeAllTableEditors();
		this.removeAll();
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifactByType("DataRepository");
		for (Artifact artifact : artifacts) {
			CustomTableItem item = new CustomTableItem(this, 0);
			item.setText(new String[] { "", artifact.getArtifactVariableName() });
			item.setControlData(artifact);
			addTableEditor(item);
		}
	}

	public CustomTableItem getSelectedTableItem() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		return (CustomTableItem) this.getSelection()[0];
	}

	public CodedFunctionBottomFactoryUI getParentBottomFactoryUI() {
		return parentBottomFactoryUI;
	}

	public void setParentBottomFactoryUI(CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		this.parentBottomFactoryUI = parentBottomFactoryUI;
	}

}
