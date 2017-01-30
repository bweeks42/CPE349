import java.util.Random;
import java.util.Arrays;

public class MatrixTest {
	
	public static final String ANSI_GREEN = "\u001B[32m";
   	public static final String ANSI_RED = "\u001B[31m";
   	public static final String ANSI_RESET = "\u001B[0m";

	public static void main(String args[]) {
		int maxSize = 800;
		DAC_Tests(maxSize);
		Strassen_Tests(maxSize);
		System.out.println("End of output");
	}

	private static void DAC_Tests(int maxSize) {
		System.out.println("DAC PRODUCT TESTS:");
		for (int i = 1; i < maxSize; i*= 2) {
			int size = i;
			int [][] testA = generateSquareMatrix(size);	
			int [][] testB = generateSquareMatrix(size);
			int [][] result = MatrixProduct.matrixProduct_DAC(testA, testB);
			int [][] solution = MatrixWork.matrixProduct(testA, testB);

			boolean isCorrect = true;
			for (int j = 0; j < size; j++) {
				isCorrect &= Arrays.equals(result[j], solution[j]);
			}

			/*System.out.println("The result is:");
			for (int k = 0; k < size; k++) {
				for (int l = 0; l < size; l++) {
					System.out.print(result[k][l] + " ");
				}
				System.out.println("");
			}

			System.out.println("CORRECT solution is:");
			for (int m = 0; m < size; m++) {
				for (int n = 0; n < size; n++) {
					System.out.print(solution[m][n] + " ");
				}
				System.out.println("");
			}*/

			if (isCorrect) {
				System.out.println(ANSI_GREEN + "Passed" + ANSI_RESET);
			}
			else {
				System.out.println(ANSI_RED + "Failed" + ANSI_RESET);
			}
		}	
	}

	private static void Strassen_Tests(int maxSize) {
		System.out.println("STRASSEN PRODUCT TESTS:");
		for (int i = 1; i < maxSize; i*= 2) {
			int size = i;
			int [][] testA = generateSquareMatrix(size);	
			int [][] testB = generateSquareMatrix(size);
			int [][] result = MatrixProduct.matrixProduct_Strassen(testA, testB);
			int [][] solution = MatrixWork.matrixProduct(testA, testB);

			boolean isCorrect = true;
			for (int j = 0; j < size; j++) {
				isCorrect &= Arrays.equals(result[j], solution[j]);
			}

			if (isCorrect) {
				System.out.println(ANSI_GREEN + "Passed" + ANSI_RESET);
			}
			else {
				System.out.println(ANSI_RED + "Failed" + ANSI_RESET);
			}
		}	
	}

	private static int[][] generateSquareMatrix(int size) {

		Random rand = new Random();

		int[][] toGenerate = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				toGenerate[i][j] = rand.nextInt();
			}
		}
		return toGenerate;
	}
}
