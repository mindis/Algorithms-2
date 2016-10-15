public class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{binarySearchStart(nums, target), binarySearchEnd(nums,target)};
    }
    
    public int binarySearchStart(int[] nums, int target){
        int start = 0; int end = nums.length -1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(target > nums[mid]) {
                start = mid;
            } else if(target < nums[mid]){
                end = mid;
            } else {
                end = mid;
            }
        }
        if(nums[start] == target) return start;
        if(nums[end] == target) return end;
        return -1;
    }
    public int binarySearchEnd(int[] nums, int target){
        int start = 0; int end = nums.length -1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(target > nums[mid]) {
                start = mid;
            } else if(target < nums[mid]){
                end = mid;
            } else {
                start = mid;
            }
        }
        if(nums[end] == target) return end;
        if(nums[start] == target) return start;
        return -1;
    }
}
