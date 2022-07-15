public class CountZeros {
	static Node root = null;
	static Node runner = null;
	public static void main(String args[]) {
		addNode(5);
		addNode(10);
		addNode(0);
		addNode(3);
		addNode(0);
		addNode(0);
		addNode(0);
		addNode(9);
		addNode(42);
		System.out.println(countZeros(root));
	}
	
	static class Node{
		int item;
		Node next;
		Node(int val){
			item = val;
			next = null;
		}
	}
	
	static void addNode(int val) {
		Node node = new Node(val);
		if (root==null) {
			root = node;
			runner = node;
		}
		else {
			runner.next = node;
			runner = runner.next;
		}
	}
	
	static int countZeros(Node root) {
		int count = 0; 
		if (root==null) return count;
		Node runner = root;
		while (runner!=null) {
			if (runner.item == 0) count++;
			runner = runner.next;
		}
		return count;
	}
}
