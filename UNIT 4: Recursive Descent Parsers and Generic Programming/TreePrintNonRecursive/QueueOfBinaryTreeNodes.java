public class QueueOfBinaryTreeNodes{

	private static class Node{
		StrTreeNode item;
		Node next;
		
		Node(StrTreeNode item){
			this.item = item;
			next = null;
		}
	}
	
	private Node front = null;
	private Node back = null;
	
	public boolean isEmpty() {
		return (front==null);
	}
	
	public void enqueue(StrTreeNode item) {
		Node node = new Node(item);
		if(isEmpty()) {
			back = node;
			front = node;
		}
		else {
			back.next = node;
			back = back.next;            //back = node;
		}
	}
	
	public StrTreeNode dequeue() {
		if (isEmpty()) throw new IllegalStateException("Cannot dequeue an empty queue");
		StrTreeNode ret = front.item;
		front = front.next;
		return ret;
	}
}
