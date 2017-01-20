import java.util.Arrays;
import java.util.Random;

public class SortTest {

   public static final String ANSI_GREEN = "\u001B[32m";
   public static final String ANSI_RED = "\u001B[31m";
   public static final String ANSI_RESET = "\u001B[0m";

   public static void main(String []args) {

      int testNumber = 1;
      int ARRAY_MAX = 12800;

      int[] test = {7, 13, 2, 2, 9, 11, 4};
      int[] solution = {2, 2, 4, 7, 9, 11, 13};
      selectionTests(test, solution, testNumber);
      mergeTests(test, solution, testNumber);
      quickTests(test, solution, testNumber++);

      test = new int[]{0};
      solution = new int[]{0};
      selectionTests(test, solution, testNumber);
      mergeTests(test, solution, testNumber);
      quickTests(test, solution, testNumber++);

      test = new int[]{5, 4, 3, 6, 7, 5};
      solution = new int[]{3, 4, 5, 5, 6, 7};
      selectionTests(test, solution, testNumber);
      mergeTests(test, solution, testNumber);
      quickTests(test, solution, testNumber++);

      test = new int[]{7, 8, 9, 10};
      solution = new int[]{7, 8, 9, 10};
      selectionTests(test, solution, testNumber);
      mergeTests(test, solution, testNumber);
      quickTests(test, solution, testNumber++);

      test = new int[]{10, 9, 8, 7};
      solution = new int[]{7, 8, 9, 10};
      selectionTests(test, solution, testNumber);
      mergeTests(test, solution, testNumber);
      quickTests(test, solution, testNumber++);

      test = new int[]{1, 1, 1, 1, 1};
      solution = new int[]{1, 1, 1, 1, 1};
      selectionTests(test, solution, testNumber);
      mergeTests(test, solution, testNumber);
      quickTests(test, solution, testNumber++);

      test = new int[]{3, 3, 2, 2, 1, 1};
      solution = new int[]{1, 1, 2, 2, 3, 3};
      selectionTests(test, solution, testNumber);
      mergeTests(test, solution, testNumber);
      quickTests(test, solution, testNumber++);

      for (int i = 100; i <= ARRAY_MAX; i += 100) {
         test = new int[i];
         solution = new int[i];
         createRandomTest(test, i);
         System.arraycopy(test, 0, solution, 0, test.length);
         Arrays.sort(solution);
         selectionTests(test, solution, testNumber);
         mergeTests(test, solution, testNumber);
         quickTests(test, solution, testNumber++);
      }
   }

   private static void selectionTests(int[] test, int[] solution, int num) {

      int[] copy = new int[test.length];
      System.arraycopy(test, 0, copy, 0, test.length);

      Sorts.selectionSort(copy, test.length);

      System.out.print("Selection Sort Test " + num + " - ");

      if(Arrays.equals(copy, solution)) {
         System.out.println(ANSI_GREEN + "Passed" + ANSI_RESET);
      }
      else {
         System.out.println(ANSI_RED + "Failed" + ANSI_RESET);
      }
   }

   private static void mergeTests(int[] test, int[] solution, int num) {

      int[] copy = new int[test.length];
      System.arraycopy(test, 0, copy, 0, test.length);

      Sorts.mergeSort(copy, test.length);

      System.out.print("Merge Sort Test " + num + " - ");

      if(Arrays.equals(copy, solution)) {
         System.out.println(ANSI_GREEN + "Passed" + ANSI_RESET);
      }
      else {
         System.out.println(ANSI_RED + "Failed" + ANSI_RESET);
      }
   }

   private static void quickTests(int[] test, int[] solution, int num) {
      int[] copy = new int[test.length];
      System.arraycopy(test, 0, copy, 0, test.length);

      Sorts.quickSort(copy, test.length);

      System.out.print("Quick Sort Test " + num + " - ");

      if(Arrays.equals(copy, solution)) {
         System.out.println(ANSI_GREEN + "Passed" + ANSI_RESET);
      }
      else {
         System.out.println(ANSI_RED + "Failed" + ANSI_RESET);
      }
   }

   private static void createRandomTest(int[] test, int size) {
      Random rand = new Random();
      for (int i = 0; i < size; i++) {
         test[i] = rand.nextInt();
      }
   }


}
