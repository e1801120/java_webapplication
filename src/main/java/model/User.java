package model;



public class User {
	
	
	private String username;
	private String name;
	private String password;
	private byte enabled = 1;
	private String role = "ROLE_USER";
	
	
	public User() {
	}
	
		 public User(String username, String name, String password) {
		        this.username = username;
		        this.name = name;
		        this.password = password;
		        
		        
		       
		    }
		 
		 public String getUsername() {
		        return this.username;
		    }

		    public void setUsername(String username) {
		        this.username = username;
		    }

		    public String getName() {
		        return this.name;
		    }

		    public void setName(String name) {
		        this.name = name;
		    }

		    public String getPassword() {
		        return this.password;
		    }

		    public void setPassword(String password) {
		        this.password = password;
		    }

		    public byte getEnabled() {
		        return this.enabled;
		    }

		    public void setEnabled(byte enabled) {
		        this.enabled = enabled;
		    }
		
		    public String getRole() {
		        return this.role;
		    }

		    public void setRole(String role) {
		        this.role = role;
		    }


}


