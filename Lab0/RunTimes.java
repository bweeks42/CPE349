/** Audrey Chan, Blain Weeks
 *  Calls dummy methods and prints timings.*/

class RunTimes {

	public static void main(String [] args) {
		
		long startTime;
		long endTime;
		long runningTime;
		System.out.println("Logarithmic algorithm's running times:");
		
		for (long i = 1000; i < 100000000; i = i * 2) {
			startTime = System.nanoTime();
			Algorithms.logarithmicAlgorithm(i);
			endTime = System.nanoTime();
			runningTime = (endTime - startTime)/1000000;
			System.out.println("T(" + i + ") = " + runningTime);
		}

		System.out.println("\nLinear algorithm's running times:"); 
		for (long i = 1000; i < 100000000; i = i * 2) {
			startTime = System.nanoTime();
			Algorithms.linearAlgorithm(i);
			endTime = System.nanoTime();
			runningTime = (endTime - startTime)/1000000;
			System.out.println("T(" + i + ") = " + runningTime);
		}

		System.out.println("\nNlogN algorithm's running times:"); 
		for (long i = 1000; i < 100000000; i = i * 2) {
			startTime = System.nanoTime();
			Algorithms.NlogNAlgorithm(i);
			endTime = System.nanoTime();
			runningTime = (endTime - startTime)/1000000;
			System.out.println("T(" + i + ") = " + runningTime);
		}
	
		System.out.println("\nQuadratic algorithm's running times:"); 
		for (long i = 1000; i <= 512000; i = i * 2) {
			startTime = System.nanoTime();
			Algorithms.quadraticAlgorithm(i);
			endTime = System.nanoTime();
			runningTime = (endTime - startTime)/1000000;
			System.out.println("T(" + i + ") = " + runningTime);
		}
		
		System.out.println("\nCubic algorithm's running times:"); 
		for (long i = 1000; i <= 8000; i = i * 2) {
			startTime = System.nanoTime();
			Algorithms.cubicAlgorithm(i);
			endTime = System.nanoTime();
			runningTime = (endTime - startTime)/1000000;
			System.out.println("T(" + i + ") = " + runningTime);
		}
	}
}
