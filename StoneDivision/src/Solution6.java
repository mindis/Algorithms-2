import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution6 {

	/*
	 * 1 10 11
	 * 
	 * Winning 1-10  wSpan = r
	 * Losing 11
	 * Winning 12-21
	 * Losing 22
	 * 
       
       
       7 20 15
        
       Winning position 1-20
        
	   Losing  21-27
       Winning position 28-47
       Losing  48-54
       
10
1 10 11
7 20 15
7 20 6
7 20 1
7 20 26
7 20 27
7 20 28
1 10 9
7 20 8
7 20 20
       
	 */
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
    	int n = scan.nextInt();
    	for(int i =0 ;i<n;i++){
    		int l = scan.nextInt();
    		int r = scan.nextInt();
    		int k = scan.nextInt();
    		if(k<=r){
    			System.out.println("Alice");
    		}else{
    			int remain = (k-r)%(r+l);
    			if(remain>=1 && remain <= l)
    				System.out.println("Bob");
    			else
    				System.out.println("Alice");
    		}
    	}
    }
}