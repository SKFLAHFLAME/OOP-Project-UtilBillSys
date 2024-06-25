package controller;

import data.Customer;
import data.DataStorage;
import data.Staff;

public class Controller {
    private DataStorage ds = new DataStorage();
    public void initialiseUsers(){
        ds.addUser(new Customer("John_Doe", "password"));
        ds.addStaff(new Staff("1234ABC", "password"));
    }

    public boolean isUser(String name) {
        return name.equals(ds.getUser(name).getUsername());
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

    public void addUser(String name, String password){
        Customer c = new Customer(name, password);
        ds.addUser(c);
    }

    public void editUser(){
        
    }

    public DataStorage getDS() {
        return ds;
    }

    public void setDS(DataStorage ds) {
        this.ds = ds;
    }

}
