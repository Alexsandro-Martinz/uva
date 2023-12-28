package br.com.uva.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.uva.connection.SingleConnection;

public class DatabaseConfig {
	
	private static String sql = 
			"""
			CREATE TABLE IF NOT EXISTS users(
				id SERIAL PRIMARY KEY,
				username VARCHAR(200) UNIQUE NOT NULL,
				firstName VARCHAR(200) NOT NULL,
				lastName VARCHAR(200) NOT NULL,
				document VARCHAR(200) UNIQUE NOT NULL,
				password VARCHAR(200) NOT NULL
			)
			""";
	
	public static void config() {
		Connection connection = SingleConnection.getConnection();
		try {
			connection.prepareStatement(sql).execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
