package tris;

/**
 *  Interfaccia che indica le funzioni necessarie che deve avere un @DBUser
 */

public interface User {
	
	public boolean login(String username, String password) throws Exception; 
	public boolean register(UserConcreto user) throws Exception;

}
