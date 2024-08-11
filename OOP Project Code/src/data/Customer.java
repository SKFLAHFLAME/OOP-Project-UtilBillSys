package data;

import java.util.HashMap;
import java.util.Map.Entry;

public class Customer extends Account {
    // HashMap to store draft meter readings, with the reading name as the key 
    // and the meter reading as the value
    private HashMap<String, Integer> draft = new HashMap<>();
    
    // Properties to store the customer's name, email, address, and last submitted date
    private String name;
    private String email;
    private String address;
    private String lastSubmitted;
    
    //!Constructors
    
    // Default constructor that initializes email and name to "Nil" and calls the superclass (Account) constructor
    public Customer() {
        super();
        this.email = "Nil";
        this.name = "Nil";
    }
    
    // Constructor that initializes the username and password in the superclass and sets email and name to "Nil"
    public Customer(String uname, String pass) {
        super(uname, pass);
        this.email = "Nil";
        this.name = "Nil";
    }
    
    // Constructor that initializes the full name, email, username, and password
    public Customer(String fname, String email, String uname, String pass) {
        super(uname, pass);
        this.email = email;
        this.name = fname;
    }
    
    // Constructor that initializes the full name, email, username, password, and address
    public Customer(String fname, String email, String uName, String pass, String address) {
        super(uName, pass);
        this.email = email;
        this.name = fname;
        this.address = address;
    }
    
    //!Meter Reading Methods
    
    // Method to add a meter reading to the draft with the given reading name and value
    public void addMeterReading(String readingName, int meterReading) {
        draft.put(readingName, meterReading);
    }

    // Method to edit an existing meter reading in the draft by updating its value
    public void editMeterReading(String readingName, int newMeterReading) {
        draft.replace(readingName, newMeterReading);
    }

    // Method to change the name of an existing meter reading in the draft
    public void editReadingName(String oldReadingName, String newReadingName) {
        // Check if the old reading name exists in the draft
        if (!draft.containsKey(oldReadingName)) {
            return;
        }
        // Replace the old reading name with the new one while retaining the reading value
        draft.put(newReadingName, draft.remove(oldReadingName));
    }

    // Method to get the draft readings as a 2D array of strings
    public String[][] getDraftArray() {
        String[][] r = new String[draft.size()][2];
        int c = 0;
        // Iterate over the draft entries to populate the array
        for (Entry<String, Integer> reading : draft.entrySet()) { // Loop through draft entries
            r[c][0] = reading.getKey(); // Set the reading name
            r[c][1] = String.valueOf(reading.getValue()); // Set the reading value
            c += 1;
        }
        return r;
    }
    
    // Method to delete a meter reading from the draft by its name
    public void deleteMeterReading(String readingName) {
        draft.remove(readingName);
    }

    // Method to clear all meter readings from the draft
    public void clearDraft() {
        draft.clear();
    }

    //!Setters and Getters
    
    // Getter method for the email property
    public String getEmail() {
        return email;
    }

    // Setter method for the email property
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for the name property
    public String getName() {
        return name;
    }

    // Setter method for the name property
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for the address property
    public String getAddress() {
        return address;
    }

    // Setter method for the address property
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter method for the draft property (returns the entire draft HashMap)
    public HashMap<String, Integer> getDraft() {
        return draft;
    }

    // Setter method for the draft property (replaces the entire draft HashMap)
    public void setDraft(HashMap<String, Integer> draft) {
        this.draft = draft;
    }

    // Getter method for the last submitted date, returns it as an array of Strings [month, year]
    public String[] getLastSubmitted() {
        return lastSubmitted.split("/");
    }

    // Setter method to set the last submitted date by month and year
    public void setLastSubmitted(String month, String year) {
        lastSubmitted = String.join("/", month, year);
    }

    // Getter method for the last submitted date, returns it as a single String
    public String getLastSubmittedString() {
        return lastSubmitted;
    }

    // Setter method to set the last submitted date using a single String
    public void setLastSubmittedString(String lastSubmitted) {
        this.lastSubmitted = lastSubmitted;
    }
}
