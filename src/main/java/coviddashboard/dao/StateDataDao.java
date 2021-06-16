package coviddashboard.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;

import coviddashboard.config.ApplicationConfig;
import coviddashboard.model.StateData;

public class StateDataDao {

	public JSONArray getStateData() {
		String sql = "select State.stateName, activeCases, confirmedCases, deceasedCases, recoveredCases from StateData, State where StateData.stateCode = State.StateCode and recordDate = ?;";
		String sqlNation = "select activeCases, confirmedCases, deceasedCases, recoveredCases from NationData where recordDate = ?;";
		ArrayList<StateData> stateDataList = new ArrayList<>();
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(LocalDate.now()));
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				stateDataList.add(new StateData(resultset.getString("stateName"), resultset.getInt("activeCases"),
						resultset.getInt("confirmedCases"), resultset.getInt("deceasedCases"),
						resultset.getInt("recoveredCases")));
			}

			PreparedStatement stmt = connection.prepareStatement(sqlNation);
			stmt.setDate(1, Date.valueOf(LocalDate.now()));
			resultset = stmt.executeQuery();

			while (resultset.next()) {
				stateDataList
						.add(new StateData("Total", resultset.getInt("activeCases"), resultset.getInt("confirmedCases"),
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
		return new JSONArray(stateDataList);
	}

	public JSONArray getStateAllData(String state) {
		String sql = "select stateCode, dailyConfirmedCases, dailyDeceasedCases, dailyRecoveredCases, recordDate from StateData where stateCode = (select stateCode from state where stateName=?) ORDER BY recordDate;";
		ArrayList<StateData> stateDataList = new ArrayList<>();
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, state);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				stateDataList.add(
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
		return new JSONArray(stateDataList);
	}

	public int getActiveCases(String state) {
		String activeCasesSQL = "select activecases from StateData where stateCode = (select stateCode from state where stateName=?) and recordDate=?;";
		ResultSet resultSet;
		int sourceCases = Integer.MIN_VALUE;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(activeCasesSQL);
			preparedStatement.setString(1, state);
			preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			sourceCases = resultSet.getInt("activecases");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
		return sourceCases;
	}
}
