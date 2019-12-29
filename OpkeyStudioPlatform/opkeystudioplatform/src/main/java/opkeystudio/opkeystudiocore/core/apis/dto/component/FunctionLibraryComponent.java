package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.ArrayList;
import java.util.List;

public class FunctionLibraryComponent extends Artifact {
	private List<ComponentInputArgument> componentInputArgument = new ArrayList<ComponentInputArgument>();
	private List<ComponentOutputArgument> componentOutputArgument = new ArrayList<ComponentOutputArgument>();

	private List<Fl_BottomFactoryInput> componentInputParameter = new ArrayList<Fl_BottomFactoryInput>();
	private List<Fl_BottomFactoryOutput> componentOutputParameter = new ArrayList<Fl_BottomFactoryOutput>();

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
