package opkeystudio.opkeystudiocore.core.communicator;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		SQLiteCommunicator sqlComm=new SQLiteCommunicator("E:\\ExportedArtifactsNeon\\ExportedArtifact.db");
		sqlComm.connect();
		String rs= sqlComm.executeQueryString("select * from global_variables");
		
		System.out.println(rs.toString());
	}

}
