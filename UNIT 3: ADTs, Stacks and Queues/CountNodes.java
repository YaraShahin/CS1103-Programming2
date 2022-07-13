package countNodes;

public class CountNodes {
	//pre-order
	static class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;
	}
	
	static int count_nodes(TreeNode root) {
		if (root == null) return 0;
		else {
			int count = 1;
			count += count_nodes(root.left);
			count += count_nodes(root.right);
			return count;
		}
	}

}
