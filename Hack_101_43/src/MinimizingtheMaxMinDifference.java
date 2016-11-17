
import java.util.Scanner;

/*
 * https://www.hackerrank.com/contests/101hack43/challenges
 */
public class MinimizingtheMaxMinDifference {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int[] min = new int[2]; min[0] = Integer.MAX_VALUE;min[1] = Integer.MAX_VALUE;
        int[] max = new int[2]; max[0] = Integer.MIN_VALUE;max[1] = Integer.MIN_VALUE;
        for(int a_i=0; a_i < n; a_i++){
            if(a[a_i]<=min[0]){
            	if(min[0]!=Integer.MAX_VALUE){
            		min[1] = min[0];
            	}
            	min[0] = a[a_i];
            }else if (a[a_i]<min[1]){
            	min[1] = a[a_i];
            }
            
            if(a[a_i]>=max[0]){
            	if(max[0]!=Integer.MIN_VALUE){
            		max[1] = max[0];
            	}
            	max[0] = a[a_i];
            }else if (a[a_i]>max[1]){
            	max[1] = a[a_i];
            }
        }
//        System.out.println(max[0]+" "+max[1]);
//        System.out.println(min[0]+" "+min[1]);
        int first = Integer.MIN_VALUE;
        if(min[1]!=Integer.MAX_VALUE){
        	first = max[0] - min[1];
        }
        int second = Integer.MIN_VALUE;
        if(max[1]!=Integer.MIN_VALUE){
        	second = max[1] - min[0];
        }
        
        int ret = first<second ? first:second;
        if(ret == Integer.MIN_VALUE){
        	ret = max[0] - min[0];
        }
        System.out.println(ret);
    }
}
