package leetcodeOct8th;

public class Solution1 {
    public static String addStrings(String num1, String num2) {
        if(num1 == null || num1.length()==0){
        	return num2;
        }
        if(num2 == null || num2.length() == 0){
        	return num1;
        }
        String small = null;
        String big = null;
        if(num1.length() < num2.length()){
        	small = num1;
        	big = num2;
        } else {
        	small = num2;
        	big = num1;
        }
        String ret = "";
        int carry = 0;
        for(int i=small.length()-1, j=big.length()-1;i>=0;i--,j--){
        	int sum = (int)(small.charAt(i) - '0') + (int)(big.charAt(j)-'0') + carry;
        	carry = sum / 10;
        	sum = sum % 10;
        	ret = sum + ret;
        }
        
        if(big.length() == small.length()){
	        if(carry != 0){
	        	ret = carry + ret;
	        }
	        return ret;
        }
        
        for(int i=big.length()-small.length()-1;i>=0;i--){
        	int sum = (int)(big.charAt(i)-'0') + carry;
        	carry = sum / 10;
        	sum = sum % 10;
        	ret = sum + ret;
        }
   
        if(carry != 0){
        	ret = carry + ret;
        }
        return ret;
    }
    public static void main(String[] args){
//    	String num1 = "239"; String num2 = "1499";
    	String num1 = "1499"; String num2 = "239";
//    	num1 = ""; num2 = "1000";
    	System.out.println(addStrings(num1, num2));
    }
}
