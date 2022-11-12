package view;
import model.Matrix;
import java.util.Scanner;

public class Window {
    public void printMenu(int s) {
        if (s == 0) {
            System.out.println("1 Enter input matrices");
            System.out.println("2 Arithmetic operations");
            System.out.println("3 Print matrix");
            System.out.println("4 Exit");
        }
        else if (s == 1){
            System.out.println("1 Transpose input matrices");
            System.out.println("2 Multiply input matrices");
            System.out.println("3 Transpose output matrix");
        }
        else if (s == 2){
            System.out.println("1 Print input matrices");
            System.out.println("2 Print output matrix");
        }
    }

    public void printMatrix(Matrix M){
        for (int i = 0; i < M.getRows(); i++) {
            for (int j = 0; j < M.getColumns(); j++) {
                System.out.print(M.getValue(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int intScanner() {
        Scanner scanIn = new Scanner(System.in);
        return scanIn.nextInt();
    }

    public static void matrixScanner(Matrix M){
        for (int i = 0; i < M.getRows(); i++) {
            for (int j = 0; j < M.getColumns(); j++) {
                M.setValue(i, j, intScanner());
            }
        }
    }
}
