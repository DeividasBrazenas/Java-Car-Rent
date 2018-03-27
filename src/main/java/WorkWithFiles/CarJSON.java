package WorkWithFiles;

import MainClasses.Car;
import com.google.gson.Gson;

import java.io.*;
import java.util.LinkedList;

public class CarJSON {
    private static final String carListName = "CarList.json";

    public static LinkedList<Car> getAllCars() {
        LinkedList<Car> carList = new LinkedList<>();
        String jsonLine;

        try {
            BufferedReader br = new BufferedReader(new FileReader(carListName));

            while ((jsonLine = br.readLine()) != null) {
                carList.add(new Gson().fromJson(jsonLine, Car.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carList;
    }

    public static boolean addCar(Car car) {
        Gson gson = new Gson();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(carListName, true));
            PrintWriter pw = new PrintWriter(bw);
            String json = gson.toJson(car);
            pw.println(json);
            pw.close();
            bw.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeCar(String vin) {
        LinkedList<Car> carList = getAllCars();

        try {
            PrintWriter writer = new PrintWriter(carListName);
            writer.print("");
            writer.close();

            for (Car c : carList) {
                if (!c.getVin().equalsIgnoreCase(vin))
                    addCar(c);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Car findCarByName(String name) {
        String jsonLine;
        Car car;

        try {
            BufferedReader br = new BufferedReader(new FileReader(carListName));

            while ((jsonLine = br.readLine()) != null) {
                car = new Gson().fromJson(jsonLine, Car.class);
                if (car.getBrand().equalsIgnoreCase(name))
                    return car;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Car findCarByVin(String vin) {
        String jsonLine;
        Car car;

        try {
            BufferedReader br = new BufferedReader(new FileReader(carListName));

            while ((jsonLine = br.readLine()) != null) {
                car = new Gson().fromJson(jsonLine, Car.class);
                if (car.getVin().equalsIgnoreCase(vin))
                    return car;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean updateCar(String vin, int kilometres) {
        Car car = findCarByVin(vin);

        if(car != null){
            removeCar(vin);
            car.addKilometres(kilometres);

            if(addCar(car))
                return true;
        }
        return  false;
    }

    public static boolean updateCar(String vin, boolean availability) {
        Car car = findCarByVin(vin);

        if(car != null){
            removeCar(vin);
            car.setCarAvailability(availability);

            if(addCar(car))
                return true;
        }
        return false;
    }

    public static boolean updateCarUseFuel(String vin, double fuelUsed) {
        Car car = findCarByVin(vin);

        if(car != null){
            removeCar(vin);
            car.useFuel(fuelUsed);

            if(addCar(car))
                return true;
        }
        return false;
    }

    public static boolean updateCarFillFuel(String vin) {
        Car car = findCarByVin(vin);

        if(car != null){
            removeCar(vin);
            car.fillFuel();

            if(addCar(car))
                return true;
        }
        return false;
    }
}
