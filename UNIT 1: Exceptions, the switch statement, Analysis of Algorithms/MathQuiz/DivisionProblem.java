public class DivisionProblem extends Problem{

	private int x,y,answer;

	public DivisionProblem() {
		answer = (int)(1 + 11*Math.random());
		y = (int)(1 + (11 * Math.random()));
		x = y * answer;
	}

	public String getProblem() {
		return "Compute the division: " + x + " / " + y;
	}

	public int getAnswer() {
		return answer;
	}

}