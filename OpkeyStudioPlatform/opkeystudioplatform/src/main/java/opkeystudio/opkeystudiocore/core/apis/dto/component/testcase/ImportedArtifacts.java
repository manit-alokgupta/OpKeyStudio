package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImportedArtifacts {
	@JsonProperty("ImportedSectionDTO")
	private List<ImportSectionDTO> importedSectionDTO = new ArrayList<>();

	public List<ImportSectionDTO> getImportedSectionDTO() {
		return importedSectionDTO;
	}

	public void setImportedSectionDTO(List<ImportSectionDTO> importedSectionDTO) {
		this.importedSectionDTO = importedSectionDTO;
	}

	public void addImportSectionDTO(ImportSectionDTO importSectionDTO) {
		this.importedSectionDTO.add(importSectionDTO);
	}
}
