/*
Date: 1/20/17
Project 1: SortCounts.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

import java.util.Random;

public class SortCounts {

   public static void main(String[] args) {

      int ARRAY_MAX = 12800;

      int[] selectArray = new int[ARRAY_MAX];
      int[] mergeArray = new int[ARRAY_MAX];
      int[] quickArray = new int[ARRAY_MAX];
      int randomInt;
      long selectSteps = 0;
      int mergeSteps = 0;
      int quickSteps = 0;
      Random rand = new Random();


      System.out.println("Average number of element-comparisons in three sorting algorithms:\n");
      for (int N = 100; N <= ARRAY_MAX; N *= 2) {
         selectSteps = mergeSteps = quickSteps = 0;
         for (int j = 0; j < 100; j++) {
            for (int i = 0; i < N; i++) {
               randomInt = rand.nextInt();
               selectArray[i] = randomInt;
               mergeArray[i] = randomInt;
               quickArray[i] = randomInt;
            }

            selectSteps += Sorts1.selectionSort(selectArray, N);

            mergeSteps += Sorts1.mergeSort(mergeArray, N);

            quickSteps += Sorts1.quickSort(quickArray, N);
         }
         System.out.println("N=" + N + ": C_ss=" + (selectSteps/100) + ", C_ms=" + (mergeSteps/100) + ", C_qs=" + (quickSteps/100));
      }
      System.out.println("\nEnd of output");
   }

}
