package controller;

import data.Customer;
import data.DataStorage;
import data.Readings;
import data.Staff;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.sql.Time;
import java.util.Date;

public class Controller {
    private DataStorage ds = new DataStorage();
    private CSVRW csv = new CSVRW();
    
    
    public void initialiseItems(){
        // ds.addUser(new Customer("John_Doe", "password"));
        // ds.addStaff(new Staff("1234ABC", "password"));
        // this.addReading("Gas", 0.24, "kWh", 2);
    	// this.addReading("Water", 1.40, "Cu_M", 10);
        String[][] x = {{"John", "1","15/7/24"}, {"Gas","10", "90"},{"Water", "30", "120"}};
        String [][] x2 = {{"John", "2","16/8/24"},{"Gas","13","100"},{"Water", "46", "170"},{"Electricity","60", "30"}};
        ds.addUserReading(x);
        ds.addUserReading(x2);
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
    
    public Staff[] getAllStaff() {
    	return ds.getAllStaff();
    }
    public Customer getCustomer(String name){
        return ds.getUser(name);
    }
    public Customer[] getAllCustomers() {
    	return ds.getAllUser();
    }
    public Readings[] getAllReadings(){
        return ds.getAllReadings();
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
    
    
    //! User Readings
    public void submitUserReading(String userName){
        String[][][] userReadings=ds.getUserReadings(userName);
        String[][] draft = ds.getDraft(userName);
        String[] initials = {userName, String.valueOf(userReadings.length+1)};
        String[][] final = new String[draft.length+1][3];
        
    }
    public void add(String[][] userReading){
        ds.addUserReading(userReading);
    }

    public String[][][] getUserReading(String userName) {
    	return ds.getUserReadings(userName);
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
        Readings[] readings= ds.getAllReadings();
        String[][][] userReadings = ds.getAllUserReadings();

        //Diff Types to String[][]
        //!Staff 
        String[][] staffData = new String[staffs.length-1][2];
        int c=0;
        for (int i = 0; i < staffs.length; i++) {
            if (staffs[i].getUsername().equals("admin")){continue;}
            staffData[c][0] = staffs[i].getUsername();
            staffData[c][1] = staffs[i].getPassword();
            c+=1;
        }

        //!Customer (UserName, Password, Full Name, Email, Address, Draft)
        String[][] customerData = new String[customer.length][6];
        for (int i = 0; i < customer.length; i++) {
            customerData[i][0] = customer[i].getUsername();
            customerData[i][1] = customer[i].getPassword();
            customerData[i][2] = customer[i].getName();
            customerData[i][3] = customer[i].getEmail();
            customerData[i][4] = customer[i].getAddress();
            String[][] draft = customer[i].getDraftArray();
            String d = new String();
            for (String [] string : draft) {
                d= d+ string[0]+":"+string[1]+"-";
            }
            customerData[i][5] = d;
        }

        //!Readings
        String[][] readingsData = new String[readings.length][4];
        for (int i = 0; i < readings.length; i++) {
            readingsData[i][0] = readings[i].getUtilityName();
            readingsData[i][1] = Double.toString(readings[i].getPrice());
            readingsData[i][2] = readings[i].getUnit();
            readingsData[i][3] = Double.toString(readings[i].getServiceCharge());
        }

        //!UserReadings
        String[][][] userR = new String[userReadings.length][][];


        //store into csv files
        String cdir = System.getProperty("java.class.path");
        String[] cp = cdir.split(System.getProperty("file.separator"));
        cp[cp.length-1] = "src";
        cdir = String.join(System.getProperty("file.separator"), cp);
        
        try {
            csv.csvWriter(cdir+"/datafiles/Staff.csv", staffData);
            csv.csvWriter(cdir+"/datafiles/Customer.csv", customerData);
            csv.csvWriter(cdir+"/datafiles/Readings.csv", readingsData);
            // csv.csvWriter("user_readings.csv", userReadings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void syncData(){
        try {
            //Read files for data
        	String cdir = System.getProperty("java.class.path");
            String[] cp = cdir.split(System.getProperty("file.separator"));
            cp[cp.length-1] = "src";
            cdir = String.join(System.getProperty("file.separator"), cp);
            
            String[][] customers = csv.csvReader(cdir+"/datafiles/Customer.csv");
            String[][] staffAcct = csv.csvReader(cdir+"/datafiles/Staff.csv");
            String[][] readings = csv.csvReader(cdir+"/datafiles/Readings.csv");
            String[][] userReadings = csv.csvReader(cdir+"/datafiles/UserReadings.csv");
            
            //Creating and adding csv contents into vectors
            //!customer
            for(String[]c:customers){
                this.addUser(c[0], c[1], c[2],c[3],c[4]);
                StringTokenizer st = new StringTokenizer(c[5],"-");
                while (st.hasMoreTokens()){
                    String reading = st.nextToken();
                    StringTokenizer st2 = new StringTokenizer(reading,":");
                    String[] items = new String[2];
                    int i =0;
                    while (st2.hasMoreTokens()){
                        items[i]=st2.nextToken();
                        i+=1;
                    }
                    ds.addMeterReading(c[0], items[0], Double.valueOf(items[1]));
                }
            }
            
            //!Staff
            for(String[]s:staffAcct){
                this.addStaff(s[0], s[1]);
            }
            
            //!Readings
            for(String[]r:readings){
                this.addReading(r[0], Double.parseDouble(r[1]), r[2], Double.parseDouble(r[3]));
            }

            //!User Readings
            for(String[] ur:userReadings){
                
            }


        } catch (FileNotFoundException ex) {

        }

    }

}
