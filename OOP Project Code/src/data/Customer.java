package data;

import java.util.Vector;

public class Customer extends Account{
    // private String address;
    private Vector<Readings> userReadings = new Vector<>();
    private String name;
	private String email;
    private String address;
    

    public Customer(){
        super();
        this.email = null;
        this.name = null;
    }
    public Customer(String uname, String pass){
        super(uname,pass);
        this.email = null;
        this.name = null;
    }
    
    public Customer(String fname, String email, String uname, String pass){
        super(uname,pass);
        this.email = email;
        this.name = fname;
    }


    public Vector<Readings> getUserReadings() {
        return userReadings;
    }
    public void setUserReadings(Vector<Readings> userReadings) {
        this.userReadings = userReadings;
    }
    public void addReading(String utilityName, double readings){
        Readings r = new Readings();
        this.userReadings.add(r);
    }
    
    

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }  
}
