import controller.Company;
import view.Window;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Company com = new Company();
        Window window = new Window();
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                window.menu(0);
                switch (scan.nextInt()){
                    case 1:
                        window.menu(1);
                        window.showList(com.getList());
                        break;
                    case 2:
                        window.menu(2);
                        com.addEmployee(window.setEmployee(scan.nextInt()));
                        break;
                    case 3:
                        window.menu(3);
                        scan.nextLine();
                        com.deleteEmployee(scan.nextLine());
                        break;
                    case 4:
                        window.menu(4);
                        switch (scan.nextInt()){
                            case 1:
                                scan.nextLine();
                                com.save();
                                break;
                            case 2:
                                com.load();
                                break;
                            default:
                                System.err.println("Wrong input");
                        }
                        break;
                    case 5:
                        return;
                    default:
                        System.err.println("Wrong input");
                }
            } catch (InputMismatchException e){
                scan.next();
                System.err.println("Input Mismatch Exception");
            }
        }
    }
}
