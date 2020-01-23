package opkeystudio.opkeystudiocore.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Utilities {
	private static Utilities util;
	private String defaultInstallDir;
	private ContentAssistData contentAssistData;

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

		path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "Artifacts";
		if (!new File(path).exists()) {
			new File(path).mkdir();
		}

		path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "Drivers";
		if (!new File(path).exists()) {
			new File(path).mkdir();
		}
	}

	public String getSeleniumDriverFolder() {
		return System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "Drivers";
	}

	public String getArtifactsDownloadFolder() {
		return System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "Artifacts";
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String dateString = format.format(new Date());
		return dateString;
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

	public String getDefaultInstallDir() {
		return defaultInstallDir;
	}

	public void setDefaultInstallDir(String defaultInstallDir) {
		this.defaultInstallDir = defaultInstallDir;
	}

	public String getLibrariesFolder() {
		String path = this.getDefaultInstallDir() + File.separator + "resources" + File.separator + "libraries";
		if (!new File(path).exists()) {
			return "E:\\OpKeyEJars";
		}
		return path;
	}

	public ContentAssistData getContentAssistData() {
		return contentAssistData;
	}

	public void setContentAssistData(ContentAssistData contentAssistData) {
		this.contentAssistData = contentAssistData;
	}
	
	public void extractZipFolder(String zipFile, String extractFolder) {
		try {
			int BUFFER = 2048;
			File file = new File(zipFile);

			ZipFile zip = new ZipFile(file);
			String newPath = extractFolder;

			new File(newPath).mkdir();
			Enumeration zipFileEntries = zip.entries();

			// Process each entry
			while (zipFileEntries.hasMoreElements()) {
				// grab a zip file entry
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
				String currentEntry = entry.getName();

				File destFile = new File(newPath, currentEntry);
				// destFile = new File(newPath, destFile.getName());
				File destinationParent = destFile.getParentFile();

				// create the parent directory structure if needed
				destinationParent.mkdirs();

				if (!entry.isDirectory()) {
					BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}

			}
		} catch (Exception e) {
		}

	}
}
