package data;

public class Readings {
    private String utilityName;
    private double readings;
    
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
    
}
