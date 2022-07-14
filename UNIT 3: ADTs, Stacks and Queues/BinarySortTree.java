public class BinarySortTree{
	
	public static void main(String args[]) {
		//make the tree
		BinarySortTree bts = new BinarySortTree();
		for(int i = 0; i<1023; i++) {
			int n = (int) (Math.random()*100000);
			bts.treeInsert(n);
		}
		//print the tree for sanity check
		System.out.println("Tree nodes in order:");
		bts.treeList(root);
		System.out.println("--\n\n");	
		
		//testing the three recursive subroutines
		int count = bts.countLeaves(root);
		int sum = bts.sumDepth(root, 0);
		int max = bts.maxDepth(root, 0);
		System.out.println("Number of leaves: " + count);
		System.out.println("Sum of leaves' depths: " + sum);
		System.out.println("Max of leaves' depths: " + max);
		System.out.println("");
		
		System.out.println("The average depth of all the leaves: " + ((double) sum / (double) count));
		System.out.println("The maximum depth of all the leaves: " + max);
		
	}
	
	private static TreeNode root = null;
	private static class TreeNode{
		int item;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int n){
			item = n;
		}
	}
	
	static int countLeaves(TreeNode root) {
		if (root==null) return 0;
		else if (root.left==null && root.right==null) return 1;
		else {
			int count = 0;
			if (root.left!=null) count += countLeaves(root.left);
			if (root.right!=null) count += countLeaves(root.right);
			return count;
		}
	}
	
	static int sumDepth(TreeNode root, int depth) {
		if (root==null) return 0;
		else if (root.left==null && root.right==null) return depth;
		else {
			int sum = 0;
			if (root.left!=null) sum += sumDepth(root.left, depth+1);
			if (root.right!=null) sum += sumDepth(root.right, depth+1);
			return sum;
		}
	}
	
	static int maxDepth(TreeNode root, int depth) {
		if (root==null) return 0;
		else if (root.left==null && root.right==null) return depth;
		else {
			int leftDepth = 0, rightDepth = 0;
			if (root.left!=null) leftDepth = maxDepth(root.left, depth+1);
			if (root.right!=null) rightDepth = maxDepth(root.right, depth+1);
			if (leftDepth>rightDepth) return leftDepth;
			else return rightDepth;
		}
	}
	
	//iterative
	static void treeInsert(int newItem) {
		TreeNode node = new TreeNode(newItem);
		if (root==null) {
			root = node;
			return;
		}
		else {
			TreeNode runner = root;
			while (true) {
				if (newItem<runner.item) {
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
	
	//pre-order
	static int count_nodes(TreeNode root) {
		if (root == null) return 0;
		else {
			int count = 1;
			count += count_nodes(root.left);
			count += count_nodes(root.right);
			return count;
		}
	}
	
	static void treeList(TreeNode root) {
		if (root==null) return;
		else {
			treeList(root.left);
			System.out.println(root.item);
			treeList(root.right);
			return;
		}
	}
	
	//recursive
	static boolean treeContains(TreeNode root, int item) {
			if (root==null) return false;
			else {
				if (item == root.item) return true;
				else {
					if (item<root.item) return (treeContains(root.left, item)); 
					else return (treeContains(root.right, item));
				}
			}
		}
}
