import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 * 
1
3
3511 3671 4153

992
3511
3671
5081
4153
7566
7790
 * @author chaoran
 *
 */
public class GenerateAllXORs {
	static Set<Integer> set = new HashSet<Integer>();
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
    	int q = Integer.parseInt(scan.nextLine());
    	for(int i = 0; i < q; i++){
    		int n = Integer.parseInt(scan.nextLine());
    		String[] arrS = scan.nextLine().split(" ");
    		int[] arr = new int[n];
    		for(int j = 0; j < n; j++){
    			arr[j] = Integer.parseInt(arrS[j]);
    		}
    		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
            if(arr == null || arr.length == 0) {
            	return;
            }
            ArrayList<Integer> list = new ArrayList<Integer>();
            Arrays.sort(arr);
            /**
             * 0 ^ A = A
             */
            subsetsHelper(arr, 0, 0);
            System.out.println(set);
    	}
	}
	private static void subsetsHelper(int[] num, int cur, int pos) {
	        set.add(cur);
	        for (int i = pos; i < num.length; i++) {
	            cur ^= num[i]; // cur is now the diff
	            subsetsHelper(num, cur, i + 1);
	            cur  = num[i] ^ cur; // have num op diff, get back cur
	        }
	}
}

