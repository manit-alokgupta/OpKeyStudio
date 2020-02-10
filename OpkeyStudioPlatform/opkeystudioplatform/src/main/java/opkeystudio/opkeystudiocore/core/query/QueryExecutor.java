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
		try {
			SQLiteCommunicator sqlComm = SQLiteCommunicator.getOpKeyDBCommunicator();
			String result = sqlComm.executeQueryString(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] executeQueryWithByteData(String query) {
		try {
			SQLiteCommunicator sqlComm = SQLiteCommunicator.getOpKeyDBCommunicator();
			return sqlComm.executeQueryStringWithByteDatas(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int executeUpdateQuery(String query) {
		try {
			SQLiteCommunicator sqlComm = SQLiteCommunicator.getOpKeyDBCommunicator();
			int result = sqlComm.executeUpdate(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
