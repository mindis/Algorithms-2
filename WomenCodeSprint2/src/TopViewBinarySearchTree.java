import java.util.HashMap;
import java.util.Map;

public class TopViewBinarySearchTree {
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int curIndex = 0;
	static Map<Integer, Integer> topViewMap = new HashMap<Integer,Integer>();
	
	static class Node{
		int value;
		Node left;
		Node right;
		Node(int value){
			this.value = value;
		}
	}
	
	static void generateTopView(Node root){
		if(root == null) return;
		if(!topViewMap.containsKey(curIndex)){
			topViewMap.put(curIndex, root.value);
			if(min>curIndex) min = curIndex;
			if(max<curIndex) max = curIndex;
		}
		curIndex --;
		generateTopView(root.left);
		curIndex ++;
		
		curIndex ++;
		generateTopView(root.right);
		curIndex --;
	}
	
	static void printTopView(Node root){
		generateTopView(root);
		for(int i = min; i<=max; i++){
			System.out.println(topViewMap.get(i));
		}
	}
	public static void main(String[] args){
		Node one = new Node(1);
		Node two = new Node(2);
		Node three = new Node(3);
		Node four = new Node(4);
		Node six = new Node(6);
		Node seven = new Node(7);
		one.left = two;
		two.left = three;
		two.right = four;
		four.right = six;
		six.right = seven;
		printTopView(one);
	}
}
