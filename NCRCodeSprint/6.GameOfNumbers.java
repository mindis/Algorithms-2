/*

Game Of Numbers
by Rajnikanth
Problem
Submissions
Leaderboard
Discussions
Alice and Bob are playing a game with the following rules:

Alice always plays first and they take alternating turns.
During each turn, a player can choose any number (regardless of whether or not it was chosen during a previous turn) in the inclusive range between  and .
The game ends when the running sum of chosen numbers (i.e., sum of all numbers chosen by both players) is , and the last player to take their turn wins.
Each player always chooses their number optimally. This means they will not choose a number that would cause them to lose the game if some better, winning choice exists.
Given the values of , , and  for  games, print the name of the winner of each game on a new line.

Input Format

The first line contains an integer, , denoting the number of games. 
Each of the  subsequent lines contains three space-separated integers describing the respective values of , , and  for a game.

Constraints

Output Format

For each game, print the name of the winner on a new line (i.e., Alice or Bob).

Sample Input

2
1 10 11
7 20 15
Sample Output

Bob
Alice
Explanation

We must evaluate the following  games:

. Regardless of which number Alice chooses, Bob can choose another number during his turn that will bring the sum to a value . Because Bob will always win the game, we print Bob on a new line.
. Alice can choose  during her first turn, at which point the game ends. Because Alice will always win the game, we print Alice on a new line.*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

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
