/**/
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Exception;

public class MatrixWork {


   public static int[][] matrixProduct(int[][] A, int[][] B) {
      if (A[0].length != B.length) {
         throw new IllegalArgumentException();
      }
      int[][] C = new int[A.length][A[0].length];
      int total = 0;
      int cCol = 0;

      for (int aRow = 0; aRow < A.length; aRow++) {
         for (int bCol = 0; bCol < B.length; bCol++) {
            for (int bRow = 0; bRow < B[0].length; bRow++) {
               total += (A[aRow][bRow] * B[bRow][bCol]);
            }
            C[aRow][cCol++] = total;
            total = 0;
         }
         cCol = 0;
      }
      return C;
   }

   public static void main(String[] args) {
      System.out.println("Enter file name: ");
      Scanner commandScanner = new Scanner(System.in);
      String fileName = commandScanner.nextLine();

      File file = new File(fileName);

      try {
         Scanner fileScanner = new Scanner(file);

         int row = fileScanner.nextInt();
         int col = fileScanner.nextInt();

         int[][] A = new int[row][col];

         for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
               A[i][j] = fileScanner.nextInt();
            }
         }

         row = fileScanner.nextInt();
         col = fileScanner.nextInt();

         int[][] B = new int[row][col];

         for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
               B[i][j] = fileScanner.nextInt();
            }
         }
         try {
            int[][] C = matrixProduct(A, B);

            System.out.println("Product matrix:");
            for (int i = 0; i < C.length; i++) {
               for (int j = 0; j < C[0].length; j++) {
                  System.out.print(C[i][j] + " ");
               }
               System.out.println();
            }
         }
         catch (IllegalArgumentException e) {
            System.out.println("Number of rows in Matrix A does not equal number of columns in Matrix B.");
         }

      }
      catch (FileNotFoundException e) {
         System.out.println("Could not find file named: " + file);
      }
   }
}