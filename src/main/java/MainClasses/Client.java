package MainClasses;

import java.util.Date;

public class Client {
    private String name;
    private String surname;
    private String rentedCarVin;
    private long personalCode;
    private long fullSum;
    private boolean hasDriverLicence;
    private boolean isRentingCar;
    private Date dateStartedRentingCar;

    public Client(String name, String surname, long personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
    }

    public Client(String name, String surname, long personalCode, boolean hasDriverLicence) {
        this(name, surname, personalCode);
        this.hasDriverLicence = hasDriverLicence;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getPersonalCode() {
        return personalCode;
    }

    public boolean hasDriverLicence() {
        return hasDriverLicence;
    }

    public String getRentedCarVin() {
        return rentedCarVin;
    }

    public Date getStartedRentingCar() {
        return dateStartedRentingCar;
    }

    public long getRentDuration() {
        return (new Date().getTime() - dateStartedRentingCar.getTime()) / (60 * 1000) + 1;
    }

    public void setDriverLicenceStatus(boolean status) {
        hasDriverLicence = status;
    }

    public void setRentingStatus(boolean status) {
        isRentingCar = status;
    }

    public void setRentedCarVin(String vin) {
        rentedCarVin = vin;
    }

    public void setRentStartDate(Date date){
        dateStartedRentingCar = date;
    }

    public void setRentStartDate(){
        dateStartedRentingCar = null;
    }

    public void addToFullSum(long money) {
        fullSum += money;
    }

    public boolean canRentCar() {
        if (!isRentingCar) {
            if (hasDriverLicence)
                return true;

            else
                ErrorOutput.driverLicenceError();
        } else
            ErrorOutput.clientAlreadyRentingCarError();
        return false;
    }



}
