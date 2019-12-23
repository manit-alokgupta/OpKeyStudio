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

	public SQLiteCommunicator() {
		this.sqliteDbUrl = "jdbc:sqlite:" + ServiceRepository.getInstance().getExportedDBFilePath();
	}

	public SQLiteCommunicator(String dbFileUrl) {
		this.sqliteDbUrl = "jdbc:sqlite:" + dbFileUrl;
	}

	public void connect() throws SQLException {
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

	private String convertToJSON(ResultSet resultSet) throws SQLException {
		JSONArray jsonArray = new JSONArray();
		while (resultSet.next()) {
			int total_rows = resultSet.getMetaData().getColumnCount();
			JSONObject obj = new JSONObject();
			for (int i = 0; i < total_rows; i++) {
				try {
					String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
					String columnTypeName = resultSet.getMetaData().getColumnTypeName(i + 1);
					if (!columnName.toLowerCase().equals("objectimage")) {
						if (columnTypeName.equals("BLOB")) {

							byte[] bytes = resultSet.getBytes(i + 1);
							String data = new String(bytes, StandardCharsets.UTF_8);
							obj.put(columnName, data);
						} else {
							Object columnData = resultSet.getObject(i + 1);
							obj.put(columnName, columnData);
						}
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
