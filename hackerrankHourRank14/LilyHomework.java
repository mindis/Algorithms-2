/*
Lily's Homework
locked
by YuryBandarchuk

    Problem
    Submissions
    Leaderboard
    Discussions
    Editorial

Whenever George asks Lily to hang out, she's busy doing homework. George wants to help her finish it faster, but he's in over his head! Can you help George understand Lily's homework so she can hang out with him?

Consider an array of distinct integers, . George can swap any two elements of the array any number of times. An array is beautiful if the sum of among is minimal possible (after, possibly, performing some swaps).

Given the array , find and print the minimum number of swaps that should be performed in order to make the array beautiful.

Input Format

The first line contains a single integer, , denoting the number of elements in the array .
The second line contains space-separated integers describing the respective distinct values of .

Constraints

Output Format

Print the minimum number of swaps that should be performed in order to make the array beautiful.

Sample Input

4
2 5 3 1

Sample Output

2

Explanation

Let's define array to be the beautiful reordering of array , as the sum of the absolute values of differences between its adjacent elements is minimal among all permutations and only two swaps ( with and then with ) was performed.

*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	/*
    	 * 4
			2 5 3 1
			 3 2 2
				2
				1 2 3 5
				1  1  2
				5 4 3 2 1
    	 */
    	Scanner scan = new Scanner(System.in);
    	int n = scan.nextInt();
    	int[] arr = new int[n];
    	int[] arrS = new int[n];
    	int[] arr1 = new int[n];
    	Integer[] arrS1 = new Integer[n];
    	for(int i = 0; i< n;i++){
    		arr[i] = scan.nextInt();
    		arrS[i] = arr[i];
    		arr1[i] = arr[i];
    		arrS1[i] = arr[i];
    	}
    	
    	int count = 0;
    	int count1 = 0;
    	Arrays.sort(arrS);
    	Arrays.sort(arrS1, Collections.reverseOrder());
    	for(int i = 0;i <n;i++){
    		if(arr[i]!=arrS[i]){
    			for(int j = i+1;j<n;j++){
    				if(arr[j]==arrS[i]){
    					
    					swap(arr, i, j);
    				    count++;
    					break;
    				}
    			}
    		}
    		
    		if(arr1[i]!=arrS1[i]){
    			for(int j = i+1;j<n;j++){
    				if(arr1[j]==arrS1[i]){
    					
    					swap(arr1, i, j);
    				    count1++;
    					break;
    				}
    			}
    		}
    	}
    	System.out.println(count<count1?count:count1);
    	
    }
    public static void swap(int[] arr, int i, int j){
    	int temp = arr[i];
    	arr[i] = arr[j];
    	arr[j] = temp;
    }
}
