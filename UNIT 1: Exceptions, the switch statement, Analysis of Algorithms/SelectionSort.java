
public class SelectionSort {
	public static void main(String[] args) {
		double[] arr = new double[] {1.65, 1.45, 7, 99, 57.75};
		printArray(sort(arr));
		

	}
	
	public static double[] sort(double[] arr) {
		for(int i = 0; i<arr.length; i++) {
			double mx = 0; 
			int mx_pos = 0;
			for(int j = 0; j<(arr.length-i); j++) {
				if(arr[j]>mx) {
					mx = arr[j];
					mx_pos = j;
				}
			}
			double temp = arr[arr.length - i-1];
			arr[arr.length - i-1] = arr[mx_pos];
			arr[mx_pos] = temp;
		}
		return arr;
	}
	
	public static void printArray(double[] arr) {
		for(int i = 0; i<arr.length; i++) {
			System.out.println(" "+arr[i]);
		}
	}

}
