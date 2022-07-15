public class TreePrintNonRecursive {
	static StrTreeNode root = null;

	static void treeInsert(String newItem) {
		StrTreeNode node = new StrTreeNode(newItem);
		if (root==null) {
			root = node;
			return;
		}
		else {
			StrTreeNode runner = root;
			while (true) {
				if (newItem.compareTo(runner.item)<0) {
					if (runner.left==null) {
						runner.left = node;
						return;
					}
					else runner = runner.left;
				}
				else {
					if (runner.right==null) {
						runner.right = node;
						return;
					}
					else runner= runner.right;
				}
			}
		}
	}
	
	static void RecursivetreeList(StrTreeNode root) {
		if (root==null) return;
		else {
			RecursivetreeList(root.left);
			System.out.println(root.item);
			RecursivetreeList(root.right);
			return;
		}
	}
	
	static void IterativeTreeList(StrTreeNode root) {
		QueueOfBinaryTreeNodes q = new QueueOfBinaryTreeNodes();
		q.enqueue(root);
		
		while(!q.isEmpty()) {
			StrTreeNode node = q.dequeue();
			if (node.left!=null) q.enqueue(node.left);
			System.out.println(node.item);
			if (node.right!=null) q.enqueue(node.right);
		}
	}

	public static void main(String[] args) {
		//insering items
		treeInsert("judy");
		treeInsert("bill");
		treeInsert("fred");
		treeInsert("mary");
		treeInsert("dave");
		treeInsert("jane");
		treeInsert("alice");
		treeInsert("joe");
		treeInsert("tom");
		
		//using recursion
		System.out.println("Printing the binary sort tree using a recursive approach:");
		RecursivetreeList(root);
		
		//using iterations
		System.out.println("Printing the binary sort tree using an iterative approach:");
		IterativeTreeList(root);
	}

}
