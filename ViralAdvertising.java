import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if(n <= 0) System.out.println(0);
        if(n == 1) System.out.println(2);
        int total = 2;
        int active = 2;
        for(int i = 1; i < n; i++){
            active = active * 3 / 2;
            total += active;
        }
        System.out.println(total);
    }
}
