package MainClasses;

public class Car {
    private String brand;
    private String vin;
    private int manufactureYear;
    private int kilometrage;
    private long carCost;
    private double fuelTankSize;
    private double fuelLeft;
    private double fuelUsage = 6.5;
    private double costPerMinute = 2.5;
    private boolean availableForRent = true;



    public Car(String vin, String brand, int manufactureYear, long carCost, double fuelTankSize) {
        this.vin = vin;
        this.brand = brand;
        this.manufactureYear = manufactureYear;
        this.carCost = carCost;
        this.fuelTankSize = fuelTankSize;
    }

    public String getBrand(){
        return brand;
    }

    public String getVin() {
        return vin;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public long getCarCost() {
        return carCost;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public double getFuelTankSize() {
        return fuelTankSize;
    }

    public double getFuelLeft() {
        return fuelLeft;
    }

    public double getFuelUsage() {
        return fuelUsage;
    }

    public double getCostPerMinute() {
        return costPerMinute;
    }

    public void addKilometres(int kilometres) {
        kilometrage += kilometres;
    }

    public void setFuelLeft(double fuelLeft) {
        this.fuelLeft = fuelLeft;
    }

    public void setFuelUsage(double fuelUsage) {
        this.fuelLeft = fuelUsage;
    }

    public void useFuel(double fuelUsed) {
        if (fuelLeft - fuelUsed >= 0)
            fuelLeft -= fuelUsed;

        else
            fuelLeft = 0;
    }

    public boolean hasEnoughFuel() {
        return (fuelLeft > (fuelTankSize) / 4);
    }

    public double howMuchFuelUsed(int kilometres) {
        return (kilometres / 100) * fuelUsage;
    }

    public boolean needsFuel() {
        if (fuelLeft < (fuelTankSize / 4))
            return true;
        return false;
    }

    public void fillFuel() {
        double fuelFilled = fuelTankSize - fuelLeft;
        fuelLeft = fuelTankSize;
    }

    public long costOfTrip(long minutes) {
        return minutes * (long) costPerMinute;
    }

    public void setCostPerMinute(double costPerMinute) {
        this.costPerMinute = costPerMinute;
    }

    public void setCarAvailability(boolean availability) {
        this.availableForRent = availability;
    }


    public boolean canBeRented() {
        if (hasEnoughFuel()) {
            if (availableForRent)
                return true;

            else
                ErrorOutput.carNotAvailableForRentError();
        } else
            ErrorOutput.carFuelError();

        return false;
    }

}
