package factorial;

public class Factorial {

	public static void main(String[] args) {
		int n = 20;
		System.out.println(factorial(n));
	}
	
	public static long factorial(int n) {
		if (n == 0) return 1;
		else return n*factorial(n-1);
	}
}
