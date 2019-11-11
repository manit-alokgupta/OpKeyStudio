package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.List;

public class FunctionLibraryComponent extends Artifact {
	private List<ComponentInputArgument> componentInputArgument;
	private List<ComponentOutputArgument> componentOutputArgument;

	public List<ComponentInputArgument> getComponentInputArgument() {
		return componentInputArgument;
	}

	public void setComponentInputArgument(List<ComponentInputArgument> componentInputArgument) {
		this.componentInputArgument = componentInputArgument;
	}

	public List<ComponentOutputArgument> getComponentOutputArgument() {
		return componentOutputArgument;
	}

	public void setComponentOutputArgument(List<ComponentOutputArgument> componentOutputArgument) {
		this.componentOutputArgument = componentOutputArgument;
	}
}
