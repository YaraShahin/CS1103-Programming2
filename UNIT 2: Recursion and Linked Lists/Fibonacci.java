
public class Fibonacci {

	public static void main(String[] args) {
		int n = 5;
		System.out.println(fibonacci(n));
	}
	
	public static int fibonacci(int n) {
		if (n==0 || n==1) return 1;
		else return fibonacci(n-1)+fibonacci(n-2);
	}
}
