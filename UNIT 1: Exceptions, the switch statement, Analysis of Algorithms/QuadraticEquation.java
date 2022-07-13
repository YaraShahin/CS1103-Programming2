import java.util.Scanner;

public class QuadraticEquation {

	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		while(true) {
			System.out.println("Do you want to solve a quadratic equation?(0:no|1:yes)");
			int x = in.nextInt();
			if(x==0) break;
			try {
				System.out.println("Please enter A: ");
				double a = in.nextDouble();
				System.out.println("Please enter B: ");
				double b = in.nextDouble();
				System.out.println("Please enter C: ");
				double c = in.nextDouble();
				
				System.out.println("The answer: "+ root(a, b, c));
			}
			catch(IllegalArgumentException e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * Returns the larger of the two roots of the quadratic equation
	 * A*x*x + B*x + C = 0, provided it has any roots.  If A == 0 or
	 * if the discriminant, B*B - 4*A*C, is negative, then an exception
	 * of type IllegalArgumentException is thrown.
	 */
	static public double root( double A, double B, double C ) throws IllegalArgumentException {
		if (A == 0) {
			throw new IllegalArgumentException("A can't be zero.");
		}
		else {
			double disc = B*B - 4*A*C;
			if (disc < 0)
				throw new IllegalArgumentException("Discriminant < zero.");
			return  (-B + Math.sqrt(disc)) / (2*A);
		}
	}
}
