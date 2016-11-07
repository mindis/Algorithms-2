/*
You've intercepted an encoded spy message! The message originated as a single line of one or more space-separated words, but it was encoded into an  matrix as a clockwise spiral starting in the lower left-hand corner. For example, the diagram below shows the decoding process for an encoded message:

spiral-message.png

The message is decoded spirally starting from the lower left-hand corner of the matrix and moving in the clockwise direction (i.e., up, right, down, left, up, right, etc.). From the starting position, you must clockwise-traverse the matrix, scanning characters and switching to the next clockwise direction each time you reach a boundary (i.e., an already-scanned character or the end of the matrix). Continue scanning characters in this manner until you've scanned all the matrix's characters into a single decoded string. The word separator for the decoded string is the hash mark (#).

Given , , and an encoded message, decode the message and print the number of words in the decoded message.

Input Format

The first line contains two space-separated positive integers describing the respective values of  and . 
Each line  of the  subsequent lines contains a string of  characters describing row  of the encoded message.

Constraints

Each word consists of lowercase English alphabetic characters (a to z).
The encoded message consists of words and hash marks (#). Each hash mark denotes a single space.
Output Format

Print an integer denoting the number of decoded words.

Sample Input

3 5
a##ar
a#aa#
xxwsr
Sample Output

4
Explanation

The diagram at the top of the challenge demonstrates the decoding process for the given Sample Input. The decoded message is xaa##ar#rswx#aa. Because hash marks denote spaces, we can break the message into four words: xaa, ar, rswx, and aa. Thus, we print  as our answer.

https://www.hackerrank.com/contests/ncr-codesprint/challenges/spiral-message*/
import java.io.*;
import java.util.*;

public class Solution2 {

    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
        String in = scan.nextLine();
        String[] inArr = in.split(" ");
        int n = Integer.parseInt(inArr[0]);
		int m = Integer.parseInt(inArr[1]);
		char[][] matrix = new char[n][m];
		for(int i = 0; i < n; i++){
			String temp = scan.nextLine();
			for(int j = 0; j < m; j++){
				matrix[i][j] = temp.charAt(j);
			}
		}
		helper(matrix);
	}
    public static void helper(char[][] matrix) {
        if(matrix == null || matrix.length == 0) 
            System.out.print("");
        
        int n = matrix.length; //row
        int m = matrix[0].length; //column
        int count = 0;
        int totalC = 0;
        int curC = 0;
        while(count * 2 < n && count * 2 < m){
           
           for(int i = n-count-1; i>= count; i--){
        	//   System.out.print(matrix[i][count]);
                if(matrix[i][count]!='#'){
        		    curC++;
	        	}
	        	else{
	        		if(curC!=0)
            		  totalC++;
	        		curC = 0;
                    
	        	}
            }
           //System.out.println();
            
           
            for(int i = count+1; i < m-count; i++){
            	//System.out.print(matrix[count][i]);
            	if(matrix[count][i]!='#'){
            		curC++;
            	}
            	else{
                    if(curC!=0)
            		  totalC++;
            		curC = 0;
            	}
            }
            //System.out.println();
            
            for(int i = count+1; i< n-count; i++){
            	//System.out.print(matrix[i][m-count-1]);
                if(matrix[i][m-count-1]!='#'){
            		curC++;
            	}
            	else{
            		if(curC!=0)
            		  totalC++;
            		curC = 0;
            	}
            }
            //System.out.println();
            
            if(n - 2 * count == 1 || m - 2 * count == 1)  // if only one row /col remains
                break;
                
            for(int i = m-count-2; i>count; i--){
            	// System.out.print(matrix[n-count-1][i]);
                if(matrix[n-count-1][i]!='#'){
            		curC++;
            	}
            	else{
            		if(curC!=0)
            		  totalC++;
            		curC = 0;
            	}
            }
            //System.out.println();
            
            count++;
 
        }
        if(curC!=0){
            totalC++;
        }
        System.out.println(totalC);
    }
}
