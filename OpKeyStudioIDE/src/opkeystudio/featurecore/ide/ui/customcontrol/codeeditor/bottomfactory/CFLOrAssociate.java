package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.core.utils.DtoToCodeConverter;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;

public class CFLOrAssociate extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;

	public CFLOrAssociate(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactoryUI) {
		super(parent, style);
		this.setParentBottomFactoryUI(parentBottomFactoryUI);
		init();
	}

	private void init() {

		String[] headers = new String[] { "#", "Object Repository" };
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

		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomTableItem selectedTableItem = getSelectedTableItem();
				Artifact artifact = (Artifact) selectedTableItem.getControlData();
				List<ORObject> allOrObjects = GlobalLoader.getInstance().getAllOrObjects(artifact.getId());
				for (ORObject object : allOrObjects) {
					List<ObjectAttributeProperty> attributeProps = GlobalLoader.getInstance()
							.getORObjectAttributeProperty(object.getObject_id());
					object.setObjectAttributesProperty(attributeProps);
				}

				JavaClassSource classSource = new DtoToCodeConverter().getJavaClassORObjects(artifact, allOrObjects);
				String dataLibraryPath = getParentBottomFactoryUI().getParentCodedFunctionView()
						.getArtifactOpkeyDataLibraryPath();
				File file = new File(dataLibraryPath + File.separator + artifact.getArtifactVariableName() + ".java");
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
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		renderORNodes();
	}

	private void renderORNodes() {
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifactByType("ObjectRepository");
		for (Artifact artifact : artifacts) {
			CustomTableItem item = new CustomTableItem(this, 0);
			item.setText(new String[] { "", artifact.getArtifactVariableName() });
			item.setControlData(artifact);
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
