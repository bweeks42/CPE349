import java.util.Random;
import java.util.Arrays;
import java.lang.Math;

class MatrixTest {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
	
	public static void main(String args[]) {
		
		Random rand = new Random();

		for (int i = 1; i < Math.pow(2, 10); i *= 2) {
			int size = i;
			int [][] testA = generateSquareMatrix(size);	
			int [][] testB = generateSquareMatrix(size);
			int [][] result = MatrixProduct.matrixProduct_DAC(testA, testB);
			int [][] solution = MatrixWork.matrixProduct(testA, testB);

			boolean isCorrect = true;
			for (int j = 0; j < size; j++) {
				isCorrect &= Arrays.equals(result[j], solution[j]);
			}

			if (isCorrect) {
				System.out.println("Size: " + i + ANSI_GREEN + " Passed. " + ANSI_RESET);
			}
			else {
				System.out.println("Size: " + i + ANSI_RED + " Failed." + ANSI_RESET);
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
