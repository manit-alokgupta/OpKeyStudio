package opkeystudio.opkeystudiocore.core.project.artificates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import opkeystudio.opkeystudiocore.core.project.artificates.Artificate.ArtificateType;

public class Main {

	public static void main(String[] args) throws JsonProcessingException {
		// TODO Auto-generated method stub
		ArtificateFolder artificate = new ArtificateFolder("deded", "deded");
		Artificate ar2=new Artificate("wdwdwd","dwdwdw",ArtificateType.TESTCASE);
		artificate.addArtificate(ar2);
		XmlMapper mapper=new XmlMapper();
		String data=mapper.writeValueAsString(artificate);
		System.out.println(data);

	}

}
