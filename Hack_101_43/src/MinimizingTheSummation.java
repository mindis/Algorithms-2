import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class MinimizingTheSummation {
	static Deque<Integer> queue = new LinkedList<Integer>();
	static int size = queue.size();
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        
        int sum = 0;
        int avg = 0;
        for(int a_i=0; a_i < k; a_i++){
            queue.push(a[a_i]);
            sum = a[a_i];
            avg = sum / k;
        }
        
        int diffSum = Integer.MAX_VALUE;
        int diffIndex = k-1;
        
        //n-1 -(n-k) +1 = k
        for(int i = k; i < n; i++){
        	
        	queue.push(a[i]);
        	sum -= queue.pollFirst();
        	avg = sum/k;
        	int curSum = 0;
        	for(int j: queue){
        		curSum += j-avg;
        	}
        	if(curSum < diffSum){
        		diffSum = curSum;
        		diffIndex = i;
        	}
        	System.out.println(curSum);
        }
        
        long ret = 0;
        for(int i = diffIndex; i>=diffIndex-k+1; i--){
        	for(int j = i; j>=diffIndex-k+1;j--){
        		ret+= 2*(a[i]-a[j]) * (a[i]-a[j]);
        	}
        }
        System.out.println(ret);
        
    }

	
}
