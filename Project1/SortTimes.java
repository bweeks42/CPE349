/* 
Date: 1/20/17
Project 1: SortTimes.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

import java.util.Random;

public class SortTimes {

   public static void main(String[] args) {

      int ARRAY_MAX = 160000;

      int[] selectArray = new int[ARRAY_MAX];
      int[] mergeArray = new int[ARRAY_MAX];
      int[] quickArray = new int[ARRAY_MAX];
      int randomInt;
      long selectTime, mergeTime, quickTime;
      Random rand = new Random();


      System.out.println("Running Times of three sorting algorithms:\n");
      for (int N = 5000; N <= ARRAY_MAX; N *= 2) {
         for (int j = 0; j < 5; j++) {
            for (int i = 0; i < N; i++) {
               randomInt = rand.nextInt();
               selectArray[i] = randomInt;
               mergeArray[i] = randomInt;
               quickArray[i] = randomInt;
            }

            selectTime = System.nanoTime();
            Sorts.selectionSort(selectArray, N);
            selectTime = (System.nanoTime() - selectTime) / 1000000;

            mergeTime = System.nanoTime();
            Sorts.mergeSort(mergeArray, N);
            mergeTime = (System.nanoTime() - mergeTime) / 1000000;

            quickTime = System.nanoTime();
            Sorts.quickSort(quickArray, N);
            quickTime = (System.nanoTime() - quickTime) / 1000000;

            System.out.println("N=" + N + ": T_ss=" + selectTime + ", T_ms=" + mergeTime + ", T_qs=" + quickTime);
         }
         System.out.println();
      }
      System.out.println("End of output");
   }

}
