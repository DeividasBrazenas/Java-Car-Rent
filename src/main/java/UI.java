import MainClasses.Car;
import MainClasses.Client;
import MainClasses.Company;
import OutputClasses.ErrorOutput;
import OutputClasses.SuccessOutput;

import java.util.Scanner;

public class UI {

    public static void ui(Company company) {
        int caseNumber = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("0 - Exit\n1 - Buy a new car\n2 - Sell a car\n3 - Add a new client\n4 - Remove a client\n5 - Start rent\n6 - Stop rent");

        do {
            try {
                System.out.print("Choose the next operation: ");
                caseNumber = scanner.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch (caseNumber) {
                case 1:
                    //TODO: INPUT BRAND, COST, YEAR ETC
                    if (askAboutSaving()) {
                        if (Management.saveNewCar(new Car("ABC123", "VW", 2017, 30000, 50.0), company)) {
                            SuccessOutput.buyCarSuccess();
                            break;
                        }
                    }
                    ErrorOutput.changesNotSaved();
                    break;

                case 2:
                    //TODO: ASK FOR VIN
                    if (askAboutSelling())
                        if (Management.removeCar("ABC123", company)) {
                            SuccessOutput.sellCarSuccess();
                            break;
                        }
                    ErrorOutput.changesNotSaved();
                    break;

                case 3:
                    //TODO: INPUT CLIENT INFO
                    if (askAboutSaving())
                        if (Management.addNewClient(new Client("Tomas", "Mikna", 321, true))) {
                            SuccessOutput.addClientSuccess();
                            break;
                        }
                    ErrorOutput.changesNotSaved();
                    break;

                case 4:
                    //TODO: INPUT CLIENT PERSONAL CODE
                    if (askAboutSaving())
                        if (Management.removeClient(321)) {
                            SuccessOutput.removeClientSuccess();
                            break;
                        }
                    ErrorOutput.changesNotSaved();
                    break;

                case 5:
                    //TODO: INPUT PERSONAL CODE AND CAR BRAND
                    if (askAboutStarting())
                        if (Management.startRent(321,"VW")) {
                            SuccessOutput.startRentSuccess();
                            break;
                        }
                    ErrorOutput.changesNotSaved();
                    break;

                case 6:
                    //TODO: INPUT PERSONAL CODE AND KILOMETRES
                    if (askAboutStopping())
                        if (Management.stopRent(321,100,company)) {
                            SuccessOutput.stopRentSuccess();
                            break;
                        }
                    ErrorOutput.changesNotSaved();
                    break;

            }
        } while (caseNumber != 0);


    }

    private static boolean askAboutSaving() {
        System.out.println("Do you want to save changes? (Y/N)");

        Scanner scanner = new Scanner(System.in);

        try {
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean askAboutSelling() {
        System.out.println("Do you really want to sell this car? (Y/N)");

        Scanner scanner = new Scanner(System.in);

        try {
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean askAboutStarting() {
        System.out.println("Do you really want to start rent? (Y/N)");

        Scanner scanner = new Scanner(System.in);

        try {
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean askAboutStopping() {
        System.out.println("Do you really want to stop rent? (Y/N)");

        Scanner scanner = new Scanner(System.in);

        try {
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
