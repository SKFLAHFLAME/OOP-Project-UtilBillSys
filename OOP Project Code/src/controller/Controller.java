package controller;

import data.Customer;
import data.DataStorage;
import data.Readings;
import data.Staff;

import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Map.Entry;

import javax.management.InstanceNotFoundException;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultEditorKit.PasteAction;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

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

    public void editUser(String oldUName, String newFName, String newEmail, String newPass, String newAddress){
        if(!this.isUser(oldUName)){return;}
        Customer c = new Customer(newFName, newEmail, oldUName, newPass, newAddress);
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
    
    public Customer[] getCustomer(String postal, String unitNo){
        return ds.getUser(postal, unitNo);
    }
    
    public Customer[] getAllCustomers() {
    	return ds.getAllUser();
    }
    public Readings[] getAllReadings(){
        return ds.getAllReadings();
    }
    public Readings getReading(String readingName){
    	return ds.getReadings(readingName);
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
    public void removeStaff(String id){
    	ds.removeStaff(id);
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
    
    public double calculateReading(String readingName, String value){
    	double discount =0;
    	String meterReading = value;
    	int average = this.getAverageReading(readingName);
    	if (Integer.valueOf(value)<average){discount = 10;}
    	if (Integer.valueOf(value)<0){meterReading ="0";}
    	Readings reading = ds.getReadings(readingName);
    	return (Double.valueOf(meterReading)*reading.getPrice()+(Double.valueOf(meterReading)*reading.getPrice()*reading.getServiceCharge()/100))*((100-discount)/100);
    }
    

    
    //! User Readings
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
        for(int i=0; i<draft.length;i++){
        	String[] temp = new String[3];
        	temp[0] = draft[i][0];
        	temp[1] = draft[i][1];
        	Readings r = ds.getReadings(draft[i][0]);
        	temp[2] = String.format("%.2f", this.calculateReading(draft[i][0], draft[i][1]));
        	bill[i+1] = temp;
        	
        }
        ds.addUserReading(bill);
        
    }
    
    public void updateUserReading(String userName){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
    	LocalDateTime now = LocalDateTime.now();
    	String date = dtf.format(now);
    	
        String[][][] userReadings=ds.getUserReadings(userName);
        String[] billDate = userReadings[userReadings.length-1][0][2].split("/");
        if (!billDate[1].equals(systemDate[0])&&!billDate[2].equals(systemDate[1])){
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
    
    public void generateBills(){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
    	LocalDateTime now = LocalDateTime.now();
    	String date = dtf.format(now);
    	
    	for (Customer c:ds.getAllUser()){
    		String userName = c.getUsername();
    		String[][] previousBill = this.getLastUserReading(userName);
    		
    		
    		
    		String[][][] userReadings=ds.getUserReadings(userName);
            String[] initials = {userName, String.valueOf(userReadings.length+1), String.join("/", date,systemDate[0], systemDate[1])};
            
            String[][] bill = new String[this.getAllReadings().length+1][3];
            bill[0] = initials;
            
            if (previousBill==null){
            	Readings[] readings = this.getAllReadings();
            	HashMap<String, Integer> aveReadings = new HashMap<>();
            	for (int i=0; i<readings.length;i++){
            		int averageReading = this.getAverageReading(readings[i].getUtilityName());
            		if (readings[i].getUnit().equals("-")){
            			averageReading=1;
            		}
            		
            		aveReadings.put(readings[i].getUtilityName(), averageReading);
            	}
            	int count=0;
            	for (Entry<String, Integer>amt : aveReadings.entrySet()){
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
        	for (int i=0; i<readings.length;i++){
        		aveReadings.put(readings[i].getUtilityName(), this.getAverageUserReading(c.getUsername(),readings[i].getUtilityName()));
        	}
        	int count=0;
        	for (Entry<String, Integer>amt : aveReadings.entrySet()){
        		String[] temp = {amt.getKey(), String.valueOf(amt.getValue()), String.format("%.2f",calculateReading(amt.getKey(), String.valueOf(amt.getValue())))};
        		bill[count+1] = temp;
        		count+=1;
        	}
        	this.addUserReading(bill);
        	resetDraft(c.getUsername());
        	continue;
            
            
            
    		
    	}
    }
    
    
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
            for (int i=0; i<readings.length;i++){
            	int averageReading = this.getAverageReading(readings[i].getUtilityName());
            	if (readings[i].getUnit().equals("-")){
            		averageReading=1;
            	}
            		
            	aveReadings.put(readings[i].getUtilityName(), averageReading);
            }
            int count=0;
            for (Entry<String, Integer>amt : aveReadings.entrySet()){
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
        for (int i=0; i<readings.length;i++){
        	aveReadings.put(readings[i].getUtilityName(), this.getAverageUserReading(userName,readings[i].getUtilityName()));
        }
        int count=0;
        for (Entry<String, Integer>amt : aveReadings.entrySet()){
        	String[] temp = {amt.getKey(), String.valueOf(amt.getValue()), String.format("%.2f",calculateReading(amt.getKey(), String.valueOf(amt.getValue())))};
        	bill[count+1] = temp;
        	count+=1;
        }
        this.addUserReading(bill);
        resetDraft(userName);
        return;
            
    		
    	
    }
    
    public void addUserReading(String[][] userReading){
        ds.addUserReading(userReading);
    }
    
    public int getAverageReading(String readingName){
    	int count=0;
    	int total =0;
    	for (Customer c:ds.getAllUser()){
    		String[][] previousBill = this.getLastUserReading(c.getUsername());
    		if (previousBill==null){continue;}
    		for (String[] i:previousBill){
    			if (i[0].equals(readingName)){
    				total+= Integer.valueOf(i[1]);
    				count+=1;
    			}
    		}
    	}
    	if (count==0){count=1;}
    	return total/count;
    }
    
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
    
    public String[][] getLastUserReading(String userName){
    	return ds.getLastUserReading(userName);
    }

    public String[][][] getUserReading(String userName) {
    	return ds.getUserReadings(userName);
    }
    
    public String[][][] getUserReading(String month, String year) {
    	return ds.getUserReadings(month,year);
    }
    public int getCurrentTotalReading(String userName, String readingName){
    	String[][][] pastUR = this.getUserReading(userName);
    	int total =0 ;
    	for (String [][] ur :pastUR){
    		for (String [] item :ur){
    			if (item[0].equals(readingName)){
    				total += Integer.valueOf(item[1]);
    			}
    		}
    	}
    	return total;
    }
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
    	
    	if (Integer.valueOf(billDate[1])<Integer.valueOf(systemDate[0]) && Integer.valueOf(billDate[2])<=Integer.valueOf(systemDate[1]) )
    	{
    		return currentTotal;
    	}
    	else {return currentTotal-previousUsed;}
    	
    }
    
    
    

    //!Meter/Draft Readings
    public void addMeterReading(String uName, String readingName, Integer meterReading) {
		ds.addMeterReading(uName, readingName, meterReading);
	}
    public boolean hasDraft(String UName){
        return ds.hasDraft(UName);
    }
    public void removeMeterReading(String userName, String readingName){
    	ds.removeMeterReading(userName, readingName);
    }
    
    public void editMeterReading(String userName, String readingName, Integer editedValue){
    	ds.editMeterReading(userName, readingName, editedValue);
    }
    
    public String[][] getDraft(String userName){
    	return ds.getDraft(userName);
    }
    
    public void clearDraft(String userName){
    	ds.removeDraft(userName);
    }
    
    public void resetDraft(String userName){
    	this.clearDraft(userName);
    	Readings[] readings = this.getAllReadings();
    	for (Readings r:readings){
    		this.addMeterReading(userName, r.getUtilityName(), this.getCurrentTotalReading(userName, r.getUtilityName()));
    	}
    	System.out.println("reseted");
    }
    public boolean checkEditStatus(String userName){
    	String[][] latestBill = this.getLastUserReading(userName);
    	if (latestBill ==null){
		return false;}
    	String[] billDate = latestBill[0][2].split("/");
    	if (Integer.valueOf(billDate[1])<Integer.valueOf(systemDate[0])&&Integer.valueOf(billDate[2])<=Integer.valueOf(systemDate[1])){
    		return false;
    	}
    	return true;
    }
    
    //! Date Methods
    public String[] getSystemDate() {
		return systemDate;
	}

	public void setSystemDate(String[] systemDate) {
		this.systemDate = systemDate;
	}
	public void setSystemDate(String month, String Year){
		String[] temp = {String.format("%02d",Integer.valueOf(month)),Year};
		System.out.println(String.join(":", temp));
		systemDate = temp;
	}
	
	public void syncDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
    	LocalDateTime now = LocalDateTime.now();
    	systemDate = dtf.format(now).split("/");
	}
    
    

    public DataStorage getDS() {
        return ds;
    }

    public void setDS(DataStorage ds) {
        this.ds = ds;
    }
 
    //!CSV methods
    public String[][] csvReader(String filepath) throws FileNotFoundException {
		File file = new File(filepath);
		Scanner scanner = new Scanner(file);
		scanner.useDelimiter(",");
		Vector<String[]> data = new Vector<>();
		int x=0;
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			StringTokenizer st = new StringTokenizer(line, ",");
			Vector<String> v = new Vector<>();
			int c=0;
			while (st.hasMoreTokens()){
				v.add(st.nextToken());
				c+=1;
			}
			String[] a = new String[c];
			v.toArray(a);
			c=0;
			data.add(a);
		}
		String[][] finaldata = new String[data.size()][2];
		data.toArray(finaldata);
		scanner.close();
		// //Testing method
		// for (String[]e:finaldata){
		// 	System.out.println(e.length);
		// 	for (String y :e){
		// 		System.out.print(y+"\t");
		// 	}
		// 	System.out.println();
		// }

		return finaldata;
		
	}
	
	public void csvWriter(String filepath, String[][] data) throws IOException {
		FileWriter writer = new FileWriter(filepath);
		for(String[] d : data) {
			for (String s:d){
				// if(s.isEmpty()){s="-";}
				writer.append(s+",");
			}
			writer.append("\n");
		}
		writer.close();
		
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
        c=0;

        //!Customer (UserName, Password, Full Name, Email, Address, Draft)
        String[][] customerData = new String[customer.length][7];
        for (int i = 0; i < customer.length; i++) {
            customerData[i][0] = customer[i].getUsername();
            customerData[i][1] = customer[i].getPassword();
            customerData[i][2] = customer[i].getName();
            customerData[i][3] = customer[i].getEmail();
            customerData[i][4] = customer[i].getAddress();
            customerData[i][5] = customer[i].getLastSubmittedString();
			
            String[][] draft = customer[i].getDraftArray();
            String d = new String();
            for (String [] string : draft) {
                d= d+ string[0]+":"+string[1]+"-";
            }
            if (d.isEmpty()){d="null";}
            customerData[i][6] = d;
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
        String[][] userR = new String[userReadings.length+1][];
        userR[0]= systemDate;
        for (int i=0;i<userReadings.length; i++){
        	String[][] ur = userReadings[i];
        	c=0;
        	Vector<String> x = new Vector<>();
        	for (String[] items : ur){
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
				customers = csvReader(cdir+"/csv/Customer.csv");
				//!customer
	            for(String[]c:customers){
	                this.addUser(c[0], c[1], c[2],c[3],c[4]);
	                if (!c[5].equals("null")){ds.setLastSubmitted(c[0], c[5]);}
	                if (c[6].equals("null")){continue;}
	                StringTokenizer st = new StringTokenizer(c[6],"-");
	                while (st.hasMoreTokens()){
	                    String reading = st.nextToken();
	                    StringTokenizer st2 = new StringTokenizer(reading,":");
	                    String[] items = new String[2];
	                    int i =0;
	                    while (st2.hasMoreTokens()){
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
			} catch (FileNotFoundException e) {
				System.out.println(cdir+"/csv/Customer.csv NOT FOUND");
			}
			
            String[][] staffAcct;
			try {
				staffAcct = csvReader(cdir+"/csv/Staff.csv");
				//!Staff
	            for(String[]s:staffAcct){
	                this.addStaff(s[0], s[1]);
	            }
	            
	            
			} catch (FileNotFoundException e) {
				System.out.println(cdir+"/csv/Staff.csv NOT FOUND");
			}
			
            String[][] readings;
			try {
				readings = csvReader(cdir+"/csv/Readings.csv");
				//!Readings
	            for(String[]r:readings){
	                this.addReading(r[0], Double.parseDouble(r[1]), r[2], Double.parseDouble(r[3]));
	            }
			} catch (FileNotFoundException e) {
				System.out.println(cdir+"/csv/Readings.csv NOT FOUND");
			}
			
            String[][] userReadings;
			try {
				userReadings = csvReader(cdir+"/csv/UserReadings.csv");
				//!User Readings
	            int c=0;
	            for(String[] ur:userReadings){
	            	if(ur.length==0){
	            		setSystemDate("1", "2020");
	            		continue;
	            	}
	                Vector<String[]> bill = new Vector<>();
	                
	                if (c==0&&ur[0].length()==2){setSystemDate(ur);c+=1;continue;}
	                else if (c==0){c+=1;setSystemDate("1", "2020");}
	                for(String item : ur){
	                	String [] t = item.split(":");
	                	bill.add(t);
	                }
	                String[][] b = new String[bill.size()][];
	                bill.toArray(b);
	                ds.addUserReading(b);
	            }
			} catch (FileNotFoundException e) {
				System.out.println(cdir+"/csv/UserReadings.csv NOT FOUND");
			}
        }
}
	