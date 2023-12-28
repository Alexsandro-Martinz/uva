package br.com.uva.services;

import br.com.uva.dao.LoginDAO;
import br.com.uva.model.User;

public class LoginAuthenticationService {

	public User login(String username, String password) {
		return LoginDAO.getUserIfAuthenticated(username, password);
	}

}
