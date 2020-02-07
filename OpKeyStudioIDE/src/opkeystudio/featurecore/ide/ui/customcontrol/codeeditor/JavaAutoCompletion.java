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
	}

	@Override
	public void addAutoCompletionListener(AutoCompletionListener l) {
		super.addAutoCompletionListener(l);
	}

	@Override
	protected Action createAutoCompleteAction() {
		return super.createAutoCompleteAction();
	}

	@Override
	public void doCompletion() {
		super.doCompletion();
	}

	@Override
	protected void fireAutoCompletionEvent(Type arg0) {
		super.fireAutoCompletionEvent(arg0);
	}

	@Override
	public int getAutoActivationDelay() {
		return super.getAutoActivationDelay();
	}

	@Override
	public boolean getAutoCompleteSingleChoices() {
		return super.getAutoCompleteSingleChoices();
	}

	@Override
	public CompletionProvider getCompletionProvider() {
		return super.getCompletionProvider();
	}

	@Override
	public Color getDescWindowColor() {
		return super.getDescWindowColor();
	}

	@Override
	public ExternalURLHandler getExternalURLHandler() {
		return super.getExternalURLHandler();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ListCellRenderer getListCellRenderer() {
		return super.getListCellRenderer();
	}

	@Override
	public ListCellRenderer<Object> getParamChoicesRenderer() {
		return super.getParamChoicesRenderer();
	}

	@Override
	protected String getReplacementText(Completion c, Document doc, int start, int len) {

		return super.getReplacementText(c, doc, start, len);
	}

	@Override
	public boolean getShowDescWindow() {

		return super.getShowDescWindow();
	}

	@Override
	public JTextComponent getTextComponent() {

		return super.getTextComponent();
	}

	@Override
	public KeyStroke getTriggerKey() {

		return super.getTriggerKey();
	}

	@Override
	public boolean hideChildWindows() {

		return super.hideChildWindows();
	}

	@Override
	protected boolean hidePopupWindow() {

		return super.hidePopupWindow();
	}

	@Override
	public void install(JTextComponent arg0) {

		super.install(arg0);
	}

	@Override
	public boolean isAutoActivationEnabled() {

		return super.isAutoActivationEnabled();
	}

	@Override
	public boolean isAutoCompleteEnabled() {

		return super.isAutoCompleteEnabled();
	}

	@Override
	protected boolean isHideOnCompletionProviderChange() {

		return super.isHideOnCompletionProviderChange();
	}

	@Override
	protected boolean isHideOnNoText() {

		return super.isHideOnNoText();
	}

	@Override
	public boolean isParameterAssistanceEnabled() {

		return super.isParameterAssistanceEnabled();
	}

	@Override
	public boolean isPopupVisible() {

		return super.isPopupVisible();
	}

	@Override
	protected int refreshPopupWindow() {

		return super.refreshPopupWindow();
	}

	@Override
	public void removeAutoCompletionListener(AutoCompletionListener l) {

		super.removeAutoCompletionListener(l);
	}

	@Override
	public void setAutoActivationDelay(int ms) {

		super.setAutoActivationDelay(ms);
	}

	@Override
	public void setAutoActivationEnabled(boolean enabled) {

		super.setAutoActivationEnabled(enabled);
	}

	@Override
	public void setAutoCompleteEnabled(boolean enabled) {

		super.setAutoCompleteEnabled(enabled);
	}

	@Override
	public void setAutoCompleteSingleChoices(boolean autoComplete) {

		super.setAutoCompleteSingleChoices(autoComplete);
	}

	@Override
	public void setChoicesWindowSize(int w, int h) {

		super.setChoicesWindowSize(w, h);
	}

	@Override
	public void setCompletionProvider(CompletionProvider provider) {

		super.setCompletionProvider(provider);
	}

	@Override
	public void setDescriptionWindowColor(Color c) {

		super.setDescriptionWindowColor(c);
	}

	@Override
	public void setDescriptionWindowSize(int w, int h) {

		super.setDescriptionWindowSize(w, h);
	}

	@Override
	public void setExternalURLHandler(ExternalURLHandler handler) {

		super.setExternalURLHandler(handler);
	}

	@Override
	protected void setHideOnCompletionProviderChange(boolean hideOnCompletionProviderChange) {

		super.setHideOnCompletionProviderChange(hideOnCompletionProviderChange);
	}

	@Override
	protected void setHideOnNoText(boolean hideOnNoText) {

		super.setHideOnNoText(hideOnNoText);
	}

	@Override
	public void setListCellRenderer(ListCellRenderer<Object> renderer) {

		super.setListCellRenderer(renderer);
	}

	@Override
	public void setParamChoicesRenderer(ListCellRenderer<Object> r) {

		super.setParamChoicesRenderer(r);
	}

	@Override
	public void setParameterAssistanceEnabled(boolean enabled) {

		super.setParameterAssistanceEnabled(enabled);
	}

	@Override
	protected void setPopupVisible(boolean visible) {

		super.setPopupVisible(visible);
	}

	@Override
	public void setShowDescWindow(boolean show) {

		super.setShowDescWindow(show);
	}

	@Override
	public void setTriggerKey(KeyStroke ks) {

		super.setTriggerKey(ks);
	}

	@Override
	public void uninstall() {

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
