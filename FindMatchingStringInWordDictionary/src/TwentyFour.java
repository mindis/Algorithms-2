import java.util.Set;

/*
 * 4 number calculation result to 24
 * Expression Tree:
 *         *
 *      +	  7
 *    3   /
 *    	 3  7
 *    
 *    
 *    /        *        +      +      -
 *  3   7     7  3    7   3   3  7   3  3  
 *  
 *  result  *
 *  	-      +
 *  
 *  result expression
 */
public class TwentyFour {
	
	private class Node{
		float value;
		Node leftNode;
		Node rightNode;
		char operation;
		
	}
//	private class Expression{
//		String 
//	}
//	ArrayList<Integer, ArrayList<Integer>>()
	
//	float eval24(int start, int end, int[] nums){
//		if(start == end){
//			return nums[start];
//		}
//		for(int i = start; i <=  end; i++){
//			float ret = eval24(start, i, nums) * eval24(i+1, end,nums);
//		}
//	}
	
	
	public static void main(String[] args){
		String[] operators = new String[]{"+", "-", "*", "/"};
		float result
	}
}
