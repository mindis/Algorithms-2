import java.util.ArrayList;
import java.util.List;

public class iprestore {
	/*0,1,2,3*/
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        dfs(0, s, 0, result, new StringBuilder());
        return result;
    }
    
    public static void dfs(int level, String s, int pos, List<String> result, StringBuilder sBuilder){
        /* at level 3 we have 3 '.', append rest of string to sBuilder, if valid*/
        if(level == 3){
            int restLen = s.length() - pos;
            if(restLen > 0 && restLen < 4 && (s.charAt(pos) != '0' || (s.charAt(pos) == '0' && restLen == 1) )){
                String rest = s.substring(pos, s.length());
                if(Integer.parseInt(rest) < 256){
                    result.add(new String(sBuilder.append(rest).toString()));
                    /*note need to backtrack here; debugged for a long time!!*/
                    sBuilder.delete(sBuilder.length() - rest.length(), sBuilder.length());
                }
            }
            return;
        }

        for(int j = 1; j <= 3; j++){
            int nextPos = pos + j;
            if(nextPos <= s.length()){
                String tempSeg = s.substring(pos, nextPos);
                if((tempSeg.charAt(0) != '0' || (tempSeg.charAt(0) == '0' && j == 1)) && Integer.parseInt(tempSeg) < 256) {
                    sBuilder.append(tempSeg); sBuilder.append(".");
                    dfs(level + 1, s, nextPos, result, sBuilder);
                    sBuilder.delete(sBuilder.length()-tempSeg.length()-1, sBuilder.length());/*note need to backtrack here*/
                } 
            } 
        }
    
    }
    public static void main(String[] args){
    	String input = "010010";
    	System.out.println(restoreIpAddresses(input));
    }
    
}
