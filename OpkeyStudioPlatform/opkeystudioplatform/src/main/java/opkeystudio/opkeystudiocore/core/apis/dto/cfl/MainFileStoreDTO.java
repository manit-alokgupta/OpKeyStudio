package opkeystudio.opkeystudiocore.core.apis.dto.cfl;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class MainFileStoreDTO extends Modified {
	private int clustering_key;
	@DBField
	private String f_id;
	@DBField
	private String filename;
	@DBField
	private String extension;
	@DBField
	private String uploadedby;
	@DBField
	private String uploadedon;
	@DBField
	private String size;
	@DBField
	private String filelocationtype;
	@DBField
	private String md5_checksum;
	@DBField
	private String tag;
	@DBField
	private String uploadedon_tz;
	@DBField
	private String session_id;
	@DBField
	private boolean isdeleted;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getUploadedby() {
		return uploadedby;
	}

	public void setUploadedby(String uploadedby) {
		this.uploadedby = uploadedby;
	}

	public String getUploadedon() {
		return uploadedon;
	}

	public void setUploadedon(String uploadedon) {
		this.uploadedon = uploadedon;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFilelocationtype() {
		return filelocationtype;
	}

	public void setFilelocationtype(String filelocationtype) {
		this.filelocationtype = filelocationtype;
	}

	public String getMd5_checksum() {
		return md5_checksum;
	}

	public void setMd5_checksum(String md5_checksum) {
		this.md5_checksum = md5_checksum;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUploadedon_tz() {
		return uploadedon_tz;
	}

	public void setUploadedon_tz(String uploadedon_tz) {
		this.uploadedon_tz = uploadedon_tz;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
}
