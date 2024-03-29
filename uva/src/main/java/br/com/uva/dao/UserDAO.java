package br.com.uva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.uva.connection.SingleConnection;
import br.com.uva.model.Role;
import br.com.uva.model.User;

public class UserDAO {

	private Connection conn;
	private final int LIMIT = 13;


	private List<User> getUsersGeneric(String search, int roleId, Integer offset) {

		List<User> users = new ArrayList<User>();
		
		if (offset == null) {
			offset = 0;
		}
		
		offset = (offset-1) * LIMIT;
		
		
		try {
			String sql = "";
			conn = SingleConnection.getConnection();
			PreparedStatement stm = null;

			if (!search.equals("")) {
				sql = """
						SELECT
						users.id, users.username, users.firstName, users.lastName, users.document, users.photo, roles.name as role
						from
							users
						inner join roles on users.role_id = roles.id
						WHERE
							username LIKE ? AND users.role_id >= ?
						ORDER BY id DESC LIMIT 
						 """ + LIMIT + " Offset " + offset;

				stm = conn.prepareStatement(sql);
				stm.setString(1, "%" + search + "%");
				stm.setLong(2, roleId);
			} else {
				sql = """
						SELECT
							USERS.id, USERS.username, USERS.firstName, USERS.lastName,
							USERS.document, USERS.photo, roles.name as role
						FROM
							USERS
						INNER JOIN roles ON users.role_id = roles.id
						WHERE
							users.role_id >= ?
						ORDER BY id DESC LIMIT 
						""" + LIMIT + " OFFSET "+ offset;
				stm = conn.prepareStatement(sql);
				stm.setLong(1, roleId);
			}

			ResultSet result = stm.executeQuery();

			while (result.next()) {

				User user = new User();
				user.setId(result.getLong("id"));
				user.setUsername(result.getString("username"));
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				user.setDocument(result.getString("document"));
				user.setRole(Role.getRole(result.getString("role")));
				//user.setPhoto(result.getString("photo"));
				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	private Double countUsersGeneric(String search, int roleId) {
		double pages = 0D;
		try {
			String sql = "";
			conn = SingleConnection.getConnection();
			PreparedStatement stm = null;

			if (!search.equals("")) {
				sql = """
						SELECT count(*) FROM users
						inner join roles on users.role_id = roles.id
						WHERE username LIKE ? AND users.role_id >= ? """;

				stm = conn.prepareStatement(sql);
				stm.setString(1, "%" + search + "%");
				stm.setLong(2, roleId);
			} else {
				sql = """
						SELECT COUNT(*) FROM USERS
						INNER JOIN roles ON users.role_id = roles.id
						WHERE users.role_id >= ? """;

				stm = conn.prepareStatement(sql);
				stm.setLong(1, roleId);
			}

			ResultSet result = stm.executeQuery();

			if(result.next()) {
				Double count = Double.parseDouble(result.getString("count"));
				pages = Math.ceil(count / LIMIT);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pages;

	}

	public List<User> getUsers(String search, int offset) {
		return getUsersGeneric(search, 3, offset);
	}

	public List<User> getUsersSupports(String search, int offset) {
		return getUsersGeneric(search, 2, offset);
	}
	
	public Double countUsers(String search) {
		return countUsersGeneric(search, 3);
	}

	public Double countUsersSupports(String search) {
		return countUsersGeneric(search, 2);	}


	public Boolean create(User user, Long userType) {
		try {
			String sql = """
						INSERT INTO public.users (username,firstName,lastName,document,password, role_id, photo, photoExtension)
						VALUES (?,?,?,?,?,?,?,?) ON CONFLICT DO NOTHING
						RETURNING id
					""";

			conn = SingleConnection.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getFirstName());
			stm.setString(3, user.getLastName());
			stm.setString(4, user.getDocument());
			stm.setString(5, user.getPassword());
			stm.setLong(6, userType);
			stm.setString(7, user.getPhoto());
			stm.setString(8, user.getPhotoExtension());
			ResultSet result = stm.executeQuery();
			if (result.next()) {
				conn.commit();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void delete(Long id) {
		try {
			String sql = "DELETE FROM users WHERE id=?";
			conn = SingleConnection.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setLong(1, id);
			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void update(User user) {

		List<String> sqlData = new ArrayList<String>();
		String sql = "UPDATE users SET ";

		if (!user.getUsername().equals("")) {
			sql += " username = ? ,";
			sqlData.add(user.getUsername());
		}

		if (!user.getFirstName().equals("")) {
			sql += " firstName = ? ,";
			sqlData.add(user.getFirstName());
		}

		if (!user.getLastName().equals("")) {
			sql += " lastName = ? ,";
			sqlData.add(user.getLastName());
		}

		if (!user.getDocument().equals("")) {
			sql += " document = ? ,";
			sqlData.add(user.getDocument());
		}

		sql = sql.substring(0, sql.length() - 1);

		sql += " WHERE id=?";

		conn = SingleConnection.getConnection();
		try {
			PreparedStatement stm = conn.prepareStatement(sql);

			int count = 1;

			for (String data : sqlData) {
				try {
					stm.setString(count, data);
					count++;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			stm.setLong(count, user.getId());
			stm.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

}
