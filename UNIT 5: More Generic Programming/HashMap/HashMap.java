public class HashMap {
	private static ListNode[] hashTable;
	private static int n;
	private static int count = 0;

	public HashMap() {
		hashTable = new ListNode[100];
		n = 100;
	}
	public HashMap(int length) {
		hashTable = new ListNode[length];
		n = length;
	}

	private static class ListNode{
		String key;
		String value;
		ListNode next;
	}

	public void put(String key, String value) {
		int index = Math.abs(key.hashCode()) % n;
		ListNode node = new ListNode();
		node.key = key;
		node.value = value;

		if(hashTable[index]==null) {                 //index is null so linkedlist is empty
			hashTable[index] = node;
			count++;
		}
		else {                                       //index is not null
			ListNode runner = hashTable[index];
			boolean found = false;
			while(runner!=null) {         
				if (runner.key.equals(key)) {        //the key already exists, then just replace value
					found = true;
					runner.value = value;
					break;
				}
				runner = runner.next;
			}
			if (!found) {
				node.next = hashTable[index];
				hashTable[index] = node;
				count++;
			}
		}
	}

	public String get(String key) {
		int index = Math.abs(key.hashCode()) % n;
		ListNode runner = hashTable[index];
		while(runner!=null) {
			if (runner.key.equals(key)) return runner.value;
			runner = runner.next;
		}
		return null;
	}
	
	public void remove(String key) {
		int index = Math.abs(key.hashCode()) % n;
		ListNode pre = null;
		ListNode runner = hashTable[index];
		while(runner!=null) {
			if (runner.key.equals(key)) {
				if (pre==null) hashTable[index] = runner.next;
				else pre.next = runner.next; 
				count--;
				break;
			}
			pre = runner;
			runner = runner.next;
		}
	}
	
	public boolean containsKey(String key) {
		int index = Math.abs(key.hashCode()) % n;
		ListNode runner = hashTable[index];
		while(runner!=null) {
			if (runner.key.equals(key)) return true;
			runner = runner.next;
		}
		return false;
	}
	
	public int size() {
		return count;
	}

}
