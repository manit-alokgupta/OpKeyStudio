package opkeystudio.opkeystudiocore.core.utils;

public class OpKeyVariables {
	public String[] getAllGlobalVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "MobileDevice", "MobileApplication",
				"File", "List", "SecuredString", "Collections(String)", "Collections(Integer)", "Collections(Double)",
				"Collections(DateTime)", "Collections(KeyValuePair)", "KeyValuePair" };
	}

	public String[] getAllFLInputVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "MobileDevice", "MobileApplication",
				"File", "List", "SecuredString", "Collections(String)", "Collections(Integer)", "Collections(Double)",
				"Collections(DateTime)", "Collections(KeyValuePair)", "KeyValuePair" };
	}

	public String[] getAllFLOutputVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "Collections(String)",
				"Collections(Integer)", "Collections(Double)", "Collections(DateTime)", "Collections(KeyValuePair)",
				"KeyValuePair" };
	}

	public String[] getAllCFLInputVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "ORObject", "Collections(String)",
				"Collections(Integer)", "Collections(Double)", "Collections(DateTime)", "Collections(KeyValuePair)",
				"KeyValuePair" };
	}

	public String[] getAllCFLOutputVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "Collections(String)",
				"Collections(Integer)", "Collections(Double)", "Collections(DateTime)", "Collections(KeyValuePair)",
				"KeyValuePair" };
	}
}
