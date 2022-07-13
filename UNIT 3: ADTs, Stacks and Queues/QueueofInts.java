public class QueueofInts{
	/*Queue of ints
	 * enqueue(int n): adds n to the end of the queue, void
	 * dequeue(): removes the front of the queue, returns its value
	 * isEmpty(): returns true if the queue is empty
	 */
	private class Node{
		int value;
		Node next;
	}
	
	private Node front = null;
	private Node back = new Node();
	
	public boolean isEmpty() {
		return (front==null);
	}
	
	public void enqueue(int n) {
		Node node = new Node();
		node.value = n;
		node.next = null;
		back.next = node;
		back = node;
		if (isEmpty()) front = back;
	}
	
	public int dequeue() {
		if (isEmpty()) throw new IllegalStateException("Cannot dequeue an empty queue");
		int ret = front.value;
		front = front.next;
		return ret;
	}
}
