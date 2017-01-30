/*
Date: 1/30/17
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

	public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) {
		checkMatrix(A, B);
		return matrixProduct_Strassen(A, 0, 0, B, 0, 0, A.length);
	}

	private static int[][] matrixProduct_Strassen(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB, int n) {
		int[][] C = new int[n][n];
		int quadLen = n/2;

		if (n == 1) {
			C[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
		}
		else {
			int[][] S1 = subtractMatrix(B, startrowB, startcolB + quadLen, B, startrowB + quadLen, startcolB + quadLen, quadLen);
			int[][] S2 = addMatrix(A, startrowA, startcolA, A, startrowA, startcolA + quadLen, quadLen);
			int[][] S3 = addMatrix(A, startrowA + quadLen, startcolA, A, startrowA + quadLen, startcolA + quadLen, quadLen);
			int[][] S4 = subtractMatrix(B, startrowB + quadLen, startcolB, B, startrowB, startcolB, quadLen);
			int[][] S5 = addMatrix(A, startrowA, startcolA, A, startrowA + quadLen, startcolA + quadLen, quadLen);
			int[][] S6 = addMatrix(B, startrowB, startcolB, B, startrowB + quadLen, startcolB + quadLen, quadLen);
			int[][] S7 = subtractMatrix(A, startrowA, startcolA + quadLen, A, startrowA + quadLen, startcolA + quadLen, quadLen);
			int[][] S8 = addMatrix(B, startrowB + quadLen, startcolB, B, startrowB + quadLen, startcolB + quadLen, quadLen);
			int[][] S9 = subtractMatrix(A, startrowA, startcolA, A, startrowA + quadLen, startcolA, quadLen);
			int[][] S10 = addMatrix(B, startrowB, startcolB, B, startrowB, startcolB + quadLen, quadLen);

			int[][] P1 = matrixProduct_Strassen(A, startrowA, startcolA, S1, 0, 0, quadLen);
			int[][] P2 = matrixProduct_Strassen(S2, 0, 0, B, startrowB + quadLen, startcolB + quadLen, quadLen);
			int[][] P3 = matrixProduct_Strassen(S3, 0, 0, B, startrowB, startcolB, quadLen);
			int[][] P4 = matrixProduct_Strassen(A, startrowA + quadLen, startcolA + quadLen, S4, 0, 0, quadLen);
			int[][] P5 = matrixProduct_Strassen(S5, 0, 0, S6, 0, 0, quadLen);
			int[][] P6 = matrixProduct_Strassen(S7, 0, 0, S8, 0, 0, quadLen);
			int[][] P7 = matrixProduct_Strassen(S9, 0, 0, S10, 0, 0, quadLen);

			int[][] C11 = addMatrix(subtractMatrix(addMatrix(P5, P4), P2), P6);
			int[][] C12 = addMatrix(P1, P2);
			int[][] C21 = addMatrix(P3, P4);
			int[][] C22 = subtractMatrix(subtractMatrix(addMatrix(P5, P1), P3), P7);

			for (int i = 0; i < quadLen; i++) {
				System.arraycopy(C11[i], 0, C[i], 0, quadLen);
				System.arraycopy(C12[i], 0, C[i], quadLen, quadLen);
				System.arraycopy(C21[i], 0, C[quadLen + i], 0, quadLen);
				System.arraycopy(C22[i], 0, C[quadLen + i], quadLen, quadLen);
			}
		}

		return C;
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

	private static int[][] subtractMatrix(int[][] X, int[][] Y) {
		int[][] resultMatrix = new int[X.length][X.length];

		for (int i = 0; i < X.length; i++) {
			for (int j = 0; j < X.length; j++) {
				resultMatrix[i][j] = X[i][j] - Y[i][j];
			}
		}
		return resultMatrix;
	}	

	private static int[][] addMatrix(int[][] X, int startrowX, int startcolX, int[][] Y, int startrowY, int startcolY, int n) {
		int[][] resultMatrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				resultMatrix[i][j] = X[startrowX + i][startcolX + j] + Y[startrowY + i][startcolY + j];
			}
		}
		return resultMatrix;
	}

	private static int[][] subtractMatrix(int[][] X, int startrowX, int startcolX, int[][] Y, int startrowY, int startcolY, int n) {
		int[][] resultMatrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				resultMatrix[i][j] = X[startrowX + i][startcolX + j] - Y[startrowY + i][startcolY + j];
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
