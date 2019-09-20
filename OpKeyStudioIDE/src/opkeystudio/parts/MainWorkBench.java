 
package opkeystudio.parts;

import javax.inject.Inject;
import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.parts.compositeui.EditorView;
import opkeystudio.parts.compositeui.WorkBenchTabHolder;

public class MainWorkBench {
	@Inject
	public MainWorkBench() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		new EditorView(parent, SWT.NONE);
	}
	
	
	
	
}