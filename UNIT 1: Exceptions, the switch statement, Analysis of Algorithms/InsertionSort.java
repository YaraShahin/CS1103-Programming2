
public class InsertionSort {
	public static void main(String[] args) {
		double[] arr = new double[] {1.65, 1.45, 7, 99, 57.75};
		printArray(sort(arr));
	}
	
	public static double[] sort(double[] arr) {
		for (int i = 1; i<arr.length; i++) {
			double temp = arr[i];
			int j = i-1;
			while (j>=0 && arr[j]>temp) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1]= temp;
		}
		return arr;
	}
	
	public static void printArray(double[] arr) {
		for(int i = 0; i<arr.length; i++) {
			System.out.println(" "+arr[i]);
		}
	}

}