/**
 * A class for experimenting with expression trees.  This class includes 
 * a nested abstract class and several subclasses that represent nodes in
 * an expression tree.  It also includes several methods that work with these
 * classes.
 * factors:
 * 1. probability maxheight
 * 2. probability that the node is constant or variable if depth = 0
 * 3. probability that the node is constant, variable, unary operator, binary operator if depth != 0
 * 4. Range of constants (currently from 1 to 100)
 * 5. Probability that mutation will change the unary operator, or the child, or both
 * 6. Probability that mutation will change the binary operator, or the left, or the right
 * 7. Probability that the unary crossover will swap the operator or the children
 * 8. Probability that the binary crossover will swap operators, left to left, right to right, right to left , left to right
 * 
 * Future plans
 * 1. Modify the equals method that 3 + 5 equals 5 + 3 and that 3 * 5 = 5 * 3
 * 2. Change the makeTestData expression to be more complicated & include unary
 * 3. Make mutation BinOpNode include all probabilities: operator, left, right, op&left, op&right, left&right, op&left&right
 * 4. Make mutations include changing an operand with a completely new random expression
 * 5. Make crossovers available for different expressions (swapping the child of unary with operand of binary, or the constant node with an operand, ...)
 * 6. Make probability to either mutate after breeding or not
 */
public class GeneticProgramming {

