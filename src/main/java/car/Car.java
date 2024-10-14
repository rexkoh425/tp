package car;

public class Car {

    private String model;
    private String licensePlateNumber;
    private double price;

    public Car(String model, String licensePlateNumber, double price) {
        this.model = model;
        this.licensePlateNumber = licensePlateNumber;
        this.price = price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
