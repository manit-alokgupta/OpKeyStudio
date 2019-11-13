package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.List;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class FunctionLibraryComponent extends Artifact {
	@DBField
	private List<ComponentInputArgument> componentInputArgument;
	@DBField
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
