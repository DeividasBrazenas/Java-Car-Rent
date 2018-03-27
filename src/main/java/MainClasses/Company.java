package MainClasses;

import java.io.*;

public class Company {
    private long budget;
    private String companyBudgetFile = "CompanyBudget.txt";

    public Company() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(companyBudgetFile));

            budget = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void updateBudgetInFile(long newBudget) {
        try {
            PrintWriter writer = new PrintWriter(companyBudgetFile);
            writer.print("");
            writer.print(newBudget);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getBudget() {
        return budget;
    }

    public void addToBudget(long money) {
        budget += money;
    }

    public void substractFromBudget(long money) {
        budget -= money;
    }




}
