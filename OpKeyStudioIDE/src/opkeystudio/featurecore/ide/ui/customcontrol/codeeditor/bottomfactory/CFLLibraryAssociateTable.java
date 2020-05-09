package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLibraryMap;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.MainFileStoreDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class CFLLibraryAssociateTable extends CustomTable {
	private CodedFunctionBottomFactoryUI parentBottomFactoryUI;

	public CFLLibraryAssociateTable(Composite parent, int style, CodedFunctionBottomFactoryUI parentBottomFactory) {
		super(parent, style);
		this.setParentBottomFactoryUI(parentBottomFactory);
		init();
	}

	private void init() {

		String[] headers = new String[] { "FileName", "Type" };
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
						column.setWidth((table_0.getBounds().width - 100) / 2);
					}
					count++;
				}
			}
		});
		renderAssociatedLibraries();
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private void disposeAllTableEditors() {
		for (Control editor : allTableEditors) {
			editor.dispose();
		}
	}

	public void renderAssociatedLibraries() {
		disposeAllTableEditors();
		this.removeAll();
		Artifact artifact = getParentBottomFactoryUI().getParentArtifactCodeView().getArtifact();
		List<String> libraryMapsFID = new ArrayList<String>();
		List<MainFileStoreDTO> filteredMainFileStoreDtos = new ArrayList<MainFileStoreDTO>();
		List<CFLibraryMap> libraryMaps = GlobalLoader.getInstance().getAllLibraryMaps();
		for (CFLibraryMap libraryMap : libraryMaps) {
			if (libraryMap.getComponent_id().equals(artifact.getId())) {
				libraryMapsFID.add(libraryMap.getF_id());
				List<MainFileStoreDTO> mainFileStoreDTOS = GlobalLoader.getInstance().getAllMainFileStoreDtos();
				for (MainFileStoreDTO mainFileStoreDto : mainFileStoreDTOS) {
					if (mainFileStoreDto.getF_id().equals(libraryMap.getF_id())) {
						filteredMainFileStoreDtos.add(mainFileStoreDto);
					}
				}
			}
		}
		String path = Utilities.getInstance().getProjectJavaLibrrayFolder();
		for (MainFileStoreDTO fileStoreDto : filteredMainFileStoreDtos) {
			if (fileStoreDto.getFilename().contains("opkeylibs")) {
				continue;
			}
			if (fileStoreDto.getFilename().contains("opkeyeruntime")) {
				continue;
			}
			String fid = fileStoreDto.getF_id();
			byte[] bytes = new CodedFunctionApi().getLibraryFileData(fid);
			File file = new File(path + File.separator + fileStoreDto.getFilename() + fileStoreDto.getExtension());
			try {
				FileOutputStream writer = new FileOutputStream(file);
				writer.write(bytes);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			CustomTableItem cti = new CustomTableItem(this, 0);
			cti.setText(new String[] { fileStoreDto.getFilename(), fileStoreDto.getExtension() });
			cti.setControlData(fileStoreDto);
			cti.setImage(0, ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ASSOCIATE_LIBRARY_ICON));
		}
		selectDefaultRow();
	}

	public CustomTableItem getSelectedLibrary() {
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
