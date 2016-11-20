import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 
6
1 2 3 4 5 6
[6]
[5, 6]
[4, 5, 6]
[3, 4, 5, 6]
[2, 3, 4, 5, 6]
[1, 2, 3, 4, 5, 6]
9223372036854775807
It's guaranteed that a valid answer exists. thus no worry on above case

5
20 7 8 2 5

3
5 10 3
 *
 */
public class MinimumLoss {
	
	static long minDiff = Long.MAX_VALUE;
	
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long[] arr = new long[n];
        for(int i = 0; i < n; i++){
            arr[i] = scan.nextLong();
        }
        minLoss(n,arr);
        System.out.println(minDiff);
    }
    
    public static void minLoss(int n, long[] arr){
    	/**
    	 * build a sorted array from behind.
    	 */
    	List<Long> list = new ArrayList<Long>();
    	int len = arr.length;
    	for(int i = 0; i < arr.length ; i++){
    		binarySearch(list, arr[len-i-1]);
    		//System.out.println(list);
    	}
    }
    
    public static void binarySearch(List<Long> list, long target) {
        if (list == null || list.size() == 0) {
        	list.add(target);
        	return;
        }

        int start = 0;
        int end = list.size() - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2; // avoid overflow when (end + start)
            if (target < list.get(mid)) {
                end = mid;
            } else if (target > list.get(mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (list.get(start) == target) {
        	minDiff = 0;
        	list.add(start, target);
        	return;
        }
        if (list.get(end) == target) {
        	minDiff = 0;
        	list.add(end, target);
        	return;
        }
        if(list.get(end)<target){
        	long curDiff = target - list.get(end);
        	if(curDiff < minDiff) minDiff = curDiff;
        	list.add(end+1,target);
        	return;
        }
        if(list.get(start)<target){
        	long curDiff = target - list.get(start);
        	if(curDiff < minDiff) minDiff = curDiff;
        	list.add(start+1,target);
        	return;
        }
        list.add(0,target);
        return;
    }
}