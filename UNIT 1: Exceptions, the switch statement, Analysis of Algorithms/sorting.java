package sorting;

import java.util.Arrays;
import java.lang.Math;   

/**
 * Class sorting has methods to sort an integer array using different
 * algorithms and comparing the time to analyze their efficiency.
 * For n =  1,000, selection sort time = 5 ms | java sort time = 1 ms
 * For n = 10,000, selection sort time = 54 ms | java sort time = 5 ms
 * For n = 100,000, selection sort time = 4943 ms | java sort time = 46 ms
 * @author YaraShahin
 */
public class sorting {
	/**
	 * makeArrays methods generates array A with random integers
	 * @param n the length of the arrays
	 * @returns array A with random ints of length n
	 */
	public static int[] makeArrays(int n) {
		int[] A = new int[n];
		for (int i = 0; i<n; i++) 
			A[i]=   (int)(Integer.MAX_VALUE * Math.random());
		return A;
	}
	
	/**
	 * sorts arr into increasing order using selection sort
	 * @param arr the int array to be sorted
	 * @return the sorted array
	 */
	public static int[] selectionSort(int[] arr) {
		for (int lastPlace = arr.length-1; lastPlace>0; lastPlace--) {
			//Find the largest item among arr[0] to arr[lastPlace], and move it
			//to position lastPlace by swapping it with the current number in lastPlace.
			int maxloc = 0;            //Location of largest element seen so far
			for (int j = 0; j<=lastPlace; j++) {
				if(arr[j]>arr[maxloc]) maxloc=j;
			}
			int temp = arr[maxloc];
			arr[maxloc] = arr[lastPlace];
			arr[lastPlace] = temp;
		}
		return arr;
	}

	public static void main(String[] args) {
		int n = 100000;
		int[] A = makeArrays(n);
		int[] B = Arrays.copyOf(A, n);
		//System.out.println("The unsorted array: "+ Arrays.toString(A)+"\n");
		
		//sorting A using selection sort
		long startTime = System.currentTimeMillis();
		selectionSort(A);
		long runTime = System.currentTimeMillis() - startTime;
		System.out.println("Array A:");
		//System.out.println("The sorted array: "+Arrays.toString(A));
		System.out.println("Time using selection sort: "+ runTime);
		
		//sorting B using standard java sort method
		startTime = System.currentTimeMillis();
		Arrays.sort(B);
		runTime = System.currentTimeMillis() - startTime;
		System.out.println("Array B:");
		//System.out.println("The sorted array: "+Arrays.toString(B));
		System.out.println("Time using java sort method: "+ runTime);
	}

}

