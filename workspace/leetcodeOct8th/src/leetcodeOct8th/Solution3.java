package leetcodeOct8th;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
	  public static List<int[]> pacificAtlantic(int[][] matrix) {
		  	
	        List<int[]> ret = new ArrayList<int[]>();
	        if(matrix == null || matrix.length==0) return ret;
	        int[][] matR = new int[matrix.length][matrix[0].length];
	        int[][] matW = new int[matrix.length][matrix[0].length];
	        for(int i = 0;i<matrix.length;i++){
	        	matR[i][0]=1;
	            matW[i][matrix[0].length-1]=1;
	        }
	        for(int j = 0;j<matrix[0].length;j++){
	        	matR[0][j]=1;
	            matW[matrix.length-1][j]=1;
	        }
	        for(int i=2;i<=matrix.length+matrix[0].length-2;i++){
	        	for(int j=1;j<=i;j++){
	        		
	        		if(j<matrix.length&&i-j>=1&&i-j<matrix[0].length){
		        		System.out.println(j+" "+(i-j));
		        		if((matR[j-1][i-j]==1 && matrix[j][i-j]>=matrix[j-1][i-j])||
		        		    (matR[j][i-j-1]==1 && matrix[j][i-j]>=matrix[j][i-j-1])) 
		        			matR[j][i-j]= 1;
	        		}
	        	}
	        }	        
	        
	        for(int i=matrix.length+matrix[0].length-4;i>=0;i--){
	        	for(int j=matrix.length-2;j>=0;j--){
	        		if( j<=i&&i-j>=0&&i-j<=matrix[0].length-2){
		        		//System.out.println(j+" "+(i-j));
		        		if((matW[j+1][i-j]==1 && matrix[j][i-j]>=matrix[j+1][i-j])||
		        		    (matW[j][i-j+1]==1 && matrix[j][i-j]>=matrix[j][i-j+1])) 
		        			matW[j][i-j]= 1;
	        		}
	        	}
	        }	 
	        
//	        for(int i=0;i<matrix.length;i++){
//	        	for(int j=0;j<matrix[0].length;j++){
//	        		if(matR[i][j]==1 &&(matW[i][j]==1 ||){
//	        			ret.add(new int[]{i,j});
//	        		}
//	        	}
//	        }
	        
	        return ret;
	  }
	  public static void main(String[] args){
		  int[][] matrix = new int[][]{
				  {1,2,2,3,5},
				  {3,2,3,4,4},
				  {2,4,5,3,1},
				  {6,7,1,4,5},
				  {5,1,1,2,4}
		  };
		  matrix = new int[][]{{1,2,3},{8,9,4},{7,6,5}};
		  List<int[]> ret = pacificAtlantic(matrix);
		  for(int i=0;i<ret.size();i++){
			  //System.out.println(ret.get(i)[0]+" "+ret.get(i)[1]);
		  }
	  }
}
