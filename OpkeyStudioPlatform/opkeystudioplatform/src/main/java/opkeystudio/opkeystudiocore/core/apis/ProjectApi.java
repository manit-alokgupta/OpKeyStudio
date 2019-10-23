package opkeystudio.opkeystudiocore.core.apis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.Project;
import opkeystudio.opkeystudiocore.core.communicator.OpKeyApiCommunicator;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ProjectApi {
	public List<Project> getAssignedProjects() throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyAuth/GetListOfAssignedProject",
				"GET", params, null, null);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Project.class);
		return mapper.readValue(retdata, type);
	}

	public String selectProject(String projectPID) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectPID);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyAuth/SelectProject", "POST",
				params, null, null);

		System.out.println(retdata);
		return retdata;
	}
}
