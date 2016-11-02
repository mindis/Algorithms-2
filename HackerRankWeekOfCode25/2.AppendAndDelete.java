/*Your submission will run against only preliminary test cases. Full test cases will run at the end of the day.
You have a string, , of lowercase English alphabetic letters. You can perform two types of operations on :

Append a lowercase English alphabetic letter to the end of the string.
Delete the last character in the string. Performing this operation on an empty string results in an empty string.
Given an integer, , and two strings,  and , determine whether or not you can convert  to  by performing exactly  of the above operations on . If it's possible, print Yes; otherwise, print No.

Input Format

The first line contains a string, , denoting the initial string. 
The second line contains a string, , denoting the desired final string. The third line contains an integer, , denoting the desired number of operations.

Constraints

 and  consist of lowercase English alphabetic letters.
Output Format

Print Yes if you can obtain string  by performing exactly  operations on ; otherwise, print No.

Sample Input 0

hackerhappy
hackerrank
9
Sample Output 0

Yes
Explanation 0

We perform  delete operations to reduce string  to hacker. Next, we perform  append operations (i.e., r, a, n, and k), to get hackerrank. Because we were able to convert  to  by performing exactly  operations, we print Yes.

Sample Input 1

aba
aba
7
Sample Output 1

Yes
Explanation 1

We perform  delete operations to reduce string  to the empty string (recall that, though the string will be empty after  deletions, we can still perform a delete operation on an empty string to get the empty string). Next, we perform  append operations (i.e., a, b, and a). Because we were able to convert  to  by performing exactly  operations, we print Yes.*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String t = in.next();
        int k = in.nextInt();
        /*
        abcdefg
ab
            k= 6
k=5
k=7
abdcdefg
abc
k=6f, k=5t,k =7t
Now

abc
k=3
*/
        if(s == null || s.length() ==0){
            if(t==null || t.length() == 0){
                System.out.println("Yes");
            }else{
                if(k>=t.length()){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }           
            }
            return;
        }
        
        if(t == null || t.length() == 0){
            if(k>=s.length()){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
            return;
        }
        
        int lenA = s.length();
        int lenB = t.length();
        
        //common length
        int c = 0;
        while(c<lenA && c<lenB){
            if(s.charAt(c)!=t.charAt(c)){
                break;
            }
            c++;
        }
        
        //if (K-2C) >= (maxLen-C)
        //K>Maxlen -C
        // K>= (LenA-C + LenB-C) + 2i
        //abc dfi, abc efj
        //K >= LenA +Len B
        //K-2C>= (LenA-C+LenB-C)
        int higherBound = lenA+lenB;
        if(k>=higherBound){
            System.out.println("Yes");
            return;
        }
        
        int lowerBound = lenA-c+lenB-c;
        if(k >= lowerBound){
            if((k -lowerBound)%2==0){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        } else{
            System.out.println("No");
        }
    }
}

