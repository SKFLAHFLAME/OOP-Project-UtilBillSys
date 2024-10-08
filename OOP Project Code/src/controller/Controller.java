package controller;

import data.Customer;
import data.DataStorage;
import data.Readings;
import data.Staff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Controller {
    private DataStorage ds = new DataStorage();	// Data storage instance to manage data
    private String[] systemDate = new String[2];//MM, YYYY
    public String cdir;
    
    
    public void initialiseItems(){
        // ds.addUser(new Customer("John_Doe", "password"));
        // ds.addStaff(new Staff("1234ABC", "password"));
        // this.addReading("Gas", 0.24, "kWh", 2);
    	// this.addReading("Water", 1.40, "Cu_M", 10);
//        String[][] x = {{"John", "1","15/7/24"}, {"Gas","10", "90"},{"Water", "30", "120"}};
//        String [][] x2 = {{"John", "2","16/8/24"},{"Gas","13","100"},{"Water", "46", "170"},{"Electricity","60", "30"}};
//        ds.addUserReading(x);
//        ds.addUserReading(x2);
//        String [][] m = {{"Mark", "1","16/7/24"},{"Gas","13","100"}};
//        ds.addUserReading(m);
    }

    //To check if a given username corresponds to a user in the system.
    public boolean isUser(String name) {
    	try {
    		return name.equals(ds.getUser(name).getUsername());
		} catch (Exception e) {
			return false;
		}
        
    }
    
    //To verify if a given name belongs to a staff member.
    public boolean isStaff(String id){
    	try {
			return id.equals(ds.getStaff(id).getUsername());
		} catch (Exception e) {
			return false;
		}
    }

    //Check username and password
    public boolean verifyUser(String name, String password){
        Customer u = ds.getUser(name);
        if (u!=null){
            return u.getPassword().equals(password);

        }
        else {return false;}
    }
    
    //Check id and password
    public boolean verifyStaff(String id, String password){
        Staff u = ds.getStaff(id);
        if (u!=null){
            return u.getPassword().equals(password);

        }
        else {return false;}
    }

    //Edit and change old name, password etc into new name, password etc
    public void editUser(String oldUName, String newFName, String newEmail, String newPass, String newAddress){
        if(!this.isUser(oldUName)){return;}
        Customer c = new Customer(newFName, newEmail, oldUName, newPass, newAddress);
        ds.editUser(oldUName, c);
    }
    
    //Edit and change old id, password etc with new id, password etc
    public void editStaff(String id, String newID, String newPassword){ 
        if (!isStaff(id)){return;}
        Staff s = new Staff(newID, newPassword);
        ds.editStaff(id, s);
    }
    
    //Get readings from datastorage
    public Readings[] allReadings(){
    	Readings[] r=ds.getAllReadings();
    	return r;
    }
    
    //Get individual selected staff from datastorage through id
    public String[] getStaff(String id){
        Staff s = ds.getStaff(id);
        String[] x = {s.getUsername(),s.getPassword()};
        return x;
    }
    
    //Get all staff from datastorage
    public Staff[] getAllStaff() {
    	return ds.getAllStaff();
    }
    
    //Get individual selected Customer from datastorage through name
    public Customer getCustomer(String name){
        return ds.getUser(name);
    }
    
    public Customer getCustomerByEmail(String email){
        return ds.getUserByEmail(email);
    }
    
    //Get individual selected Customer from datastorage through postal code or unitNo
    public Customer[] getCustomer(String postal, String unitNo){
        return ds.getUser(postal, unitNo);
    }
    
    //Get all customer from datastorage
    public Customer[] getAllCustomers() {
    	return ds.getAllUser();
    }
    
    //Get all readings from datastorage
    public Readings[] getAllReadings(){
        return ds.getAllReadings();
    }
    
    //Get Readings based on the utility name
    public Readings getReading(String readingName){
    	return ds.getReadings(readingName);
    }
    
    //Temporary add user with username and password ( temporary method to test username and password)
    public void addUser(String username, String password){
        Customer c = new Customer(username, password);
        ds.addUser(c);
    }
    
    //Temporary add user with username, password, fullName and email
    public void addUser(String username, String password,String fullName, String email){
        Customer c = new Customer(fullName, email, username, password);
        ds.addUser(c);
    }
    
    //Add user with username, password, fullName and email, address
    public void addUser(String username, String password,String fullName, String email,String address){
        Customer c = new Customer(fullName, email, username, password, address);
        ds.addUser(c);
    }
    
    //Add staff through id and password
    public void addStaff(String id, String pass){
    	Staff s= new Staff(id,pass);
    	ds.addStaff(s);
    }
    
    //Remove staff by id
    public void removeStaff(String id){
    	ds.removeStaff(id);
    }
    public void removeUser (String userName){
    	ds.removeUser(userName);
    }
    
    //Add reading name, price, unit, service charge
    public void addReading(String name, double price, String unit, double serviceCharge){
    	Readings readings = new Readings(name, price, unit, serviceCharge);
    	ds.addReading(readings);
    }
    
    //Update the old reading
    public void updateReading(String name, double price, String unit, double serviceCharge, int index){
    	System.out.println(name+":"+price+":"+serviceCharge+":"+index);
    	Readings readings = new Readings(name, price, unit, serviceCharge);
    	ds.updateReading(readings, index);
    }
    
    //Remove reading by index
    public void removeReading(int index){
    	ds.removeReading(index);
    }
    
    //Calculate reading for total
       public double calculateReading(String readingName, String value){
    	double discount =0;
    	String meterReading = value;
    	int average = this.getAverageReading(readingName);
    	if (Integer.valueOf(value)<average){discount = 10;}
    	if (Integer.valueOf(value)<0){meterReading ="0";}
    	Readings reading = ds.getReadings(readingName);
    	return (Double.valueOf(meterReading)*reading.getPrice()+(Double.valueOf(meterReading)*reading.getPrice()*reading.getServiceCharge()/100))*((100-discount)/100);
    }
    
    public boolean hasDiscount(String readingName, String value){
    	int average = this.getAverageReading(readingName);
    	if (Integer.valueOf(value)<average){return true;}
    	return false;
    }
    

    
    //! User Readings/ Bill Methods
    //Submit user reading based on username for that date, month and year(test code, not used currently)
    public void submitUserReading(String userName){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDateTime now = LocalDateTime.now();
    	String date = dtf.format(now);
    	
        String[][][] userReadings=ds.getUserReadings(userName);
        System.out.println(userReadings.length);
        String[][] draft = ds.getDraft(userName);
        String[] initials = {userName, String.valueOf(userReadings.length+1), date};
        
        String[][] bill = new String[draft.length+1][3];
        bill[0] = initials;
        for(int i=0; i<draft.length;i++){	//Create a new bill array
        	String[] temp = new String[3];
        	temp[0] = draft[i][0];
        	temp[1] = draft[i][1];
        	Readings r = ds.getReadings(draft[i][0]);
        	temp[2] = String.format("%.2f", this.calculateReading(draft[i][0], draft[i][1]));
        	bill[i+1] = temp;
        	
        }
        ds.addUserReading(bill);
        
    }
    
    //Update user reading for the latest bill
    public void updateUserReading(String userName){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
    	LocalDateTime now = LocalDateTime.now();
    	String date = dtf.format(now);
    	
        String[][][] userReadings=ds.getUserReadings(userName);
        String[] billDate = userReadings[userReadings.length-1][0][2].split("/");
        if (!billDate[1].equals(systemDate[0])&&!billDate[2].equals(systemDate[1])){	//(Cautionary code in case edit button not blanked)
        	JOptionPane.showMessageDialog(null, "Not Avaliable for editing", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        System.out.println(userReadings.length);
        String[][] draft = ds.getDraft(userName);
        String[] initials = {userName, String.valueOf(userReadings.length), String.join("/", date,systemDate[0], systemDate[1])};
        
        String[][] bill = new String[draft.length+1][3];
        bill[0] = initials;
        // go thru each reading in draft
        for(int i=0; i<draft.length;i++){	
        	int usage = Integer.valueOf(draft[i][1])-this.getPastTotalReading(userName, draft[i][0]);
        	System.out.println(usage);
        	String[] temp = new String[3];
        	temp[0] = draft[i][0];
        	temp[1] = String.valueOf(usage);
        	temp[2] = String.format("%.2f", this.calculateReading(draft[i][0], String.valueOf(usage)));
        	bill[i+1] = temp;
        }
        ds.editUserReading(userName,String.join("/", systemDate),bill);
        
    }
    
    //Generate bill based on average previous user readings for all accounts
    public void generateBills(){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
    	LocalDateTime now = LocalDateTime.now();
    	String date = dtf.format(now);
    	
    	for (Customer c:ds.getAllUser()){ //Go through all customer
    		String userName = c.getUsername();
    		String[][] previousBill = this.getLastUserReading(userName);
    		
    		
    		
    		String[][][] userReadings=ds.getUserReadings(userName);
            String[] initials = {userName, String.valueOf(userReadings.length+1), String.join("/", date,systemDate[0], systemDate[1])};
            
            String[][] bill = new String[this.getAllReadings().length+1][3];
            bill[0] = initials;
            
            if (previousBill==null){	//if no previous bill return average reading of all customer 
            	Readings[] readings = this.getAllReadings();
            	HashMap<String, Integer> aveReadings = new HashMap<>();
            	for (int i=0; i<readings.length;i++){	//Get average reading of the whole system
            		int averageReading = this.getAverageReading(readings[i].getUtilityName());
            		if (readings[i].getUnit().equals("-")){
            			averageReading=1;
            		}
            		
            		aveReadings.put(readings[i].getUtilityName(), averageReading);
            	}
            	int count=0;
            	for (Entry<String, Integer>amt : aveReadings.entrySet()){	//sort data with map to follow format for Bills
            		String[] temp = {amt.getKey(), String.valueOf(amt.getValue()), String.format("%.2f",calculateReading(amt.getKey(), String.valueOf(amt.getValue())))};
            		bill[count+1] = temp;
            		count+=1;
            	}
            	this.addUserReading(bill);
            	resetDraft(c.getUsername());
            	continue;
            }
            
            String[] previousDate = previousBill[0][2].split("/");
    		if (previousDate[1].equals(systemDate[0])&& previousDate[2].equals(systemDate[1])){
    			continue;
    		}
            
            Readings[] readings = this.getAllReadings();
        	HashMap<String, Integer> aveReadings = new HashMap<>();
        	for (int i=0; i<readings.length;i++){	//Get average reading of all user
        		aveReadings.put(readings[i].getUtilityName(), this.getAverageUserReading(c.getUsername(),readings[i].getUtilityName()));
        	}
        	int count=0;
        	for (Entry<String, Integer>amt : aveReadings.entrySet()){	//sort data with map to follow format for Bills
        		String[] temp = {amt.getKey(), String.valueOf(amt.getValue()), String.format("%.2f",calculateReading(amt.getKey(), String.valueOf(amt.getValue())))};
        		bill[count+1] = temp;
        		count+=1;
        	}
        	this.addUserReading(bill);
        	resetDraft(c.getUsername());
        	continue;
            
            
            
    		
    	}
    }
    
    //Generate Bill based on average of previous user readings for customer
    public void generateBills(String userName){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
    	LocalDateTime now = LocalDateTime.now();
    	String date = dtf.format(now);
    	String[][] previousBill = this.getLastUserReading(userName);
    		
    		
    		
    	String[][][] userReadings=ds.getUserReadings(userName);
        String[] initials = {userName, String.valueOf(userReadings.length+1), String.join("/", date,systemDate[0], systemDate[1])};
            
        String[][] bill = new String[this.getAllReadings().length+1][3];
        bill[0] = initials;
            
        if (previousBill==null){
        	Readings[] readings = this.getAllReadings();
            HashMap<String, Integer> aveReadings = new HashMap<>();
            for (int i=0; i<readings.length;i++){	//Get average reading of the all customer
            	int averageReading = this.getAverageReading(readings[i].getUtilityName());
            	if (readings[i].getUnit().equals("-")){
            		averageReading=1;
            	}
            		
            	aveReadings.put(readings[i].getUtilityName(), averageReading);
            }
            int count=0;
            for (Entry<String, Integer>amt : aveReadings.entrySet()){	//sort data with Map follow format of Bills
            	String[] temp = {amt.getKey(), String.valueOf(amt.getValue()), String.format("%.2f",calculateReading(amt.getKey(), String.valueOf(amt.getValue())))};
            	bill[count+1] = temp;
            	count+=1;
            }
            this.addUserReading(bill);
            resetDraft(userName);
            return;
        }
            
        String[] previousDate = previousBill[0][2].split("/");
    	if (previousDate[1].equals(systemDate[0])&& previousDate[2].equals(systemDate[1])){
    		return;
    	}
            
        Readings[] readings = this.getAllReadings();
        HashMap<String, Integer> aveReadings = new HashMap<>();
        for (int i=0; i<readings.length;i++){	//Get average reading of individual customer
        	aveReadings.put(readings[i].getUtilityName(), this.getAverageUserReading(userName,readings[i].getUtilityName()));
        }
        int count=0;
        for (Entry<String, Integer>amt : aveReadings.entrySet()){	//sort data with map to follow format for Bills
        	String[] temp = {amt.getKey(), String.valueOf(amt.getValue()), String.format("%.2f",calculateReading(amt.getKey(), String.valueOf(amt.getValue())))};
        	bill[count+1] = temp;
        	count+=1;
        }
        this.addUserReading(bill);
        resetDraft(userName);
        return;
            
    		
    	
    }
    
    // exports bill selected
    public boolean printBills(String username, String month, String year){
    	//gets user bill that month
    	String [][] bill = null;
    	for (String[][] b : this.getUserReading(month, year)){
    		if (b[0][0].equals(username)){
    			bill = b;
    			break;
    		}
    	}
    	if (bill == null){return false;}// do not generate a empty bill
    	Customer customer = this.getCustomer(bill[0][0]);
    	JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    	chooser.setFileFilter(new FileNameExtensionFilter(".csv","csv"));
    	chooser.setFileFilter(new FileNameExtensionFilter(".txt","txt"));
    	int r = chooser.showSaveDialog(null);
    	String fileSelected = null;
		if (r == JFileChooser.APPROVE_OPTION){
			fileSelected = chooser.getSelectedFile().getAbsolutePath();
		}
		else {
			System.out.println("Cancelled");
			return false;
		}
		Vector<String[]> textout = new Vector<>();
		if (chooser.getFileFilter().getDescription().equals(".csv")){
			fileSelected+=".csv";
			String [] addr = customer.getAddress().split(":");
			String [][] top = {{"PS Group\n"},// setup the top of the file
					{"User : ",bill[0][0]},
					{"Bill No : " ,bill[0][1]},
					{"Bill Date : ",bill[0][2]+'\n'}, 
					{"Name : ",customer.getName()},
					{"Email : ",customer.getEmail()},
					{"Postal Code : ",addr[0]},
					{"Unit No. : ",addr[1]+'\n'},
					{"Reading Name","Amount Used","Average Reading","Cost"}};
			for (String[] item :top){textout.add(item);}
			for (int i =0; i<bill.length-1;i++){
				String unit = getReading(bill[i+1][0]).getUnit();
				String hasDiscount = "";
				if (Integer.valueOf(bill[i+1][1])<this.getAverageReading(bill[i+1][0])){hasDiscount = "*";}
				String[] item = 
						{bill[i+1][0],
						bill[i+1][1]+" "+unit+"  "+hasDiscount,
						this.getAverageReading(bill[i+1][0])+" "+unit,
						"$"+bill[i+1][2]};
				textout.add(item);
			}
			String[][] x = new String[textout.size()][];
			textout.toArray(x);
			try {
				csvWriter(fileSelected, x);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			fileSelected += ".txt";
			String [] addr = customer.getAddress().split(":");
			String [][] top = {{"PS Group\n"},
					{"User : "+bill[0][0]},
					{"Bill No : " +bill[0][1]},
					{"Bill Date : "+bill[0][2]+'\n'}, 
					{"Name : "+customer.getName()},
					{"Email : "+customer.getEmail()},
					{"Postal Code : "+addr[0]},
					{"Unit No. : "+addr[1]+'\n'},
					{"\'*\' shows a discount given\n"}};
			for (String[] item :top){textout.add(item);}
			for (int i =0; i<bill.length-1;i++){
				String unit = getReading(bill[i+1][0]).getUnit();
				String hasDiscount = "";
				if (Integer.valueOf(bill[i+1][1])<this.getAverageReading(bill[i+1][0])){hasDiscount = "*";}
				String[][] item = 
						{{"Reading : "+bill[i+1][0]},
						{"	Amount used : "+bill[i+1][1]+" "+unit+"  "+hasDiscount},
						{"	Average Reading : " + this.getAverageReading(bill[i+1][0])+" "+unit},
						{"	Cost : $"+bill[i+1][2]}, 
						{""}};
				textout.add(item[0]);
				textout.add(item[1]);
				textout.add(item[2]);
				textout.add(item[3]);
				textout.add(item[4]);
			}
			String[][] x = new String[textout.size()][];
			textout.toArray(x);
			try {
				csvWriter(fileSelected, x);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		System.out.println(fileSelected);
		
    	
    	
    	
    	return true;
    }
    
	//add bill in a String 2D array
    public void addUserReading(String[][] userReading){
        ds.addUserReading(userReading);
    }
    
    //calculate average reading of (total customer bill / how many submitted bills)
    public int getAverageReading(String readingName){
    	int count=0;
    	int total =0;
    	for (Customer c:ds.getAllUser()){	//Go through all customer readings
    		String[][] previousBill = this.getLastUserReading(c.getUsername());
    		if (previousBill==null){continue;}
    		for (String[] i:previousBill){	//Go through all previous bill
    			if (i[0].equals(readingName)){
    				total+= Integer.valueOf(i[1]);
    				count+=1;
    			}
    		}
    	}
    	if (count==0){count=1;}
    	return total/count;
    }
    	
    //calculate average reading of (each individual customer bill /  how many submitted bills)
    public int getAverageUserReading(String userName,String readingName){
    	int count=0;
    	int total =0;
    	int monthLimit = 4;
    	String[][][] bills = this.getUserReading(userName);
    	for (int i=bills.length; i>bills.length-monthLimit;i--){	
    		String[][] b = new String[1][];
    		try {
    			b = bills[i-1];
			} catch (Exception e) {
				continue;
			}
    		for (String[] x:b){
    			if (x[0].equals(readingName)){
    				total+= Integer.valueOf(x[1]);
    				count+=1;
    			}
//    			else{
//    				total+= this.getAverageReading(readingName);
//    				count+=1;
//    			}
    		}
    	}
    	if (count==0){count=1;}
    	return total/count;
    }
    
    // Retrieves the last user reading from the DataStorage (ds) class.
    public String[][] getLastUserReading(String userName){
    	return ds.getLastUserReading(userName);
    }

    // Retrieves all readings for a specific user from the DataStorage (ds) class.
    public String[][][] getUserReading(String userName) {
    	return ds.getUserReadings(userName);
    }
    
    // Retrieves all readings for a specific month and year from the DataStorage (ds) class.
    public String[][][] getUserReading(String month, String year) {
    	return ds.getUserReadings(month,year);
    }
    
    // Retrieves all past readings for the specified user.
    public int getCurrentTotalReading(String userName, String readingName){
    	String[][][] pastUR = this.getUserReading(userName);
    	int total =0 ;
    	for (String [][] ur :pastUR){	// Loop through each set of user readings.
    		for (String [] item :ur){	// Loop through each reading within the set.
    			if (item[0].equals(readingName)){            // If the reading name matches, add the value to the total.
    				total += Integer.valueOf(item[1]);
    			}
    		}
    	}
    	return total;
    }
    
    // Retrieves the last user reading for the specified user.
    public int getPastTotalReading(String userName, String readingName){
    	String[][] currentBill = this.getLastUserReading(userName);
    	int previousUsed = 0;
    	// get reading value
    	for (String [] i:currentBill){
    		if (i[0].equals(readingName)){
    			previousUsed = Integer.valueOf(i[1]);
    			break;
    		}
    	}
    	// get Total till now
    	int currentTotal = this.getCurrentTotalReading(userName, readingName);
    	//returns reading bill as of lastmonth
    	String[] billDate = currentBill[0][2].split("/");
    	
//    	if (Integer.valueOf(billDate[1])<Integer.valueOf(systemDate[0]) && Integer.valueOf(billDate[2])<=Integer.valueOf(systemDate[1]) )
//    	{
//    		return currentTotal;
//    	}
//    	else {return currentTotal-previousUsed;}
    	return currentTotal-previousUsed;
    	
    }
    
    
    

    //!Meter/Draft Readings
    // Adds a meter reading to the data storage (ds) for the specified user and reading name
    public void addMeterReading(String uName, String readingName, Integer meterReading) {
		ds.addMeterReading(uName, readingName, meterReading);
	}
    
    // Checks if the user has a draft saved in the data storage
    public boolean hasDraft(String UName){
        return ds.hasDraft(UName);
    }
    
    // Removes a specific meter reading for the given user and reading name from the data storage
    public void removeMeterReading(String userName, String readingName){
    	ds.removeMeterReading(userName, readingName);
    }
    
    // Edits an existing meter reading for the specified user and reading name with a new value
    public void editMeterReading(String userName, String readingName, Integer editedValue){
    	ds.editMeterReading(userName, readingName, editedValue);
    }
    
    // Retrieves the draft for the specified user from the data storage
    public String[][] getDraft(String userName){
    	return ds.getDraft(userName);
    }
    
    // Clears the draft associated with the specified user from the data storage
    public void clearDraft(String userName){
    	ds.removeDraft(userName);
    }
    
    // Clears any existing draft for the user
    public void resetDraft(String userName){
    	this.clearDraft(userName);
    	Readings[] readings = this.getAllReadings();
    	for (Readings r:readings){	    // Loops through all readings and adds them to the user's draft
    		this.addMeterReading(userName, r.getUtilityName(), this.getCurrentTotalReading(userName, r.getUtilityName()));
    	}
    	System.out.println(userName+" reseted");
    }
    
    // Retrieve the latest bill for the user
    public boolean checkEditStatus(String userName){
    	String[][] latestBill = this.getLastUserReading(userName);
    	if (latestBill ==null){	    // Check if the latest bill is null
		return false;}
    	String[] billDate = latestBill[0][2].split("/");
        // Compare the bill date with the system date
    	if (Integer.valueOf(billDate[1])<Integer.valueOf(systemDate[0])&&Integer.valueOf(billDate[2])<=Integer.valueOf(systemDate[1])){
    		return false;
    	}
    	return true;
    }
    
    //! Date Methods
    // Method to return the current system date as a String array
    public String[] getSystemDate() {
		return systemDate;
	}
    
    // Method to set the system date using a String array
	public void setSystemDate(String[] systemDate) {
		this.systemDate = systemDate;
	}
	
    // Method to set the system date using a month and year string
	public void setSystemDate(String month, String Year){
		String[] temp = {String.format("%02d",Integer.valueOf(month)),Year};
		System.out.println(String.join(":", temp));
		systemDate = temp;
	}
	
    // Method to synchronize the system date with the current date
	public void syncDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
    	LocalDateTime now = LocalDateTime.now();
    	systemDate = dtf.format(now).split("/");
	}
    
    
    // Method to return the current DataStorage object
    public DataStorage getDS() {
        return ds;
    }

    // Method to set the DataStorage object
    public void setDS(DataStorage ds) {
        this.ds = ds;
    }
 
    //!CSV methods
    public String[][] csvReader(String filepath) throws IOException {
		BufferedReader read = null;
		try {
	        // Attempt to create a BufferedReader using the specified file path
			File in = new File(filepath);
			read = new BufferedReader(new FileReader(in));
			
		} catch (Exception e) {
	        // If the file is not found, read from the resource stream (useful when running from a JAR)
			InputStream in = this.getClass().getResourceAsStream(filepath);// if no file is found, it will read from stream. For JAR
			read = new BufferedReader(new InputStreamReader(in));
		}
		String line;
	    Vector<String[]> file = new Vector<>(); // Vector to store the lines of the file as arrays of Strings
		while ((line = read.readLine())!=null){
	        // Split each line by commas and add the resulting array to the Vector
			String [] l = line.split(",");
			file.add(l);
		}
	    // Convert the Vector to a 2D array
		String [][] f= new String[file.size()][];
		file.toArray(f);
	    return f; // Return the 2D array representing the CSV file contents
	}
	
    // Create a FileWriter to write data to the specified file path
    // This method writes the provided 2D String array (data) to a CSV file at the specified file path.
	public void csvWriter(String filepath, String[][] data) throws IOException {
		FileWriter writer = new FileWriter(filepath);
		for(String[] d : data) { // Loop through each row (String array) in the 2D data array
			writer.append(String.join(",", d));// adds the array as a line
			writer.append("\n");
		}
		writer.close();
		
	}


    // Retrieve all customer, staff, readings, and user readings data from DataStorage
    public void saveData(){
        Customer[] customer = ds.getAllUser();
        Staff[] staffs = ds.getAllStaff(); 
        Readings[] readings= ds.getAllReadings();
        String[][][] userReadings = ds.getAllUserReadings();

        //Diff Types to String[][]
        //!Staff 
        String[][] staffData = new String[staffs.length-1][2];
        int c=0;
        for (int i = 0; i < staffs.length; i++) {	// Loop through the staff array
            if (staffs[i].getUsername().equals("admin")){continue;}
            staffData[c][0] = staffs[i].getUsername();
            staffData[c][1] = staffs[i].getPassword();
            c+=1;
        }
        c=0;

        //!Customer (UserName, Password, Full Name, Email, Address, Draft)
        String[][] customerData = new String[customer.length][7];
        for (int i = 0; i < customer.length; i++) {	// Loop through the customer array

            customerData[i][0] = customer[i].getUsername();
            customerData[i][1] = customer[i].getPassword();
            customerData[i][2] = customer[i].getName();
            customerData[i][3] = customer[i].getEmail();
            customerData[i][4] = customer[i].getAddress();
            customerData[i][5] = customer[i].getLastSubmittedString();
			
            String[][] draft = customer[i].getDraftArray();
            String d = new String();
            for (String [] string : draft) {	// Loop through the draft array to concatenate draft data into a single string
                d= d+ string[0]+":"+string[1]+"-";
            }
            if (d.isEmpty()){d="null";}
            customerData[i][6] = d;
        }

        //!Readings
        String[][] readingsData = new String[readings.length][4];
        for (int i = 0; i < readings.length; i++) {    // Loop through the readings array
            readingsData[i][0] = readings[i].getUtilityName();
            readingsData[i][1] = Double.toString(readings[i].getPrice());
            readingsData[i][2] = readings[i].getUnit();
            readingsData[i][3] = Double.toString(readings[i].getServiceCharge());
        }

        //!UserReadings
        String[][] userR = new String[userReadings.length+1][];
        userR[0]= systemDate;
        for (int i=0;i<userReadings.length; i++){	// Loop through the user readings array
        	String[][] ur = userReadings[i];
        	c=0;
        	Vector<String> x = new Vector<>();
        	for (String[] items : ur){	// Loop through each reading to concatenate them into a single string
        		String it = String.join(":", items);
        		x.add(it);
        		c+=1;
        	}
        	String[] y = new String[x.size()];
        	x.toArray(y);
        	userR[i+1]=y;
        	c=0;
        }


        //store into CSV files
        cdir = System.getProperty("java.class.path").split(";")[0];
       if (!(System.getProperty("file.separator")=="/")){
           cdir = cdir.replace("\\", "/");}
       String[] cp = cdir.split("/");
       cp[cp.length-1] = "src";// redirects to SRC csv for easy access to edit data
       cdir = String.join("/", cp);
       System.out.println(cdir);
        
        try {
            csvWriter(cdir+"/csv/Staff.csv", staffData);
            csvWriter(cdir+"/csv/Customer.csv", customerData);
            csvWriter(cdir+"/csv/Readings.csv", readingsData);
            csvWriter(cdir+"/csv/UserReadings.csv", userR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Get current directory
    public void syncData(){
    	//get current directory

    	cdir = System.getProperty("java.class.path").split(";")[0];
       System.out.println(cdir);
       if (!(System.getProperty("file.separator")=="/")){
           cdir = cdir.replace("\\", "/");}
       String[] cp = cdir.split("/");
       cp[cp.length-1] = "src";// redirects to SRC csv for easy access to edit data
       cdir = String.join("/", cp);
       System.out.println(cdir);

        //Read files for data
        //Creating and adding csv contents into vectors
            String[][] customers;
			try {
				try {
					customers = csvReader(cdir+"/csv/Customer.csv");
				} catch (Exception e) {
					customers = csvReader("/csv/Customer.csv");
				}
				
				//!customer
	            for(String[]c:customers){	// Loop to read Customer data from CSV and add to the system
	                this.addUser(c[0], c[1], c[2],c[3],c[4]);
	                if (!c[5].equals("null")){ds.setLastSubmitted(c[0], c[5]);}
	                if (c[6].equals("null")){continue;}
	                StringTokenizer st = new StringTokenizer(c[6],"-");
	                while (st.hasMoreTokens()){	// Inner loop to process each draft item
	                    String reading = st.nextToken();
	                    StringTokenizer st2 = new StringTokenizer(reading,":");
	                    String[] items = new String[2];
	                    int i =0;
	                    while (st2.hasMoreTokens()){	// Process each token in the draft item
	                        items[i]=st2.nextToken();
	                        i+=1;
	                    }
                        int mr = 0;
                        try {
                            mr = Integer.valueOf(items[1]);
                        } catch (Exception e) {
                            mr = Double.valueOf(items[1]).intValue();
                        }
	                    ds.addMeterReading(c[0], items[0], mr);
	                }
	            }
			} catch (IOException e) {
				System.out.println(cdir+"/csv/Customer.csv NOT FOUND");
			}
			
            String[][] staffAcct;
			try {
				try {
					staffAcct = csvReader(cdir+"/csv/Staff.csv");
				} catch (Exception e) {
					staffAcct = csvReader("/csv/Staff.csv");
				}
				//!Staff
	            for(String[]s:staffAcct){	// Loop to read Staff data from CSV and add to the system
	                this.addStaff(s[0], s[1]);
	            }
	            
	            
			} catch (IOException e) {
				System.out.println(cdir+"/csv/Staff.csv NOT FOUND");
			}
			
            String[][] readings;
			try {
				try {
					readings = csvReader(cdir+"/csv/Readings.csv");
				} catch (Exception e) {
					readings = csvReader("/csv/Readings.csv");
				}
				//!Readings
	            for(String[]r:readings){	// Loop to read Readings data from CSV and add to the system
	                this.addReading(r[0], Double.parseDouble(r[1]), r[2], Double.parseDouble(r[3]));
	            }
			} catch (IOException e) {
				System.out.println(cdir+"/csv/Readings.csv NOT FOUND");
			}
			
            String[][] userReadings;
			try {
				try {
					userReadings = csvReader(cdir+"/csv/UserReadings.csv");
				} catch (Exception e) {
					userReadings = csvReader("/csv/UserReadings.csv");
				}
				//!User Readings
	            int c=0;
	            for(String[] ur:userReadings){	// Loop to read userReadings data from CSV and add to the system
	            	if(ur.length==0){
	            		setSystemDate("1", "2020");
	            		continue;
	            	}
	                Vector<String[]> bill = new Vector<>();
	                
	                if (c==0&&ur[0].length()==2){setSystemDate(ur);c+=1;continue;}
	                else if (c==0){c+=1;setSystemDate("1", "2020");}
	                for(String item : ur){	// Loop to process UserReadings from CSV
	                	String [] t = item.split(":");
	                	bill.add(t);
	                }
	                String[][] b = new String[bill.size()][];
	                bill.toArray(b);
	                ds.addUserReading(b);
	            }
			} catch (IOException e) {
				System.out.println(cdir+"/csv/UserReadings.csv NOT FOUND");
				setSystemDate("1", "2020");
			}
        }
}
	