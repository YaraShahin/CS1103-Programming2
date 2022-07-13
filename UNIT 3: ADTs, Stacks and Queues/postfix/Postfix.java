public class Postfix extends StackOfInts{
	static String expression = "2 15 12 - 17 * +";
	//static String expression = "2 2 +";
	static StackOfInts nums = new StackOfInts();

	public static void main(String[] args) {
		System.out.println(calculate_postfix());
	}

	public static int calculate_postfix() {
		String next = getNextElement();
		while (!next.equals("s")) {
			
			if (next.length()>1 || next.equals("0") || next.equals("1") || next.equals("2") || next.equals("3") || next.equals("4") || next.equals("5") || next.equals("6") || next.equals("7") || next.equals("8") || next.equals("9")) {
				int num = Integer.parseInt(next);
				nums.push(num);
			}
			else if (next.equals("+")) {
				int ans = nums.pop() + nums.pop();
				nums.push(ans);
			}
			else if (next.equals("-")) {
				int ans = (-1*nums.pop()) + nums.pop();
				nums.push(ans);
			}
			else if (next.equals("*")) {
				int ans = nums.pop() * nums.pop();
				nums.push(ans);
			}
			else if (next.equals("/")) {
				int ans = nums.pop() * nums.pop();
				nums.push(ans);
			}
			else if (next.equals("^")) {
				int ans = (int) Math.pow(nums.pop(),nums.pop());
				nums.push(ans);
			}
			else {
				System.out.println("ERROR");
				return -1;
			}
			next = getNextElement();
		}
		return nums.pop();
	}

	public static String getNextElement() {
		String ret = "";
		while (expression.length()>0) {
			if (expression.charAt(0)==' ') {
				expression = expression.substring(1);
				return ret;
			}
			else {
				ret += expression.charAt(0);
				if (expression.length()>1) expression = expression.substring(1);
				else{
					expression = "";
					return ret;
				}
			}
		}
		return "s";
	}
}
