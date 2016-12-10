import java.io.*;
import java.util.*;

public class Solution3 {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int nL = Integer.parseInt(scan.nextLine());
        for(int i = 0; i<nL;i++){
        	String[] in = (scan.nextLine()).split(" ");
        	int n = Integer.parseInt(in[0]), m=Integer.parseInt(in[1]), k =Integer.parseInt(in[2]);
        	char[][] matrix = new char[n][m];
        	for(int j = 0; j < n; j++){
        		String s= scan.nextLine();
        		for(int l=0;l<m;l++){
        			matrix[j][l] = s.charAt(l);
        		}
        	}
        	boolean X = false;
        	boolean O = false;
        	int[] pre = new int[2];
        	
        	//Horizontal
         	for(int j = 0; j < n; j++){
        		for(int l=0;l<m;l++){
        			if(matrix[j][l]=='X'){
        				pre[0]++;
        				pre[1]=0;
        				if(pre[0]>=k) X = true;
        			}else if(matrix[j][l]=='O'){
        				pre[1]++;
        				pre[0]=0;
        				if(pre[1]>=k) O = true;
        			}else{
        				pre[0]=0;pre[1]=0;	
        			}
        		}
        	}
         	
         	
         	//Vertical
         	pre[0]=0;pre[1]=0;
         	for(int l=0;l<m;l++){
         		for(int j = 0; j < n; j++){
        			if(matrix[j][l]=='X'){
        				pre[0]++;
        				pre[1]=0;
        				if(pre[0]>=k) X = true;
        			}else if(matrix[j][l]=='O'){
        				pre[1]++;
        				pre[0]=0;
        				if(pre[1]>=k) O = true;
        			}else{
        				pre[0]=0;pre[1]=0;	
        			}
        		}
        	}
         	
         	//Diagonal, for each cell can move left down diagnally or move right down diagnally
         	//Need a memory for the visited, otherwise time out, O(n^3) -> O(n^2)
         	for(int j = 0; j < n; j++){
        		for(int l=0;l<m;l++){
         			pre[0]=0;pre[1]=0;
         			if(n-j>=k && l-0+1>=k){//preemptively resolve time-out
	         			for(int o =j,p=l;o<n&&p>=0;o++,p--){
	             			if(matrix[o][p]=='X'){
	            				pre[0]++;
	            				pre[1]=0;
	            				if(pre[0]>=k) X = true;
	            			}else if(matrix[o][p]=='O'){
	            				pre[1]++;
	            				pre[0]=0;
	            				if(pre[1]>=k) O = true;
	            			}else{
	            				pre[0]=0;pre[1]=0;	
	            			}
	         			}
         			}
         			
        			pre[0]=0;pre[1]=0;
        			if(n-j>=k && m-l>=k){
	         			for(int o =j,p=l;o<n&&p<m;o++,p++){
	             			if(matrix[o][p]=='X'){
	            				pre[0]++;
	            				pre[1]=0;
	            				if(pre[0]>=k) X = true;
	            			}else if(matrix[o][p]=='O'){
	            				pre[1]++;
	            				pre[0]=0;
	            				if(pre[1]>=k) O = true;
	            			}else{
	            				pre[0]=0;pre[1]=0;	
	            			}
	         			}
        			}
         		}
        	}
        	
        	
//        	for
//        	OXO
//        	XOX
        	
        	//3 4 3
//        	OOXX
//        	XXOO
//        	XOXO
//        	LOSE
        	if(X && O) System.out.println("NONE");
        	if(!X && O) System.out.println("WIN");
        	if(X && !O) System.out.println("LOSE");
        	if(!X && !O) System.out.println("NONE");
        }
    }
}