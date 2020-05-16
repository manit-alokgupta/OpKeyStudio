package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.text.JTextComponent;

import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;

public class JavaCompletionProvider extends DefaultCompletionProvider {
	private boolean containsIntellisenseData = false;

	@Override
	public String getAlreadyEnteredText(JTextComponent arg0) {
		return super.getAlreadyEnteredText(arg0);
	}

	@Override
	public List<Completion> getCompletionsAt(JTextComponent arg0, Point arg1) {
		return super.getCompletionsAt(arg0, arg1);
	}

	@Override
	public List<ParameterizedCompletion> getParameterizedCompletions(JTextComponent arg0) {
		return super.getParameterizedCompletions(arg0);
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected boolean isValidChar(char ch) {
		return super.isValidChar(ch);
	}

	@Override
	public void loadFromXML(File file) throws IOException {
		super.loadFromXML(file);
	}

	@Override
	public void loadFromXML(InputStream arg0, ClassLoader arg1) throws IOException {
		super.loadFromXML(arg0, arg1);
	}

	@Override
	public void loadFromXML(InputStream in) throws IOException {
		super.loadFromXML(in);
	}

	@Override
	public void loadFromXML(String arg0) throws IOException {
		super.loadFromXML(arg0);
	}

	@Override
	public void addCompletion(Completion c) {
		super.addCompletion(c);
	}

	@Override
	public void addCompletions(List<Completion> arg0) {
		super.addCompletions(arg0);
	}

	@Override
	protected void addWordCompletions(String[] arg0) {
		super.addWordCompletions(arg0);
	}

	@Override
	protected void checkProviderAndAdd(Completion c) {
		super.checkProviderAndAdd(c);
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public List<Completion> getCompletionByInputText(String inputText) {
		return super.getCompletionByInputText(inputText);
	}

	@Override
	protected List<Completion> getCompletionsImpl(JTextComponent arg0) {
		return super.getCompletionsImpl(arg0);
	}

	@Override
	public boolean removeCompletion(Completion c) {
		return super.removeCompletion(c);
	}

	public boolean isContainsIntellisenseData() {
		return containsIntellisenseData;
	}

	public void setContainsIntellisenseData(boolean containsIntellisenseData) {
		this.containsIntellisenseData = containsIntellisenseData;
	}

}
