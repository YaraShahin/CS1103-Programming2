
public class ReverseOrder {

	public static void main(String[] args) {
		ListNode one = new ListNode();
		one.item = 0;
		ListNode root = one;
		for (int i = 2; i<=20; i+=2) {
			if (one.next == null ) one.next = new ListNode();
			one = one.next;
			one.item = i;
		}
		printList(root);
		printList(reverse(root));

	}

	static class ListNode {
		int item; // An item in the list.
		ListNode next; // Pointer to the next node in the list.
	}
	
	static private ListNode reverse(ListNode runner) {
		ListNode root = new ListNode();
		if (runner==null) {
			return null;
		}
		else {
			root.item = runner.item;
			runner = runner.next;
		}
		while(runner!=null) {
			ListNode node = new ListNode();
			node.item = runner.item;
			node.next = root;
			root = node;
			runner = runner.next;
		}
		return root;
	}
	
	static private void printList(ListNode root) {
		System.out.println("Nodes in order:");
		System.out.println("Root: "+root.item);
		while(root.next!=null) {
			root = root.next;
			System.out.println(root.item);
		}
	}


}
