package br.com.uva.services;

import br.com.uva.model.User;

public class UserValidateData {
	
	private String username;
	private String firstName;
	private String lastName;
	private String document;
	private String password;

	public UserValidateData(String username, String firstName, String lastName, String document, String password) {

		validadeField(username, "Username invalid");
		validadeField(firstName, "First name invalid");
		validadeField(lastName, "Last name invalid");
		validadeField(document, "Document invalid");
		validadeField(password, "Password invalid");
		
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.document = document;
		this.password = password;
	}
	
	public User getUser() {
		User user = new User();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDocument(document);
		user.setPassword(password);
		return user;
	}
	
	private void validadeField(String field, String messageError) {
		if(field == null || field.isEmpty()) {
			throw new IllegalArgumentException(messageError);
		}
		
	}
	
}
