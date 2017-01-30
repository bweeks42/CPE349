import java.util.Random;
import java.util.Arrays;
class MatrixTest {

	public static void main(String args[]) {
		
		Random rand = new Random();

		for (int i = 1; i < 10; i++) {
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
				System.out.println("Passed.");
			}
			else {
				System.out.println("Failed.");
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
