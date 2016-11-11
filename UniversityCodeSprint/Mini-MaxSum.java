/*
https://www.hackerrank.com/contests/university-codesprint/challenges/mini-max-sum
Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.

Input Format

A single line of five space-separated integers.

Constraints

Each integer is in the inclusive range .
Output Format

Print two space-separated long integers denoting the respective minimum and maximum values that can be calculated by summing exactly four of the five integers.

Sample Input

1 2 3 4 5
Sample Output

10 14
Explanation

Our initial numbers are , , , , and . We can calculate the following sums using four of the five integers:

If we sum everything except , our sum is .
If we sum everything except , our sum is .
If we sum everything except , our sum is .
If we sum everything except , our sum is .
If we sum everything except , our sum is .
As you can see, the minimal sum is  and the maximal sum is . Thus, we print these minimal and maximal sums as two space-separated integers on a new line.
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        String[] sArr = scan.nextLine().split(" ");
        long sum = 0; long min = Long.MAX_VALUE; long max = Long.MIN_VALUE;
        for(int i=0;i<5;i++){
            long cur = Integer.parseInt(sArr[i]);
            if(min>cur) min = cur;
            if(max<cur) max = cur;
            sum += cur;
        }
        System.out.println((sum-max)+" "+(sum-min));        
    }
}
