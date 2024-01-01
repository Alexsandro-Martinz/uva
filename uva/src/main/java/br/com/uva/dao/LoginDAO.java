package br.com.uva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.uva.connection.SingleConnection;
import br.com.uva.model.User;

public class LoginDAO {

	public static User getUserIfAuthenticated(String username, String password) throws SQLException {

		String authenticationSql = """
					SELECT * FROM users
					WHERE username = ? and password = ?;
				""";

		Connection conn = SingleConnection.getConnection();
		PreparedStatement authenticationStm = conn.prepareStatement(authenticationSql);
		authenticationStm.setString(1, username);
		authenticationStm.setString(2, password);

		ResultSet userResult = authenticationStm.executeQuery();

		if (!userResult.next()) {
			return null;
		}

		User user = new User();
		user.setId(userResult.getLong("id"));
		user.setUsername(username);

		String userRoleSql = "SELECT * FROM users_roles WHERE user_id=?";
		PreparedStatement userRolesStm = conn.prepareStatement(userRoleSql);
		userRolesStm.setLong(1, user.getId());
		ResultSet userRolesResult = userRolesStm.executeQuery();
		
		while(userRolesResult.next()) {
			System.err.println(userRolesResult.getLong("role_id"));
		}
		
		return user;
	}

}
