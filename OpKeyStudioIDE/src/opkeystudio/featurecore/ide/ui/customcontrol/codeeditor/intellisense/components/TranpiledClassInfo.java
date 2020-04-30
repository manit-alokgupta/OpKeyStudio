package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.components;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public class TranpiledClassInfo {
	private JavaClassSource classSource;

	public TranpiledClassInfo(JavaClassSource classSource) {
		this.setClassSource(classSource);
	}

	public JavaClassSource getClassSource() {
		return classSource;
	}

	public void setClassSource(JavaClassSource classSource) {
		this.classSource = classSource;
	}
}
