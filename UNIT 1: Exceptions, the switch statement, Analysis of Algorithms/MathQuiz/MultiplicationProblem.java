public class MultiplicationProblem extends Problem{

	private int x,y,answer;

	public MultiplicationProblem() {
		x = (int)(1 + 11*Math.random());
		y = (int)(1+ (11* Math.random()));
		answer = x * y;
	}

	public String getProblem() {
		return "Compute the product: " + x + " * " + y;
	}

	public int getAnswer() {
		return answer;
	}

}