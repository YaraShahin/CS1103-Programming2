package CreateArray;
import java.util.Random;

public class createArray {
	public static int[] getArray(int n, int mx) {
		int[] ans = new int[n];
		int x;
		Random ran = new Random();
		for(int i = 0; i < n; i++) {
			while(true) {
				x = ran.nextInt(mx) + 1;
				boolean flag = false;
				for(int j = 0; j<i; j++) {
					if(ans[j]==x) {
						flag = true;
						break;
					}
				}
				if(!flag) break;
			}
			ans[i] = x;
		}
		return ans;
	}

	public static void main(String[] args) {
		int n = 100;
		int mx = 100;
		int[] arr = getArray(n, mx);
		for(int i = 0; i<n; i++) System.out.println(" "+arr[i]);
	}

}
