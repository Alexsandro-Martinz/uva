package br.com.uva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.uva.connection.SingleConnection;
import br.com.uva.model.User;

public class UserDAO {

	private Connection conn;

	public List<User> getUsers(String search) {

		List<User> users = new ArrayList<User>();

		try {

			String sql = "";
			conn = SingleConnection.getConnection();
			PreparedStatement stm = null;

			if (!search.equals("")) {
				sql = "SELECT * FROM users WHERE username LIKE ? ORDER BY id DESC LIMIT 13";
				stm = conn.prepareStatement(sql);
				stm.setString(1, "%" + search + "%");
			} else {
				sql = "SELECT * FROM users ORDER BY id DESC LIMIT 13";
				stm = conn.prepareStatement(sql);
			}

			ResultSet result = stm.executeQuery();
			while (result.next()) {

				User user = new User();
				user.setId(result.getLong("id"));
				user.setUsername(result.getString("username"));
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				user.setDocument(result.getString("document"));
				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	public Boolean create(User user) {
		try {
			String sql = """
						INSERT INTO public.users (username,firstName,lastName,document,password)
						VALUES (?,?,?,?,?) ON CONFLICT DO NOTHING
						RETURNING id
					""";

			conn = SingleConnection.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getFirstName());
			stm.setString(3, user.getLastName());
			stm.setString(4, user.getDocument());
			stm.setString(5, user.getPassword());

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
	
		sql = sql.substring(0, sql.length()-1);

		
		sql += " WHERE id=?";

		conn = SingleConnection.getConnection();
		try {
			PreparedStatement stm = conn.prepareStatement(sql);
			
			int count = 1;
			
			for(String data : sqlData){
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
