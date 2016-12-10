import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution7 {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	Scanner scan = new Scanner(System.in);
    	int p = scan.nextInt();
    	int r = scan.nextInt();
    	int c = scan.nextInt();
    	int n = scan.nextInt();
    	int m = scan.nextInt();
    	int[][] matrix = new int[r][c];
    	for(int i = 0;i<n;i++){
    		int x1 = scan.nextInt();
    		int y1 = scan.nextInt();
    		int x2 = scan.nextInt();
    		int y2 = scan.nextInt();
    		for(int j=x1;j<=x2;j++){
    			for(int k=y1;k<=y2;k++){
    				matrix[j][k] ++;
    			}
    		}
    	}
		for(int i=0;i<r;i++){
			for(int j=0;j<=c;j++){
				if(matrix[i][j]>=m){
					matrix[i][j] = 1;
				}else{
					matrix[i][j] = 0;
				}
			}
		}
    }
    
}