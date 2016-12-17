/**
312. Burst Balloons   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 19247
Total Submissions: 46648
Difficulty: Hard
Contributors: Admin
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
*/

/*
This solution is inspired by The_Duck with his C++ solution

https://leetcode.com/discuss/72186/c-dynamic-programming-o-n-3-1100-ms-with-comments

However, I would give an explanation based on my own understanding.

The basic idea is that we can find the maximal coins of a subrange by trying every possible final burst within that range. Final burst means that we should burst balloon i as the very last one and burst all the other balloons in whatever order. dp[i][j] means the maximal coins for range [i...j]. In this case, our final answer is dp[0][nums.length - 1].

When finding the maximal coins within a range [start...end], since balloon i is the last one to burst, we know that in previous steps we have already got maximal coins of range[start .. i - 1] and range[i + 1 .. start], and the last step is to burst ballon i and get the product of balloon to the left of i, balloon i, and ballon to the right of i. In this case, balloon to the left/right of i is balloon start - 1 and balloon end + 1. Why? Why not choosing other balloon in range [0...start - 1] and [end + 1...length] because the maximal coins may need other balloon as final burst?

In my opinion, it's because this subrange will only be used by a larger range when it's trying for every possible final burst. It will be like [larger start.....start - 1, [start .. end] end + 1/ larger end], when final burst is at index start - 1, the result of this sub range will be used, and at this moment, start - 1 will be there because it's the final burst and end + 1 will also be there because is out of range. Then we can guarantee start - 1 and end + 1 will be there as adjacent balloons of balloon i for coins. That's the answer for the question in previous paragraph.

*/

public class Solution {
public int maxCoins(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    
    int[][] dp = new int[nums.length][nums.length];
    for (int len = 1; len <= nums.length; len++) {
        for (int start = 0; start <= nums.length - len; start++) {
            int end = start + len - 1;
            for (int i = start; i <= end; i++) {
                int coins = nums[i] * getValue(nums, start - 1) * getValue(nums, end + 1);
                coins += i != start ? dp[start][i - 1] : 0; // If not first one, we can add subrange on its left.
                coins += i != end ? dp[i + 1][end] : 0; // If not last one, we can add subrange on its right
                dp[start][end] = Math.max(dp[start][end], coins);
            }
        }
    }
    return dp[0][nums.length - 1];
}

private int getValue(int[] nums, int i) { // Deal with num[-1] and num[num.length]
    if (i < 0 || i >= nums.length) {
        return 1;
    }
    return nums[i];
}
}
