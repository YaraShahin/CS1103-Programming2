package avgTemp;

public class avgTemp {

	public static void main(String[] args) {
		int[][] temps = new int[100][365];
		for (int i = 0; i<temps.length; i++) {
			int plus = 0;
			for (int j = 0; j<365; j++) plus+=temps[i][j];
			System.out.println("The average tempratue for the city number "+i+" is "+ ((double)plus/(double)365));
		}
	}
}
