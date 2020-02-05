package com.opkeystudio.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DRObject {
	private Map<String, List<String>> drDatas = new HashMap<>();

	public void addDRCell(String column, String cellValue) {
		Map<String, List<String>> drDatas = getDrDatas();
		if (drDatas.get(column) != null) {
			drDatas.get(column).add(cellValue);
			return;
		}
		List<String> cellDatas = new ArrayList<String>();
		cellDatas.add(cellValue);
		drDatas.put(column, cellDatas);
	}

	public List<String> getDRCells(String column) {
		List<String> drcells = getDrDatas().get(column);
		if (drcells == null) {
			return new ArrayList<String>();
		}
		return drcells;
	}

	public Map<String, List<String>> getDrDatas() {
		return drDatas;
	}

	public void setDrDatas(Map<String, List<String>> drDatas) {
		this.drDatas = drDatas;
	}
}
