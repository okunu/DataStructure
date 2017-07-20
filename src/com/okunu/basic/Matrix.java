package com.okunu.basic;

public class Matrix {

    public int row;
    public int column;
    public int[][] array;
    
    public Matrix(int r, int c){
        row = r;
        column = c;
        array = new int[row][column];
    }
    
    public Matrix mulMatrix(Matrix m){
        if (this.column != m.row) {
            return null;
        }
        Matrix result = new Matrix(row, m.column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < m.column; j++) {
                int c = 0;
                for (int k = 0; k < column; k++) {
                    c = c + array[i][k] * m.array[k][j];
                }
                result.array[i][j] = c;
            }
        }
        return result;
    }
    
    public void setParams(int[][] p){
        this.array = p;
    }
    
    public void print(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(array[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(2, 3);
        int[][] p = new int[][]{{1,2,3},{4,5,6}};
        Matrix m = new Matrix(3, 2);
        int[][] mp = new int[][]{{1,4},{2,5},{3,6}};
        matrix.setParams(p);
        m.setParams(mp);
        matrix.print();
        m.print();
        
        matrix.mulMatrix(m).print();
    }
}
