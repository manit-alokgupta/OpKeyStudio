package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.ArrayList;
import java.util.List;

public class FunctionLibraryComponent extends Artifact {
	private List<ComponentInputArgument> componentInputArguments = new ArrayList<ComponentInputArgument>();
	private List<ComponentOutputArgument> componentOutputArguments = new ArrayList<ComponentOutputArgument>();

	private List<Fl_BottomFactoryInput> componentInputParameter = new ArrayList<Fl_BottomFactoryInput>();
	private List<Fl_BottomFactoryOutput> componentOutputParameter = new ArrayList<Fl_BottomFactoryOutput>();

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
