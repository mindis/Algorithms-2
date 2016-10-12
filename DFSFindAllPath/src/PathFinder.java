import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class PathFinder {
    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "input_1.txt";
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
    	/*Your code goes here*/
    	String start = null;
    	String end = null;
    	Map<String, List<String>> map = new HashMap<String, List<String>>();
    	for(String line:lines){
    		if(start == null){
    			String[] str = line.split(" ");
    			start = str[0];
    			end = str[1];
    			continue;
    		}
    		String[] strSplit = line.split(" : ");
    		List<String> list = new ArrayList<>();
			map.put(strSplit[0], list);
    		for(String s:strSplit[1].split(" ")){
    			list.add(s);
    		}
    	}
        List<String> paths = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        set.add(start);
        dfs(paths, map, end, new StringBuilder(start), set);
    	return paths;
    }
    
    private static void dfs(List<String> paths, Map<String, List<String>> map, String end, StringBuilder path, Set<String> set){
    	//get current node
    	String cur = String.valueOf(path.charAt(path.length() - 1));
    	
    	if(cur.equals(end)){
    		paths.add(new String(path.toString()));
    		return;
    	}
    	
    	//reach leaf node of graph, prevent map.get(cur) returns null;
    	if(!map.containsKey(cur)) return;
    	
    	for(String neighbor:map.get(cur)){
    		//prevent A->(B->C->D->B) happens;(back-edge prevention)
    		if(set.contains(neighbor)) return;
    		path.append(neighbor);set.add(neighbor);
    		dfs(paths,map,end,path,set);
    		path.deleteCharAt(path.length()-1);set.remove(neighbor);
    	}
    	return;
    }

}