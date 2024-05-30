package data;

public class Account {
    private String username;
    private String password;
    
    public Account(){
        this.username = null;
        this.password = null;
    }

    /*username, password, is Staff? */
    public Account(String name, String pass){
        this.username = name;
        this.password = pass;
        
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

}
