package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import com.fasterxml.jackson.core.JsonProcessingException;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class Main {
	public static void main(String[] args) throws JsonProcessingException {
		FlowSchema fs=new FlowSchema();
		String xmlData=Utilities.getInstance().getXMLSerializedData(fs);
		System.out.println(xmlData);
	}
}
