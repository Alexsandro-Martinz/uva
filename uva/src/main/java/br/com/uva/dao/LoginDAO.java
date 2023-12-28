package br.com.uva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.uva.connection.SingleConnection;
import br.com.uva.model.User;

public class LoginDAO {

	public static User getUserIfAuthenticated(String username, String password) {

		try {
			String sql = """
						SELECT * FROM users
						WHERE username = ? and password = ?;
					""";

			Connection conn = SingleConnection.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, password);

			ResultSet result = stm.executeQuery();

			if (result.next()) {
				User user = new User();
				user.setId(result.getLong("id"));
				user.setUsername(username);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
