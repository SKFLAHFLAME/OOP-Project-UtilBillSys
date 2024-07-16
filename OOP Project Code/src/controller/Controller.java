package controller;

import data.Customer;
import data.DataStorage;
import data.Readings;
import data.Staff;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    private DataStorage ds = new DataStorage();
    private CSVRW csv = new CSVRW();
    
    
    public void initialiseItems(){
        // ds.addUser(new Customer("John_Doe", "password"));
        // ds.addStaff(new Staff("1234ABC", "password"));
        // this.addReading("Gas", 0.24, "kWh", 2);
    	// this.addReading("Water", 1.40, "Cu_M", 10);
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

    public void editUser(String oldUName, String newFName, String newEmail, String newUName, String newPass, String newAddress){
        if(!this.isUser(oldUName)){return;}
        Customer c = new Customer(newFName, newEmail, newUName, newPass, newAddress);
        ds.editUser(oldUName, c);
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
    
    public void addUser(String username, String password){
        Customer c = new Customer(username, password);
        ds.addUser(c);
    }

    public void addUser(String username, String password,String fullName, String email){
        Customer c = new Customer(fullName, email, username, password);
        ds.addUser(c);
    }
    public void addUser(String username, String password,String fullName, String email,String address){
        Customer c = new Customer(fullName, email, username, password, address);
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

    //!Meter Readings
    public void addMeterReading(String uName, String readingName, Double meterReading) {
		ds.addMeterReading(uName, readingName, meterReading);
	}
    public boolean hasDraft(String UName){
        return ds.hasDraft(UName);
    }


    public DataStorage getDS() {
        return ds;
    }

    public void setDS(DataStorage ds) {
        this.ds = ds;
    }




    //!CSV methods
    public String[][] readCSV(String filepath) throws FileNotFoundException {
    	return csv.csvReader(filepath);
    }

    public void saveData(){
        Customer[] customer = ds.getAllUser();
        Staff[] staffs = ds.getAllStaff(); 
        Readings[] readings = ds.getAllReadings();
        String[][] userReadings = ds.getAllUserReadings();

        //Diff Types to String[][]
        //Staff 
        String[][] staffData = new String[staffs.length-1][2];
        int c=0;
        for (int i = 0; i < staffs.length; i++) {
            if (staffs[i].getUsername().equals("admin")){continue;}
            staffData[c][0] = staffs[i].getUsername();
            staffData[c][1] = staffs[i].getPassword();
            c+=1;
        }

        //Customer
        String[][] customerData = new String[customer.length][4];
        for (int i = 0; i < customer.length; i++) {
            customerData[i][0] = customer[i].getUsername();
            customerData[i][1] = customer[i].getPassword();
            customerData[i][2] = customer[i].getName();
            customerData[i][3] = customer[i].getEmail();
        }

        //Readings
        String[][] readingsData = new String[readings.length][4];
        for (int i = 0; i < readings.length; i++) {
            readingsData[i][0] = readings[i].getUtilityName();
            readingsData[i][1] = Double.toString(readings[i].getPrice());
            readingsData[i][2] = readings[i].getUnit();
            readingsData[i][3] = Double.toString(readings[i].getServiceCharge());
        }

        //store into csv files
        try {
            csv.csvWriter("OOP Project Code/src/datafiles/Staff.csv", staffData);
            csv.csvWriter("OOP Project Code/src/datafiles/Customer.csv", customerData);
            csv.csvWriter("OOP Project Code/src/datafiles/Readings.csv", readingsData);
            // csv.csvWriter("user_readings.csv", userReadings);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void syncData(){
        try {
            //Read files for data
            String[][] customers = csv.csvReader("OOP Project Code/src/datafiles/Customer.csv");
            String[][] staffAcct = csv.csvReader("OOP Project Code/src/datafiles/Staff.csv");
            String[][] readings = csv.csvReader("OOP Project Code/src/datafiles/Readings.csv");
            
            //Creating and adding csv contents into vectors
            //customer
            for(String[]c:customers){
                this.addUser(c[0], c[1], c[2],c[3]);
            }
            
            //Staff
            for(String[]s:staffAcct){
                this.addStaff(s[0], s[1]);
            }
            
            //Readings
            for(String[]r:readings){
                this.addReading(r[0], Double.parseDouble(r[1]), r[2], Double.parseDouble(r[3]));
            }
        } catch (FileNotFoundException ex) {
        }

    }

}
