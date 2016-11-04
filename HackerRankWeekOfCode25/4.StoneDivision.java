/*Your submission will run against only preliminary test cases. Full test cases will run at the end of the day.
Consider the following game:

There are two players, First and Second, sitting in front of a pile of  stones. First always plays first.
There is a set, , of  distinct integers defined as .
The players move in alternating turns. During each turn, a player chooses some  and splits one of the piles into exactly  smaller piles of equal size. If no  exists that will split one of the available piles into exactly  equal smaller piles, the player loses.
Both players always play optimally.
Given , , and the contents of , find and print the winner of the game. If First wins, print First; otherwise, print Second.

Input Format

The first line contains two space-separated integers describing the respective values of  (the size of the initial pile) and  (the size of the set). 
The second line contains  distinct space-separated integers describing the respective values of .

Constraints

Output Format

Print First if the First player wins the game; otherwise, print Second.

Sample Input

15 3
5 2 3
Sample Output

Second
Explanation

The initial pile has  stones, and . During First's initial turn, they have two options:

Split the initial pile into  equal piles, which forces them to lose after the following sequence of turns: 
stone-division.png
Split the initial pile into  equal piles, which forces them to lose after the following sequence of turns: 
stone-division-2.png
Because First never has any possible move that puts them on the path to winning, we print Second as our answer*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Solution {
	
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        /*15 3 
        * 5 2 3
        * 18
        * 3 2
        * 6 6 6 
        * 
        */
        //input
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        int len = scan.nextInt();
        long[] div = new long[len];
        for(int i=0;i<len;i++){
            div[i] = scan.nextLong();
        }
        
//        long n = 18;
//        int len = 2;
//        long[] div = new long[]{2,3};

        Map<Long, Map<Boolean, Boolean>> nTmap = new HashMap<Long, Map<Boolean, Boolean>>();
        //Group size map to frequency.
        Map<Long, Long> map = new ConcurrentHashMap<Long, Long>();
        map.put(n, (long) 1);
        
        //turn true for first player, turn false for second player
       if(buildDecisionTree(div, map, true, len, nTmap, n)){
    	   System.out.println("First");
       }else{
    	   System.out.println("Second");
       }
    }
    
    //build tree and evaluate winner all together, DFS
    public static boolean buildDecisionTree(long[] div, Map<Long, Long> map, boolean turn, int len, Map<Long, Map<Boolean, Boolean>> nTmap,long n){
//    	Iterator<Map.Entry<Long, Long>> it0 = map.entrySet().iterator();
//    	while(it0.hasNext()){
//    		
//    		Entry<Long, Long> entry = it0.next();
//    		
//    		long size = entry.getKey();
//    		long freq = entry.getValue();
//    		System.out.print("size"+size+"freq"+freq+";");
//    	}
//    	System.out.println();
    	//for each size group, try each divider
//    	Iterator<Map.Entry<Long, Long>> it = map.entrySet().iterator();
    	if(nTmap.containsKey(n)) {
    		if(nTmap.get(n).containsKey(turn)){
    			return nTmap.get(n).get(turn);
    		}
    	}
    	List<Long> keys = new ArrayList<Long>(map.keySet());
    	for(int j=0;j<keys.size();j++){
    		long size = keys.get(j);
    		long freq = map.get(size);
    		
    		if(nTmap.containsKey(size)){
    			if(nTmap.get(size).containsKey(turn)){
    				nTmap.get(size).get(turn);
    			}else{
    				continue;
    			}
    		}
    		
    		//try each divider
    		for(int i=0;i<len;i++){
    			//if can be divided update map.
    			if(size%div[i]==0){
    				
    				long nextSize = size/div[i];
    				
    				//update group previous group size
    				if(freq == 1){
    					map.remove(size);
    					//it.remove();
    				}else{
    					map.put(size, freq-1);
    				}
    				
    				//update group next group size
    				if(map.containsKey(nextSize)){
    					map.put(nextSize, map.get(nextSize) + div[i]);
    				}else{
    					map.put(nextSize, div[i]);
    				}

    				
    				//go to next state/child
    				boolean child = buildDecisionTree(div, map, !turn, len, nTmap, size);
    				//back tracking,always backtrack before returning
    				if(map.containsKey(nextSize)){
	    				if(map.get(nextSize) == div[i]){
	    					map.remove(nextSize);
	    				}else{
	    					map.put(nextSize, map.get(nextSize)-div[i]);
	    				}
    				}
    				
    				if(map.containsKey(size)){
    					map.put(size, freq);
    				}else{
    					map.put(size,(long)1);
    				}
    				
    				if(child == turn) {
    					if(nTmap.containsKey(n)){
    						nTmap.get(n).put(turn, turn);
    					}else{
    						Map<Boolean, Boolean> cur = new HashMap<Boolean, Boolean>();
    						cur.put(turn, turn);
    						nTmap.put(n,cur);
    					}
    					return turn;
    				}
    			}
    		}
    	}
    	//no child is matching turner, or map is undividable
    	//have to return opponent.
			if(nTmap.containsKey(n)){
				nTmap.get(n).put(turn, !turn);
			}else{
				Map<Boolean, Boolean> cur = new HashMap<Boolean, Boolean>();
				cur.put(turn, !turn);
				nTmap.put(n,cur);
			}
		return !turn;
    }

}
