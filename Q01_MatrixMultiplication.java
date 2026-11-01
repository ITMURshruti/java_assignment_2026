
/*
Experiment 1: Write a program for matrix multiplication. Use InputStreamReader and BufferedReader classes for Input/Output. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q01_MatrixMultiplication {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter rows and columns for Matrix A:");
        int r1 = Integer.parseInt(reader.readLine());
        int c1 = Integer.parseInt(reader.readLine());

        System.out.println("Enter rows and columns for Matrix B:");
        int r2 = Integer.parseInt(reader.readLine());
        int c2 = Integer.parseInt(reader.readLine());

        if (c1 != r2) {
            System.out.println("Matrix multiplication not possible with these dimensions.");
            return;
        }

        int[][] a = new int[r1][c1];
        int[][] b = new int[r2][c2];
        int[][] product = new int[r1][c2];

        System.out.println("Enter elements of Matrix A:");
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                a[i][j] = Integer.parseInt(reader.readLine());
            }
        }

        System.out.println("Enter elements of Matrix B:");
        for (int i = 0; i < r2; i++) {
            for (int j = 0; j < c2; j++) {
                b[i][j] = Integer.parseInt(reader.readLine());
            }
        }

        // Multiplying matrices
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    product[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        System.out.println("Resultant Matrix:");
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                System.out.print(product[i][j] + " ");
            }
            System.out.println();
        }
    }
}