package coviddashboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

import coviddashboard.config.ApplicationConfig;

public class StateDao {
	public JSONObject getStates() {
		String sql = "select stateName from state order by stateName;";
		ArrayList<String> list = new ArrayList<>();
		ResultSet resultSet;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);

			resultSet = connection.createStatement().executeQuery(sql);
			while (resultSet.next()) {
				list.add(resultSet.getString("stateName"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new JSONObject().put("states", list);
	}

}
