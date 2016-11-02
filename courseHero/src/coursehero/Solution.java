package coursehero;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
    
/*
 * Complete the function below.
 */


    static int tasks(int n, int[] a, int[] b) {

    	for(int i = 0; i<a.length;i++){
    		a[i] --;b[i]--;
    	}
        List<Set<Integer>> adjLists = new ArrayList<Set<Integer>>();  
        for (int i = 0; i < n; i++) {  
            adjLists.add(new HashSet<Integer>());  
        }  
          
        for (int i = 0; i < a.length; i++) {  
            adjLists.get(b[i]).add(a[i]);  
        }  
          
        int[] indegrees = new int[n];  
        for (int i = 0; i < n; i++) {  
            for (int x : adjLists.get(i)) {  
                indegrees[x]++;  
            }  
        }  
          
        Queue<Integer> queue = new LinkedList<Integer>();  
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { 
        	if(indegrees[i]<min) min = indegrees[i];
         
        }
        
        
        for (int i = 0; i < n; i++) {
        	   indegrees[i]-=min;
        	   if (indegrees[i] == 0) {  
                   queue.offer(i); 
               }  
        }
        
     
  
    
        int[] res = new int[n];  
        int count = 0;  
        while (!queue.isEmpty()) {  
            int cur = queue.poll();  
            for (int x : adjLists.get(cur)) {  
                indegrees[x]--;  
                if (indegrees[x] == 0) {  
                    queue.offer(x);  
                }  
            }  
            res[count++] = cur;  
        }  
        return count-min;
    }


 public static void main(String[] args) throws IOException{
	 int n = 2;
//	 int[] a = new int[]{2,1,2};
//	 int[] b = new int[]{2,2,1};
//	 int[] a = new int[]{2,1,2};
//	 int[] b = new int[]{1,2,2};
//	 int[] a = new int[]{2,1};
//	 int[] b = new int[]{1,2};
	 int[] a ={1};
	 int[] b ={2};
	 //output 1;
	 System.out.println(tasks(n, a, b));
//        Scanner in = new Scanner(System.in);
//        //final String fileName = System.getenv("OUTPUT_PATH");
//        BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/chaoran/output1.txt"));
//
//
//
//       // BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
//        int res;
//        int _n;
//        _n = Integer.parseInt(in.nextLine().trim());
//        
//        
//        int _a_size = 0;
//        _a_size = Integer.parseInt(in.nextLine().trim());
//        int[] _a = new int[_a_size];
//        int _a_item;
//        for(int _a_i = 0; _a_i < _a_size; _a_i++) {
//            _a_item = Integer.parseInt(in.nextLine().trim());
//            _a[_a_i] = _a_item;
//        }
//        
//        
//        int _b_size = 0;
//        _b_size = Integer.parseInt(in.nextLine().trim());
//        int[] _b = new int[_b_size];
//        int _b_item;
//        for(int _b_i = 0; _b_i < _b_size; _b_i++) {
//            _b_item = Integer.parseInt(in.nextLine().trim());
//            _b[_b_i] = _b_item;
//        }
//        
//        res = tasks(_n, _a, _b);
//        bw.write(String.valueOf(res));
//        bw.newLine();
//        
//        bw.close();
    }
}