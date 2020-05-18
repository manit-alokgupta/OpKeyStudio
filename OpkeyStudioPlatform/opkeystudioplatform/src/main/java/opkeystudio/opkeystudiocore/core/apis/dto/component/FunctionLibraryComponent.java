package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.ArrayList;
import java.util.List;

public class FunctionLibraryComponent extends ArtifactDTO {

	private List<ComponentInputParameter> componentInputArguments = new ArrayList<ComponentInputParameter>();
	private List<ComponentOutputParameter> componentOutputArguments = new ArrayList<ComponentOutputParameter>();
	
	public List<ComponentInputParameter> getComponentInputArguments() {
		return componentInputArguments;
	}

	public void setComponentInputArguments(List<ComponentInputParameter> componentInputArguments) {
		this.componentInputArguments = componentInputArguments;
	}

	public List<ComponentOutputParameter> getComponentOutputArguments() {
		return componentOutputArguments;
	}

	public void setComponentOutputArguments(List<ComponentOutputParameter> componentOutputArguments) {
		this.componentOutputArguments = componentOutputArguments;
	}

}
