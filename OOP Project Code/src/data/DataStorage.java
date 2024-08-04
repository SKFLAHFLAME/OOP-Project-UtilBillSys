package data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class DataStorage {
    private Vector<Staff> staff = new Vector<>();
    private Vector<Customer> user = new Vector<>();
    private Vector<Readings> readings = new Vector<>();
    private Vector<String[][]> userReading = new Vector<>();//UName, Entry_No, Readings...


    public DataStorage (){
        Staff admin = new Staff("admin", "admin");
        staff.add(admin);
    }


    //!Customer Methods
    public void addUser(Customer cust){
        user.add(cust);
    }

    public void editUser(String uname, Customer acct){
        int i=0;
        for(Customer c:user){
            if (c.getUsername().equals(uname)){
                user.setElementAt(acct, i);
            }
            i+=1;
        }
    }

    public void removeUser(String userName){
        int c=0;
        for(Customer cust:user){
            if(cust.getUsername().equals(userName)){
                user.remove(c);
            }
            c+=1;
        }
    }

    public Customer getUser(String name) {
        for(Customer u:user){
            if (u.getUsername().equals(name)){
                return u;
            }
        }
        return null;
    }

    public Customer[] getAllUser() {
        Customer[] c = new Customer[user.size()];
        user.toArray(c);
        return c;
    }
        
    //! user draft Methods
    public boolean hasDraft(String userName){
        Customer c = this.getUser(userName);
        return (c.getDraft().size()!=0);
    }

    public void addMeterReading(String userName, String readingName, Integer meterReading){
        int i=0;
        for(Customer c : user){
            if(c.getUsername().equals(userName)){
                c.addMeterReading(readingName, meterReading);
                user.setElementAt(c, i);
            }
            i+=1;
        }
    }

    public void editMeterReading(String userName, String readingName, Integer newMeterReading){
        int i=0;
        for(Customer c : user){
            if(c.getUsername().equals(userName)){
                c.editMeterReading(readingName, newMeterReading);
                user.setElementAt(c, i);
            }
            i+=1;
        }
    }
    public void editMeterReadingName(String userName, String readingName, String newReadingName){
        int i=0;
        for(Customer c : user){
            if(c.getUsername().equals(userName)){
                c.editReadingName(readingName, newReadingName);
                user.setElementAt(c, i);
            }
            i+=1;
        }
    }

    public void removeMeterReading(String userName, String readingName){
        int i=0;
        for(Customer c : user){
            if(c.getUsername().equals(userName)){
                c.deleteMeterReading(readingName);
                user.setElementAt(c, i);
            }
            i+=1;
        }
    }

    public void removeDraft(String userName){
        int i=0;
        for(Customer c : user){
            if(c.getUsername().equals(userName)){
                c.clearDraft();
                user.setElementAt(c, i);
            }
            i+=1;
        }
    }

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
    public void addStaff(Staff s){
        staff.add(s);
    }

    public void editStaff(String id, Staff acct){
        int c=0;
        for (Staff s:staff){
            if(s.getUsername().equals(id)){
                staff.setElementAt(acct, c);
            }
            c+=1;
        }
    }

    public void removeStaff(String id){
        int c=0;
        for (Staff s:staff){
            if (s.getUsername().equals(id)){
                staff.remove(c);
                c+=1;
                break;
            }
            c+=1;
        }
    }

    public Staff getStaff(String id) {
        for(Staff s:staff){
            if (id.equals(s.getUsername())){
                return s;
            }
        }
        return null;
    }

    public Staff[] getAllStaff() {
        Staff[] s = new Staff[staff.size()];
        staff.toArray(s);
        return s;
    }


    //!Reading Methods
    public void addReading(Readings r){
    	readings.add(r);
    }
    public void updateReading(Readings r,int index){
    	System.out.println(index);
    	readings.setElementAt(r, index);
    }
    
    public void removeReading(int index){
    	readings.remove(index);
    }


    public Readings getReadings(String name) {
        for(Readings r:readings){
            if (name.equals(r.getUtilityName())){
                return r;
            }
        }
        return null;
    }

    public Readings[] getAllReadings() {
        Readings[] r = new Readings[readings.size()];
        readings.toArray(r);
        return r;
    }



    //!userReading Methods
    public void addUserReading(String[][] userReading){
        this.userReading.add(userReading);
    }

    //add more here
    
    public String[][] getLastUserReading(String userName){
    	String[][][] acctUR = this.getUserReadings(userName);
    	String [][] last = acctUR[acctUR.length-1];
    	return last;
    }

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
		} catch (Exception e) {
		}
        return arr;
    }
    
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
		} catch (Exception e) {
		}
        return arr;
    }

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
