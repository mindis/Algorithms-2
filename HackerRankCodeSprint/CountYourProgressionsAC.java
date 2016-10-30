Count Your Progressions locked
by saikiran9194
Problem
Submissions
Leaderboard
Discussions
John has a sequence of numbers with him. He is interested in finding out the number of subsequences of his sequence that are arithmetic progressions.

A subsequence is a sequence that can be derived from another sequence by deleting some elements without changing the order of the remaining elements. For example, the sequence {4,6,9} is a subsequence of {4,5,6,7,8,9} obtained after removal of elements 5,7,8. The relation of one sequence being the subsequence of another is a preorder.

Arithmetic Progression: A sequence is an arithmetic progression if the difference between consecutive elements is constant. If the initial term of arithmetic progression is b[1] and the common difference is D then: b[n] = b[1] + (n-1)*D

Note that empty sequence or a single element sequence are arithmetic progressions.

Input Format

The first line of the input is an integer , total number of elements in the sequence. Each of the next  lines contains a single integer representing an element of the sequence.

Constraints

Let  be the number of elements in input sequence. Let a[i] represent the  element of the input sequence. 
 

Output Format

Let A be the number of subsequences that are arithmetic progressions. Print the value of A modulo 1000000009 (10^9 + 9).

Use PyPy and PyPy3 instead of Python2 and Python3 as a few test cases might not pass in Python2 and Python3

Sample Input

3
2
4
2
Sample Output

7
Explanation

The different arithmetic progressions are : {}, {2}, {4}, {2}, {2, 4}, {4,2}, {2, 2}. Note that the subsequence {2} is present twice because the 2 is picked from different indices.

import java.io.*;
import java.util.*;


public class Solution {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      /*  []
            2
            4
            2
            2, 4
            2, 2
            4, 2*/
                /*
        *
2  [0][diff]
4 [1][diff]
6  [2][diff]
8 [3] [diff]


2


*/
        int mod = 1000000009;
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        // (prev, diff) -> count
        // (cur) -> only single element #count
        int[][] pd = new int[101][199];
        int[] s = new int[101];
        int[] list = new int[n];
        int count = 1;
        
       // for(int i = 0; i < n; i++){
         //   list[i] = scan.nextInt();
        //}
        
        for(int i = 0; i < n; i++){
            int cur = scan.nextInt();
            for(int diff = -99; diff<= 99;diff++){
                int pre = cur - diff;
                if(pre < 1 || pre > 100) continue;
                int diffT = diff+ 99;
                //update (pre->cur) diff, //update s ->cur
                int addon = pd[pre][diffT]+ s[pre];
                while(addon>=mod) addon = addon % mod;
                pd[cur][diffT] = pd[cur][diffT] + addon;
                while(pd[cur][diffT]>=mod) pd[cur][diffT] = pd[cur][diffT]% mod;
                count = count + addon;
                while(count>=mod) count = count % mod;
            }
            s[cur]++;
            while(s[cur]>=mod) s[cur] = s[cur]%  mod;
            count++;
            while(count>=mod) count = count%  mod;
         }
        System.out.println(count);
    }
}



