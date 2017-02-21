/*
Date: 
Project 4: ChangeMaker.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

import java.util.Arrays;

public class ChangeMaker {
	public static void main(String args[]) {

	}

	public static int[] change_DP(int n, int[] d) {
		int[] C = new int[n]; //array containing values of subproblems
		int[] A = new int[n]; //array containing minimum index choice
		int[] solution = new int[d.length];
		Arrays.fill(solution, 0);
		int minIdx;

		C[0] = 0;
		for (int i = 1; i < n; i++) {
			int[] minD = new int[d.length];
			for (int j = 0; j < d.length; j++) {
				if (i >= d[j]) {
					minD[j] = C[i - d[j]];
				}
			}

			minIdx = findMinIndex(minD);

			C[i] = 1 + minD[minIdx];
			A[i] = minIdx;
		}

		//fill solution array with coin counts
		int j = A.length - 1;
		while (j >= 0) {
			solution[A[j]] += 1;
			j -= d[A[j]];
		}

		/* IN PROGRESS */

		return C;
	}

	private static int findMinIndex(int[] x) {
		int minVal = x[0];
		int minIdx = 0;

		for (int i = 1; i < x.length; i++) {
			if (minVal > x[i]) {
				minVal = x[i];
				minIdx = i;
			}
		}

		return minIdx;
	}
}