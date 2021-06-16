package coviddashboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import coviddashboard.config.ApplicationConfig;

public class SubscriptionDataDao {
	public boolean addEmail(String mail) {
		String sql = "insert into SubscriptionData value(?,?);";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mail);
			preparedStatement.setBoolean(2, true);
			if (preparedStatement.executeUpdate() > 0) {
				;
			}
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
