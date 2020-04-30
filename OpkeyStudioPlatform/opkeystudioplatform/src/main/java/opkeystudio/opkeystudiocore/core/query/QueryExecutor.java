package opkeystudio.opkeystudiocore.core.query;

import java.sql.Connection;
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
		System.out.println("Query " + query);
		try {
			SQLiteCommunicator sqlComm = SQLiteCommunicator.getOpKeyDBCommunicator();
			int result = sqlComm.executeUpdate(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Connection getConnection() {
		SQLiteCommunicator sqlComm;
		try {
			sqlComm = SQLiteCommunicator.getOpKeyDBCommunicator();
			return sqlComm.getSqliteConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
