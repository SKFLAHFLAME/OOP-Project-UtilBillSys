package data;

import java.util.HashMap;
import java.util.Map.Entry;

import java.util.HashMap;

public class Customer extends Account{
    // private String address;
	private HashMap<String, Double> draft = new HashMap<>();
    private String name;
	private String email;
    private String address;
    private String lastSubmitted;
    

    //!Constructors
    public Customer(){
        super();
        this.email = "Nil";
        this.name = "Nil";
    }
    public Customer(String uname, String pass){
        super(uname,pass);
        this.email = "Nil";
        this.name = "Nil";
    }
    
    public Customer(String fname, String email, String uname, String pass){
        super(uname,pass);
        this.email = email;
        this.name = fname;
    }
    
    public Customer(String fname, String email, String uName, String pass, String address) {
        super(uName,pass);
        this.email = email;
        this.name = fname;
        this.address = address;
    }






    //!Meter Reading Methods
    public void addMeterReading(String readingName, Double meterReading){
        draft.put(readingName, meterReading);
    }

    public void editMeterReading(String readingName, Double newMeterReading){
        draft.replace(readingName, newMeterReading);
    }

    public void editReadingName(String oldReadingName, String newReadingName){
        if(!draft.containsKey(oldReadingName)){return;}
        draft.put(newReadingName, draft.remove(oldReadingName));
    }

    public String[][] getDraftArray(){
        String[][] r = new String[draft.size()][2];
        int c=0;
        for (Entry<String, Double> reading: draft.entrySet()){
            r[c][0] = reading.getKey();
            r[c][1] = String.valueOf(reading.getValue());
            c+=1;
        }
        return r;

    }
    
    public void deleteMeterReading(String readingName){
        draft.remove(readingName);
    }

    public void clearDraft(){
        draft.clear();
    }


    //!Setters and getters
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    } 

    public HashMap<String, Double> getDraft() {
        return draft;
    }

    public void setDraft(HashMap<String, Double> draft) {
        this.draft = draft;
    }
	public String[] getLastSubmitted() {
		return lastSubmitted.split("/");
	}
	public void setLastSubmitted(String month, String year) {
		lastSubmitted = String.join("/", month,year);
	}
	public String getLastSubmittedString() {
		return lastSubmitted;
	}
	public void setLastSubmittedString(String lastSubmitted) {
		this.lastSubmitted = lastSubmitted;
	}
}
