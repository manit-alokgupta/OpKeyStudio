package opkeystudio.opkeystudiocore.core.transpiler.artifactparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactParser {
	public void parseArtifact(Artifact artifact) {
		if (artifact.getFile_type_enum() != MODULETYPE.Flow && artifact.getFile_type_enum() != MODULETYPE.Component) {
			return;
		}
		String filePath = Utilities.getInstance().getProjectTranspiledArtifactsFolder() + File.separator
				+ artifact.getPackagePath() + File.separator + artifact.getVariableName() + ".java";
		String javaContents = Utilities.getInstance().readTextFile(new File(filePath));
		JavaClassSource classSorce = (JavaClassSource) Roaster.parse(javaContents);
		MethodSource<JavaClassSource> method = classSorce.getMethod("execute");
		System.out.println(">>Method Body " + method.getBody());
		try {
			parseMethods(method);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseMethods(MethodSource<JavaClassSource> method) throws IOException {
		String methodBody = method.getBody();
		BufferedReader br = new BufferedReader(new StringReader(methodBody));
		String line = null;
		while ((line = br.readLine()) != null) {
			parseCodeLine(line);
		}
		br.close();
	}

	private void parseCodeLine(String code) {
		int dotIndex = getIndexOfChar(code, '.');
		if (dotIndex > -1) {
			String functionCall = code.substring(dotIndex + 1);
			parseKeywordFunctionCall(functionCall);
		}
	}

	private void parseKeywordFunctionCall(String keywordCode) {
		int openBracketIndex = getIndexOfChar(keywordCode, '(');
		int closeBracketIndex = getIndexOfChar(keywordCode, ')');
		String keywordName = keywordCode.substring(0, openBracketIndex);
		String argumentCode = keywordCode.substring(openBracketIndex + 1, closeBracketIndex);
		System.out.println("KyewordName " + keywordName + "   Arguments " + argumentCode);
	}

	private int getIndexOfChar(String code, char _char) {
		char[] chars = code.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == _char) {
				return i;
			}
		}
		return -1;
	}

}
