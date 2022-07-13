
public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[] {3, 13, 6, 8, 9, 0, 1, 5, 20, 2, 1};
		quick_sort(arr, 0, arr.length-1);
		for(int i : arr) System.out.print(i+" ");

	}

	public static int pivot(int[] arr, int start, int end) {
		//we have a hole at the start (pivot value)
		//loop per each misplaced number: in each loop, we skip numbers that are bigger and decrease end index
		//we reach a number at end but its value is smaller. put the number in hole
		//do the same for the smaller side
		int pivot = arr[start];
		
		while(start<end) {
			while(start<end && arr[end]>=pivot) end--;
			if (start==end) break;
			arr[start] = arr[end];
			while(start<end && arr[start]<=pivot) start++;
			if(start==end) break;
			arr[end] = arr[start];
		}
		arr[start] = pivot;
		return start;
		
	}
	
	public static void quick_sort(int[] arr, int start, int end) {
		if(start>=end) return;
		else {
			int pivot_pos = pivot(arr, start, end);
			quick_sort(arr, start, pivot_pos-1);
			quick_sort(arr, pivot_pos+1, end);
		}
	}

}
