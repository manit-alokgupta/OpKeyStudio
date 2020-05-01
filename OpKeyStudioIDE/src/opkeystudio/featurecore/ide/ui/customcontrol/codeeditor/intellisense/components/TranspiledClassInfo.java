package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.components;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public class TranspiledClassInfo {
	private JavaClassSource classSource;
	private Class reflectionClassObject;

	public TranspiledClassInfo(JavaClassSource classSource) {
		this.setClassSource(classSource);
	}

	public TranspiledClassInfo(Class classSource) {
		this.setReflectionClassObject(classSource);
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
}
