package opkeystudio.opkeystudiocore.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Utilities {
	private static Utilities util;

	public static Utilities getInstance() {
		if (util == null) {
			util = new Utilities();
		}
		return util;
	}

	public String getDefaultWorkSpacePath() {
		String path = System.getProperty("user.dir") + File.separator + "OpKeyStudio" + File.separator + "workspace";
		new File(path).mkdir();
		return path;
	}

	public String getXMLSerializedData(Object object) throws JsonProcessingException {
		XmlMapper mapper = new XmlMapper();
		return mapper.writeValueAsString(object);
	}

	public Object getXMLDeSerializedData(File filepath, Class<?> _class) throws IOException {
		XmlMapper mapper = new XmlMapper();
		return mapper.readValue(filepath, _class);
	}

	public void writeToFile(File file, String data) throws IOException {
		System.out.println(file.getAbsolutePath());
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(data);
		bw.flush();
		bw.close();
	}
}
