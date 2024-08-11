package data;

public class Staff extends Account{

    // Default constructor that calls the superclass constructor
    public Staff(){
        super();
    }

    // Constructor that initializes the Staff object with a username and password
    public Staff(String name, String pass){
        super(name, pass);
    }
    
}
