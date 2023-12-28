package br.com.uva.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String bank = "jdbc:postgresql://localhost:5432/uva?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "131620";
	private static Connection connection = null;

	public static Connection getConnection() {
		try {

			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(bank, user, password);
				connection.setAutoCommit(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

}
