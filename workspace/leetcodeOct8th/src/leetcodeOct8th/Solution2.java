package leetcodeOct8th;

import java.util.Arrays;

public class Solution2 {
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if (sum%2!= 0) return false;
        return isSubSum (nums, nums.length, sum/2);
    }
    
    static boolean isSubSum (int nums[], int n, int sum)
    {
        if (sum == 0)return true;
        if (n == 0 && sum != 0)return false;
        if (nums[n-1] > sum) return isSubSum (nums, n-1, sum);
        return isSubSum (nums, n-1, sum-nums[n-1])||isSubSum (nums, n-1, sum);
    }
    public static void main(String[] args){
    	//int[] arr = {1, 2, 3, 5};
    	int arr[] = {3, 1, 5, 9, 12};
    	System.out.println(canPartition(arr));
    }
}
