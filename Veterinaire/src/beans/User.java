package beans;

public class User {
	private Long id;
	private String nomComplet;
	private String email;
	private String password;
	private String Role;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public User(String nomComplet, String email, String password, String role) {
		super();
		this.nomComplet = nomComplet;
		this.email = email;
		this.password = password;
		Role = role;
	}
	public User() {
		super();
	}
}