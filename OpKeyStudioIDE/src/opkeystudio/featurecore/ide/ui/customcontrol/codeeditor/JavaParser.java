package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.net.URL;

import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.parser.ExtendedHyperlinkListener;
import org.fife.ui.rsyntaxtextarea.parser.ParseResult;
import org.fife.ui.rsyntaxtextarea.parser.Parser;

public class JavaParser implements Parser {

	@Override
	public ExtendedHyperlinkListener getHyperlinkListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getImageBase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ParseResult parse(RSyntaxDocument arg0, String arg1) {
		System.out.println("Parsed data " + arg1);
		return null;
	}

}
