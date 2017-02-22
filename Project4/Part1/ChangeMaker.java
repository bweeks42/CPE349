/*
Date: 
Project 4: ChangeMaker.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;

public class ChangeMaker {
	public static void main(String args[]) {

		int []denominations, solution;
		int k, n;

		System.out.println("Enter value of k and list of k denominations in decreasing order:");
		Scanner s = new Scanner(System.in);
		k = s.nextInt();
		denominations = new int[k];
		for (int i = 0; i < k; i++) {
			denominations[i] = s.nextInt();
		}
		
		System.out.println("Enter value of n. 0 to quit.");
		n = s.nextInt();

		while(n > 0) {

			solution = change_DP(n, denominations);

			printSolution(n, k, solution, denominations);

			System.out.println("Enter value of n. 0 to quit.");
			n = s.nextInt();
		}

	}

	public static int[] change_DP(int n, int[] d) {
		int[] C = new int[n + 1]; //array containing values of subproblems
		int[] A = new int[n + 1]; //array containing minimum index choice
		int[] solution = new int[d.length];
		Arrays.fill(solution, 0);
		int minIdx, minVal;

		C[0] = 0;
		for (int i = 1; i < n + 1; i++) {
			minVal = i;
			minIdx = i;
			for (int j = 0; j < d.length; j++) {
				if (i >= d[j]) {
					if (minVal >= C[i - d[j]]) {
						minVal = C[i - d[j]];
						minIdx = j;
					}
				}
			}

			C[i] = 1 + minVal;
			A[i] = minIdx;
		}

		System.out.println(Arrays.toString(A));

		//fill solution array with coin counts
		int j = A.length - 1;
		while (j > 0) {
			solution[A[j]] += 1;
			j -= d[A[j]];
		}

		return solution;
	}

	private static void printSolution(int n, int k, int []solution, int []denominations) {

		int coinCount = 0;

		System.out.println("DP Algorithm results");
		System.out.println("Amount: " + n);

		System.out.print("Optimal distribution: ");
		for (int i = 0; i < k - 1; i++) {
			if (solution[i] > 0) {
				System.out.print(solution[i] + "*" + denominations[i] + "c" + " + ");
				coinCount += solution[i];
			}
		}
		if (solution[k-1] > 0) {
			System.out.println(solution[k-1] + "*" + denominations[k-1] + "c");
			coinCount += solution[k-1];
		}

		System.out.println("Optimal coin count: " + coinCount);
	}
}