package tris;

/**
 *	Questa classe rappresenta un oggetto di tipo User, necessaria quando bisogna 
 *	inserire/recuperare le tuple dal database.
 */

public class UserConcreto {
	private String username;
	private String password;
	
	public UserConcreto(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public UserConcreto() {};

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
	

}
