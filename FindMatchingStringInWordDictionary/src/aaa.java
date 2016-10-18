import java.util.ArrayList;
import java.util.List;

public class aaa {
		public static void main(String[] args){
			List<String> words = new ArrayList<String>();
			words.add(new String("abcd"));
			words.add(new String("bnrt"));
			words.add(new String("crmy"));
			words.add(new String("dtye"));
			System.out.println(validWordSquare(words));
		}
		
	    public static boolean validWordSquare(List<String> words) {
	        

	        if(words==null||words.size()==0) return true;
	        if(words.get(0)==null) return false;
	        int prevLen = words.get(0).length();
	        if(prevLen != words.size()) return false;
	        for(int i=0;i<words.size();i++){
	            if(words.get(i)==null) return false;
	            if(prevLen != words.get(i).length()) return false;
	        }        
	        for(int i=0;i<words.size();i++){
	            String temp = words.get(i);
	            for(int j=i;j<words.size();j++){
	                if(temp.charAt(j) != words.get(j).charAt(i)) return false;
	            }
	        }
	        return true;
	    }

}
