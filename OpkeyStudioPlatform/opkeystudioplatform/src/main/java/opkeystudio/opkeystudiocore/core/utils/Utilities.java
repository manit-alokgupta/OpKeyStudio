package opkeystudio.opkeystudiocore.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Utilities {
	private static Utilities util;
	private String defaultInstallDir;

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
				+ File.separator + "CodedFL";

		if (!new File(path).exists()) {
			new File(path).mkdir();
		}

		path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "CodedFL" + File.separator + "CFLOpKeyLibs";
		if (!new File(path).exists()) {
			new File(path).mkdir();
		}
		path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "CodedFL" + File.separator + "CFLAssociatedLibs";
		if (!new File(path).exists()) {
			new File(path).mkdir();
		}
	}

	public String getDefaultInstallDir() {
		// return "E:\\OpKeyEResources";
		return defaultInstallDir;
	}

	public String getDefaultPluginBaseDir() {
		return getDefaultInstallDir() + File.separator + "resources" + File.separator + "libraries" + File.separator
				+ "PluginBase";
	}

	public String getDefaultPluginsDir() {
		return getDefaultInstallDir() + File.separator + "resources" + File.separator + "libraries" + File.separator
				+ "Plugins";
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

	public String getDefaultCodedFLOpKeyLibrariesDirPath() {
		String path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "CodedFL" + File.separator + "CFLOpKeyLibs";
		return path;
	}

	public String getDefaultCodedFLAssociatedLibrariesDirPath() {
		String path = System.getProperty("user.home") + File.separator + "OpKeyStudio" + File.separator + "workspace"
				+ File.separator + "CodedFL" + File.separator + "CFLAssociatedLibs";
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dateString = format.format(new Date());
		return dateString;
	}

	@SuppressWarnings("deprecation")
	public String getUpdateCurrentDateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateString = format.format(date);
		return dateString;
	}

	public static void main(String[] args) {
		String date = Utilities.getInstance().getUpdateCurrentDateTime();
		System.out.println(date);
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

	public File createZip(List<File> inputFiles) throws FileNotFoundException, IOException {
		File outZipFile = new File(inputFiles.get(0).getParent() + File.separator + "opkeylibs.jar");
		FileOutputStream fos = new FileOutputStream(outZipFile.getAbsolutePath());
		ZipOutputStream zipOS = new ZipOutputStream(fos);
		for (File inputFile : inputFiles) {
			writeToZipFile(inputFile.getAbsolutePath(), zipOS);
		}
		zipOS.close();
		fos.close();
		return outZipFile;
	}

	private void writeToZipFile(String path, ZipOutputStream zipStream) throws FileNotFoundException, IOException {
		File aFile = new File(path);
		FileInputStream fis = new FileInputStream(aFile);
		ZipEntry zipEntry = new ZipEntry(aFile.getName());
		zipStream.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipStream.write(bytes, 0, length);
		}
		zipStream.flush();
		zipStream.closeEntry();
		fis.close();
	}

	public String removeSpecialCharacters(String input) {
		return input.replaceAll("[^a-zA-Z0-9]", "");
	}

	public String getMD5String(byte[] buffer) {
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			byte[] digest = m.digest(buffer);
			String myHash = DatatypeConverter.printBase64Binary(digest);
			return myHash;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
