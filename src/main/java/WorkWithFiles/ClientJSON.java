package WorkWithFiles;

import MainClasses.Client;
import com.google.gson.Gson;

import java.io.*;
import java.util.LinkedList;

public class ClientJSON {
    private static final String clientListName = "ClientList.json";

    public static LinkedList<Client> getAllClients() {
        LinkedList<Client> clientList = new LinkedList<>();
        String jsonLine;

        try {
            BufferedReader br = new BufferedReader(new FileReader(clientListName));

            while ((jsonLine = br.readLine()) != null) {
                clientList.add(new Gson().fromJson(jsonLine, Client.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }

    public static boolean addClient(Client client) {
        Gson gson = new Gson();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(clientListName, true));
            PrintWriter pw = new PrintWriter(bw);
            String json = gson.toJson(client);
            pw.println(json);
            pw.close();
            bw.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeClient(long personalCode) {
        LinkedList<Client> clientList = getAllClients();

        try {
            PrintWriter writer = new PrintWriter(clientListName);
            writer.print("");
            writer.close();

            for (Client c : clientList) {
                if (Long.compare(personalCode, c.getPersonalCode()) != 0)
                    addClient(c);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Client findClientByPersonalCode(long personalCode) {
        String jsonLine;
        Client client;

        try {
            BufferedReader br = new BufferedReader(new FileReader(clientListName));

            while ((jsonLine = br.readLine()) != null) {
                client = new Gson().fromJson(jsonLine, Client.class);
                if (Long.compare(personalCode, client.getPersonalCode()) == 0)
                    return client;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateClient(long personalCode, boolean driverLicenceStatus) {
        Client client = findClientByPersonalCode(personalCode);
        removeClient(personalCode);
        client.setDriverLicenceStatus(driverLicenceStatus);
        addClient(client);
    }

    public static void updateClient(long personalCode, long money) {
        Client client = findClientByPersonalCode(personalCode);
        removeClient(personalCode);
        client.addToFullSum(money);
        addClient(client);
    }

    public static void updateClientRentedCarVin(long personalCode, String vin) {
        Client client = findClientByPersonalCode(personalCode);
        removeClient(personalCode);
        client.setRentedCarVin(vin);
        addClient(client);
    }

    public static void updateClientRentStatus(long personalCode, boolean status) {
        Client client = findClientByPersonalCode(personalCode);
        removeClient(personalCode);
        client.setRentingStatus(status);
        addClient(client);
    }
}
