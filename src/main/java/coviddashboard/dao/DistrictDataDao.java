package coviddashboard.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

import coviddashboard.config.ApplicationConfig;
import coviddashboard.config.CreateExcelFile;
import coviddashboard.model.DistrictData;
import coviddashboard.model.StateData;

public class DistrictDataDao {
	public JSONArray getDistrictData(String state) {
		String sql = "select districtName, activeCases, confirmedCases, deceasedCases, recoveredCases from DistrictData where DistrictData.stateCode = (select stateCode from state where stateName =?) and recordDate=?;";
		ArrayList<DistrictData> districtDataList = new ArrayList<>();
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, state);
			statement.setDate(2, Date.valueOf(LocalDate.now()));
//			stmt.setDate(1, Date.valueOf("2020-07-23"));
//			statement.setDate(2, Date.valueOf("2020-07-23"));
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				districtDataList.add(new DistrictData(resultset.getString("districtName"),
						resultset.getInt("activeCases"), resultset.getInt("confirmedCases"),
						resultset.getInt("deceasedCases"), resultset.getInt("recoveredCases")));
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
		return new JSONArray(districtDataList);
	}

	public JSONArray getAllDistrictData(String stateName, String districtName) {
		String sql = "select dailyConfirmedCases, dailyDeceasedCases, dailyRecoveredCases, recordDate from DistrictData where DistrictData.stateCode = (select stateCode from state where stateName = ?) and districtName=? ORDER BY recordDate";
		ArrayList<StateData> districtDataList = new ArrayList<>();
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, stateName);
			statement.setString(2, districtName);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				districtDataList.add(
						new StateData(resultset.getInt("dailyConfirmedCases"), resultset.getInt("dailyDeceasedCases"),
								resultset.getInt("dailyRecoveredCases"), resultset.getDate("recordDate")));
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
		return new JSONArray(districtDataList);
	}

	public JSONObject getDistricts(String stateName) {
		String sql = "select distinct(districtName) from districtdata where stateCode = (select stateCode from state where stateName= ? )";
		ArrayList<String> list = new ArrayList<>();
		ResultSet resultSet;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL,
					ApplicationConfig.DATABASEUSERNAME, ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, stateName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString("districtName"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.remove("Unknown");
		list.remove("Other State");
		return new JSONObject().put("districts", list);
	}

	public int getActiveCases(String state, String district) {
		String activeCasesSQL = "select activecases from districtdata where stateCode = (select stateCode from state where stateName=?) and districtName=? and recordDate=?;";
		ResultSet resultSet;
		int sourceCases = Integer.MIN_VALUE;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL,
					ApplicationConfig.DATABASEUSERNAME, ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(activeCasesSQL);
			preparedStatement.setString(1, state);
			preparedStatement.setString(2, district);
			preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
//			preparedStatement.setDate(3, Date.valueOf("2020-07-23"));
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			sourceCases = resultSet.getInt("activecases");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sourceCases;
	}

	public void generateReport() {
		String sql = "select stateName, districtName, activeCases, confirmedCases, deceasedCases, recoveredCases, dailyConfirmedCases, dailyDeceasedCases, dailyRecoveredCases, recordDate from State, DistrictData where State.stateCode = Districtdata.stateCode and recordDate=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL,
					ApplicationConfig.DATABASEUSERNAME, ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
			ResultSet resultSet = preparedStatement.executeQuery();
			Map<String, Object[]> data = new TreeMap<>();
			data.put("1",
					new Object[] { "State name", "District name", "Active cases", "Confirmed cases", "Deceased cases",
							"Recovered cases", "Today's Confirmed Cases", "Today's Deceased Cases",
							"Today's Recovered Cases", "Date" });
			int count = 2;
			while (resultSet.next()) {
				data.put(Integer.toString(count),
						new Object[] { resultSet.getString("stateName"), resultSet.getString("districtName"),
								resultSet.getString("activeCases"), resultSet.getString("confirmedCases"),
								resultSet.getString("deceasedCases"), resultSet.getString("recoveredCases"),
								resultSet.getString("dailyConfirmedCases"), resultSet.getString("dailyDeceasedCases"),
								resultSet.getString("dailyRecoveredCases"), resultSet.getString("recordDate") });
				count++;
			}
			new CreateExcelFile().createExcelSheet(data);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}