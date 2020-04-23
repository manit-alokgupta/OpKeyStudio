package com.opkey.OpKeyGenericPlugin;

public class DataType {
	public static boolean getBoolean(String bool) {
		return Boolean.parseBoolean(bool);
	}

	public static int getInt(String num) {
		return Integer.parseInt(num);
	}

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
}
