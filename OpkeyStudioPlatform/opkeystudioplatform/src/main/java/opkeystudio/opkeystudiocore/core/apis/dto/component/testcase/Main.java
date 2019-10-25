package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import java.io.File;
import java.io.IOException;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class Main {
	public static void main(String[] args) throws IOException {
		FlowSchema fs = (FlowSchema) Utilities.getInstance().getXMLDeSerializedData(
				new File("C:\\Users\\neon.nishant\\Desktop\\OpKeyProjects\\TestProject\\Project Workspace\\TestCase1.oktc"),
				FlowSchema.class);
	}
}
