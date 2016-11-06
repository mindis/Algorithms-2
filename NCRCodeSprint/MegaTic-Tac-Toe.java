/*
Mega Tic-Tac-Toe
by faiyaz26
Problem
Submissions
Leaderboard
Discussions
Alexis is bored with regular Tic-Tac-Toe, played on a  board. She decides to invent Mega Tic-Tac-Toe, which has the following additional rules:

The board is of size .
Alexis is always the letter O, and the other player is always the letter X.
To win a game, there should be at least  consecutive cells containing the same symbol (i.e., either an X or an O). Each group of  consecutive cells must be in the horizontal, vertical, or diagonal direction (i.e., you cannot mix and match a cluster of adjacent cells).
As the size of the board increases, it becomes more and more difficult to determine who wins each game of Mega Tic-Tac-Toe. Given the value of  and the layout of the board for  games of Mega Tic-Tac-Toe, print the winner of each game on a new line. If Alexis wins, print WIN; if she loses, print LOSE. If neither player wins, print NONE.

Note If both players have at least  consecutive cells, neither player wins.

Input Format

The first line contains an integer, , denoting the number of games played. The subsequent lines describe each game as follows:

The first line contains three space-separated integers describing the respective values of , , and  for that game of Mega Tic-Tac-Toe.
Each of the  subsequent lines contains a string of  characters. Each character will be one of the following: an O (denoting a cell marked by Alexis), an X (denoting a cell marked by her opponent), or a - (denoting an unmarked cell).
Constraints

There may not be a winner for every game.
Output Format

For each game board, print the WIN, LOSE, NONE according to the statement.

Sample Input

4
3 3 3
XOX
XOX
XXX
3 3 3
X-X
O-O
X-X
3 3 3
O-X
XOO
XOO
3 3 3
O-X
O-X
O-X
Sample Output

LOSE    
NONE
WIN
NONE
Explanation

We must evaluate the following  games:

Alexis loses this game because there are  consecutive X's in both the horizontal and vertical directions. Thus, we print LOSE on a new line.
Neither player has marked  consecutive cells, so nobody wins and we print NONE on a new line.
Alexis wins this game because there are  consecutive diagonal cells marked with O. Thus, we print WIN on a new lne.
Because both players marked  consecutive cells, neither can win. Thus, we print NONE on a new line.
https://www.hackerrank.com/contests/ncr-codesprint/challenges/mega-tic-tac-toe*/
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
