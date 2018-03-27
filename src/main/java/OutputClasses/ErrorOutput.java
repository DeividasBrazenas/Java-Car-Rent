package OutputClasses;

public class ErrorOutput {

    public static void driverLicenceError(){
        System.out.println("MainClasses.Client doesn't have drivers licence");
    }

    public static void clientAlreadyRentingCarError(){
        System.out.println("MainClasses.Client is already renting a car");
    }

    public static void carNotAvailableForRentError(){
        System.out.println("This car is not available for rent");
    }

    public static void carFuelError(){
        System.out.println("This car doesn't have enough fuel");
    }

    public static void carNotFoundError(){
        System.out.println("MainClasses.Car with this brand hasn't been found");
    }

    public static void clientNotFOundError(){
        System.out.println("MainClasses.Client with this personal number hasn't been found");
    }

    public static void changesNotSaved(){
        System.out.println("Changes were not saved");
    }
}

