import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * https://www.hackerrank.com/contests/university-codesprint/challenges/walking-the-approximate-longest-path
 */
public class WalkingtheLongestPathApproximationProblem {
	static Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
	static List<Integer> topList = new ArrayList<Integer>();
	public static void main(String[] args){
		//input
		Scanner scan = new Scanner(System.in);
		int nNodes = scan.nextInt();
		int nEdges = scan.nextInt();
		for(int i = 0; i < nEdges; i++){
			int start = scan.nextInt();
			int end = scan.nextInt();
			if(map.containsKey(start)){
				map.get(start).add(end);
			}else{
				List<Integer> list= new ArrayList<Integer>();
				list.add(end);
				map.put(start,list);
			}
		}
		List<Integer> paths = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>();
        

	}
	
	private static void topologicalSort(){
	}
	
    //search all path
	/*dist[v] < dist[u] + weight(u, v)) ………………………dist[v] = dist[u] + weight(u, v) */
    private static void dfs(List<Integer> paths,Set<Integer> set){
    }
	
}
