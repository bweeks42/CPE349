/*
Date: 1/20/17
Project 1: Sorts1.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

import java.util.Arrays;

public class Sorts1 {
	public static long selectionSort (int[] arr, int N) {
		long steps = 0;
		int startIndex = 0;
		int lowestIndex = 0;
		int lowestValue = arr[startIndex];
		while (startIndex < N - 1) {
			for (int i = startIndex + 1; i < N; i++) {
				steps++;
				if (arr[i] < lowestValue) {
					lowestValue = arr[i];
					lowestIndex = i;
				}
			}
			arr[lowestIndex] = arr[startIndex];
			arr[startIndex] = lowestValue;
			startIndex++;
			lowestIndex = startIndex;
			lowestValue = arr[startIndex];
		}
		return steps;
	}
	public static long mergeSort (int[] arr, int N) {
		return mergeSort(arr, 0, N - 1);
	}

	private static long mergeSort(int[] arr, int first, int last) {
		long steps = 0;
		if (first < last) {
			int middle = (first + last)/2;
			steps += mergeSort(arr, first, middle);
			steps += mergeSort(arr, middle + 1, last);
			steps += mergeSortedHalves(arr, first, middle, last);
		}
		return steps;
	}

	private static long mergeSortedHalves(int[] arr, int first, int middle, int last) {
		int []tmp =  new int[last - first + 1];
		int index1 = first;
		int index2 = middle + 1;
		int indexTmp = 0;
		long steps = 0;

		while(index1 < middle + 1 && index2 <= last) {
			steps++;
			if (arr[index1] < arr[index2]) {
				tmp[indexTmp++] = arr[index1++];
			}
			else {
				tmp[indexTmp++] = arr[index2++];
			}
		}

		while (index1 < middle + 1) {
			tmp[indexTmp++] = arr[index1++];
		}
		while (index2 <= last) {
			tmp[indexTmp++] = arr[index2++];
		}

		indexTmp = 0;
		for (int i = first; i <= last; i++) {
			arr[i] = tmp[indexTmp++];
		}

		return steps;
	}

	public static long quickSort(int[] arr, int N) {
		return quickSort(arr, 0, N - 1);
	}

	private static long quickSort(int[] arr, int first, int last) {
		long steps = 0;
		long[] r = new long[2];

		if (first < last) {
			steps += setPivotToEnd(arr, first, last);
			r = splitList(arr, first, last);
			int pivotIndex = (int)r[0];
			steps += r[1];
			steps += quickSort(arr, first, pivotIndex - 1);
			steps += quickSort(arr, pivotIndex + 1, last);
		}
		return steps;
	}

	private static long setPivotToEnd(int[] arr, int left, int right) {
		int middleIndex = (left + right) / 2;
		long steps = 0;
		int tmp;


		steps++;
		if (arr[left] > arr[middleIndex]) {
			tmp = arr[left];
			arr[left] = arr[middleIndex];
			arr[middleIndex] = tmp;
		}

		steps++;
		if (arr[left] > arr[right]) {
			tmp = arr[left];
			arr[left] = arr[right];
			arr[right] = tmp;
		}

		steps++;
		if (arr[middleIndex] < arr[right]) {
			tmp = arr[middleIndex];
			arr[middleIndex] = arr[right];
			arr[right] = tmp;
		}

		return steps;
	}

	private static long[] splitList(int[] arr, int left, int right) {
		int indexL = left;
		int indexR = right - 1;
		int pivot = arr[right];
		long steps = 0;
		long[] r = new long[2];
		while (indexL <= indexR) {

			steps++;
			while (arr[indexL] < pivot) {
				++indexL;
				steps++;
			}

			while (indexR >= indexL) {
				steps++;
				if (arr[indexR] >= pivot) {
					--indexR;
				}
				else {
					break;
				}
			}

			if (indexR >= indexL) {
				int tmp = arr[indexR];
				arr[indexR--] = arr[indexL];
				arr[indexL++] = tmp;
			}
		}
		arr[right] = arr[indexL];
		arr[indexL] = pivot;

		r[0] = indexL;
		r[1] = steps;
		return r;
	}
}
