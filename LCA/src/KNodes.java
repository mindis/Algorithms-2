import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 发个你两个变种
/*Given K nodes in a binary tree, find their lowest common ancestor.

Assumptions

K >= 2

There is no parent pointer for the nodes in the binary tree

The given K nodes are guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2, 3, 14 is 5

The lowest common ancestor of 2, 3, 9 is 9

*/
 
public class KNodes {
	public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> list) {
		Set<TreeNode> set = new HashSet<TreeNode>(list);
		return lowestCommonAncestorHelper(root, set);
    }
	
	public TreeNode lowestCommonAncestorHelper(TreeNode root, Set<TreeNode> set) {
        if (root == null || set.contains(root)) {
            return root;
        }
        // Divide
        TreeNode left = lowestCommonAncestorHelper(root.left, set);
        TreeNode right = lowestCommonAncestorHelper(root.right, set);
        
        // Conquer
        if (left != null && right != null) {
            return root;
        } 
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
	}
}
