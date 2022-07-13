public class StackOfInts {
	/*a stack of ints
	 * push: add an int, void
	 * pop: remove top int, return the int
	 * isEmpty: returns true if stack is empty
	 */
	private int count = 0;
	private Node runner;

	private class Node{
		int value;
		Node previous;
	}

	public boolean isEmpty() {
		if (count==0) return true;
		else return false;
	}

	public void push(int newInt) {
		Node node = new Node();
		node.value = newInt;
		node.previous = runner;
		runner = node;
		count++;
	}
	
	public int pop() {
		assert (!isEmpty());
		int ret = runner.value;
		runner = runner.previous;
		count--;
		return ret;
	}

}
