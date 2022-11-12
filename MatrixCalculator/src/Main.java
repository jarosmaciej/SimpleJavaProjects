import controller.MatrixCalculator;
import view.Window;
import java.util.NoSuchElementException;
import static view.Window.intScanner;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        MatrixCalculator mxCalc = new MatrixCalculator();
        while (true) {
            window.printMenu(0);
            switch (intScanner()) {
                case 1:
                    System.out.println("Enter the number of rows and columns of the first matrix");
                    int rows = intScanner();
                    int cols = intScanner();
                    System.out.println("Enter first matrix's values: ");
                    mxCalc.setA(rows, cols);
                    System.out.println("Enter the number of columns and rows of the second matrix: ");
                    rows = intScanner();
                    cols = intScanner();
                    System.out.println("Enter second matrix's values: ");
                    mxCalc.setB(rows, cols);
                    break;
                case 2:
                    if (mxCalc.getA() != null){
                        window.printMenu(1);
                        int n = intScanner();
                        if (n == 1) {
                            mxCalc.setA(mxCalc.matrixTranspose(mxCalc.getA()));
                            mxCalc.setB(mxCalc.matrixTranspose(mxCalc.getB()));
                        } else if (n == 2) {
                            mxCalc.matrixMultiply();
                        } else if (n == 3){
                            if (mxCalc.getC() != null)
                                mxCalc.setC(mxCalc.matrixTranspose(mxCalc.getC()));
                            else{
                                System.err.println("Output matrix must exist");
                                throw new NoSuchElementException();
                            }
                        } else {
                            System.err.println("You've entered the wrong number");
                            throw new IllegalArgumentException();
                        }
                    }
                    else{
                        System.err.println("Input matrices must exist");
                        throw new NoSuchElementException();
                    }
                    break;
                case 3:
                    if (mxCalc.getA() != null) {
                        window.printMenu(2);
                        int n = intScanner();
                        if (n == 1) {
                            System.out.println("First input matrix:");
                            window.printMatrix(mxCalc.getA());
                            System.out.println("Second input matrix:");
                            window.printMatrix(mxCalc.getB());
                        } else if (n == 2 && mxCalc.getC() != null) {
                            System.out.println("Output matrix:");
                            window.printMatrix(mxCalc.getC());
                        } else {
                            System.err.println("Output matrix must exist");
                            throw new NoSuchElementException();
                        }
                    } else {
                        System.err.println("Input matrices must exist");
                        throw new NoSuchElementException();
                    }
                    break;
                case 4:
                    return;
                default:
                    System.err.println("You've entered the wrong number");
                    throw new IllegalArgumentException();
            }
        }
    }
}