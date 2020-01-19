package opkeystudio.featurecore.ide.ui.ui.sourcecodeeditor;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformationValidator;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import opkeystudio.opkeystudiocore.core.utils.ContentAssistData;

class ContentAssistProcessor implements IContentAssistProcessor {
	private String lastError = null;

	private IContextInformationValidator contextInfoValidator;

	private ContentAssistData wordTracker;

	public ContentAssistProcessor(ContentAssistData tracker) {
		super();
		contextInfoValidator = new ContextInformationValidator(this);
		wordTracker = tracker;
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer textViewer, int documentOffset) {
		IDocument document = textViewer.getDocument();
		int currOffset = documentOffset - 1;

		try {
			String currWord = "";
			char currChar;
			while (currOffset > 0 && !Character.isWhitespace(currChar = document.getChar(currOffset))) {
				currWord = currChar + currWord;
				currOffset--;
			}

			List suggestions = wordTracker.suggest(currWord);
			ICompletionProposal[] proposals = null;
			if (suggestions.size() > 0) {
				proposals = buildProposals(suggestions, currWord, documentOffset - currWord.length());
				lastError = null;
			}
			return proposals;
		} catch (BadLocationException e) {
			e.printStackTrace();
			lastError = e.getMessage();
			return null;
		}
	}

	private ICompletionProposal[] buildProposals(List suggestions, String replacedWord, int offset) {
		ICompletionProposal[] proposals = new ICompletionProposal[suggestions.size()];
		int index = 0;
		for (Iterator i = suggestions.iterator(); i.hasNext();) {
			String currSuggestion = (String) i.next();
			proposals[index] = new CompletionProposal(currSuggestion, offset, replacedWord.length(),
					currSuggestion.length());
			index++;
		}
		return proposals;
	}

	public IContextInformation[] computeContextInformation(ITextViewer textViewer, int documentOffset) {
		lastError = "No Context Information available";
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		// we always wait for the user to explicitly trigger completion
		return null;
	}

	public char[] getContextInformationAutoActivationCharacters() {
		// we have no context information
		return null;
	}

	public String getErrorMessage() {
		return lastError;
	}

	public IContextInformationValidator getContextInformationValidator() {
		return contextInfoValidator;
	}
}