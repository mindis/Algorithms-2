package solution2;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution3 {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	Scanner scan = new Scanner(System.in);
    	int n = scan.nextInt();
    	int m = scan.nextInt();
    	int d = scan.nextInt();
    	Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();
    	for(int i = 0; i<m;i++){
    		int u = scan.nextInt();
    		int v = scan.nextInt();
    		int mark = scan.nextInt();
    		if(map.containsKey(u)){
    			map.get(u).add(new int[]{v,mark});
    		}else{
    			List<int[]> list = new ArrayList<int[]>(); 
    			list.add(new int[]{v,mark});
    			map.put(u,list);
    		}
    		
    		if(map.containsKey(v)){
    			map.get(v).add(new int[]{u,mark});
    		}else{
    			List<int[]> list = new ArrayList<int[]>(); 
    			list.add(new int[]{u,mark});
    			map.put(v,list);
    		}
    	}
    	Set<String> set = new HashSet<String>();
    	List<Integer> path = new ArrayList<Integer>();
    	dfs(map, path, set, d);
    	System.out.println(set.size());
    }
//    private static void dfs(Map<Integer, List<int[]>> map,List<Integer> path, Set<String> set, int d){
//    	if(path.size()==d){
//    		StringBuilder strbul  = new StringBuilder();
//    	     Iterator<Integer> iter = path.iterator();
//    	     while(iter.hasNext())
//    	     {
//    	         strbul.append(iter.next());
//    	        if(iter.hasNext()){
//    	         strbul.append(",");
//    	        }
//    	     }
//    	  String temp = strbul.toString();
//    	  if(set.contains(temp)) 
//    	}
//    }
}

