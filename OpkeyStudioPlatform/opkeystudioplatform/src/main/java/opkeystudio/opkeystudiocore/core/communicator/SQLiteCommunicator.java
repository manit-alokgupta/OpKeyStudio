package opkeystudio.opkeystudiocore.core.communicator;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class SQLiteCommunicator {
	private Connection connection;
	private String sqliteDbUrl;
	private static SQLiteCommunicator opkeyDBConnector;

	public static SQLiteCommunicator getOpKeyDBCommunicator() throws SQLException {
		return opkeyDBConnector;
	}

	public static void getOpKeyDBCommunicator(SQLiteCommunicator comm) {
		opkeyDBConnector = comm;
	}

	public SQLiteCommunicator() {
		this.sqliteDbUrl = "jdbc:sqlite:" + ServiceRepository.getInstance().getExportedDBFilePath();
	}

	public SQLiteCommunicator(String dbFileUrl) {
		this.sqliteDbUrl = "jdbc:sqlite:" + dbFileUrl;
	}

	public void connect() throws SQLException {
		if (connection != null) {
			System.out.println("Already Connected");
			return;
		}
		connection = DriverManager.getConnection(this.sqliteDbUrl);
	}

	public void disconnect() throws SQLException {
		connection.close();
	}

	public ResultSet executeQuery(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		return stmt.executeQuery(query);
	}

	public int executeUpdate(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		return stmt.executeUpdate(query);
	}

	public String executeQueryString(String query) throws SQLException {
		ResultSet rs = executeQuery(query);
		return convertToJSON(rs);
	}

	public byte[] executeQueryStringWithByteDatas(String query) throws SQLException {
		ResultSet resultSet = executeQuery(query);
		while (resultSet.next()) {
			int total_rows = resultSet.getMetaData().getColumnCount();
			for (int i = 0; i < total_rows; i++) {
				String columnTypeName = resultSet.getMetaData().getColumnTypeName(i + 1);
				if (columnTypeName.equals("BLOB")) {
					byte[] bytes = resultSet.getBytes(i + 1);
					if (bytes == null) {
						continue;
					}
					return bytes;
				}
			}
		}
		return null;
	}

	private String convertToJSON(ResultSet resultSet) throws SQLException {
		JSONArray jsonArray = new JSONArray();
		while (resultSet.next()) {
			int total_rows = resultSet.getMetaData().getColumnCount();
			JSONObject obj = new JSONObject();
			for (int i = 0; i < total_rows; i++) {
				try {
					String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
					String columnTypeName = resultSet.getMetaData().getColumnTypeName(i + 1);
					if (columnTypeName.equals("BLOB")) {
						byte[] bytes = resultSet.getBytes(i + 1);
						if (bytes == null) {
							continue;
						}
						String data = new String(bytes, StandardCharsets.UTF_8);
						obj.put(columnName, data);
					} else {
						Object columnData = resultSet.getObject(i + 1);
						obj.put(columnName, columnData);
					}
				} catch (JSONException | SQLException e) {
					e.printStackTrace();
				}
			}
			jsonArray.put(obj);
		}
		// System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
}
