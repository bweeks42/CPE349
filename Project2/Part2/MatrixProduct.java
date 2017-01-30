/*
Date: date here
Project 2: MatrixProduct.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

import java.lang.Math;

public class MatrixProduct {
	
	public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {
		checkMatrix(A, B);
		return matrixProduct_DAC(A, 0, 0, B, 0, 0, A.length);
	}

	private static int[][] matrixProduct_DAC(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB, int n) {
		int[][] C = new int[n][n];
		int quadLen = n/2;

		if (n == 1) {
			C[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
		}
		else {
			int[][] C11 = addMatrix(matrixProduct_DAC(A, startrowA, startcolA, B, startrowB, startcolB, quadLen), 
									matrixProduct_DAC(A, startrowA, startcolA + quadLen, B, startrowB + quadLen, startcolB, quadLen));
			int[][] C12 = addMatrix(matrixProduct_DAC(A, startrowA, startcolA, B, startrowB, startcolB + quadLen, quadLen), 
									matrixProduct_DAC(A, startrowA, startcolA + quadLen, B, startrowB + quadLen, startcolB + quadLen, quadLen));
			int[][] C21 = addMatrix(matrixProduct_DAC(A, startrowA + quadLen, startcolA, B, startrowB, startcolB, quadLen), 
									matrixProduct_DAC(A, startrowA + quadLen, startcolA + quadLen, B, startrowB + quadLen, startcolB, quadLen));
			int[][] C22 = addMatrix(matrixProduct_DAC(A, startrowA + quadLen, startcolA, B, startrowB, startcolB + quadLen, quadLen), 
									matrixProduct_DAC(A, startrowA + quadLen, startcolA + quadLen, B, startrowB + quadLen, startcolB + quadLen, quadLen));

			for (int i = 0; i < quadLen; i++) {
				System.arraycopy(C11[i], 0, C[i], 0, quadLen);
				System.arraycopy(C12[i], 0, C[i], quadLen, quadLen);
				System.arraycopy(C21[i], 0, C[quadLen + i], 0, quadLen);
				System.arraycopy(C22[i], 0, C[quadLen + i], quadLen, quadLen);
			}
		}

		return C;
	}

	public static void matrixProduct_Strassen(int[][] A, int[][] B) {
		checkMatrix(A, B);
//		return matrixProduct_Strassen(A, 0, 0, B, 0, 0, A.length);
	}

	private static void matrixProduct_Strassen(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB, int n) {

	}

	private static int[][] addMatrix(int[][] X, int[][] Y) {
		int[][] resultMatrix = new int[X.length][X.length];

		for (int i = 0; i < X.length; i++) {
			for (int j = 0; j < X.length; j++) {
				resultMatrix[i][j] = X[i][j] + Y[i][j];
			}
		}

		return resultMatrix;
	}

	private static void checkMatrix(int[][] A, int[][] B) {
		boolean isValid = false;
		double logVal;

		if (A.length == A[0].length && A[0].length == B.length && B.length == B[0].length) {
			logVal = Math.log(A.length)/Math.log(2);
			if (logVal == Math.floor(logVal)) {
				isValid = true;
			}
		}

		if (!isValid) {
			throw new IllegalArgumentException();
		}
	}
}
