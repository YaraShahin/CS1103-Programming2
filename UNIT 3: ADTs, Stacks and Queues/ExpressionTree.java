public class ExpressionTree {
	public static void main(String args[]) {
		Node leftNode = new ConstNode(5);
		Node rightNode = new ConstNode(10);
		Node node = new OpNode('+', leftNode, rightNode);
		System.out.println(node.value());
	}
	
	abstract static class Node{
		abstract double value();
	}
	
	private static class ConstNode extends Node{
		double val;
		
		ConstNode(double val){
			this.val = val;
		}
		
		double value() {
			return val;
		}
	}
	
	private static class OpNode extends Node{
		char op;
		Node left;
		Node right;
		
		OpNode(char op, Node left, Node right){
			this.op = op;
			this.left = left;
			this.right = right;
		}
		
		double value() {
			if (op=='+') return left.value() + right.value();
			else if (op=='-') return left.value() - right.value();
			else if (op=='*') return left.value() * right.value();
			else if (op=='/') return left.value() / right.value();
			else return Double.NaN;
		}
	}
}
