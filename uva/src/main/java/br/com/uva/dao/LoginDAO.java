package br.com.uva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.uva.connection.SingleConnection;
import br.com.uva.model.Role;
import br.com.uva.model.User;

public class LoginDAO {

	public static User getUserIfAuthenticated(String username, String password) throws SQLException {

		String authenticationSql = """
					SELECT
						USERS.id, USERS.username, USERS.firstName, USERS.lastName,
						USERS.document, roles.name as role
					FROM
						USERS
					INNER JOIN roles ON users.role_id = roles.id
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
		user.setUsername(userResult.getString("username"));
		user.setFirstName(userResult.getString("firstName"));
		user.setLastName(userResult.getString("lastName"));
		user.setDocument(userResult.getString("document"));
		user.setRole(Role.getRole(userResult.getString("role")));

		return user;
	}

}
