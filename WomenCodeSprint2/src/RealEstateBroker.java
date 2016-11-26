/*
https://www.hackerrank.com/contests/womens-codesprint-2/challenges/real-estate-broker
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class RealEstateBroker {
	public static final int sourceVal = 0;
	public static final int sinkVal   = 2000000001;
	public static final int houseVal  = 1000000001;
	public static final int numVal 	  = 2000000002;
	static class Client{
		int a;
		int p;
		int index;
		public Client(int a, int p, int index){
			this.a = a;
			this.p = p;
			this.index = index;
		}
	}
	
	static class House{
		int x;
		int y;
		int index;
		public House(int x, int y, int index){
			this.x = x;
			this.y = y;
			this.index = index;
		}
	}
	
//    private static Comparator<House> houseAreaComparator = new Comparator<House>() {
//        public int compare(House left, House right) {
//        	return (left.x - right.x);
//        }
//    };
//    
//    private static Comparator<House> housePriceComparator = new Comparator<House>() {
//        public int compare(House left, House right) {
//        	return (left.y - right.y);
//        }
//    };
    
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	String[] nm = scan.nextLine().split(" ");
    	
    	int n = Integer.parseInt(nm[0]);
    	int m = Integer.parseInt(nm[1]);
    	
    	List<Client> cList = new ArrayList<Client>();
    	List<House> hList = new ArrayList<House>();
    	
    	for(int i = 0; i < n; i++){
    		String[] ap = scan.nextLine().split(" ");
    		cList.add(new Client(Integer.parseInt(ap[0]),Integer.parseInt(ap[1]), i));
    	}
    	for(int i = 0; i < m; i++){
    		String[] xy = scan.nextLine().split(" ");
    		hList.add(new House(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), i));
    	}
    	
    	/**
    	 * keep two HashMap, 
    	 * 1.one HashMap with Client -> HouseList
    	 * 2.another HashMap with House -> ClientList
    	 * (key, value) are index pointers
    	 */
    	
    	/**
    	 * 1.sort house by area
    	 * 2.sort house by price
    	 * 
    	 * for each client:
    	 * 1. binary search area to get a set of house
    	 * 2. binary search price to get a set of house
    	 * get their overlaps to get the houseList for a particular client
    	 */
    	
    	/**
    	 * sort house by area
    	 */
//    	List<House> hListByArea = new ArrayList<House>(hList);
//    	List<House> hListByPrice = new ArrayList<House>(hList);
//    	Collections.sort(hListByArea, houseAreaComparator);
//    	Collections.sort(hListByPrice, housePriceComparator);
    	
    	
    	/**
    	 * Use Ford–Fulkerson algorithm
    	 * https://www.topcoder.com/community/data-science/data-science-tutorials/maximum-flow-section-1/
    	 * 
    	 * 
  int bfs() 
  queue Q
  push source to Q
  mark source as visited
  keep an array from with the semnification: from[x] is the 
previous vertex visited in the shortest path from the source to x;
initialize from with -1 (or any other sentinel value) 
  while Q is not empty
    where = pop from Q 
    for each vertex next adjacent to where
      if next is not visited and capacity[where][next] > 0
        push next to Q
        mark next as visited
        from[next] = where
        if next = sink
          exit while loop
    end for
  end while
  // we compute the path capacity
  where = sink, path_cap = infinity
  while from[where] > -1
    prev = from[where] // the previous vertex 
    path_cap = min(path_cap, capacity[prev][where])
    where = prev
  end while
  // we update the residual network; if no path is found the while 
loop will not be entered
  where = sink
  while from[where] > -1
    prev = from[where]
    capacity[prev][where] -= path_capacity
        capacity[where][prev] += path_capacity
    where = prev
  end while
  // if no path is found, path_cap is infinity
  if path_cap = infinity
    return 0
  else return path_cap
    	 */
    	
