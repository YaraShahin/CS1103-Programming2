
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = new int[] {2, 6, 9, 10, 40, 42, 84, 110, 111, 114, 150};
		//int[] arr = new int[] {1,2,3};
		System.out.println(search(arr, 0, arr.length, 150));
	}

	public static int search(int[] arr, int startPos, int endPos, int n) {
		if (startPos>endPos) return -1;
		else {
			//the i is the middle element and it's in the (endPosition-startPos)/2 index plus offset startPosition. do the math and you'll find it cancels out to this equation: (start+end)/2 
			int i = (endPos+startPos)/2;
			if(n==arr[i]) return i;
			else { 
				if (arr[i]>n) return search(arr,startPos, i-1, n);
				else return search(arr,i+1, endPos, n);
			}
		}
	}

}
