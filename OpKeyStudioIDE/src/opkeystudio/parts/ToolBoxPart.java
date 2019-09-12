 
package opkeystudio.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.swt.widgets.Composite;

public class ToolBoxPart {
	@Inject
	public ToolBoxPart() {
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
	}
	
	
	@PreDestroy
	public void preDestroy() {
		
	}
	
	
	@Focus
	public void onFocus() {
		System.out.println("ToolBox has Focus");
	}
	
	
	@Persist
	public void save() {
		
	}
	
}