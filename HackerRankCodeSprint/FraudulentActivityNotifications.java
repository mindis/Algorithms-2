import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        String[] line0= scan.nextLine().split(" ");
        int k = Integer.parseInt(line0[0]);
        int d = Integer.parseInt(line0[1]);
        boolean even = d%2==0?true:false;
        if(k==0 || d == 0 || d>=k) System.out.println(0);
        int count = 0;
        String[] line1 = scan.nextLine().split(" ");
        int[] nums = new int[k];
        for(int i=0;i<line1.length;i++){
            nums[i] = Integer.parseInt(line1[i]);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for(int i = 1;i<k;i++){
            // add element into one of the heap
            if (maxHeap.isEmpty() || nums[i-1] < maxHeap.peek()) {
                maxHeap.offer(nums[i-1]);
            } else {
                minHeap.offer(nums[i-1]);
            }
            // remove element outside of window
            if (i >= d+1) {
                if (nums[i - d-1] <= maxHeap.peek()) {
                    maxHeap.remove(nums[i - d-1]);
                } else {
                    minHeap.remove(nums[i - d-1]);
                }
            }
          // balancing two heaps, make sure maxHeap contains one more element if k is odd.
          while (minHeap.size() >= maxHeap.size() + 1) {  
            maxHeap.offer(minHeap.poll());
          }
          while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
          }
          // calculate result
          if (i >= d) {
            double median = 0;
            if(even){
                median = (maxHeap.peek()+minHeap.peek())*1.0/2;
            } else{
                median = maxHeap.peek();
            }
            //System.out.println(median +","+nums[i]);
            if(Double.compare(nums[i]*1.0,2*median)>=0){
                count++;
            }
          }
    }
    System.out.println(count);
    }
}
