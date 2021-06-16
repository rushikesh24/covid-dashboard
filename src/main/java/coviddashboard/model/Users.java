package coviddashboard.model;

public class Users {

	private String name;
	private String username;
	private String password;
	private String email;
	private String mobileNumber;
	private String roles;
	private String approved;

	public Users() {

	}

	public Users(String name, String username, String password, String email, String mobileNumber, String roles,
			String approved) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.roles = roles;
		this.approved = approved;
	}

	public Users(String name, String email, String mobileNumber, String roles, String approved) {
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.roles = roles;
		this.approved = approved;
	}
	
	public Users(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRoles() {
		return this.roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getApproved() {
		return this.approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Users [name=" + name + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", roles=" + roles + ", approved=" + approved + "]";
	}
	
	
}
