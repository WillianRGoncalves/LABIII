package hello;



import java.util.LinkedList;
import java.util.List;

public class User {
	
	private String userName;
	private String password;
	private int acesso;
	
	
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAcesso() {
		return acesso;
	}
	public void setAcesso(int acesso) {
		this.acesso = acesso;
	}
	
	
	
	
	
	
}
