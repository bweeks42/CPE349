/*
Date: 
Project 3: FactoryProblem.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

import java.lang.Math;
import java.util.Scanner;
import java.io.File;
import java.iod.FileNotFoundException;

public class FactoryProblem {
	public static void main(String args[]) {
		System.out.println("Enter file name: ");
    	Scanner commandScanner = new Scanner(System.in);
    	String fileName = commandScanner.nextLine();

    	File file = new File(fileName);

    	int n;
    	int[] line1;
    	int[] line2;
    	int[] transfer1;
    	int[] transfer2;

    	try {
        	Scanner fileScanner = new Scanner(file);

        	n = fileScanner.nextInt();
        	
        	line1 = new int[n + 2];
        	line2 = new int[n + 2];
        	transfer1 = new int[n - 1];
        	transfer2 = new int[n - 1];

        	line1[0] = fileScanner.nextInt();
        	line2[0] = fileScanner.nextInt();
        	line1[n + 1] = fileScanner.nextInt();
        	line2[n + 1] = fileScanner.nextInt();

        	for (int i = 1; i < n + 1; i++) {
        		line1[i] = fileScanner.nextInt();
        	}
        	for (i = 1; i < n + 1; i++) {
        		line2[i] = fileScanner.nextInt();
        	}
        	for (i = 0; i < n - 1; i++) {
        		transfer1[i] = fileScanner.nextInt();
        	}
        	for (i = 0; i < n - 1; i++) {
        		transfer2[i] = fileScanner.nextInt();
        	}
     	}
     	catch(FileNotFoundException e) {
        	System.out.println("Could not find file named: " + file);
      	}

      	int[] solutionArray = FactorySolver(n, line1, line2, transfer1, transfer2);
	}

	public static int[] FactorySolver(int n, int[] line1, int[] line2, int[] transfer1, int[] transfer2) {
		int[] solutionArray = new int[n + 1];
		solutionArray[0] = 0; //the first index holds fastest time

		for (int i = n; i > 0; i--) {
			if (i == n) { //exit station
				solutionArray[0] += Math.min(line1[n] + line1[n + 1], line2[n] + line2[n + 1]);
				solutionArray[i] = ((line1[n] + line1[n + 1]) < (line2[n] + line2[n + 1])) ? 1 : 2;
			}
			else if (i == 1) { //entry station
				solutionArray[0] += Math.min(line1[0] + line1[1], line2[0] + line2[1]);
				solutionArray[i] = ((line1[0] + line1[1]) < (line2[0] + line2[1])) ? 1 : 2;
			}
			else {

			}
		}



		return solutionArray;
	}







}