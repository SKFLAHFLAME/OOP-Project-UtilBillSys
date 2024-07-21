package data;

public class Readings {
    private String utilityName;
    private double readings;
    private String unit;
    private double price;
    private double serviceCharge;
    
    public Readings(){
        utilityName = null;
        readings = 0.0;
    }
    public Readings(String name, double reading){
        utilityName = name;
        readings = reading;
    }
    public Readings(String name, double price,String unit, double serviceCharge){
        this.utilityName = name;
        this.price = price;
        this.unit=unit;
        this.serviceCharge = serviceCharge;
    }
    
    public Readings(String name,double mreading, double price,String unit, double serviceCharge){
        this.utilityName = name;
        this.readings = mreading;
        this.price = price;
        this.unit=unit;
        this.serviceCharge = serviceCharge;
    }
    
    

    public String getUtilityName() {
        return utilityName;
    }
    public void setUtilityName(String utilityName) {
        this.utilityName = utilityName;
    }
    public double getReadings() {
        return readings;
    }
    public void setReadings(double readings) {
        this.readings = readings;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

    
}
