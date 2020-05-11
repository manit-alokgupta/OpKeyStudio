package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.ArrayList;
import java.util.List;

public class FunctionLibraryComponent extends Artifact {

	private List<ComponentInputArgument> componentInputArguments = new ArrayList<ComponentInputArgument>();
	private List<ComponentOutputArgument> componentOutputArguments = new ArrayList<ComponentOutputArgument>();
	
	public List<ComponentInputArgument> getComponentInputArguments() {
		return componentInputArguments;
	}

	public void setComponentInputArguments(List<ComponentInputArgument> componentInputArguments) {
		this.componentInputArguments = componentInputArguments;
	}

	public List<ComponentOutputArgument> getComponentOutputArguments() {
		return componentOutputArguments;
	}

	public void setComponentOutputArguments(List<ComponentOutputArgument> componentOutputArguments) {
		this.componentOutputArguments = componentOutputArguments;
	}

}
