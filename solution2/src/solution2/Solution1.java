import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Arrays;
import java.util.Collections;

public class Solution1 {

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