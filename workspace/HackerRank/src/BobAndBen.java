import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BobAndBen {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //number of node, if even, BEN win, otherwise Bob wins, they all remove from leaf
        Scanner scan = new Scanner(System.in);
        int nGame = Integer.parseInt(scan.nextLine());
        for(int i = 0; i < nGame; i++){
             int nTree = Integer.parseInt(scan.nextLine());
             boolean isEven = true;
             for(int j = 0; j < nTree; j++){
                 String[] mk= scan.nextLine().split(" ");
                 int m = Integer.parseInt(mk[0]);
                 int k = Integer.parseInt(mk[1]);
                 if (m%2==1) isEven = !isEven;
             }
             System.out.println(isEven?"BEN":"BOB");
        }
        
    }
}