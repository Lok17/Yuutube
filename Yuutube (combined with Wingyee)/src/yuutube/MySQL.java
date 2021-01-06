package yuutube;

import java.sql.*;

public class MySQL {
	private static final String url = "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL"; // MySQL username
	private static final String user = "root";// MySQL password
	private static final String password = "12345678";

	public MySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Success!");

		} catch (Exception e) {
			System.out.println("Fail!");
			// e.printStackTrace();
		}
	}

	static boolean emailExist(String email) {
		try {
			Connection com;
			com = DriverManager.getConnection(url, user, password);
//			if(!com.isClosed()) {
//			} 
			Statement statement = com.createStatement();
			String sql = "USE test";
			statement.executeQuery(sql);
			// the following is for my database
			ResultSet rs = statement.executeQuery("SELECT email FROM users WHERE email='" + email + "';");

			if (rs.next()) {
				return true;
			}
			return false;

		} catch (Exception e) {
			// System.out.println("error!");
			// e.printStackTrace();
		}
		return false;
	}

	static boolean checkPassword(String email, String pass) {
		try {  
			Connection com;
			com = DriverManager.getConnection(url, user, password);
//			if(!com.isClosed())
//				System.out.println("connection successful!");
			Statement statement = com.createStatement();

			String sql = "USE test";
			statement.executeQuery(sql);
			// the following is for my database
			ResultSet rs = statement.executeQuery("SELECT password FROM users WHERE email='" + email + "';");
			if (rs.next()) {
				String dbpass = rs.getString("password");
				if (pass.equals(dbpass)) {
					return true;
				}

			}
			return false;

		} catch (Exception e) {
//			System.out.println("error!");
//			e.printStackTrace();
		}
		return false;
	}

	static void registerUser(String email, String name, String pass) {
		try {
			Connection com;
			com = DriverManager.getConnection(url, user, password);
//			if(!com.isClosed())
//				System.out.println("connection successful!");
			Statement statement = com.createStatement();

			String sql = "USE test";
			statement.executeQuery(sql);
			// the following is for my database
                        statement.executeUpdate("INSERT INTO users (email,password,user_name)VALUES(\"" + email + "\",\"" + pass
					+ "\",\"" + name + "\");");
			// System.out.println();

		} catch (Exception e) {
			// System.out.println("error!");
			// e.printStackTrace();
		}

	}

	static void resetPassword(String email, String pass) {
		try {
			Connection com;
			com = DriverManager.getConnection(url, user, password);
//			if(!com.isClosed())
//				System.out.println("connection successful!");
			Statement statement = com.createStatement();

			String sql = "USE test";
			statement.executeQuery(sql);
			// the following is for my database
			statement.executeUpdate(
					"UPDATE users SET password = '"+pass+"' WHERE email = '"+email + "';");
			// System.out.println();

		} catch (Exception e) {
			// System.out.println("error!");
			// e.printStackTrace();
		}

	}

	static String[] getUserInfo(String email) {
		String id = null, name = null, userPassword = null, subscriberCount = null, userEmail = null; 
		try {
			Connection com;
			com = DriverManager.getConnection(url, user, password);
//			if(!com.isClosed())
//				System.out.println("connection successful!");
			Statement statement = com.createStatement();

			String sql = "USE test";
			statement.executeQuery(sql);

			ResultSet rs = statement.executeQuery("SELECT user_id, user_name, password, subscriber_count FROM users WHERE email='" + email + "';");
			if (rs.next()) { 
				id = rs.getString("user_id");
				name = rs.getString("user_name");
                                userPassword = rs.getString("password");
                                subscriberCount = rs.getString("subscriber_count");
                                userEmail = email;
                                
			}

		} catch (Exception e) {
			// System.out.println("error!");
			// e.printStackTrace();
		}
		return new String[] { id, name, userPassword, subscriberCount,userEmail};

	}

}