	public static void main(String[] args) {
		int population = 1000;
		int generations = 0;
		double sum = 0;
		double[][] dt = makeTestData();
		Individual people[] = new Individual[population*2];
		int j = 0;
		while(j<population) {
			Individual person = new Individual();
			int n = (int) (Math.random()*10);
			person.exp = randomExpression(n);
			person.fitness = RMSError(person.exp, dt);
			if (!Double.isNaN(person.fitness) && !Double.isInfinite(person.fitness) && person.fitness<10000) {
				people[j] = person;
				sum+=person.fitness;
				System.out.println((int)person.fitness);
				j++;
			}
		}
		quickSort(people, 0, 999);
		System.out.println("best initial fitness is: "+people[0].fitness);

		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis()-startTime<10000) {
			generations++;
			int i = population;
			while(i<(2*population)) {
				Individual person1 = new Individual();
				Individual person2 = new Individual();
				int p1 = (int) (Math.random() * 1000);
				int p2 = (int) (Math.random() * 1000);
				person1.exp = copy(people[p1].exp);
				person2.exp = copy(people[p2].exp);
				cross(person1.exp, person2.exp);
				mutate(person1.exp);
				mutate(person2.exp);
				person1.fitness = RMSError(person1.exp, dt);
				person2.fitness = RMSError(person2.exp, dt);
				if (!Double.isNaN(person1.fitness) && !Double.isInfinite(person1.fitness)) {
					people[i] = person1;
					i++;
				}
				if (!Double.isNaN(person2.fitness) && !Double.isInfinite(person2.fitness)) {
					if (i<population*2) people[i] = person2;
					i++;
				}
			}
			quickSort(people, 0, people.length-1);
		}
		System.out.println("Average initial population: "+sum/1000.0);
		System.out.println("Number of generations: "+ generations);
		System.out.println("Best final fitness: "+ people[0].fitness);
	}

	/**
	 * Given an ExpNode that is the root of an expression tree, this method
	 * makes a full copy of the tree.  The tree that is returned is constructed
	 * entirely of freshly made nodes.  (That is, there are no pointers back
	 * into the old copy.)
	 */
	static ExpNode copy(ExpNode root) {
		if (root instanceof ConstNode)
			return new ConstNode(((ConstNode)root).number);
		else if (root instanceof VariableNode)
			return new VariableNode();
		else if (root instanceof UnaryOpNode) {
			UnaryOpNode node = (UnaryOpNode) root;
			return new UnaryOpNode(node.id, copy(node.child));
		}
		else {
			BinOpNode node = (BinOpNode)root;
			// Note that left and right operand trees have to be COPIED, 
			// not just referenced.
			return new BinOpNode(node.op, copy(node.left), copy(node.right));
		}
	}

	/**
	 * Returns an n-by-2 array containing sample input/output pairs. If the
	 * return value is called data, then data[i][0] is the i-th input (or x)
	 * value and data[i][1] is the corresponding output (or y) value.
	 * (This method is currently unused, except to test it.)
	 */
	static double[][] makeTestData() {
		double[][] data = new double[21][2];
		double xmax = 5;
		double xmin = -5;
		double dx = (xmax - xmin) / (data.length - 1);
		for (int i = 0; i < data.length; i++) {
			double x = xmin + dx * i;
			double y = 2.5*x*x*x - x*x/3 + 3*x;
			data[i][0] = x;
			data[i][1] = y;
		}
		return data;
	}

	static ExpNode randomExpression(int maxHeight) {
		ExpNode node;
		if (maxHeight==0) {
			int p = (int) (Math.random() * 3);
			if (p<=1) {          //then it's a constant
				int num = (int) (Math.random()*100);
				node = new ConstNode(num);
			}
			else {               //then it's a variable
				node = new VariableNode();
			}
			return node;
		}
		else {
			int p = (int) (Math.random()* 6);
			if (p<=1) {                          //constant
				int num = (int) ((Math.random()*100) + 1);
				node = new ConstNode(num);
				return node;
			}
			else if(p==2) {                      //variable
				node = new VariableNode();
				return node;
			}
			else if (p==3) {                     //unary expression
				int opval = (int) (Math.random()* 4);
				return new UnaryOpNode(opval, randomExpression(maxHeight-1));
			}
			else {                               //binary expression
				int opval = (int) (Math.random()* 4);
				char op;
				if (opval==0) op = '+';
				else if (opval==1) op = '-';
				else if (opval==2) op = '*';
				else op = '/';
				node = new BinOpNode(op, randomExpression(maxHeight-1), randomExpression(maxHeight-1));
				return node;
			}
		}
	}

	static double RMSError( ExpNode f, double[][] sampleData ) {
		double error = 0;
		for(int i = 0; i<sampleData.length; i++) {
			error += Math.pow(f.value(sampleData[i][0]) - sampleData[i][1], 2);
		}
		return Math.sqrt(error/sampleData.length);
	}

	static ExpNode mutate (ExpNode f) {
		if (f instanceof VariableNode) return f;
		if (f instanceof ConstNode) {
			int num = (int) ((Math.random()*100) + 1);
			((ConstNode) f).number = num;
			return f;
		}
		if (f instanceof UnaryOpNode) {
			int p = (int) (Math.random() * 3);
			switch(p) {
			case 0:           //just change the operator
				int op = (int) (Math.random() * 4);
				((UnaryOpNode) f).id = op;
				return f;
			case 1:           //just change the child recursive
				return mutate(((UnaryOpNode) f).child);
			default:          //change both
				int id = (int) (Math.random() * 4);
				((UnaryOpNode) f).id = id;
				return mutate(((UnaryOpNode) f).child);
			}
		}
		else if (f instanceof BinOpNode) {
			int p = (int) (Math.random() * 6);
			switch(p) {
			case 0:           //just change the operator
				int op = (int) (Math.random() * 4);
				switch(op) {
				case 0:
					((BinOpNode) f).op = '+';
					return f;
				case 1:
					((BinOpNode) f).op = '-';
					return f;
				case 2:
					((BinOpNode) f).op = '*';
					return f;
				default:
					((BinOpNode) f).op = '/';
					return f;
				}
			case 1:           //just change the left
				((BinOpNode) f).left = mutate(((BinOpNode) f).left);
				return f;
			default:          //just change the right
				((BinOpNode) f).right = mutate(((BinOpNode) f).right);
				return f;
			}
		}
		return f;
	}

	static void cross(ExpNode f1, ExpNode f2) {
		if (f1 instanceof VariableNode || f2 instanceof VariableNode || f1 instanceof ConstNode || f2 instanceof ConstNode) return ;         //can't cross any constant or variable expression
		if (f1 instanceof UnaryOpNode) {
			if (f2 instanceof UnaryOpNode) {
				int p = (int) (Math.random() * 4);
				switch(p) {
				case 0:                               //swap the operators
				case 1:
				case 2:
					ExpNode tempOp= copy(f1);
					((UnaryOpNode) f1).id = ((UnaryOpNode) f2).id;
					((UnaryOpNode) f2).id = ((UnaryOpNode) tempOp).id;
					return;
				case 3:                               //swap the children
					ExpNode tempExp = copy(f1);
					((UnaryOpNode) f1).child = ((UnaryOpNode) f2).child;
					((UnaryOpNode) f2).child = ((UnaryOpNode) tempExp).child;
					return;
				}
			}
			else {                                                             //can't cross unary expression with a binary expression. for now.
				cross(((UnaryOpNode) f1).child, f2);               
				return;
			}
		}
		else {
			if (f2 instanceof UnaryOpNode) {                                  //can't cross unary expression with a binary expression
				cross(((BinOpNode) f1).left, f2);
				cross(((BinOpNode) f1).right, f2);
				return;
			}
			else {                                    
				int p = (int) (Math.random() * 8);
				switch(p) {
				case 0:                               //swap the operator
				case 1:
				case 2:
				case 3:
					ExpNode tempOp= copy(f1);
					((BinOpNode) f1).op = ((BinOpNode) f2).op;
					((BinOpNode) f2).op = ((BinOpNode) tempOp).op;
					return;
				case 4:                               //swap left with left
					ExpNode temp= copy(f1);
					((BinOpNode) f1).left = ((BinOpNode) f2).left;
					((BinOpNode) f2).left = ((BinOpNode) temp).left;
					return;
				case 5:                               //swap left with right
					ExpNode emp= copy(f1);
					((BinOpNode) f1).left = ((BinOpNode) f2).right;
					((BinOpNode) f2).left = ((BinOpNode) emp).right;
					return;
				case 6:                               //swap right with left
					ExpNode mp= copy(f1);
					((BinOpNode) f1).right = ((BinOpNode) f2).left;
					((BinOpNode) f2).right = ((BinOpNode) mp).left;
				case 7:                               //swap right with right
					ExpNode tempp= copy(f1);
					((BinOpNode) f1).right = ((BinOpNode) f2).right;
					((BinOpNode) f2).right = ((BinOpNode) tempp).right;
				}

			}
		}
	}

	static void testMutate() {
		int changed = 0;
		int unchanged = 0;
		for (int i = 0; i < 100; i++) {
			ExpNode e = randomExpression(6);
			ExpNode f = copy(e);
			System.out.println(e);
			mutate(f);
			System.out.println(f);
			System.out.println(f.equals(e) ? "equal" : "changed");
			System.out.println();
			if (f.equals(e))
				unchanged++;
			else
				changed++;
		}
		System.out.println(changed + " changed; " + unchanged + " unchanged");
	}
	//------------------- Definitions of Expression node classes ---------


	static void testCrossover() {
		int changed1 = 0, changed2 = 0;
		for (int i = 0; i < 100; i++) {
			ExpNode e1 = randomExpression(6);
			ExpNode e2 = randomExpression(6);
			ExpNode f1 = copy(e1);
			ExpNode f2 = copy(e2);
			cross(e1,e2);
			System.out.println("f1: " + f1);
			System.out.println("f2: " + f2);
			System.out.println("e1: " + e1);
			System.out.println("e2: " + e2);

			if (!e1.equals(f1)) {
				changed1++;
				System.out.println("f1 Changed");
			}
			else System.out.println("f1 not Changed");
			if (!e2.equals(f2)) {
				changed2++;
				System.out.println("f2 Changed");
			}
			else System.out.println("f2 not Changed");
			System.out.println();
		}
		System.out.println("crossover changed first  expreesion " + changed1 + " times");
		System.out.println("crossover changed second expreesion " + changed2 + " times");
	}

	static void quickSort(Individual[] array, int lo, int hi) {
		int mid = quickSortStep(array, lo, hi);
		if (mid - 1 > lo)
			quickSort(array, lo, mid - 1);
		if (mid + 1 < hi)
			quickSort(array, mid + 1, hi);
	}

	static int quickSortStep(Individual[] array, int lo, int hi) {
		Individual temp = array[lo];
		while (true) {
			while (hi > lo && array[hi].fitness > temp.fitness)
				hi--;
			if (hi == lo)
				break;
			array[lo] = array[hi];
			lo++;
			while (hi > lo && array[lo].fitness < temp.fitness)
				lo++;
			if (hi == lo)
				break;
			array[hi] = array[lo];
			hi--;
		}
		array[lo] = temp;
		return lo;
	}

	static class Individual{
		ExpNode exp;
		double fitness;
	}

	/**
	 * An abstract class that represents a general node in an expression
	 * tree.  Every such node must be able to compute its own value at
	 * a given input value, x.  Also, nodes should override the standard
	 * toString() method to return a fully parameterized string representation
	 * of the expression.
	 */
	static abstract class ExpNode {
		abstract double value(double x);
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
		public String toString() {
			if (number < 0)
				return "(" + number + ")"; // add parentheses around negative number
			else
				return "" + number;  // just convert the number to a string
		}
		public boolean equals(Object o) {
			return (o instanceof ConstNode) && ((ConstNode)o).number == number;
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
		public String toString() {
			return "x";
		}
		public boolean equals(Object o) {
			return true; 
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
		public boolean equals(Object o) {
			return (o instanceof BinOpNode)
					&& ((BinOpNode)o).op == op
					&& ((BinOpNode)o).left.equals(left)
					&& ((BinOpNode)o).right.equals(right);
		}
	}

	//0: sin, 1: cos, 2: exp, 3: abs 
	static class UnaryOpNode extends ExpNode{
		int id;
		ExpNode child;

		UnaryOpNode(int id, ExpNode child){
			if (id>3) throw new IllegalArgumentException("Bruh. 0: sin, 1: cos, 2: exp, 3: abs");
			this.id = id;
			this.child = child;
		}

		double value(double x) {
			double val = child.value(x);
			switch (id) {
			case 0: return Math.sin(val);
			case 1: return Math.cos(val);
			case 2: return Math.exp(val);
			default: return Math.abs(val);
			}
		}

		public String toString() {
			switch(id) {
			case 0: return "sin("+child.toString()+")";
			case 1: return "cos("+child.toString()+")";
			case 2: return "exp("+child.toString()+")";
			default: return "abs("+child.toString()+")";
			}
		}
		public boolean equals(Object o) {
			return (o instanceof UnaryOpNode)
					&& ((UnaryOpNode)o).id == id
					&& ((UnaryOpNode)o).child.equals(child);
		}

	}

}
