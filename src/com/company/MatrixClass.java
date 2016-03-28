package com.company;

import java.util.Random;


public class MatrixClass {

    public void MultipleMatrix() {
        int[][] matrixA = new int[1000][700];
        int[][] matrixB = new int[700][1300];
        FillMatrix(matrixA);
        //PrintMatrix(matrixA);
        FillMatrix(matrixB);
        //PrintMatrix(matrixB);
        long start = System.currentTimeMillis();
        Multiplication(matrixA,matrixB);
        long stop = System.currentTimeMillis();
        System.out.println("Consistent: "+(stop-start)+" ms");
        start = System.currentTimeMillis();
        ParallelMultiplication(matrixA,matrixB);
        stop = System.currentTimeMillis();
        System.out.println("Parallel: "+(stop-start)+" ms");
        //PrintMatrix(matrixM);
    }


    public int[][] Transposition(int[][] matrix) {
        int[][] newMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public int[][] ParallelMultiplication(int[][] a, int[][] b) {

        int[][] bT = Transposition(b);
        int lengthA = a.length;
        int lengthB = b[0].length;
        int lengthBT = bT.length;
        int[][] matrixM = new int[lengthA][lengthB];
        for (int i = 0; i < lengthA; i++) {
            for (int j = 0; j < lengthBT; j++) {
                final int tmp = a[i][0];
                new SummThread(a[i],bT[j]).run();
                matrixM[i][j] = a[i][0];
                a[i][0] = tmp;
            }
        }
        return matrixM;
    }

    public int[][] Multiplication(int[][] a, int[][] b) {
        int lengthA = a.length;
        int lengthB = b[0].length;
        int lengthK = b.length;
        int sum = 0;
        int[][] matrixM = new int[lengthA][lengthB];
        for (int i = 0; i < lengthA; i++) {
            for (int j = 0; j < lengthB; j++) {
                for (int k = 0; k < lengthK; k++) {
                    sum += a[i][k] * b[k][j];
                }
                matrixM[i][j] = sum;
                sum = 0;
            }
        }
        return matrixM;
    }

    public void FillMatrix(int[][] matrix) {
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
    }

    public void PrintMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------");
    }


}
