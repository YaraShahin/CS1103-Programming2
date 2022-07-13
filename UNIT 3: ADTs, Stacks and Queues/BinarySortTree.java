public class BinarySortTree{
	
	private static class TreeNode{
		String item = null;
		TreeNode left = null;
		TreeNode right = null;
		
		TreeNode(String str){
			item = str;
		}
	}
	private static TreeNode root = null;
	
	
	public static void main(String args[]) {
		BinarySortTree bts = new BinarySortTree();
		bts.treeInsert("Yara");
		bts.treeInsert("Nada");
		//bts.treeInsert("Hadeer");
		//bts.treeInsert("Mama");
		//bts.treeInsert("Baba");
		//bts.treeList(bts.root);
		System.out.println(bts.treeContains(bts.root, "Yara"));

		System.out.println(bts.treeContains(bts.root, "Nada"));
		System.out.println(bts.count_nodes(bts.root));
	}
	
	//recursive
	static boolean treeContains(TreeNode root, String item) {
		if (root==null) return false;
		else {
			if (item.equals(root.item)) return true;
			else {
				if (item.compareTo(root.item)<0) return (treeContains(root.left, item)); 
				else return (treeContains(root.right, item));
			}
		}
	}
	
	//iterative
	static void treeInsert(String newItem) {
		TreeNode node = new TreeNode(newItem);
		if (root==null) {
			root = node;
			return;
		}
		else {
			TreeNode runner = root;
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
			System.out.println(root.item);
			treeList(root.left);
			treeList(root.right);
			return;
		}
	}
	
	

}