//    Queue<> = new LinkedList<>();
     /**
      * 0 for source,1-1000000000,1000000001- 2000000000, 2000000001 for sink
      */
    	
    	//making a flow graph
    	Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    	
    	int[][] capacity = new int[numVal][numVal];//4 *10^18/1024/1024/1024 = 3725290298.46 GB out of memory
    	
    	//clients connecting to source 
    	for(int i = 0; i < n; i++){
    		Client curC = cList.get(i);
    		List<Integer> l = new ArrayList<Integer>();
			l.add(i);
    		map.put(sourceVal, l);
    		capacity[sourceVal][i] = 1;
    	}
    	//houses connecting to sink
    	for(int i = 0; i < m; i++){
    		House curH = hList.get(i);
    		List<Integer> l = new ArrayList<Integer>();
			l.add(i+houseVal);
    		map.put(sinkVal, l);
    		capacity[sinkVal][i+houseVal] = 1;
    	}
    	//clients connecting to houses
    	for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		Client curC = cList.get(i);
        		House curH = hList.get(j);
        		if(curC.a <= curH.x && curC.p >= curH.y){
        			if(map.containsKey(i)){
        				List<Integer> l = map.get(i);
        				l.add(j+houseVal);
        				capacity[i][j+houseVal] = 1;
        			}else{
        				List<Integer> l = new ArrayList<Integer>();
        				l.add(j+houseVal);
        				map.put(i, l);
        				capacity[i][j+houseVal] = 1;
        			}
        		}
        	}
    	}
    	
    	/**
    	 *   int bfs() 
		  queue Q
		  push source to Q
		  mark source as visited
		  keep an array from with the semnification: from[x] is the 
		previous vertex visited in the shortest path from the source to x;
		initialize from with -1 (or any other sentinel value) 
		  while Q is not empty
		    where = pop from Q 
		    for each vertex next adjacent to where
		      if next is not visited and capacity[where][next] > 0
		        push next to Q
		        mark next as visited
		        from[next] = where
		        if next = sink
		          exit while loop
		    end for
		  end while
    	 */
    	Queue<Integer> queue = new LinkedList<Integer>();
    	queue.add(sourceVal);
    	int[] from = new int[numVal];
    	boolean[] visited = new boolean[numVal];
    	
    	visited[0] = true;
    	Arrays.fill(from, -1);
    	boolean out = false;
    	while(!queue.isEmpty()){
    		int where = queue.poll();
    		List<Integer> adj = map.get(where);
    		for(int next: adj){
    			if(!visited[next] && capacity[where][next]>0){
    				queue.add(next);
    				visited[next] = true;
    				from[next] = where;
    				if(next == sinkVal){
    					out = true;
    					break;
    				}
    			}
    		}
    		if(out) break;
    	}
    	
    	/**
    	 * // we compute the path capacity
  where = sink, path_cap = infinity
  while from[where] > -1
    prev = from[where] // the previous vertex 
    path_cap = min(path_cap, capacity[prev][where])
    where = prev
  end while
  // we update the residual network; if no path is found the while 
loop will not be entered
  where = sink
  while from[where] > -1
    prev = from[where]
    capacity[prev][where] -= path_capacity
        capacity[where][prev] += path_capacity
    where = prev
  end while
  // if no path is found, path_cap is infinity
  if path_cap = infinity
    return 0
  else return path_cap
    	 */
    	
    	// we compute the path capacity
    	int where = sinkVal, path_cap = Integer.MAX_VALUE;
    	while(from[where]> -1){
    		int prev = from [where];
    		path_cap = Math.min(path_cap, capacity[prev][where]);
    		where = prev;
    	}
    	// we update the residual network; if no path is found the while loop will not be entered
    	where = sinkVal;
    	while(from[where]>-1){
    		int prev = from[where];
    		capacity[prev][where] -= path_cap;
    		capacity[where][prev] += path_cap;
    		where = prev;
    	}
    	//if no path is found, path_cap is infinity
    	if(path_cap == Integer.MAX_VALUE)
    		System.out.println(0);
    	else System.out.println(path_cap);
    	
    }
}
