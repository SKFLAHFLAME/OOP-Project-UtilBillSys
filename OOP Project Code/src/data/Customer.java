package data;

import java.util.Vector;

public class Customer extends Account{
    // private String address;
    private Vector<Readings> userReadings = new Vector<>();
    
    public Customer(){
        super();
    }
    public Customer(String name, String pass){
        super(name,pass);
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
    
}
