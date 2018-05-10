package pl.stacje;

import java.sql.*;

public class DBConnection {
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "root", "root");
			Statement statement = myConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from stacje");
			while (resultSet.next()){
				System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " 
						+ resultSet.getString(4) + " " + resultSet.getInt(5) + " " + resultSet.getInt(6) + " " + resultSet.getInt(7));
			}
			myConnection.close();
		} catch (Exception e) {
			System.err.println("Połączenie z baza danych nieudane");
//			e.printStackTrace();			
		}
	}
}
