package controller;

import data.Customer;
import data.DataStorage;
import data.Readings;
import data.Staff;

public class Controller {
    private DataStorage ds = new DataStorage();
    
    
    public void initialiseUsers(){
        ds.addUser(new Customer("John_Doe", "password"));
        ds.addStaff(new Staff("1234ABC", "password"));
        this.addReading("Gas", 0.24, "kWh", 2);
    	this.addReading("Water", 1.40, "Cu_M", 10);
    }

    public boolean isUser(String name) {
        return name.equals(ds.getUser(name).getUsername());
    }
    
    public boolean isStaff(String id){
    	return id.equals(ds.getStaff(id).getUsername());
    }

    public boolean verifyUser(String name, String password){
        Customer u = ds.getUser(name);
        if (u!=null){
            return u.getPassword().equals(password);

        }
        else {return false;}
    }

    public boolean verifyStaff(String id, String password){
        Staff u = ds.getStaff(id);
        if (u!=null){
            return u.getPassword().equals(password);

        }
        else {return false;}
    }

    public void editUser(String uname, String newFName, String newEmail, String newUName, String newPass, String newAddress){
        
    }

    public void editStaff(String id, String newID, String newPassword){ 
        if (!isStaff(id)){return;}
        Staff s = new Staff(newID, newPassword);
        ds.editStaff(id, s);
    }

    public Readings[] allReadings(){
    	Readings[] r=ds.getAllReadings();
    	return r;
    }
    public String[] getStaff(String id){
        Staff s = ds.getStaff(id);
        String[] x = {s.getUsername(),s.getPassword()};
        return x;
    }
    public Customer getCustomer(String name){
        return ds.getUser(name);
    }

    public void addUser(String name, String password){
        Customer c = new Customer(name, password);
        ds.addUser(c);
    }
    public void addStaff(String id, String pass){
    	Staff s= new Staff(id,pass);
    	ds.addStaff(s);
    }
    public void addReading(String name, double price, String unit, double serviceCharge){
    	Readings readings = new Readings(name, price, unit, serviceCharge);
    	ds.addReading(readings);
    }
    
    public void updateReading(String name, double price, String unit, double serviceCharge, int index){
    	System.out.println(name+":"+price+":"+serviceCharge+":"+index);
    	Readings readings = new Readings(name, price, unit, serviceCharge);
    	ds.updateReading(readings, index);
    }
    
    public void removeReading(int index){
    	ds.removeReading(index);
    }

    public DataStorage getDS() {
        return ds;
    }

    public void setDS(DataStorage ds) {
        this.ds = ds;
    }

}
