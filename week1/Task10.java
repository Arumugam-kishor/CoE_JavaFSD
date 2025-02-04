package week1;

import java.util.Arrays;

class MatrixMultiplier implements Runnable {
    private int[][] A, B, result;
    private int row;

    public MatrixMultiplier(int[][] A, int[][] B, int[][] result, int row) {
        this.A = A;
        this.B = B;
        this.result = result;
        this.row = row;
    }

    @Override
    public void run() {
        int colsB = B[0].length;
        int colsA = A[0].length;
        
        for (int j = 0; j < colsB; j++) {
            result[row][j] = 0;
            for (int k = 0; k < colsA; k++) {
                result[row][j] += A[row][k] * B[k][j];
            }
        }
    }
}

public class Task10 {
    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsB = matrixB[0].length;
        int[][] result = new int[rowsA][colsB];

        Thread[] threads = new Thread[rowsA];

        for (int i = 0; i < rowsA; i++) {
            threads[i] = new Thread(new MatrixMultiplier(matrixA, matrixB, result, i));
            threads[i].start();
        }

        for (int i = 0; i < rowsA; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrixA = { {1, 2}, {3, 4} };
        int[][] matrixB = { {2, 0}, {1, 2} };

        int[][] result = multiplyMatrices(matrixA, matrixB);

        System.out.println("Result of matrix multiplication:");
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}

