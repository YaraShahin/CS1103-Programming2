public class SubtractionProblem extends Problem{
	private int x,y,answer;

	public SubtractionProblem() {
		x = (int)(10 + 40*Math.random());
		y = (int)(x*Math.random());
		answer = x-y;
	}
	public String getProblem() {
		return "Compute the difference: " + x + " - " + y;
	}

	public int getAnswer() {
		return answer;
	}

}
