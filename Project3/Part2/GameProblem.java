/*
Date: 2/10/2017
Project 3: GameProblem.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

import java.lang.Math;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GameProblem {


   public static void main(String args[]) {
      System.out.println("Enter file name: ");
      Scanner commandScanner = new Scanner(System.in);
      String fileName = commandScanner.nextLine();

      File file = new File(fileName);

      int n, m;
      int[][] board;

      try {
         Scanner fileScanner = new Scanner(file);
         n = fileScanner.nextInt();
         m = fileScanner.nextInt();
         board = new int[n][m];

         for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
               board[i][j] = fileScanner.nextInt();
            }
         }

         game(n, m, board);
      }
      catch(FileNotFoundException e) {
         System.out.println("Could not find file named: " + file);
         }
   }


   public static void game(int n, int m, int[][] A) {
      int[][] S = new int[n][m];
      char[][] R = new char[n][m];

      for (int i = n - 1; i >= 0; i--) {
         for (int j = m - 1; j >= 0; j--) {
            if (i == n - 1 && j == m - 1) {
               S[i][j] = A[n - 1][m - 1];
               R[i][j] = 'e';
            }
            else if (j == m - 1) {
               S[i][j] = Math.max(S[i+1][m - 1], 0) + A[i][m - 1];
               R[i][j] = S[i+1][m - 1] > 0 ? 'd' : 'e';
            }
            else if (i == n - 1) {
               S[i][j] = Math.max(S[n - 1][j+1], 0) + A[n - 1][j];
               R[i][j] = S[n - 1][j+1] > 0 ? 'r' : 'e';
            }
            else {
               S[i][j] = Math.max(S[i+1][j], S[i][j+1]) + A[i][j];
               R[i][j] = S[i+1][j] > S[i][j+1] ? 'd' : 'r';
            }
         }
      }

      printOutput(S, R);

   }

   private static void printOutput(int[][] S, char[][] R) {

      int max = S[0][0];
      int maxN = 0;
      int maxM = 0;

      for (int i = 0; i < S.length; i++) {
         for (int j = 0; j < S[0].length; j++) {
            if (S[i][j] > max) {
               max = S[i][j];
               maxN = i;
               maxM = j;
            }
         }
      }


      System.out.println("Best score: " + max);
      System.out.print("Best route: ");
      while (R[maxN][maxM] != 'e') {
         System.out.print("[" + (maxN + 1) + "," + (maxM + 1) + "] to ");

         if (R[maxN][maxM] == 'd') {
            maxN++;
         }
         else {
            maxM++;
         }
      }
      System.out.print("[" + (maxN + 1) + "," + (maxM + 1) + "] to exit\n");

   }

}