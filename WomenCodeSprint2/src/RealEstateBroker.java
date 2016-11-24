/*
https://www.hackerrank.com/contests/womens-codesprint-2/challenges/real-estate-broker
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RealEstateBroker {
	
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
    	
    	
    }
}
