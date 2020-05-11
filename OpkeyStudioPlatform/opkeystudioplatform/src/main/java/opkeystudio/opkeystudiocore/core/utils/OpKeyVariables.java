package opkeystudio.opkeystudiocore.core.utils;

import com.crestech.opkey.plugin.codedfl.KeyValuePair;

public class OpKeyVariables {
	private static OpKeyVariables instance;

	public static OpKeyVariables getInstance() {
		if (instance == null) {
			instance = new OpKeyVariables();
		}
		return instance;
	}

	public String[] getAllGlobalVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "MobileDevice", "MobileApplication",
				"File", "List", "SecuredString", "CollectionOfString", "CollectionOfInteger", "CollectionOfDouble",
				"CollectionOfDateTime", "CollectionOfKeyValuePair", "KeyValuePair" };
	}

	public String[] getAllFLInputVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "MobileDevice", "MobileApplication",
				"File", "List", "SecuredString", "CollectionOfString", "CollectionOfInteger", "CollectionOfDouble",
				"CollectionOfDateTime", "CollectionOfKeyValuePair", "KeyValuePair" };
	}

	public String[] getAllFLOutputVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "CollectionOfString",
				"CollectionOfInteger", "CollectionOfDouble", "CollectionOfDateTime", "CollectionOfKeyValuePair",
				"KeyValuePair" };
	}

	public String[] getAllCFLInputVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "ORObject", "CollectionOfString",
				"CollectionOfInteger", "CollectionOfDouble", "CollectionOfDateTime", "CollectionOfKeyValuePair",
				"KeyValuePair" };
	}

	public String[] getAllCFLOutputVariablesType() {
		return new String[] { "String", "Boolean", "Integer", "Double", "DateTime", "CollectionOfString",
				"CollectionOfInteger", "CollectionOfDouble", "CollectionOfDateTime", "CollectionOfKeyValuePair",
				"KeyValuePair" };
	}
}
