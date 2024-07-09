package data;

import java.util.Vector;

public class DataStorage {
    private Vector<Staff> staff = new Vector<>();
    private Vector<Customer> user = new Vector<>();
    private Vector<Readings> readings = new Vector<>();
    private Vector<String[]> userReading = new Vector<>();

    public DataStorage (){
        Staff admin = new Staff("admin", "admin");
        staff.add(admin);
    }
    public void addStaff(Staff s){
        staff.add(s);
    }


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

    public void editStaff(String id, Staff acct){
        int c=0;
        for (Staff s:staff){
            if(s.getUsername().equals(id)){
                staff.setElementAt(acct, c);
            }
            c+=1;
        }
    }
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
    
    public void editUser(String type,String user, String val) {
        type = type.toLowerCase();
        int i=0;
    	for(Customer u : this.getAllUser()){
    		if(user.equals(u.getUsername())) {
    			switch(type) {
    			case "name": 
    				u.setName(val);
                    this.user.set(i, u);
    				break;
    			case "email":
                    u.setEmail(val);
                    this.user.set(i, u);
    				break;
    			case "password":
    				u.setPassword(val);
                    this.user.set(i, u);
    				break;
    			default:
    				break;
    			}
    		}
    		else if(user.equals(u.getEmail())) {
    			switch(type){
                case "name": 
                    u.setName(val);
                    this.user.set(i, u);
    			    break;
    			case "email":
                    u.setEmail(val);
                    this.user.set(i, u);
    	    		break;
                case "username":
                    u.setUsername(val);
                    this.user.set(i, u);
                    break;
    		    case "password":
                    u.setPassword(val);
                    this.user.set(i, u);
    			    break;
    			default:
        			break;
    		    	
                }
    		}
            i+=1;
    	}
    	
    	
    	
		
	}




    // public void setStaff(Vector<Staff> staff) {
    //     this.staff = staff;
    // }

    // public void setUser(Vector<Customer> user) {
    //     this.user = user;
    // }

    // public void setReadings(Vector<Readings> readings) {
    //     this.readings = readings;
    // }


    public Staff getStaff(String id) {
        for(Staff s:staff){
            if (id.equals(s.getUsername())){
                return s;
            }
        }
        return null;
    }

    public Customer getUser(String name) {
        for(Customer u:user){
            if (u.getUsername().equals(name)){
                return u;
            }

        }
        return null;
    }
    
    

    public Readings getReadings(String name) {
        for(Readings r:readings){
            if (name.equals(r.getUtilityName())){
                return r;
            }
        }
        return null;
    }

    public Staff[] getAllStaff() {
        Staff[] s = new Staff[staff.size()];
        staff.toArray(s);
        return s;

    }

    public Customer[] getAllUser() {
        Customer[] c = new Customer[user.size()];
        user.toArray(c);
        return c;
    }

    public Readings[] getAllReadings() {
        Readings[] r = new Readings[readings.size()];
        readings.toArray(r);
        return r;
    }

    
}
