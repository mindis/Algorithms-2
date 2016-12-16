import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class PathFinder {
	public static String START = null;
	public static String END = null;
    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "input_4.txt";
        if (args.length > 0) {
        	filename = args[0];
        }
        
        List<String> answer = parseFile(filename);
        System.out.println(answer);
    }
    
    static List<String> parseFile(String filename)
    		throws FileNotFoundException, IOException {
    	/*
    	 *  Don't modify this function
    	 */
        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
        	allLines.add(line);
        }
        input.close();

        return parseLines(allLines);    	
    }
    
    static List<String> parseLines(List<String> lines) {
    	/*
    	 * 
    	 *  Your code goes here
    	 *  
    	 *  
    	 */
    	
    	Graph graph = new Graph();
    	for(String line:lines){
    		if(START == null){
    			String str[] = line.split(" ");
    			START = str[0];
    			END = str[1];
    			continue;
    		}
    		String strSplit[] = line.split(" : ");
    		for(String s:strSplit[1].split(" ")){
    			graph.addEdge(strSplit[0], s);
    		}
    	}
        

        LinkedList<String> visited = new LinkedList();
        visited.add(START);
        List<String> ret = new ArrayList<String>();
        depthFirst(graph, visited, ret);
    	
    	return ret;
    }
    
    private static void depthFirst(Graph graph, LinkedList<String> visited, List<String> ret) {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
        // examine adjacent nodes
        for (String node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(END)) {
                visited.add(node);
                String retString = printPath(visited);
                ret.add(retString);
                visited.removeLast();
                break;
            }
        }
        for (String node : nodes) {
            if (visited.contains(node) || node.equals(END)) {
                continue;
            }
            visited.addLast(node);
            depthFirst(graph, visited, ret);
            visited.removeLast();
        }
    }

    private static String printPath(LinkedList<String> visited) {
    	String ret ="";
        for (String node : visited) {
            ret+=node;
        }
        return ret;
    }
}