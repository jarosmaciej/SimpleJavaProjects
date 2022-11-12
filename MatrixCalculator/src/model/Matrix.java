package model;

public class Matrix {
    private final int[][] values;
    private final int rows;
    private final int cols;

    public Matrix(int m, int k){
        rows = m;
        cols = k;
        values = new int [m][k];
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return cols;
    }

    public int getValue(int i, int j){
        if (i < rows && j < cols && i >= 0 && j >= 0)
            return values[i][j];
        System.err.println("values[" + i + "][" + j + "] couldn't be get - array index out bounds");
        throw new ArrayIndexOutOfBoundsException();
    }

    public void setValue(int i, int j, int value){
        if (i < rows && j < cols && i >= 0 && j >= 0){
            values[i][j] = value;
            return;
        }
        System.err.println("values[" + i + "][" + j + "] couldn't be set - array index out bounds");
        throw new ArrayIndexOutOfBoundsException();
    }

}