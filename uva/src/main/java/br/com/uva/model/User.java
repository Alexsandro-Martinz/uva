package br.com.uva.model;

import java.util.Objects;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String document;
	private String username;
	private String password;
	private Role role;
	
	private String photo;
	private String photoExtension;

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhotoExtension() {
		return photoExtension;
	}
	public void setPhotoExtension(String photoExtension) {
		this.photoExtension = photoExtension;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public User() {
		super();
	}
	public User(long id, String firstName, String lastName, String document, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.document = document;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", document=" + document
				+ ", username=" + username + ", role=" + role + ", photo=" + photo + ", photoExtension="
				+ photoExtension + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(document, id, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(document, other.document) && id == other.id && Objects.equals(username, other.username);
	}
	
	
	
}
