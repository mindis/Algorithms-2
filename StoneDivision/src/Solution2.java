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