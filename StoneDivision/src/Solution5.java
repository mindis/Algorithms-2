import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}
class SumInLeavesVisitor extends TreeVis {
    public int sum;
    public int getResult() {
      	//implement this
        return sum;
    }

    public void visitNode(TreeNode node) {
      	//implement this
        return;
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
        sum=sum+leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    public long mod = 1000000007;
    public long product=1;
    
    public int getResult() {
      	//implement this
        return (int)product;
    }

    public void visitNode(TreeNode node) {
      	//implement this
        if(node.getColor()==Color.RED){
            product = product*(node.getValue()%mod)%mod;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
        if(leaf.getColor()==Color.RED){
             product= product*(leaf.getValue()%mod)%mod;
        }
    }
}

class FancyVisitor extends TreeVis {
    
    int greenLeafSum;
    int nonLeafEvenDepthSum;
    
    public int getResult() {
      	//implement this
        //System.out.println("nonLeafEvenDepthSum"+nonLeafEvenDepthSum);
       // System.out.println("greenLeafSum"+greenLeafSum);
        return Math.abs(greenLeafSum - nonLeafEvenDepthSum);
    }

    public void visitNode(TreeNode node) {
    	//implement this,
        if(node.getDepth()%2==0){
            nonLeafEvenDepthSum+=node.getValue();
            //System.out.println("node.getValue()"+node.getValue());
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
    	//leaf can be at even depth too
        if(leaf.getColor()==Color.GREEN){
            greenLeafSum+=leaf.getValue();
        }
    }
}

public class Solution5 {
  
    public static Tree solve() {
        //read the tree from STDIN and return its root as a return value of this function
        /*
        5
4 7 2 5 12
0 1 0 0 1
3 4
3 5
1 2
1 3
*/
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Map<Integer, Set<Integer>> map = new HashMap<Integer,Set<Integer>>();
        int[] valArr = new int[n];
        int[] colArr = new int[n];
        for(int i =0;i<n;i++){
            valArr[i] = scan.nextInt();
        }
        for(int i =0;i<n;i++){
            colArr[i] = scan.nextInt();
        }
        for(int i=0;i<n-1;i++){
            //10^10 / 1024/ 1024/1024, 10GB
            int a = scan.nextInt()-1;
            int b = scan.nextInt()-1;
            
            //Tree[] treeArr = new Tree[n];
            if(map.containsKey(a)){
                map.get(a).add(b);
            }else{
                Set<Integer> set = new HashSet<Integer>();
                set.add(b);
                map.put(a,set);
            }
            //case 1-2, 2-1
            if(map.containsKey(b)){
                map.get(b).add(a);
            }else{
                Set<Integer> set = new HashSet<Integer>();
                set.add(a);
                map.put(b,set);
            }        
        }
        Set<Integer> visited = new HashSet<Integer>();
        Tree root =buildTree(map,0,0,valArr,colArr);
        return root;
    }
    
    public static Tree buildTree(Map<Integer, Set<Integer>> map,int depth, int cur, int[] valArr, int[] colArr){
         if(map.containsKey(cur)){
            TreeNode newNode = new TreeNode(valArr[cur],colArr[cur]==0?Color.RED:Color.GREEN,depth);
            //System.out.println("cur"+(cur+1)+"depth"+depth);
            Set<Integer> set = map.get(cur);
            
            for(Integer i:set){
                //remove same reverse edge
                if(map.containsKey(i)){
                    map.get(i).remove(cur);
                    if(map.get(i).size()==0){
                        map.remove(i);
                    }
                }
                Tree child = buildTree(map, depth+1,i,valArr,colArr);
                newNode.addChild(child);
            }
             return newNode;
        }else{
             Tree root = new TreeLeaf(valArr[cur],colArr[cur]==0?Color.RED:Color.GREEN,depth);
            // System.out.println("cur"+(cur+1)+"depth"+depth);
            return root;
        }
    }
    

    public static void main(String[] args) {
      	Tree root = solve();
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
      	ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
      	FancyVisitor vis3 = new FancyVisitor();

      	root.accept(vis1);
      	root.accept(vis2);
      	root.accept(vis3);

      	int res1 = vis1.getResult();
      	int res2 = vis2.getResult();
      	int res3 = vis3.getResult();

      	System.out.println(res1);
     	System.out.println(res2);
    	System.out.println(res3);
	}
}