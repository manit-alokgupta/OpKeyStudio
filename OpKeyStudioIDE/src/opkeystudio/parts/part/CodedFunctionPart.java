package opkeystudio.parts.part;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.featurecore.ide.ui.ui.CodedFunctionView;

public class CodedFunctionPart {
	@Inject
	MPart projectExplorerPart;
	private CodedFunctionView codedFunctionView;

	@PostConstruct
	public void postConstruct(Composite parent) throws IOException {
		codedFunctionView = new CodedFunctionView(parent, 0);
	}

	@PreDestroy
	public void preDestroy() {

	}

	@Focus
	public void onFocus() {
		System.out.println("Console Window Focused");
	}

	@Persist
	public void save() {
		System.out.println("Console Window Save");

	}

}
