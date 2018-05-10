package pl.stacje;

import java.sql.*;
import java.util.ArrayList;


public class DBConnection {
	Connection myConnection;
	public DBConnection() {
		String jdbcURL = "jdbc:mysql://localhost:3306/app?useSSL=false";
		String user = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			myConnection = DriverManager.getConnection(jdbcURL, user, password);
			Statement statement = myConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from stacje");
			while (resultSet.next()){
				System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " 
						+ resultSet.getString(4) + " " + resultSet.getInt(5) + " " + resultSet.getInt(6) + " " + resultSet.getInt(7));
			}
//			myConnection.close();
		} catch (Exception e) {
			System.err.println("Połączenie z baza danych nieudane");
//			e.printStackTrace();			
		}
	}
	
	public ArrayList<DaneStacji> getStacje() {
		ArrayList<DaneStacji> stacje = new ArrayList<DaneStacji>();
		
	
		
		return stacje;
	}
}
