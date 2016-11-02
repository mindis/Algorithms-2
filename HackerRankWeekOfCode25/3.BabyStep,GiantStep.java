/*
Baby Step, Giant Step
by zemen

    Problem
    Submissions
    Leaderboard
    Discussions

Your submission will run against only preliminary test cases. Full test cases will run at the end of the day.

You are standing at point on an infinite plane. In one step, you can move from some point to any point as long as the Euclidean distance, , between the two points is either or . In other words, each step you take must be exactly or in length.

You are given queries in the form of , , and . For each query, print the minimum number of steps it takes to get from point to point on a new line.

Input Format

The first line contains an integer, , denoting the number of queries you must process.
Each of the subsequent lines contains three space-separated integers describing the respective values of , , and for a query.

Constraints

Output Format

For each query, print the minimum number of steps necessary to get to point on a new line.

Sample Input

3
2 3 1
1 2 0
3 4 11

Sample Output

2
0
3

Explanation

We perform the following queries:

    One optimal possible path requires two steps of length : . Thus, we print the number of steps, , on a new line.
    The starting and destination points are both , so we needn't take any steps. Thus, we print on a new line.
    One optimal possible path requires two steps of length and one step of length : . Thus, we print on a new line.

*/

import java.io.*;
import java.util.*;

public class Solution {
    /*2 3 1: 1*/
    /*1 2 0:  0*/
    /*3 4 11: 4 4 3*/
    /*3 4 5: in a plane*/
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        /* giant step  bigger of a, b, */
        /* 5 7 10___5 5*/
        /*4 6 5*/
        /*3 4 15, 4*/
        /*7 10 7, 1*/
        /*7 10 10, 1*/
        /*2 3 1, 2*/
        
        Scanner scan = new Scanner(System.in);
        long n = scan.nextInt();
        for(long i =0 ;i<n;i++){
            long a = scan.nextLong();
            long b = scan.nextLong();
            long d = scan.nextLong();
            long count = d/ b;
            long oD = d;
            d -= count * b;
           if(d!=0){
               count++;
               if(oD<d+b && d!=a)count++;
           }
            System.out.println(count);
        }
    }
}
