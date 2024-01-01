package br.com.uva.services;

import java.sql.SQLException;

import br.com.uva.dao.LoginDAO;
import br.com.uva.model.User;

public class LoginAuthenticationService {

	public User login(String username, String password) throws SQLException {
		return LoginDAO.getUserIfAuthenticated(username, password);
	}

}
