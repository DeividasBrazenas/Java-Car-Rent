package OutputClasses;

import java.util.Date;

public class SuccessOutput {

    public static void buyCarSuccess(){
        System.out.println("New car bought and saved successfully");
    }

    public static void sellCarSuccess(){
        System.out.println("The car was sold successfully");
    }

    public static void addClientSuccess(){
        System.out.println("The client was added successfully");
    }

    public static void removeClientSuccess(){
        System.out.println("The client was removed successfully");
    }

    public static void startRentSuccess(){
        System.out.println("The rent of a car started at " + new Date());
    }
    public static void stopRentSuccess(){
        System.out.println("The rent of a car was stopped at " + new Date());
    }

}
