package coviddashboard.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;

import coviddashboard.config.ApplicationConfig;
import coviddashboard.model.NationData;

public class NationDataDao {

	public JSONArray getData() {
		String sql = "select * from NationData where recordDate = ?;";
		ArrayList<NationData> nationDataList = new ArrayList<>();
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(LocalDate.now()));
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				nationDataList.add(new NationData(resultset.getDate("recordDate"), resultset.getInt("activeCases"),
						resultset.getInt("confirmedCases"), resultset.getInt("deceasedCases"),
						resultset.getInt("recoveredCases"), resultset.getInt("dailyConfirmedCases"),
						resultset.getInt("dailyDeceasedCases"), resultset.getInt("dailyRecoveredCases")));
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
		return new JSONArray(nationDataList);
	}

	public JSONArray getAllData() {
		String sql = "select * from NationData ORDER BY recordDate";
		ArrayList<NationData> nationDataList = new ArrayList<>();
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);

			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				nationDataList.add(new NationData(resultset.getDate("recordDate"), resultset.getInt("activeCases"),
						resultset.getInt("confirmedCases"), resultset.getInt("deceasedCases"),
						resultset.getInt("recoveredCases"), resultset.getInt("dailyConfirmedCases"),
						resultset.getInt("dailyDeceasedCases"), resultset.getInt("dailyRecoveredCases")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONArray(nationDataList);
	}
}
