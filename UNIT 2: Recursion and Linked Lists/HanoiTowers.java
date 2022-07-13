
public class HanoiTowers {

	public static void main(String[] args) {
		int n = 3;
		solver_towers_of_Hanoi(n, 1, 3, 2);
	}
	
	public static void solver_towers_of_Hanoi(int n, int from, int to, int spare) {
		if (n==1) System.out.println("Move disk 1 from tower "+ from+" to tower "+to);
		else {
			solver_towers_of_Hanoi(n-1, from, spare, to);
			System.out.println("Move disk "+n+" from tower "+ from+" to tower "+to);
			solver_towers_of_Hanoi(n-1, spare, to, from);
		}
	}

}
