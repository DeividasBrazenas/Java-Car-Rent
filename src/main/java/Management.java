import MainClasses.Car;
import MainClasses.Client;
import MainClasses.Company;
import WorkWithFiles.CarJSON;
import WorkWithFiles.ClientJSON;

import java.util.Date;

public class Management {
    public static boolean saveNewCar(Car car, Company company){
        car.setFuelLeft(car.getFuelTankSize());
        car.setCarAvailability(true);
        if(CarJSON.addCar(car)){
            company.substractFromBudget(car.getCarCost());
            company.updateBudgetInFile(company.getBudget());
            return true;
        }

        return false;
    }

    public static boolean removeCar(String vin, Company company){
        Car car = CarJSON.findCarByVin(vin);

        if(car != null)
            if(CarJSON.removeCar(vin)){
                company.addToBudget((car.getCarCost() / 2));
                company.updateBudgetInFile(company.getBudget());

                return true;
            }
        return false;
    }

    public static boolean addNewClient(Client client){
        return ClientJSON.addClient(client);
    }

    public static boolean removeClient(long personalCode) {
        return ClientJSON.removeClient(personalCode);
    }

    public static boolean startRent(long personalCode, String brand) {
        Client client = ClientJSON.findClientByPersonalCode(personalCode);
        Car car = CarJSON.findCarByName(brand);

        if (client != null && car != null)
            if (car.canBeRented() && client.canRentCar())
                if (takeCar(client,car))
                    return true;

        return false;
    }

    public static boolean stopRent(long personalCode, int kilometres, Company company) {
        Client client = ClientJSON.findClientByPersonalCode(personalCode);
        Car car = CarJSON.findCarByVin(client.getRentedCarVin());

        if (client != null && car != null) {
            long minutes = client.getRentDuration();
            payBill(company, client, car, minutes);

            if (CarJSON.updateCarUseFuel(client.getRentedCarVin(), car.howMuchFuelUsed(kilometres))) {
                car = CarJSON.findCarByVin(client.getRentedCarVin());

                if (car.needsFuel()) {
                    CarJSON.updateCarFillFuel(client.getRentedCarVin());
                    company.substractFromBudget((long) (car.getFuelTankSize() - car.getFuelLeft()) * 2);
                    company.updateBudgetInFile(company.getBudget());
                }

                if (returnCar(client,kilometres))
                    return true;
            }
        }
        return false;
    }

    private static boolean takeCar(Client client, Car car) {
        if(CarJSON.updateCar(car.getVin(), false)){
            client.setRentingStatus(true);
            client.setRentedCarVin(car.getVin());
            client.setRentStartDate(new Date());

            if(ClientJSON.removeClient(client.getPersonalCode()) && ClientJSON.addClient(client));
                return true;
        }
        return false;
    }

    private static boolean returnCar(Client client, int kilometres) {
        Car car = CarJSON.findCarByVin(client.getRentedCarVin());

        if(car != null && ClientJSON.removeClient(client.getPersonalCode())){
            client.setRentedCarVin(null);
            client.setRentingStatus(false);
            client.setRentStartDate();

            if(CarJSON.updateCar(car.getVin(), true) && CarJSON.updateCar(car.getVin(), kilometres) && ClientJSON.addClient(client))
                return true;
        }
        return false;
    }


    private static void payBill(Company company, Client client, Car car, long minutes) {
        client.addToFullSum(car.costOfTrip(minutes));
        company.addToBudget(car.costOfTrip(minutes));
        company.updateBudgetInFile(company.getBudget());
    }
}
