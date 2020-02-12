package com.opkeystudio.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DRObject {
	private static Map<String, List<String>> drDatas = new HashMap<>();

	public static void addDRCell(String column, String cellValue) {
		cellValue=encodeToBase64(cellValue);
		Map<String, List<String>> drDatas = getDrDatas();
		if (drDatas.get(column) != null) {
			drDatas.get(column).add(cellValue);
			return;
		}
		List<String> cellDatas = new ArrayList<String>();
		cellDatas.add(cellValue);
		drDatas.put(column, cellDatas);
	}

	public static List<String> getDRCells(String column) {
		List<String> drcells = getDrDatas().get(column);
		if (drcells == null) {
			return new ArrayList<String>();
		}
		List<String> filteredDatas = new ArrayList<String>();
		for (String drcell : drcells) {
			if (!drcell.trim().isEmpty()) {
				drcell=decodeToBase64(drcell);
				filteredDatas.add(drcell);
			}
		}
		return filteredDatas;
	}

	public static String getDRCell(String columnName, int rowNo) {
		return getDRCells(columnName).get(rowNo);
	}

	public static Map<String, List<String>> getDrDatas() {
		return drDatas;
	}

	public static void setDrDatas(Map<String, List<String>> drDatas2) {
		drDatas = drDatas2;
	}
	
	private static String encodeToBase64(String inputString) {
		return java.util.Base64.getEncoder().encodeToString(inputString.getBytes());
	}

	private static String decodeToBase64(String inputString) {
		byte[] bytes = java.util.Base64.getDecoder().decode(inputString);
		return new String(bytes);
	}

}
