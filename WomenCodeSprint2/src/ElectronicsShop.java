/*
https://www.hackerrank.com/contests/womens-codesprint-2/challenges/electronics-shop
*/
import java.util.Arrays;
import java.util.Scanner;

public class ElectronicsShop {
	
	static int s;
	static int maxSum = Integer.MIN_VALUE;
	/**
	 * Tests:
	 * 10 2 3
	 * 3 1
	 * 5 2 8
	 * 
	 * 5 1 1
	 * 4
     * 5
	 */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        s = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] keyboards = new int[n];
        for(int keyboards_i=0; keyboards_i < n; keyboards_i++){
            keyboards[keyboards_i] = in.nextInt();
        }
        int[] pendrives = new int[m];
        for(int pendrives_i=0; pendrives_i < m; pendrives_i++){
            pendrives[pendrives_i] = in.nextInt();
        }
        /**
         * find n + m that is most near s, if no such value return -1;
         * if n < m
         * find all s-n, then binary search s-n in m, find largest element smaller than s-n,  log m  * n
         * if m < n vice versa
         * find all s-m, then binary search s-m in n, find largest element smaller than s-m, log n * m
         */
        if(n<m){
        	System.out.println(searchMaxSum(keyboards, pendrives));
        }else{
        	System.out.println(searchMaxSum(pendrives, keyboards));
        }

    }
    
    public static int searchMaxSum(int[] loopArr, int[] targetArr){
    	Arrays.sort(targetArr);
        for(int i = 0; i < loopArr.length; i++){
        	int retIndex = binarySearch(targetArr,s - loopArr[i]);
        	if(retIndex != -1){
	        	int curSum = targetArr[retIndex] + loopArr[i];
	        	if(maxSum < curSum){
	        		maxSum = curSum;
	        	}
        	}
        }
        if(maxSum == Integer.MIN_VALUE){
        	return -1;
        }else{
        	return maxSum;
        }
    }
    
    /**
     * return index of the largest element <= target
     * @param nums
     * @param target
     * @return
     * Tests:
     * Arrays.sort(pendrives);
     * System.out.println(binarySearch(pendrives, 9));
     */
    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2; // avoid overflow when (end + start)
            if (target < nums[mid]) {
                end = mid;
            } else if (target > nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        if(nums[end]<target){
        	return end;
        }
        if(nums[start]<target){
        	return start;
        }
        return -1;
    }
    
}
