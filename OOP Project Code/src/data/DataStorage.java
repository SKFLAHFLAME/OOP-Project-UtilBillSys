package data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class DataStorage {
    private Vector<Staff> staff = new Vector<>();	// Vector to store staff objects
    private Vector<Customer> user = new Vector<>();	// Vector to store customer objects
    private Vector<Readings> readings = new Vector<>();	// Vector to store readings objects
    private Vector<String[][]> userReading = new Vector<>();	// Vector to store userReadings in a specific format of (UName, Entry_No, Readings...)

    // Initialize with a default admin staff
    public DataStorage (){
        Staff admin = new Staff("admin", "admin");
        staff.add(admin);
    }


    //!Customer Methods
    // Add a new customer to the user list
    public void addUser(Customer cust){
        user.add(cust);
    }
    
    // Edit an existing customer's information
    public void editUser(String uname, Customer acct){
        int i=0;
        for(Customer c:user){	// Iterate over users
            if (c.getUsername().equals(uname)){	// If username matches
                user.setElementAt(acct, i);	// Update customer info
            }
            i+=1;
        }
    }

    // Remove a customer from the user list by username
    public void removeUser(String userName){
        int c=0;
        for(Customer cust:user){	// Iterate over users
            if(cust.getUsername().equals(userName)){ // If username matches
                user.remove(c);	// Remove the customer
            }
            c+=1;
        }
    }

    // Retrieve a customer by their username
    public Customer getUser(String name) {
        for(Customer u:user){	// Iterate over users
            if (u.getUsername().equals(name)){	/// Iterate over users
                return u;	// Return the customer
            }
        }
        return null;
    }
    	
    // Retrieve customers by their postal code and unit number
    public Customer[] getUser(String postal, String unitNo){
    	Vector<Customer> filter = new Vector<>();
    	for (Customer c : user){	// Iterate over users
    		String[] address = c.getAddress().split(":");	// Split address into postal and unitNo
    		if (postal.equals("")){continue;}
    		if (!address[0].equals(postal)){continue;}
    		if (unitNo.equals("")){filter.add(c);continue;}
    		if (address[1].equals(unitNo)){filter.add(c);}
			
    	}
//    	if (filter.isEmpty()){
//    		return null;
//    	}
    	Customer[] output = new Customer[filter.size()];
    	filter.toArray(output);	// Convert vector to array
    	return output;
    }
    
    // Retrieve all customers
    public Customer[] getAllUser() {
        Customer[] c = new Customer[user.size()];
        user.toArray(c);
        return c;
    }
        
    //! user draft Methods
    //Check if a customer has a draft
    public boolean hasDraft(String userName){
        Customer c = this.getUser(userName);
        return (c.getDraft().size()!=0);
    }

    // Add a meter reading to a customer's draft
    public void addMeterReading(String userName, String readingName, Integer meterReading){
        int i=0;
        for(Customer c : user){	// Iterate over users
            if(c.getUsername().equals(userName)){	// If username matches
                c.addMeterReading(readingName, meterReading);	// Add meter reading to draft	
                user.setElementAt(c, i);	// Update customer info
            }
            i+=1;
        }
    }

    // Edit an existing meter reading in a customer's draft
    public void editMeterReading(String userName, String readingName, Integer newMeterReading){
        int i=0;
        for(Customer c : user){	// Iterate over users
            if(c.getUsername().equals(userName)){	// If username matches
                c.editMeterReading(readingName, newMeterReading);	// Edit meter reading to draft	
                user.setElementAt(c, i);	// Update customer info
            }
            i+=1;
        }
    }
    
    // Edit the name of a meter reading in a customer's draft
    public void editMeterReadingName(String userName, String readingName, String newReadingName){
        int i=0;
        for(Customer c : user){	// Iterate over users
            if(c.getUsername().equals(userName)){	// If username matches
                c.editReadingName(readingName, newReadingName);	// Edit meter reading to draft	
                user.setElementAt(c, i);	// Update customer info
            }
            i+=1;
        }
    }

    // Remove a meter reading from a customer's draft
    public void removeMeterReading(String userName, String readingName){
        int i=0;
        for(Customer c : user){	// Iterate over users
            if(c.getUsername().equals(userName)){	// If username matches
                c.deleteMeterReading(readingName);	// Remove the meter reading
                user.setElementAt(c, i);	// Update customer info
            }
            i+=1;
        }
    }

    // Remove all drafts from a customer
    public void removeDraft(String userName){
        int i=0;
        for(Customer c : user){	// Iterate over users
            if(c.getUsername().equals(userName)){	// If username matches
                c.clearDraft();	 // Clear the draft
                user.setElementAt(c, i);	// Update customer info
            }
            i+=1;
        }
    }
    
    // Retrieve the latest submitted readings for a customer
    public String[][][] getLatestSubmittedReadings(String userName) {
        Customer user = getUser(userName);
        if (user == null) {
            return new String[0][][]; // No user found
        }

        String lastSubmittedDate = user.getLastSubmittedString();
        if (lastSubmittedDate == null || lastSubmittedDate.isEmpty()) {
            return new String[0][][]; // No last submitted date
        }

        String month = lastSubmittedDate.split("/")[0];
        String year = lastSubmittedDate.split("/")[1];

        // Filter readings based on the last submitted date
        Vector<String[][]> latestReadings = new Vector<>();
        for (String[][] reading : userReading) {
            String[] dateParts = reading[0][2].split("/");
            if (dateParts[0].equals(month) && dateParts[1].equals(year)) {
                latestReadings.add(reading);	// Add to latest readings
            }
        }

        String[][][] arr = new String[latestReadings.size()][this.readings.size() + 2][3];
        latestReadings.toArray(arr);

        // Sort by Entry_Number
        try {
            Arrays.sort(arr, (a, b) -> Integer.compare(Integer.parseInt(a[0][1]), Integer.parseInt(b[0][1])));
        } catch (Exception e) {
            // Handle parsing exceptions if needed
        }

        return arr;
    }

    // Method to update the last submitted date
    public void updateLastSubmittedDate(String userName, String month, String year) {
        Customer user = getUser(userName);
        if (user != null) {
            user.setLastSubmitted(month, year); // Update the last submitted date
        }
    }

    // Retrieves the draft of meter readings for a specific user.
    public String[][] getDraft(String userName){
        String[][] draft;
        for(Customer c:user){
            if(c.getUsername().equals(userName)){
                HashMap<String,Integer> d = c.getDraft();
                draft = new String[d.size()][2];

                int i=0;
                for(String k:d.keySet()){
                    String[] t = {k,String.valueOf(d.get(k))};
                    draft[i]=t;
                    i+=1;
                }

                return draft;
            }
        }
        return null;
        
    }
    
    //! LastSubmitted Methods
    // Sets the last submitted date for a customer's meter readings using separate month and year strings.
    public void setLastSubmitted(String userName,String month, String year){
    	int i=0;
    	for(Customer c : user){
            if(c.getUsername().equals(userName)){
            	c.setLastSubmitted(month, year);
                user.setElementAt(c, i);
            }
            i+=1;
        }
    }
    // Sets the last submitted date for a customer's meter readings using a single date string.
    public void setLastSubmitted(String userName,String date){
    	int i =0;
    	for(Customer c : user){
            if(c.getUsername().equals(userName)){
            	c.setLastSubmittedString(date);
                user.setElementAt(c, i);
            }
            i+=1;
        }
    }


    //!Staff Methods
    // Add a new staff member
    public void addStaff(Staff s){
        staff.add(s);
    }

    // Edit an existing staff member's information
    public void editStaff(String id, Staff acct){
        int c=0;
        for (Staff s : staff) { // Iterate over staff members
            if (s.getUsername().equals(id)) { // If id matches
                staff.setElementAt(acct, c); // Update staff info
            }
            c+= 1;
        }
    }

    // Remove a staff member by username
    public void removeStaff(String id){
        int c=0;
        for (Staff s:staff){	// Iterate over staff members
            if (s.getUsername().equals(id)){	// If id matches
                staff.remove(c);	// Remove the staff member
                c+=1;
                break;
            }
            c+=1;
        }
    }

    // Retrieve a staff member by their id
    public Staff getStaff(String id) {
        for(Staff s:staff){
            if (id.equals(s.getUsername())){
                return s;
            }
        }
        return null;
    }

    // Retrieve all staff members
    public Staff[] getAllStaff() {
        Staff[] s = new Staff[staff.size()];
        staff.toArray(s);
        return s;
    }


    //!Reading Methods
    // Adds a new Readings object to the readings list.
    public void addReading(Readings r){
    	readings.add(r);
    }
    
    // Updates an existing Readings object in the readings list at the specified index.
    public void updateReading(Readings r,int index){
    	System.out.println(index);
    	readings.setElementAt(r, index);
    }
    
    // Removes a Readings object from the readings list at the specified index.
    public void removeReading(int index){
    	readings.remove(index);
    }

    // Retrieves a Readings object by utility name from the readings list.
    // Returns the Readings object if found, otherwise returns null.
    public Readings getReadings(String name) {
        for(Readings r:readings){
            if (name.equals(r.getUtilityName())){
                return r;
            }
        }
        return null;
    }

    // Retrieves all Readings objects in the readings list as an array.
    public Readings[] getAllReadings() {
        Readings[] r = new Readings[readings.size()];
        readings.toArray(r);
        return r;
    }



    //!userReading Methods
    
    // Adds a new user reading entry (a 2D array) to the userReading list.
    public void addUserReading(String[][] userReading){
        this.userReading.add(userReading);
    }
    
    // Edits an existing user reading entry for a specific user and date.
    // The entry is updated with the provided newBill 2D array.
    public void editUserReading(String username,String date, String[][] newBill){
    	int index =0;
    	for (String[][] bill : userReading){
    		String[] billDate = bill[0][2].split("/");
    		 // Check if the username and date match the current bill entry.
    		if (bill[0][0].equals(username)&&(String.join("/", billDate[1],billDate[2]).equals(date))){
                this.userReading.setElementAt(newBill, index);  // Update the entry with newBill.
    			break;
    		}
    		
    		index+=1;
    	}
    }
    
    // Retrieves the last submitted user reading entry for the specified user.
    // Returns the last 2D array if found, otherwise returns null.
    public String[][] getLastUserReading(String userName){
    	String[][][] acctUR = this.getUserReadings(userName);
    	if (acctUR.length==0){return null;}
    	String [][] last = acctUR[acctUR.length-1];
    	return last;
    }

    // Retrieves all user readings for the specified user as a 3D array.
    // The readings are sorted by the entry number.
    public String[][][] getUserReadings(String userName){
        Vector<String[][]> ureading = new Vector<>();
        for(String[][] r: userReading){
            if(r[0][0].equals(userName)){
                ureading.add(r);
            }
        }
        
        String[][][] arr=new String[ureading.size()][this.readings.size()+2][3];
        ureading.toArray(arr);
        //sort by Entry_Number
        try {
        	Arrays.sort(arr, (a,b)-> Integer.compare(Integer.parseInt(a[0][1]), Integer.parseInt(b[0][1])));
		} catch (Exception e) {	// Handle potential errors during sorting.
		}
        return arr;
    }
    
    // Retrieves all user readings for the specified month and year as a 3D array.
    // The readings are sorted by username.
    public String[][][] getUserReadings(String month,String year){
        Vector<String[][]> ureading = new Vector<>();
        for(String[][] r: userReading){
        	String[] d = r[0][2].split("/");
            if(d[d.length-2].equals(month) && d[d.length-1].equals(year)){
                ureading.add(r);
            }
        }
        
        String[][][] arr=new String[ureading.size()][this.readings.size()+2][3];
        ureading.toArray(arr);
        //sort by Username
        try {
        	Arrays.sort(arr, (a,b)-> a[0][0].compareTo(b[0][0]));
		} catch (Exception e) {	// Handle potential errors during sorting
		}
        return arr;
    }
    
    
    
    // Retrieves all user readings as a 3D array.
    public String[][][] getAllUserReadings(){
        String[][][] arr = new String[userReading.size()][][];
        userReading.toArray(arr);
        return arr;
    }

    


    
    // public void editUser(String type,String user, String val) {
    //     type = type.toLowerCase();
    //     int i=0;
    // 	for(Customer u : this.getAllUser()){
    // 		if(user.equals(u.getUsername())) {
    // 			switch(type) {
    // 			case "name": 
    // 				u.setName(val);
    //                 this.user.set(i, u);
    // 				break;
    // 			case "email":
    //                 u.setEmail(val);
    //                 this.user.set(i, u);
    // 				break;
    // 			case "password":
    // 				u.setPassword(val);
    //                 this.user.set(i, u);
    // 				break;
    // 			default:
    // 				break;
    // 			}
    // 		}
    // 		else if(user.equals(u.getEmail())) {
    // 			switch(type){
    //             case "name": 
    //                 u.setName(val);
    //                 this.user.set(i, u);
    // 			    break;
    // 			case "email":
    //                 u.setEmail(val);
    //                 this.user.set(i, u);
    // 	    		break;
    //             case "username":
    //                 u.setUsername(val);
    //                 this.user.set(i, u);
    //                 break;
    // 		    case "password":
    //                 u.setPassword(val);
    //                 this.user.set(i, u);
    // 			    break;
    // 			default:
    //     			break;
    		    	
    //             }
    // 		}
    //         i+=1;
    // 	}
	// }

    // public void setStaff(Vector<Staff> staff) {
    //     this.staff = staff;
    // }

    // public void setUser(Vector<Customer> user) {
    //     this.user = user;
    // }

    // public void setReadings(Vector<Readings> readings) {
    //     this.readings = readings;
    // }





    
    










    
}
