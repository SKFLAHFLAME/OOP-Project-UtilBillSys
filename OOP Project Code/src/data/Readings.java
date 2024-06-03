package data;

public class Readings {
    private String utilityName;
    private double readings;
    private double serviceCharge;
    
    public Readings(){
        utilityName = null;
        readings = 0.0;
    }
    public Readings(String name, double reading){
        utilityName = name;
        readings = reading;
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

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
    
}
