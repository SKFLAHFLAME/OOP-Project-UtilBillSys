package data;

public class Readings {
    // Properties to store the utility name, readings value, unit of measurement, price, and service charge
    private String utilityName;
    private double readings;
    private String unit;
    private double price;
    private double serviceCharge;
    
    // Default constructor that initializes all properties to their default values
    public Readings() {
        this.utilityName = null;
        this.readings = 0.0;
        this.unit = null;
        this.price = 0.0;
        this.serviceCharge = 0.0;
    }
    
    // Constructor that initializes the utility name and readings value
    public Readings(String name, double reading) {
        this.utilityName = name;
        this.readings = reading;
    }
    
    // Constructor that initializes the utility name, price, unit of measurement, and service charge
    public Readings(String name, double price, String unit, double serviceCharge) {
        this.utilityName = name;
        this.price = price;
        this.unit = unit;
        this.serviceCharge = serviceCharge;
    }
    
    // Constructor that initializes all properties of the Readings object
    public Readings(String name, double mreading, double price, String unit, double serviceCharge) {
        this.utilityName = name;
        this.readings = mreading;
        this.price = price;
        this.unit = unit;
        this.serviceCharge = serviceCharge;
    }
    
    // Getter method for the utility name
    public String getUtilityName() {
        return utilityName;
    }
    
    // Setter method for the utility name
    public void setUtilityName(String utilityName) {
        this.utilityName = utilityName;
    }
    
    // Getter method for the readings value
    public double getReadings() {
        return readings;
    }
    
    // Setter method for the readings value
    public void setReadings(double readings) {
        this.readings = readings;
    }
    
    // Getter method for the price
    public double getPrice() {
        return price;
    }
    
    // Setter method for the price
    public void setPrice(double price) {
        this.price = price;
    }
    
    // Getter method for the service charge
    public double getServiceCharge() {
        return serviceCharge;
    }
    
    // Setter method for the service charge
    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
    
    // Getter method for the unit of measurement
    public String getUnit() {
        return unit;
    }
    
    // Setter method for the unit of measurement
    public void setUnit(String unit) {
        this.unit = unit;
    }
}
