/*
Two Closest Strings
by sears_admin
Problem
Submissions
Leaderboard
Discussions
Given a string  of length  and an integer , find the lexicographically smallest string  of the same length as  such that hamming distance between  and  is exactly .

Note 
The final string  should have lowercase alphabetic characters.

Input Format

First line of the input contains  denoting the number of test cases. First and the only line of every test case contains a string  and an integer  separated by a single space.

Constraints

Sum of lengths of strings over all testcases does not exceed 
A consists of lowercase alphabetic characters
Output Format

For each testcase, output the lexicographically smallest string  such that hamming distance between  and is exactly .

Sample Input

2
pqrs 1
pqrs 2
Sample Output

aqrs
aars
Explanation

For the first sample test case i.e given string  as pqrs and the required hamming distance to be , then the smallest lexicographically string that can be produced which has hamming distance  from , will be aqrs.
*/

import java.io.*;
import java.util.*;

public class Solution {

    /*
aabb 3
abaa
*/
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int nText = Integer.parseInt(scan.nextLine());
        for(int i=0;i<nText;i++){
            String[] curS = scan.nextLine().split(" ");
            int dist = Integer.parseInt(curS[1]);
            StringBuilder sb = new StringBuilder(curS[0].toLowerCase());
            int len = sb.length();
            boolean[] mark = new boolean[len];
            int cur = 0;
            while(dist!=0){
                char temp = sb.charAt(cur);
                if(temp!='a'){
                   sb.setCharAt(cur, 'a');
                   dist--;
                   mark[cur] = true;
                }
                cur++;
                if(cur==len) break;
            }
            while(dist!=0){
                --cur;
                if(!mark[cur]){
                    sb.setCharAt(cur, 'b');
                    dist--;
                }
             
            }
             
            System.out.println(sb.toString());
        }
    }

}
