package opkeystudio.opkeystudiocore.core.query;

import java.sql.SQLException;

import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;

public class QueryExecutor {
	private static QueryExecutor queryExecutor;

	public static QueryExecutor getInstance() {
		if (queryExecutor == null) {
			queryExecutor = new QueryExecutor();
		}
		return queryExecutor;
	}

	public String executeQuery(String query) {
		System.out.println(query);
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		try {
			String result = sqlComm.executeQueryString(query);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int executeUpdateQuery(String query) {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		try {
			int result = sqlComm.executeUpdate(query);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
