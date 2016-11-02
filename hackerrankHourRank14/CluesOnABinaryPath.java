/*
Clues on a Binary Path
locked
by YuryBandarchuk

    Problem
    Submissions
    Leaderboard
    Discussions
    Editorial

Logan and Veronica live in Neptune, which has houses and bidirectional roads connecting them. Each road has an assigned value, , where , and each house is numbered with a distinct integer from to .

Logan and Veronica are looking for clues and need to find the number of different paths of length from house number . Each path is characterized by a binary sequence of length , where each integer in the path is the value of for the edge in the path. Two paths are different if the binary sequences characterizing these paths are distinct. Note that they may need to visit the same house several times or use the same road several times to find all possible paths.

Given a map of Neptune, help Logan and Veronica find and print the number of different paths of length from house number to the other houses in Neptune.

Input Format

The first line contains three space-separated integers describing the respective values of (the number of houses), (the number of bidirectional roads), and (the distance they want to travel).
Each of the subsequent lines contains three space-separated integers describing the respective values of , , and that define a bidirectional road between houses and having assigned value .

Constraints

    There may be roads connecting house to itself.
    There may be more than one road between two houses.

Output Format

Print an integer denoting the total number of paths.

Sample Input

3 2 3
1 2 0
1 3 1

Sample Output

4

Explanation

There are four possible paths:

Thus, we print as our answer.

*/

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


