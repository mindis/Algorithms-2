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
    public int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        pathSumHelper(root, sum, list);
        return count;
    }
    
    public void pathSumHelper(TreeNode root, int sum, ArrayList<Integer> list){
        if(root == null) return;
        list.add(0, root.val);
        if(root.val == sum){ count ++;}
        for(int i=1;i<list.size();i++){
            int cur = list.get(i)+root.val;
            if(cur == sum) { count ++;}
            list.set(i, cur);
        }
        pathSumHelper(root.left, sum, list);
        pathSumHelper(root.right, sum, list);
        int first = list.get(0);
        list.remove(0);
        for(int i=0;i<list.size();i++){
            int cur = list.get(i)-first;
            list.set(i, cur);
        }
    }
}


