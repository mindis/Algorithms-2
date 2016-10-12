class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }
        // Divide
        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);
        
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
    
    public static void main(String[] args){
    	//[37,-34,-48,null,-100,-100,48,null,null,null,null,-54,null,-71,-22,null,null,null,8]
    	
    	TreeNode root = new TreeNode(37);
    	root.left = new TreeNode(-34);
    	root.right = new TreeNode(-48);
    	root.left.left = null;
    	root.left.right = new TreeNode(-100);
    	root.right.left = new TreeNode(-100);
    	root.right.right = new TreeNode(48);
    }
    
}
