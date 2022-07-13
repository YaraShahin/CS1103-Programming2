public class DFS {
	public static void main(String[] args) {
		boolean[][] maze = new boolean[][] {{false, true, false, false, false}, {false, false, false, true, false}, {false, false, true, false, false}, {true, false, true, false, true}, {false, false, true, false, false}};
		//boolean[][] maze = {{false, true}, {false, false}};
		solve_maze(maze, 0, 0);
		System.out.println("Finished");
	}

	public static boolean solve_maze(boolean[][] maze, int i, int j) {
		if (i>=0 && j>=0 && i<maze.length && j<maze[0].length) {
			if (!maze[i][j]) {
				maze[i][j] = true;
				if (i==maze.length-1 && j==maze[0].length-1) {
					System.out.println("In the bottom right corner. i = "+(maze.length-1)+" | j = "+(maze[0].length-1));
					return true; 
				}
				if (solve_maze(maze, i+1, j) || solve_maze(maze, i-1, j) || solve_maze(maze, i, j+1) || solve_maze(maze, i, j-1)) {
					System.out.println("Go through "+i+" and "+j);
					return true;
				}
			}
		}
		return false;
	}
}
