package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.awt.Color;

import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.AutoCompletionEvent.Type;
import org.fife.ui.autocomplete.AutoCompletionListener;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ExternalURLHandler;

public class JavaAutoCompletion extends AutoCompletion {

	public JavaAutoCompletion(CompletionProvider provider) {
		super(provider);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addAutoCompletionListener(AutoCompletionListener l) {
		// TODO Auto-generated method stub
		super.addAutoCompletionListener(l);
	}

	@Override
	protected Action createAutoCompleteAction() {
		// TODO Auto-generated method stub
		return super.createAutoCompleteAction();
	}

	@Override
	public void doCompletion() {
		// TODO Auto-generated method stub
		super.doCompletion();
	}

	@Override
	protected void fireAutoCompletionEvent(Type arg0) {
		// TODO Auto-generated method stub
		super.fireAutoCompletionEvent(arg0);
	}

	@Override
	public int getAutoActivationDelay() {
		// TODO Auto-generated method stub
		return super.getAutoActivationDelay();
	}

	@Override
	public boolean getAutoCompleteSingleChoices() {
		// TODO Auto-generated method stub
		return super.getAutoCompleteSingleChoices();
	}

	@Override
	public CompletionProvider getCompletionProvider() {
		// TODO Auto-generated method stub
		return super.getCompletionProvider();
	}

	@Override
	public Color getDescWindowColor() {
		// TODO Auto-generated method stub
		return super.getDescWindowColor();
	}

	@Override
	public ExternalURLHandler getExternalURLHandler() {
		// TODO Auto-generated method stub
		return super.getExternalURLHandler();
	}

	@Override
	public ListCellRenderer getListCellRenderer() {
		// TODO Auto-generated method stub
		return super.getListCellRenderer();
	}

	@Override
	public ListCellRenderer<Object> getParamChoicesRenderer() {
		// TODO Auto-generated method stub
		return super.getParamChoicesRenderer();
	}

	@Override
	protected String getReplacementText(Completion c, Document doc, int start, int len) {
		// TODO Auto-generated method stub
		return super.getReplacementText(c, doc, start, len);
	}

	@Override
	public boolean getShowDescWindow() {
		// TODO Auto-generated method stub
		return super.getShowDescWindow();
	}

	@Override
	public JTextComponent getTextComponent() {
		// TODO Auto-generated method stub
		return super.getTextComponent();
	}

	@Override
	public KeyStroke getTriggerKey() {
		// TODO Auto-generated method stub
		return super.getTriggerKey();
	}

	@Override
	public boolean hideChildWindows() {
		// TODO Auto-generated method stub
		return super.hideChildWindows();
	}

	@Override
	protected boolean hidePopupWindow() {
		// TODO Auto-generated method stub
		return super.hidePopupWindow();
	}

	@Override
	public void install(JTextComponent arg0) {
		// TODO Auto-generated method stub
		super.install(arg0);
	}

	@Override
	public boolean isAutoActivationEnabled() {
		// TODO Auto-generated method stub
		return super.isAutoActivationEnabled();
	}

	@Override
	public boolean isAutoCompleteEnabled() {
		// TODO Auto-generated method stub
		return super.isAutoCompleteEnabled();
	}

	@Override
	protected boolean isHideOnCompletionProviderChange() {
		// TODO Auto-generated method stub
		return super.isHideOnCompletionProviderChange();
	}

	@Override
	protected boolean isHideOnNoText() {
		// TODO Auto-generated method stub
		return super.isHideOnNoText();
	}

	@Override
	public boolean isParameterAssistanceEnabled() {
		// TODO Auto-generated method stub
		return super.isParameterAssistanceEnabled();
	}

	@Override
	public boolean isPopupVisible() {
		// TODO Auto-generated method stub
		return super.isPopupVisible();
	}

	@Override
	protected int refreshPopupWindow() {
		// TODO Auto-generated method stub
		return super.refreshPopupWindow();
	}

	@Override
	public void removeAutoCompletionListener(AutoCompletionListener l) {
		// TODO Auto-generated method stub
		super.removeAutoCompletionListener(l);
	}

	@Override
	public void setAutoActivationDelay(int ms) {
		// TODO Auto-generated method stub
		super.setAutoActivationDelay(ms);
	}

	@Override
	public void setAutoActivationEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		super.setAutoActivationEnabled(enabled);
	}

	@Override
	public void setAutoCompleteEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		super.setAutoCompleteEnabled(enabled);
	}

	@Override
	public void setAutoCompleteSingleChoices(boolean autoComplete) {
		// TODO Auto-generated method stub
		super.setAutoCompleteSingleChoices(autoComplete);
	}

	@Override
	public void setChoicesWindowSize(int w, int h) {
		// TODO Auto-generated method stub
		super.setChoicesWindowSize(w, h);
	}

	@Override
	public void setCompletionProvider(CompletionProvider provider) {
		// TODO Auto-generated method stub
		super.setCompletionProvider(provider);
	}

	@Override
	public void setDescriptionWindowColor(Color c) {
		// TODO Auto-generated method stub
		super.setDescriptionWindowColor(c);
	}

	@Override
	public void setDescriptionWindowSize(int w, int h) {
		// TODO Auto-generated method stub
		super.setDescriptionWindowSize(w, h);
	}

	@Override
	public void setExternalURLHandler(ExternalURLHandler handler) {
		// TODO Auto-generated method stub
		super.setExternalURLHandler(handler);
	}

	@Override
	protected void setHideOnCompletionProviderChange(boolean hideOnCompletionProviderChange) {
		// TODO Auto-generated method stub
		super.setHideOnCompletionProviderChange(hideOnCompletionProviderChange);
	}

	@Override
	protected void setHideOnNoText(boolean hideOnNoText) {
		// TODO Auto-generated method stub
		super.setHideOnNoText(hideOnNoText);
	}

	@Override
	public void setListCellRenderer(ListCellRenderer<Object> renderer) {
		// TODO Auto-generated method stub
		super.setListCellRenderer(renderer);
	}

	@Override
	public void setParamChoicesRenderer(ListCellRenderer<Object> r) {
		// TODO Auto-generated method stub
		super.setParamChoicesRenderer(r);
	}

	@Override
	public void setParameterAssistanceEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		super.setParameterAssistanceEnabled(enabled);
	}

	@Override
	protected void setPopupVisible(boolean visible) {
		// TODO Auto-generated method stub
		super.setPopupVisible(visible);
	}

	@Override
	public void setShowDescWindow(boolean show) {
		// TODO Auto-generated method stub
		super.setShowDescWindow(show);
	}

	@Override
	public void setTriggerKey(KeyStroke ks) {
		// TODO Auto-generated method stub
		super.setTriggerKey(ks);
	}

	@Override
	public void uninstall() {
		// TODO Auto-generated method stub
		super.uninstall();
	}

	@Override
	public void insertCompletion(Completion c, boolean parametrizedCompletion) {
		System.out.println("Inserting " + c.getInputText());
		if (c instanceof JavaBasicCompletion) {
			JavaBasicCompletion jbc = (JavaBasicCompletion) c;
			BasicCompletion bc = new BasicCompletion(c.getProvider(), jbc.getTextToEnter());
			bc.setShortDescription(jbc.getShortDescription());
			bc.setSummary(jbc.getSummary());
			super.insertCompletion(bc, parametrizedCompletion);
			return;
		}
		super.insertCompletion(c, parametrizedCompletion);
	}

}
