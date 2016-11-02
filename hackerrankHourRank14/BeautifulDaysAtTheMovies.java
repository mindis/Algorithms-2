/*
Beautiful Days at the Movies
locked
by YuryBandarchuk

    Problem
    Submissions
    Leaderboard
    Discussions
    Editorial

Lily likes to play games with integers and their reversals. For some integer , we define to be the reversal of all digits in . For example, , , and .

Logan wants to go to the movies with Lily on some day satisfying , but he knows she only goes to the movies on days she considers to be beautiful. Lily considers a day to be beautiful if the absolute value of the difference between and is evenly divisible by .

Given , , and , count and print the number of beautiful days when Logan and Lily can go to the movies.

Input Format

A single line of three space-separated integers describing the respective values of , , and .

Constraints

Output Format

Print the number of beautiful days in the inclusive range between and .

Sample Input

20 23 6

Sample Output

2

Explanation

Logan wants to go to the movies on days , , , and . We perform the following calculations to determine which days are beautiful:

    Day is beautiful because the following evaluates to a whole number:
    Day is not beautiful because the following doesn't evaluate to a whole number:
    Day is beautiful because the following evaluates to a whole number:
    Day is not beautiful because the following doesn't evaluate to a whole number:

Only two days, and , in this interval are beautiful. Thus, we print as our answer.
*/
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int n = scan.nextInt();
        int count = 0;
        for(int i = a; i<=b;i++){
            if((i - reverseInteger(i))%n==0){
            	count ++;
            }
        }
        System.out.println(count);
    }
    
    public static int reverseInteger(int i){
        StringBuilder temp = new StringBuilder();
        
        while(i>0){
        	int d = i %10;
        	temp = temp.append(d);
        	i/=10;
        }
        return Integer.parseInt(temp.toString());
        
    }
}
