package br.com.uva.model;

import java.util.HashMap;

public enum Role {
	USER, SUPPORT, ADMINISTRATOR;

	private static HashMap<String, Role> roles = new HashMap<String, Role>();
	static {
		roles.put("administrator", ADMINISTRATOR);
		roles.put("support", SUPPORT);
		roles.put("user", USER);
	}
	
	public static Role getRole(String role) {
		return roles.get(role);
	}
}
