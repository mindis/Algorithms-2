/**
379. Design Phone Directory   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 5154
Total Submissions: 17967
Difficulty: Medium
Contributors: Admin
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
*/


public class PhoneDirectory {

    private boolean[] nums;
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        nums = new boolean[maxNumbers];
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        for(int i = 0; i< nums.length;i++){
            if(check(i)){
                nums[i] = true;
                return i;
            }
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !nums[number];
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        nums[number] = false;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
