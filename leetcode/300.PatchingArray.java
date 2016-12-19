/*
show the algorithm with an example,

let nums=[1 2 5 6 20], n = 50.

Initial value: with 0 nums, we can only get 0 maximumly.

Then we need to get 1, since nums[0]=1, then we can get 1 using [1]. now the maximum number we can get is 1. (actually, we can get all number no greater than the maximum number)

number used [1], number added []
can achieve 1~1
Then we need to get 2 (maximum number +1). Since nums[1]=2, we can get 2. Now we can get all number between 1 ~ 3 (3=previous maximum value + the new number 2). and 3 is current maximum number we can get.

number used [1 2], number added []
can achieve 1~3
Then we need to get 4 (3+1). Since nums[2]=5>4; we need to add a new number to get 4. The optimal solution is to add 4 directly. In this case, we could achieve maximumly 7, using [1,2,4].

number used [1 2], number added [4]
can achieve 1~7
Then we need to get 8 (7+1). Since nums[2]=5<8, we can first try to use 5. Now the maximum number we can get is 7+5=12. Since 12>8, we successfully get 8.

number used [1 2 5], number added [4]
can achieve 1~12
Then we need to get 13 (12+1), Since nums[3]=6<13, we can first try to use 6. Now the maximum number we can get is 12+6=18. Since 18>13, we successfully get 13.

number used [1 2 5 6], number added [4]
can achieve 1~18
Then we need to get 19 (18+1), Since nums[4]=20>19, we need to add a new number to get 19. The optimal solution is to add 19 directly. In this case, we could achieve maximumly 37.

number used [1 2 5 6], number added [4 19]
can achieve 1~37
Then we need to get 38(37+1), Since nums[4]=20<38, we can first try to use 20. Now the maximum number we can get is 37+20=57. Since 57>38, we successfully get 38.

number used [1 2 5 6 20], number added [4 19]
can achieve 1~57
Since 57>n=50, we can all number no greater than 50.

The extra number we added are 4 and 19, so we return 2.

The code is given as follows
*/

class Solution {
public:
int minPatches(vector<int>& nums, int n) {
    int cnt=0,i=0;
    long long maxNum=0;
    while (maxNum<n){
       if (i<nums.size() && nums[i]<=maxNum+1)
            maxNum+=nums[i++];
       else{
            maxNum+=maxNum+1;cnt++;
       }
   }
   return cnt;
}
};
