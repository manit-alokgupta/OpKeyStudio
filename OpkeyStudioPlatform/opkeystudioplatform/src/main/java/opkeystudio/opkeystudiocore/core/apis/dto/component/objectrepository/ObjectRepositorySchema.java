package opkeystudio.opkeystudiocore.core.apis.dto.component.objectrepository;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import opkeystudio.opkeystudiocore.core.apis.dto.component.generic.MetaInformation;
import opkeystudio.opkeystudiocore.core.apis.dto.component.generic.SuperSchema;

public class ObjectRepositorySchema extends SuperSchema {
	@JsonProperty("MetaInformation")
	private MetaInformation metaInformation;
	
	@JsonProperty("ORObjects")
	private List<ORObject> orObjects = new ArrayList<>();

	public MetaInformation getMetaInformation() {
		return metaInformation;
	}

	public void setMetaInformation(MetaInformation metaInformation) {
		this.metaInformation = metaInformation;
	}

	public List<ORObject> getOrObjects() {
		return orObjects;
	}

	public void setOrObjects(List<ORObject> orObjects) {
		this.orObjects = orObjects;
	}
}
