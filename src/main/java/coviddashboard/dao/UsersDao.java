package coviddashboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import org.json.JSONArray;

import com.mysql.cj.xdevapi.JsonArray;

import coviddashboard.config.ApplicationConfig;
import coviddashboard.config.EncryptSHA256;
import coviddashboard.model.PassData;
import coviddashboard.model.Users;

public class UsersDao {

	public boolean loginUser(String username, String password) {
		String sql = "select * from users where username=? and password=? and approved='accepted';";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, EncryptSHA256.getSHA(password));

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isUsernmaeExist(String username) {
		String sql = "select username from users where username=?";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean save(Users user) {
		// TODO Auto-generated method stub
		String sql = "insert into users values (?, ?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, EncryptSHA256.getSHA(user.getPassword()));
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getMobileNumber());
			preparedStatement.setString(6, user.getRoles());
			preparedStatement.setString(7, user.getApproved());
			System.out.println(preparedStatement);
			if (preparedStatement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public JSONArray getUserList() {
		String sql = "select name, email, mobilenumber, roles, approved from Users where approved = 'pending';";
		ArrayList<Users> list = new ArrayList<>();
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				list.add(new Users(
						resultSet.getString("name"),
						resultSet.getString("email"),
						resultSet.getString("mobilenumber"),
						resultSet.getString("roles"),
						resultSet.getString("approved")
						));
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


	public boolean approveUser(String status, String mobilenumber) {
		// TODO Auto-generated method stub
		String sql = "update Users SET approved=? where mobilenumber=?;";
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, status);
			statement.setString(2, mobilenumber);
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
