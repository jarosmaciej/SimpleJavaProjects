package view;

import model.Director;
import model.Employee;
import model.Trader;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class Window {

    public void menu(int a){
        if (a == 0) {
            System.out.println("MENU:");
            System.out.println("    1: Employees list");
            System.out.println("    2: Input employee");
            System.out.println("    3: Delete employee");
            System.out.println("    4: Backup");
            System.out.println("    5: Exit");
        }
        else if (a == 1){
            System.out.println("EMPLOYEES LIST");
        }
        else if (a == 2){
            System.out.println("INPUT EMPLOYEE:");
            System.out.println("    1: DIRECTOR");
            System.out.println("    2: TRADER");
        }
        else if (a == 3){
            System.out.println("DELETE EMPLOYEE");
            System.out.print("Input PESEL: ");
        }
        else if (a == 4){
            System.out.println("BACKUP");
            System.out.println("  1. Save");
            System.out.println("  2. Open");
        }
    }
    public Employee setEmployee(int a){
        Employee e;
        Scanner scan = new Scanner(System.in);
        System.out.print("Input PESEL: ");
        String pesel = scan.nextLine();
        System.out.print("Input first name: ");
        String firstName = scan.nextLine();
        System.out.print("Input last name: ");
        String lastName = scan.nextLine();
        System.out.print("Input salary: ");
        BigDecimal salary = scan.nextBigDecimal();
        System.out.print("Input phone number: ");
        String phoneNumber = scan.nextLine();
        scan.nextLine();
        if (a == 1){
            System.out.print("Input service allowance: ");
            BigDecimal serviceAllowance = scan.nextBigDecimal();
            scan.nextLine();
            System.out.print("Input business card number: ");
            String businessCard = scan.nextLine();
            System.out.print("Input month cost limit: ");
            BigDecimal costLimit = scan.nextBigDecimal();
            e = new Director(pesel, firstName, lastName, phoneNumber, salary, serviceAllowance, businessCard, costLimit);
        }
        else if (a == 2){
            System.out.print("Input commission (%): ");
            int commission = scan.nextInt();
            System.out.print("Input month commission limit (PLN): ");
            BigDecimal commissionLimit = scan.nextBigDecimal();
            e = new Trader(pesel, firstName, lastName, phoneNumber, salary, commission, commissionLimit);
        } else
            return null;
        return e;
    }

    public void showList(HashMap<String, Employee> list){
        int size = list.size();
        int i = 1;
        for (HashMap.Entry<String, Employee> it : list.entrySet()) {
            it.getValue().showEmployee();

            System.out.println("[Q] Quit");
            System.out.println("[N] Next");
            System.out.println("[Position: " + i + "/" + size + "]");

            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();

            ++i;
            if (s.equals("Q"))
                return;
            if (!s.equals("N"))
                System.err.println("Wrong input");
        }
    }
}
