package data;

import java.util.Vector;

public class DataStorage {
    private Vector<Staff> staff = new Vector<>();
    private Vector<Customer> user = new Vector<>();
    private Vector<Readings> readings = new Vector<>();


    public void addUser(Customer cust){
        user.add(cust);
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
        user.toArray(r);
        return r;
    }

    
}
