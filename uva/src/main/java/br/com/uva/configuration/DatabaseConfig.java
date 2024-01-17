package br.com.uva.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.uva.connection.SingleConnection;

public class DatabaseConfig {

	

	private static String createTableSql = """

				CREATE TABLE IF NOT EXISTS roles(
					id SERIAL PRIMARY KEY,
					name VARCHAR(200) UNIQUE NOT NULL);

				CREATE TABLE IF NOT EXISTS users(
					id SERIAL PRIMARY KEY,
					username VARCHAR(200) UNIQUE NOT NULL,
					firstName VARCHAR(200) NOT NULL,
					lastName VARCHAR(200) NOT NULL,
					document VARCHAR(200) UNIQUE NOT NULL,
					password VARCHAR(200) NOT NULL,
					role_id INTEGER DEFAULT 3,
					
					photo TEXT,
					photoExtension VARCHAR(10),
					
					FOREIGN KEY (role_id)
						REFERENCES roles(id)
				);

				""";

	
	private static String insertSql = """

			INSERT INTO roles(name)
			VALUES ('administrator'), ('support'), ('user')
			ON CONFLICT DO NOTHING;

			INSERT INTO users(username, firstName, lastName, document, password, role_id)
			values ('admin', 'alex', 'silva', '654545546654', 'admin', 1)
			ON CONFLICT DO NOTHING;

			""";
	
	private static String dropTablesSql = """
			
			DROP TABLE users, roles;
			
			""";
	
	
	public static void config() {
//		runSql(dropTablesSql);
		runSql(createTableSql);
		runSql(insertSql);
	}
	
	private static void runSql(String sql) {
		Connection connection = SingleConnection.getConnection();
		try {
			connection.prepareStatement(sql).execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: class: DatabaseConfig.java method: runSql()");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
