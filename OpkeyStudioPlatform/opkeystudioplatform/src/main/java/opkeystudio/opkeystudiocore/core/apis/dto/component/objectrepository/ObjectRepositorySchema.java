package opkeystudio.opkeystudiocore.core.apis.dto.component.objectrepository;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.generic.MetaInformation;
import opkeystudio.opkeystudiocore.core.apis.dto.component.generic.SuperSchema;

public class ObjectRepositorySchema extends SuperSchema {
	private MetaInformation metaInformation;
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
