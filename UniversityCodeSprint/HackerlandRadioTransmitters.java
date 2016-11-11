/*https://www.hackerrank.com/contests/university-codesprint/challenges/hackerland-radio-transmitters
Hackerland is a one-dimensional city with  houses, where each house  is located at some  on the -axis. The Mayor wants to install radio transmitters on the roofs of the city's houses. Each transmitter has a range, , meaning it can transmit a signal to all houses  units of distance away.

Given a map of Hackerland and the value of , can you find and print the minimum number of transmitters needed to cover every house in the city? (Every house must be covered by at least one transmitter) Each transmitter must be installed on top of an existing house.

Input Format

The first line contains two space-separated integers describing the respective values of  (the number of houses in Hackerland) and  (the range of each transmitter). 
The second line contains  space-separated integers describing the respective locations of each house (i.e., ).

Constraints

There may be more than one house at the same location.
Subtasks

 for  of the maximum score.
Output Format

Print a single integer denoting the minimum number of transmitters needed to cover all the houses.

Sample Input 0

5 1
1 2 3 4 5
Sample Output 0

2
Explanation 0

The diagram below depicts our map of Hackerland:

k-nearest(2).png

We can cover the entire city by installing transmitters on houses at locations  and . Thus, we print  on a new line.

Sample Input 1

8 2
7 2 4 6 5 9 12 11 
Sample Output 1

3
Explanation 1

The diagram below depicts our map of Hackerland:

k-nearest2(2).png

We can cover the entire city by installing transmitters on houses at locations , , and . Thus, we print  on a new line.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];
        for(int x_i=0; x_i < n; x_i++){
            x[x_i] = in.nextInt();
        }
        Arrays.sort(x);
        //greedy algorithm, find the next furthest radar that can cover current point
        //and then leap forward
        int count = 0;
        int prev = x[0];
        boolean hasPoint = false;
        for(int i = 1; i < n; i++){
            
            //find point
            if(!hasPoint){
                if(x[i]-prev>k){
                    prev = x[i-1];//radar here
                    //System.out.println("radar:"+x[i-1]);
                    hasPoint = true;
                    /*
                    *for case:
                    *4 2
                    *1 4 7 10
                    */
                    i--;
                        
                    count ++;
                }
            }else{//has point leap foward
                if(x[i]-prev>k){
                    //System.out.println("exceed radar range"+x[i]);
                    hasPoint = false;
                    prev = x[i];
                }
            }
        }
        if(hasPoint == false) count++;//add last radar
        System.out.println(count);
    }
}

