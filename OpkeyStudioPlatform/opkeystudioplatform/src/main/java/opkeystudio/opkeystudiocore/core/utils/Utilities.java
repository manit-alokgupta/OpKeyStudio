package opkeystudio.opkeystudiocore.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Utilities {
	private static Utilities util;

	public static Utilities getInstance() {
		if (util == null) {
			util = new Utilities();
		}
		return util;
	}

	public void initializeOpKeyStudioPath() {
		String path = System.getProperty("user.home") + File.separator + "OpKeyStudio";
		if (!new File(path).exists()) {
			new File(path).mkdir();
		}
		path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace";
		if (!new File(path).exists()) {
			new File(path).mkdir();
		}
		path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "SourceCodes";
		if (!new File(path).exists()) {
			new File(path).mkdir();
		}
	}

	public String getDefaultWorkSpacePath() {
		String path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace";
		return path;
	}

	public String getDefaultSourceCodeDirPath() {
		String path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "SourceCodes";
		return path;
	}

	public String getXMLSerializedData(Object object) throws JsonProcessingException {
		XmlMapper mapper = new XmlMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper.writeValueAsString(object);
	}

	public Object getXMLDeSerializedData(File filepath, Class<?> _class) throws IOException {
		XmlMapper mapper = new XmlMapper();
		return mapper.readValue(filepath, _class);
	}

	public Object getXMLDeSerializedData(String data, Class<?> _class) throws IOException {
		XmlMapper mapper = new XmlMapper();
		return mapper.readValue(data, _class);
	}

	public void writeToFile(File file, String data) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(data);
		bw.flush();
		bw.close();
	}

	public Object cloneObject(Object object, Class<?> _class) throws IOException {
		String serializedData = getXMLSerializedData(object);
		return getXMLDeSerializedData(serializedData, _class);
	}

	public ObjectMapper getObjectMapperInstance() {
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

	public String getUniqueUUID(String prefix) {
		return prefix + "" + UUID.randomUUID().toString();
	}

	public String getCurrentDateTime() {
		return new Date().toString();
	}

	public String getCurrentTimeZone() {
		return "Coordinated Universal Time";
	}

	public int getIndexOfItem(String[] arrays, String item) {
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].toLowerCase().equals(item.toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	public String getRandomVariableName(String prefix) {
		return prefix + String.valueOf(System.nanoTime());
	}
}
