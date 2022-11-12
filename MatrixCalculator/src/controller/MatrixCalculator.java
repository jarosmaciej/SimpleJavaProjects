package controller;
import model.Matrix;
import static view.Window.matrixScanner;

public class MatrixCalculator {
    private Matrix A;
    private Matrix B;
    private Matrix C;

    public void setA(int rows, int cols) {
        A = new Matrix(rows, cols);
        matrixScanner(A);
    }

    public void setA(Matrix M) { A = M; }

    public void setB(int rows, int cols) {
        B = new Matrix(rows, cols);
        matrixScanner(B);
    }

    public void setB(Matrix M) { B = M; }

    public void setC(Matrix M) { C = M; }

    public Matrix getA(){
        return A;
    }

    public Matrix getB(){
        return B;
    }

    public Matrix getC() { return C; }

    public Matrix matrixTranspose(Matrix M){
        int rows = M.getRows();
        int cols = M.getColumns();
        Matrix T = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                T.setValue(j, i, M.getValue(i, j));
        return T;
    }

    public void matrixMultiply(){
        int colsA = A.getColumns();
        int rowsB = B.getRows();
        if (colsA == rowsB) {
            int rowsA = A.getRows();
            int colsB = B.getColumns();
            C = new Matrix(rowsA, colsB);
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsB; j++) {
                    C.setValue(i, j, 0);
                    for (int k = 0; k < rowsB; k++) {
                        C.setValue(i, j, C.getValue(i, j) + A.getValue(i, k) * B.getValue(k, j));
                    }
                }
            }
        } else{
            System.err.println("First input matrix must have the same number of columns as the second matrix rows");
            throw new ArithmeticException();
        }
    }

}
