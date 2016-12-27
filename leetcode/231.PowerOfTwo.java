/*231. Power of Two   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 113645
Total Submissions: 290446
Difficulty: Easy
Contributors: Admin
Given an integer, write a function to determine if it is a power of two.
*/

/*
Using n&(n-1) trick

177
D dong.wang.1694 
Reputation:  1,363
Power of 2 means only one bit of n is '1', so use the trick n&(n-1)==0 to judge whether that is the case
*/


class Solution {
public:
    bool isPowerOfTwo(int n) {
        if(n<=0) return false;
        return !(n&(n-1));
    }
};
