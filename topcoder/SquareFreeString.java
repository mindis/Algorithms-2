/*Problem Statement

We say that a string S is a square if it has the form TT, where T is some non-empty string. In other words, a square is a string that is a concatenation of two copies of the same non-empty string. For example, the strings "aa", "bbbb", and "beriberi" are squares.

A string is called square-free if none of its substrings is a square. For example, the string "abca" is square-free. (The substrings of this string are the strings "a", "b", "c", "a", "ab", "bc", "ca", "abc", "bca", and "abca". None of these strings is a square.)

You are given a s. Return "square-free" if s is square-free. Otherwise, return "not square-free". Note that the return value is case-sensitive.
Definition
Class:
SquareFreeString
Method:
isSquareFree
Parameters:
String
Returns:
String
Method signature:
String isSquareFree(String s)
(be sure your method is public)
Limits
Time limit (s):
2.000
Memory limit (MB):
256
Constraints
- s will consist only of lowercase English letters ('a'-'z').
- The length of s will be between 1 and 50, inclusive.
Examples
0)
"bobo"
Returns: "not square-free"
"bobo" = T + T, where T = "bo", so it is not square-free.
1)
"apple"
Returns: "not square-free"
Substring "pp" is a square.

 2)
"pen"
Returns: "square-free"
"pen" does not contain any substrings that are squares.
3)
"aydyamrbnauhftmphyrooyq"
Returns: "not square-free"
4)
"qwertyuiopasdfghjklzxcvbnm"
Returns: "square-free"
*/
public class SquareFreeString{
    public  String isSquareFree(String s){
        //aydyamrb
        //ay dy   am rb
        // <--     -->
        //ay dy a  am rb
        //0, 1
        char[] cArray = s.toCharArray();
        boolean ret = isSquareFree(cArray, 0, cArray.length-1);
        if(ret){
            return new String("square-free");
         }else{
            return new String("not square-free");
         }
    }
    boolean isSquareFree(char[] cArray, int start, int end ){
        //if even 
        //if ((end - start + 1) % 2 == 0){
        	if(start>=end) return true;
            int mid = start + (end -start)/2;
            boolean cur = true;
            if ((end - start + 1) % 2 == 1){cur = true;}
            else{
                boolean token = true;
                int  i = 0; int j = 0;
                for(i = 0, j = mid +1; i<= mid && j <=end; i++,j++){
                  if(cArray[i] != cArray[j]){token = false; break;}
                }
                if(token) cur = false;
            }
            if(cArray[mid] == cArray[mid+1]) cur = false;
        	boolean left = isSquareFree(cArray, start, mid);
        	boolean right = isSquareFree(cArray, mid+1, end);
			if( cur && left && right){
            	return true;
            } else{
            	return false;
            }
           
    }
}
