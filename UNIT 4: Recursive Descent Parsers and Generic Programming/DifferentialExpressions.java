public class DifferentialExpressions {

	public static void main(String[] args) {
		System.out.println("Testing expression creation and evaluation...\n");

		ExpNode e1 = new BinOpNode('+', new VariableNode(), new ConstNode(3));
		ExpNode e2 = new BinOpNode('*', new ConstNode(2), new VariableNode());
		ExpNode e3 = new BinOpNode('-', e1, e2);
		ExpNode e4 = new BinOpNode('/', e1, new ConstNode(-3));

		System.out.println("For x = 3:");
		System.out.println("   " + e1 + " = " + e1.value(3));
		System.out.println("   " + e2 + " = " + e2.value(3));
		System.out.println("   " + e3 + " = " + e3.value(3));
		System.out.println("   " + e4 + " = " + e4.value(3));
		
		System.out.println("Testing derivatives");
		ExpNode exp1 = new BinOpNode('+', new VariableNode(), new ConstNode(3));
		System.out.println(exp1.derivative().value(3));
		ExpNode exp2 = new BinOpNode('*', new VariableNode(), new ConstNode(3));
		System.out.println(exp2.derivative().value(3));
	}



	//------------------- Definitions of Expression node classes ---------

	/**
	 * An abstract class that represents a general node in an expression
	 * tree.  Every such node must be able to compute its own value at
	 * a given input value, x.  Also, nodes should override the standard
	 * toString() method to return a fully parameterized string representation
	 * of the expression.
	 */
	static abstract class ExpNode {
		abstract double value(double x);
		abstract ExpNode derivative();
		// toString method should also be defined in each subclass
	}

	/**
	 * A node in an expression tree that represents a constant numerical value.
	 */
	static class ConstNode extends ExpNode {
		double number;  // the number in this node.
		ConstNode(double number) {
			this.number = number;
		}
		double value(double x) {
			return number;
		}
		ExpNode derivative() {
			return new ConstNode(0);
		}
		public String toString() {
			if (number < 0)
				return "(" + number + ")"; // add parentheses around negative number
			else
				return "" + number;  // just convert the number to a string
		}
	}

	/**
	 * A node in an expression tree that represents the variable x.
	 */
	static class VariableNode extends ExpNode {
		VariableNode() {
		}
		double value(double x) {
			return x;
		}
		ExpNode derivative() {
			return new ConstNode(1);
		}
		public String toString() {
			return "x";
		}
	}

	/**
	 * A node in an expression tree that represents one of the
	 * binary operators +, -, *, or /.
	 */
	static class BinOpNode extends ExpNode {
		char op;  // the operator, which must be '+', '-', '*', or '/'
		ExpNode left, right;  // the expression trees for the left and right operands.
		BinOpNode(char op, ExpNode left, ExpNode right) {
			if (op != '+' && op != '-' && op != '*' && op != '/')
				throw new IllegalArgumentException("'" + op + "' is not a legal operator.");
			this.op = op;
			this.left = left;
			this.right = right;
		}
		double value(double x) {
			double a = left.value(x);  // value of the left operand expression tree
			double b = right.value(x); // value of the right operand expression tree
			switch (op) {
			case '+': return a + b;
			case '-': return a - b;
			case '*': return a * b;
			default:  return a / b;
			}
		}
		public String toString() {
			return "(" + left.toString() + op + right.toString() + ")";
		}

		ExpNode derivative() {
			switch (op) {
			case '+': return new BinOpNode('+', left.derivative(), right.derivative());
			case '-': return new BinOpNode('-', left.derivative(), right.derivative());
			case '*': 
				ExpNode t1 = new BinOpNode('*', left, right.derivative());
				ExpNode t2 = new BinOpNode('*', left.derivative(), right);
				return new BinOpNode('+', t1, t2);
			default:
				ExpNode term1 = new BinOpNode('*', left, right.derivative());
				ExpNode term2 = new BinOpNode('*', left.derivative(), right);
				ExpNode num = new BinOpNode('-', term2, term1);
				ExpNode dom = new BinOpNode('*', right, right);
				return new BinOpNode('/', num, dom);
			}
		}
	}

}
