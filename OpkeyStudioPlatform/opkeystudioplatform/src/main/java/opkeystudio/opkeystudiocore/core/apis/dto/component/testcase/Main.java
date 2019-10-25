package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import java.io.File;
import java.io.IOException;

import opkeystudio.opkeystudiocore.core.apis.dto.component.objectrepository.ObjectRepositorySchema;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class Main {
	public static void main(String[] args) throws IOException {
		ObjectRepositorySchema fs = (ObjectRepositorySchema) Utilities.getInstance().getXMLDeSerializedData(
				new File("C:\\Users\\neon.nishant\\Desktop\\OpKeyProjects\\TestProject\\Project Workspace\\TestOR.okor"),
				ObjectRepositorySchema.class);
		System.out.println(fs.getOrObjects().get(0).getChildObjects().get(0).getObjectProperties().get(0).getPropertyName());
	}
}
