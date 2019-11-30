package opkeystudio.opkeystudiocore.core.keywordmanager.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Keyword {
	private String keywordid;
	private String outputdescription;
	private String description;
	private String keywordtype;
	private boolean isoptional;
	private String associatedmethod;
	private String keywordexpectedresult;
	private String datatype;
	private String name;
	private String keyworddescription;
	private String outputtype;
	private int position;
	@JsonProperty("class")
	private String class1;
	private String argid;
	private String categoryid;
	private String defaultvalue;
	private String keywordname;
	private String argumentname;
	private String pluginid;
	private String deprecation_reason;
	private String pluginName;
	private List<KeyWordInputArgument> keywordInputArguments;

	public String getKeywordid() {
		return keywordid;
	}

	public void setKeywordid(String keywordid) {
		this.keywordid = keywordid;
	}

	public String getOutputdescription() {
		return outputdescription;
	}

	public void setOutputdescription(String outputdescription) {
		this.outputdescription = outputdescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywordtype() {
		return keywordtype;
	}

	public void setKeywordtype(String keywordtype) {
		this.keywordtype = keywordtype;
	}

	public boolean isIsoptional() {
		return isoptional;
	}

	public void setIsoptional(boolean isoptional) {
		this.isoptional = isoptional;
	}

	public String getAssociatedmethod() {
		return associatedmethod;
	}

	public void setAssociatedmethod(String associatedmethod) {
		this.associatedmethod = associatedmethod;
	}

	public String getKeywordexpectedresult() {
		return keywordexpectedresult;
	}

	public void setKeywordexpectedresult(String keywordexpectedresult) {
		this.keywordexpectedresult = keywordexpectedresult;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyworddescription() {
		return keyworddescription;
	}

	public void setKeyworddescription(String keyworddescription) {
		this.keyworddescription = keyworddescription;
	}

	public String getOutputtype() {
		return outputtype;
	}

	public void setOutputtype(String outputtype) {
		this.outputtype = outputtype;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
	}

	public String getArgid() {
		return argid;
	}

	public void setArgid(String argid) {
		this.argid = argid;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getKeywordname() {
		return keywordname;
	}

	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}

	public String getArgumentname() {
		return argumentname;
	}

	public void setArgumentname(String argumentname) {
		this.argumentname = argumentname;
	}

	public String getPluginid() {
		return pluginid;
	}

	public void setPluginid(String pluginid) {
		this.pluginid = pluginid;
	}

	public String getDeprecation_reason() {
		return deprecation_reason;
	}

	public void setDeprecation_reason(String deprecation_reason) {
		this.deprecation_reason = deprecation_reason;
	}

	public List<KeyWordInputArgument> getKeywordInputArguments() {
		return keywordInputArguments;
	}

	public void setKeywordInputArguments(List<KeyWordInputArgument> keywordInputArguments) {
		this.keywordInputArguments = keywordInputArguments;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
}
