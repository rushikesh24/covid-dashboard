package coviddashboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import coviddashboard.config.ApplicationConfig;
import coviddashboard.model.PassData;

public class PassDataDao {
	public JSONObject getPass(PassData passData) {
		String sql = "select count(passID) as passCount from PassData;";
		String insertSQL = "insert into PassData values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		ResultSet resultSet;
		String status = "";
		int count = Integer.MIN_VALUE;
		Connection connection = null;

		int sourceActiveCases = new DistrictDataDao().getActiveCases(passData.getSourceState(),
				passData.getSourceDistrict());
		int destinationCases = new DistrictDataDao().getActiveCases(passData.getDestinationState(),
				passData.getDestinationDistrict());
		float sourceStateCases = new StateDataDao().getActiveCases(passData.getSourceState());
		float destinationStateCases = new StateDataDao().getActiveCases(passData.getDestinationState());

		if (((sourceActiveCases / sourceStateCases) < 0.10) && ((destinationCases / destinationStateCases) < 0.10)) {
			status = "Successful";
		} else {
			status = "Pending";
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);

			resultSet = connection.createStatement().executeQuery(sql);
			resultSet.next();
			count = resultSet.getInt("passCount");
			count++;

			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, count);
			preparedStatement.setString(2, passData.getName());
			preparedStatement.setString(3, passData.getAadharNumber());
			preparedStatement.setString(4, passData.getSourceState());
			preparedStatement.setString(5, passData.getSourceDistrict());
			preparedStatement.setString(6, passData.getDestinationState());
			preparedStatement.setString(7, passData.getDestinationDistrict());
			preparedStatement.setString(8, passData.getReason());
			preparedStatement.setDate(9, passData.getTravelDate());
			preparedStatement.setString(10, passData.getComments());
			preparedStatement.setString(11, status);
			preparedStatement.executeUpdate();
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
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("passID", count);
		return json;
	}

	public JSONObject getPassStatus(int passId, String aadharNumber) {
		String sql = "select passID, passStatus from PassData where passID=? and aadharNumber=? ;";
		String status = "";
		int id = Integer.MIN_VALUE;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, passId);
			preparedStatement.setString(2, aadharNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			id = resultSet.getInt("passID");
			status = resultSet.getString("passStatus");
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
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("passID", id);
		return json;
	}

	public JSONArray getPassList() {
		String sql = "select * from PassData where passStatus = 'pending';";
		ArrayList<PassData> list = new ArrayList<>();
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				list.add(new PassData(resultSet.getInt("passID"), resultSet.getString("name"),
						resultSet.getString("aadharNumber"), resultSet.getString("sourceState"),
						resultSet.getString("sourceDistrict"), resultSet.getString("destinationState"),
						resultSet.getString("destinationDistrict"), resultSet.getString("reason"),
						resultSet.getDate("travelDate"), resultSet.getString("comments"),
						resultSet.getString("passStatus")));
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
		return new JSONArray(list);
	}

	public boolean updatePasssStatus(String status, int passID) {
		// TODO Auto-generated method stub
		String sql = "update PassData SET passStatus=? where passID=?;";
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, status);
			statement.setInt(2, passID);
			if(statement.executeUpdate() > 0) 
				return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
