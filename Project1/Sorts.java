/*
Date: 1/20/17
Project 1: Sorts.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

import java.util.Arrays;

public class Sorts {

	public static void selectionSort (int[] arr, int N) {
		int startIndex = 0;
		int lowestIndex = 0;
		int lowestValue = arr[startIndex];
		while (startIndex < N - 1) {
			for (int i = startIndex + 1; i < N; i++) {
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
	}
	public static void mergeSort (int[] arr, int N) {
		mergeSort(arr, 0, N - 1);
	}

	private static void mergeSort(int[] arr, int first, int last) {
		if (first < last) {
			int middle = (first + last)/2;
			mergeSort(arr, first, middle);
			mergeSort(arr, middle + 1, last);
			mergeSortedHalves(arr, first, middle, last);
		}
	}

	private static void mergeSortedHalves(int[] arr, int first, int middle, int last) {
		int []tmp =  new int[last - first + 1];
		int index1 = first;
		int index2 = middle + 1;
		int indexTmp = 0;

		while(index1 < middle + 1 && index2 <= last) {

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
	}

	public static void quickSort(int[] arr, int N) {
		quickSort(arr, 0, N - 1);
	}

	private static void quickSort(int[] arr, int first, int last) {
		if (first < last) {
			setPivotToEnd(arr, first, last);
			int pivotIndex = splitList(arr, first, last);
			quickSort(arr, first, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, last);
		}
	}

	private static void setPivotToEnd(int[] arr, int left, int right) {
		int middleIndex = (left + right) / 2;
		int tmp;

		if (arr[left] > arr[middleIndex]) {
			tmp = arr[left];
			arr[left] = arr[middleIndex];
			arr[middleIndex] = tmp;
		}

		if (arr[left] > arr[right]) {
			tmp = arr[left];
			arr[left] = arr[right];
			arr[right] = tmp;
		}

		if (arr[middleIndex] < arr[right]) {
			tmp = arr[middleIndex];
			arr[middleIndex] = arr[right];
			arr[right] = tmp;
		}
	}

	private static int splitList(int[] arr, int left, int right) {
		int indexL = left;
		int indexR = right - 1;
		int pivot = arr[right];
		while (indexL <= indexR) {

			while (arr[indexL] < pivot) {
				++indexL;
			}
			while (indexR >= indexL && arr[indexR] >= pivot) {
				--indexR;
			}

			if (indexR >= indexL) {
				int tmp = arr[indexR];
				arr[indexR--] = arr[indexL];
				arr[indexL++] = tmp;
			}
		}
		arr[right] = arr[indexL];
		arr[indexL] = pivot;

		return indexL;
	}
}
