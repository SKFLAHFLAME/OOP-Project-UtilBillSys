package data;

public class MReadings {
    private String utilityName;
    private double meterReading;
    private double price;
    private String unit;
    private double totalPrice;  // Assuming this is the combined price including service charges

    public MReadings() {
        this.utilityName = "";
        this.meterReading = 0.0;
        this.price = 0.0;
        this.unit = "";
        this.totalPrice = 0.0;
    }

    public MReadings(String utilityName, double meterReading, double price, String unit, double totalPrice) {
        this.utilityName = utilityName;
        this.meterReading = meterReading;
        this.price = price;
        this.unit = unit;
        this.totalPrice = totalPrice;
    }

    public String getUtilityName() {
        return utilityName;
    }

    public void setUtilityName(String utilityName) {
        this.utilityName = utilityName;
    }

    public double getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(double meterReading) {
        this.meterReading = meterReading;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
