/*
Date: 2/27/17
Project 4: Tester.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

public class Tester {
	public static void main(String args[]) {
		int[] US_d = {100, 50, 25, 10, 5, 1};
		int[] Soviet_d = {100, 50, 20, 15, 10, 5, 3, 2, 1};
		int[] Pow2_d = {64, 32, 16, 8, 4, 2, 1};
		int[] USnonick_d = {100, 50, 25, 10, 1};
		int[] Some_d = {66, 35, 27, 18, 10, 1};

		System.out.println("Testing change_DP and change_greedy algorithms");
		printTest(US_d, 1);
		printTest(Soviet_d, 2);
		printTest(Pow2_d, 3);
		printTest(USnonick_d, 4);
		printTest(Some_d, 5);		
	}

	private static void printTest(int[] d, int setNum){
		int matches = 0;
		int[] greedySol;
		int[] DPSol;

		System.out.print("Testing set" + setNum + ": ");
		for (int n = 1; n <= 100000; n++) {
			greedySol = ChangeMaker.change_greedy(n, d);
			DPSol = ChangeMaker.change_DP(n, d);
			if (sumOfArray(greedySol) == sumOfArray(DPSol)) {
				matches += 1;
			}
		}

		System.out.println(matches + " matches in 100000 tests");
	}

	private static int sumOfArray(int[] x) {
		int sum = 0;

		for (int i = 0; i < x.length; i++) {
			sum += x[i];
		}

		return sum;
	}
}