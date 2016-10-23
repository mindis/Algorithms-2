/*

The Easy Puzzle
by sears_admin
Problem
Submissions
Leaderboard
Discussions
Given an array of  integers and a prime number , your task is to find out if LCM of the whole array is divisible by . Output YES in case it is divisible, NO otherwise.

Input Format

First line of the input contains an integer  denoting the number of testcases. 
First line of each testcase contains two space separated integers  and . 
Second line of each testcase contains  integers separated by a single space.

Constraints

Each integer in the array 
Output Format

For each of the testcase, print YES or NO in a separate line.

Sample Input

1
3 3
2 3 12
Sample Output

YES
Explanation

For the given sample test case, LCM of the whole array will be .  can be written as  which evaluates to . Since,  is divisible be , answer is YES
*/

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int nTest = Integer.parseInt(scan.nextLine());
        for(int i = 0; i < nTest; i++){
            String[] nAndK= scan.nextLine().split(" ");
            int N = Integer.parseInt(nAndK[0]);
            int K = Integer.parseInt(nAndK[1]);
            String[] inputsS= scan.nextLine().split(" ");
            long[] inputsL = new long[N];
            for(int j = 0; j < N; j++){
                inputsL[j] = Long.parseLong(inputsS[j]);
            }
            if(lcm(inputsL, K)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
    
     private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

private static long gcd(long[] input)
{
    long result = input[0];
    for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
    return result;
}
    private static long lcm(long a, long b)
{
    return a * (b / gcd(a, b));
}

//private static long lcm(long[] input)
//{
  //  long result = input[0];
    //for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
    //return result;
//}
    
    private static boolean lcm(long[] input,int k)
    {
        long result = input[0];
        for(int i = 1; i < input.length; i++){
            result = lcm(result, input[i]);
            if(result%k==0) return true;
        }
        return false;
    }
}
