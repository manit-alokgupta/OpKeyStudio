package com.ssts.reporting;

public enum Status {

	PASS("PASS"), FAIL("FAIL"), InProgress("InProgress"), NotStarted("NotStarted");
	
	private String value;
	
	Status(String value){
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}
}
