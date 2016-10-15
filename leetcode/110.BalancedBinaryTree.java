/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private class ResultType{
        public int height;
        public boolean balanced;
        public ResultType(int height, boolean balanced){
            this.height = height;
            this.balanced = balanced;
        }
    }
    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root).balanced;
    }
    public ResultType isBalancedHelper(TreeNode root) {
        if(root == null) {
            return new ResultType(0, true);
        }
        ResultType left = isBalancedHelper(root.left);
        ResultType right = isBalancedHelper(root.right);
        if((!left.balanced) || (!right.balanced)) return new ResultType(0,false);
        if(Math.abs(left.height - right.height) <=1)
            return (new ResultType(left.height>right.height?left.height+1:right.height+1,true));
        return (new ResultType(0,false));
    }
    

}
