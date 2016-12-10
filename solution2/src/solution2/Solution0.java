import java.io.*;
import java.util.*;

public class Solution0 {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int n = scan.nextInt();
        int count = 0;
        for(int i = a; i<=b;i++){
            if((i - reverseInteger(i))%n==0){
            	count ++;
            }
        }
        System.out.println(count);
    }
    
    public static int reverseInteger(int i){
        StringBuilder temp = new StringBuilder();
        
        while(i>0){
        	int d = i %10;
        	temp = temp.append(d);
        	i/=10;
        }
        return Integer.parseInt(temp.toString());
        
    }
}