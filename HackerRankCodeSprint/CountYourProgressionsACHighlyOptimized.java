import java.io.*;
import java.util.*;


public class Solution {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      /*  []
            2
            4
            2
            2, 4
            2, 2
            4, 2*/
                /*
        *
2  [0][diff]
4 [1][diff]
6  [2][diff]
8 [3] [diff]


2


*/
        int mod = 1000000009;
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        // (prev, diff) -> count
        // (cur) -> only single element #count
        int[][] pd = new int[101][199];
        int[] s = new int[101];
        int[] list = new int[n];
        int count = 1;
        
        for(int i = 0; i < n; i++){
            list[i] = scan.nextInt();
        }
        
        for(int i = 0; i < n; i++){
            int cur = list[i];
            for(int diff = -99; diff<= 99;diff++){
                int pre = cur - diff;
                if(pre < 1 || pre > 100) continue;
                int diffT = diff+ 99;
                //update (pre->cur) diff, //update s ->cur
                int addon = pd[pre][diffT]+ s[pre];
                if(addon>=mod) addon = addon - mod;
                pd[cur][diffT] = pd[cur][diffT] + addon;
                if(pd[cur][diffT]>=mod) pd[cur][diffT] = pd[cur][diffT]- mod;
                count = count + addon;
                if(count>=mod) count = count - mod;
            }
            s[cur]++;
            if(s[cur]>=mod) s[cur] = s[cur]-  mod;
            count++;
            if(count>=mod) count = count-  mod;
         }
        System.out.println(count);
    }
}



