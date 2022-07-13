public class Transpose {

	public static void main(String[] args) {
		int[][] arr = new int[][] {{1, 2, 3, 4}, {-1, -2, -3, -4}, {6, 7, 8, 9}, {100, 1000, 10000, 100000}, {2, 4, 6, 8}};
		printArray(arr);
		int[][] transposed = getTranspose(arr);
		printArray(transposed);
		
	}
	
	public static int[][] getTranspose(int[][] arr){
		int[][] ans = new int[arr[0].length][arr.length];
		for(int i = 0; i<arr.length; i++) {
			for(int j = 0; j<arr[0].length; j++) {
				ans[j][i] = arr[i][j];
			}
		}
		return ans;
	}
	
	public static void printArray(int[][] arr) {
		for(int i = 0; i<arr.length; i++) {
			for(int j = 0; j<arr[0].length; j++) {
				System.out.print(" "+ arr[i][j]);
			}
			System.out.println("");
		}
	}

}
