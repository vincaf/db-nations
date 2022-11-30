package org.nations.italy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/nations";
		String user = "root";
		String password = "root";
		 
		Connection con = null;
		try {
			
			con = DriverManager.getConnection(url, user, password);
			
			final String sql = "SELECT country_id, countries.name AS 'country_name', regions.name AS 'region_name', continents.name AS 'continent_name'\r\n"
					+ "FROM nations.countries\r\n"
					+ "JOIN regions\r\n"
					+ "	ON countries.region_id = regions.region_id\r\n"
					+ "JOIN continents\r\n"
					+ "	ON regions.continent_id = continents.continent_id\r\n"
					+ "ORDER BY countries.name;";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				final int id = rs.getInt(1);
				final String name = rs.getString(2);
				
				System.out.println(id + " - " + name);
			}
			
			ps.close();
			
		} catch (SQLException ex) {
			
			ex.printStackTrace();
		} finally{
			
			try {
				con.close();
			} catch (Exception e) { }
		}
	}

}
