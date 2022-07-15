public class SumBinaryTree {
	private static TreeNode root = null;
	
	private static class TreeNode{
		int item;
		TreeNode left = null;
		TreeNode right = null;
		
		TreeNode(int val){
			item = val;
		}
	}
	
	static void treeInsert(int newItem) {
		TreeNode node = new TreeNode(newItem);
		if (root==null) {
			root = node;
			return;
		}
		else {
			TreeNode runner = root;
			while (true) {
				if (newItem - runner.item<0) {
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
	
	static int sumTree(TreeNode root) {
		int sum = 0; 
		if (root == null) return 0;
		else {
			sum+=root.item;
			sum+=sumTree(root.left);
			sum+=sumTree(root.right);
			return sum;
		}
	}
	
	static TreeNode copy(TreeNode root) {
		if (root==null) return null;
		TreeNode node = new TreeNode(root.item);
		if (root.left==null && root.right==null) return node;
		else {
			if (root.left!=null) node.left = copy(root.left);
			if (root.right!=null) node.right = copy(root.right);
			return node;
		}
	}
	
	static void treeList(TreeNode root) {
		if (root==null) return;
		else {
			System.out.println(root.item);
			treeList(root.left);
			treeList(root.right);
			return;
		}
	}
	
	public static void main(String[] args) {
		treeInsert(5);
		treeInsert(20);
		treeInsert(10);
		treeInsert(0);
		treeInsert(1);
		
		System.out.println("Testing count zeros:");
		System.out.println(sumTree(root)+"\n\n");
		
		System.out.println("Testing copy:");
		System.out.println("The original list:");
		treeList(root);
		System.out.println("\nThe copied list:");
		TreeNode copied = copy(root);
		treeList(copied);
		
		treeInsert(500);        //modifying 
		
		System.out.println("\n\nThe original list after modification:");
		treeList(root);
		System.out.println("\nThe copied list (should not show the modification:");
		treeList(copied);
		
	}
	
	
}
