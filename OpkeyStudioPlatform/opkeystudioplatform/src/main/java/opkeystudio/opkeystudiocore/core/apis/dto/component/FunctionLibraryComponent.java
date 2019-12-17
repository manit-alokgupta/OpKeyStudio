package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.List;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class FunctionLibraryComponent extends Artifact {
	@DBField
	private List<ComponentInputArgument> componentInputArgument;
	@DBField
	private List<ComponentOutputArgument> componentOutputArgument;

	@DBField
	private List<Fl_BottomFactoryInput> componentInputParameter;
	@DBField
	private List<Fl_BottomFactoryOutput> componentOutputParameter;

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

	public List<Fl_BottomFactoryInput> getComponentInputParameter() {
		return componentInputParameter;
	}

	public void setComponentInputParameter(List<Fl_BottomFactoryInput> componentInputParameter) {
		this.componentInputParameter = componentInputParameter;
	}

	public List<Fl_BottomFactoryOutput> getComponentOutputParameter() {
		return componentOutputParameter;
	}

	public void setComponentOutputParameter(List<Fl_BottomFactoryOutput> componentOutputParameter) {
		this.componentOutputParameter = componentOutputParameter;
	}

}
