package data;

public class Account {
    // Properties to store the username and password for the account
    private String username;
    private String password;
    
    // Default constructor initializing username and password to null
    public Account(){
        this.username = null;
        this.password = null;
    }

    /* 
     * Parameterized constructor to initialize the account with a specific 
     * username and password 
     */
    public Account(String name, String pass){
        this.username = name;
        this.password = pass;
    }

    // Getter method for retrieving the username
    public String getUsername() {
        return username;
    }

    // Setter method for updating the username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for retrieving the password
    public String getPassword() {
        return password;
    }

    // Setter method for updating the password
    public void setPassword(String password) {
        this.password = password;
    }
}
