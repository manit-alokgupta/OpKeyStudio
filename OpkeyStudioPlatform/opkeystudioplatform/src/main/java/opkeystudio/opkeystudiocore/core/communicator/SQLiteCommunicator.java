package opkeystudio.opkeystudiocore.core.communicator;

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
		ServiceRepository.getInstance().setExortedDBFilePath("E:\\ExportedArtifactsNeon\\ExportedArtifact.db");
		this.sqliteDbUrl = "jdbc:sqlite:"+ServiceRepository.getInstance().getExportedDBFilePath();
		System.out.println(this.sqliteDbUrl);
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
					obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			jsonArray.put(obj);
		}
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
}
