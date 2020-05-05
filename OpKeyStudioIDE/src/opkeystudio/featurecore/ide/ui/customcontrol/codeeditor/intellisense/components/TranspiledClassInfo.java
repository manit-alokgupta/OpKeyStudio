package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.components;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dto.intellisense.ClassIntellisenseDTO;

public class TranspiledClassInfo {
	private JavaClassSource classSource;
	private Class reflectionClassObject;

	private ClassIntellisenseDTO classIntellisenseDTO;

	public TranspiledClassInfo(JavaClassSource classSource) {
		this.setClassSource(classSource);
	}

	public TranspiledClassInfo(Class classSource) {
		this.setReflectionClassObject(classSource);
	}

	public TranspiledClassInfo(ClassIntellisenseDTO classIntellisenseDTO) {
		this.setClassIntellisenseDTO(classIntellisenseDTO);
	}

	public JavaClassSource getClassSource() {
		return classSource;
	}

	public void setClassSource(JavaClassSource classSource) {
		this.classSource = classSource;
	}

	public Class getReflectionClassObject() {
		return reflectionClassObject;
	}

	public void setReflectionClassObject(Class reflectionClassObject) {
		this.reflectionClassObject = reflectionClassObject;
	}

	public ClassIntellisenseDTO getClassIntellisenseDTO() {
		return classIntellisenseDTO;
	}

	public void setClassIntellisenseDTO(ClassIntellisenseDTO classIntellisenseDTO) {
		this.classIntellisenseDTO = classIntellisenseDTO;
	}
}
