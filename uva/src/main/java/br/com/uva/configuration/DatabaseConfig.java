package br.com.uva.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JList.DropLocation;

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
					password VARCHAR(200) NOT NULL
				);

				CREATE TABLE IF NOT EXISTS users_roles(
					id SERIAL PRIMARY KEY,
					user_id integer not null,
					role_id integer not null,
					FOREIGN KEY (user_id)
						REFERENCES users(id),
					FOREIGN KEY (role_id)
						REFERENCES roles(id)
				);

				""";

	
	private static String insertSql = """

			INSERT INTO roles(name)
			VALUES ('administrator'), ('support'), ('user')
			ON CONFLICT DO NOTHING;

			INSERT INTO users(username, firstName, lastName, document, password)
			values ('admin', 'alex', 'silva', '654545546654', 'admin')
			ON CONFLICT DO NOTHING;
			
			INSERT INTO users_roles(user_id, role_id)
			SELECT 1,1
			WHERE NOT EXISTS (
				SELECT * FROM users_roles 
				WHERE user_id = 1 and role_id = 1
			);

			""";
	
	private static String dropTablesSql = """
			
			DROP TABLE users_roles;
			DROP TABLE users, roles;
			
			""";
	
	
	public static void config() {
		runSql(dropTablesSql);
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
