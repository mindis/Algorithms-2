/*Super-Valid Bracket Sequences
by darkshadows
Problem
Submissions
Leaderboard
Discussions
We recursively define a valid bracket sequence as follows:

An empty string is a valid bracket sequence.
If  is valid sequence, then (s) is also valid where ( and ) denote actual bracket characters.
If  and  are valid, then ab (i.e., the concatanation of  and ) is also valid.
For example, (), (()), and ()(()) are all valid bracket sequences.

Next, we define a -super-valid sequence, where  is a positive integer. The following pseudocode function determines whether or not a sequence, , is a -super-valid sequence:

bool isSuperValid(s, k):
    count = 0
    for (i = 0 to length(s) - 2):
        if (s[i] != s[i + 1]):
            count += 1

    if (count >= k): return true
    else: return false
You are given  queries consisting of  and . For each query, find and print the number of valid sequences of length  that are also -super-valid on a new line. As these values can be quite large, print each answer modulo .

Input Format

The first line contains an integer, , denoting the number of queries. 
Each of the  subsequent lines contains two space-separated integers describing the respective values of  and for a query.

Constraints

Output Format

For each query, print a single integer denoting the number of valid sequences that are also -super-valid on a new line. As these values can be quite large, your answer must be modulo .

Sample Input

2
2 1
4 2
Sample Output

1
1
Explanation

We perform the following  queries:

The only valid sequence of length  is . Because  is -super-valid for , we print the result of  on a new line.
The valid sequences of length  are  and .

 is -super-valid for the values of .
 is -super-valid for the values of .
Because we have one valid sequence that is -super-valid for , we print the result of on a new line.*/

import java.io.*;
import java.util.*;

public class Solution4 {

	public static long[][] nCk = new long[201][201];
    public static long mod = 1000000007;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	/*initialize pascal's triangle*/
    	nCk[0][0]=1;
    	nCk[1][0]=1;
    	nCk[1][1]=1;
    	
    	Scanner scan = new Scanner(System.in);
    	int q= scan.nextInt();
    	
    	int[][] qArr = new int[q][2];
    	for(int i=0;i<q;i++){
    		qArr[i][0] = scan.nextInt();
    		qArr[i][1] = scan.nextInt();
    	}
    	
    	for(int i=0;i<q;i++){
    		int n = qArr[i][0];int kPie = qArr[i][1]; //k'
    		
    		if(n%2==0){
    			n=n/2;
	    		long count = 0;
	    		for(int j = 1; j<=n;j++){
	    			int k=j*2-1;
	    			if(k>=kPie){
		    			//System.out.println("j"+j);
		    			long sum = (mod + nChooseK(n-1,j-1)*nChooseK(n+1,j)%mod - nChooseK(n,j-1)*nChooseK(n,j)%mod)%mod;
		    			count= (count+sum)%mod;
		    			//System.out.println(k+":"+sum);
	    			}
	    		}
	    		System.out.println(count);
    		}else{
    			System.out.println(0);
    		}
    	}
    	
    	/*
    	 * 1 1
    	 * 1 2
    	 * 
    	 * 1 3 1
    	 * 1 3 5
    	 * 
    	 * 1 6 6 1
		   1 3 5 7
    	 */
    	/*
    	4 2
    	n=2, k'>=2
*/
    	/*n=2, k>=1*/
    	
    	/*
    	 * k =      1   2   3   4   5   6   7   8
n = 1    1
    2    1   1
    3    1   3   1
    4    1   6   6   1
    5    1  10  20  10   1
    6    1  15  50  50  15   1
    7    1  21 105 175 105  21   1
    8    1  28 196 490 490 196  28   1
    	 */
    	//1/n(nCk)(nCk-1)
    
    }
    
    public static long nChooseK(int n, int k){
    	
    	
    	if(k<0) return 0;
    	if(k==0) return 1;
    	if(n==0) return 0;
    	//System.out.println("n"+n+"k"+k);
    	if(k==n || k ==0){
    		nCk[n][k] = 1;
    		return 1;
    	}
    	if(nCk[n][k]!=0){
    		return nCk[n][k];
    	}else{
    		long sum = nChooseK(n-1,k-1)+nChooseK(n-1, k);
    		sum %= mod;
    		nCk[n][k] = sum;
    		return sum;
    	}
    }
    
    
    
    
    
    
    
    /*https://en.wikipedia.org/wiki/Narayana_number*/
    
//    public static int countValid(String s){
//    	int count = 0;
//        for (int i = 0; i<= s.length() - 2;i++){
//            if (s.charAt(i) != s.charAt(i+1))
//                count ++;
//        }
//        return count;
//    }
    
//    public static void Brackets(int n) {
//        for (int i = 1; i <= n; i++) {
//            Brackets("", 0, 0, i);
//        }
//    }
//    
//    private static void Brackets(String output, int open, int close, int pairs) {
//        if((open==pairs)&&(close==pairs)) {
//            System.out.println(output);
//        } else {
//            if(open<pairs)
//                Brackets(output + "(", open+1, close, pairs);
//            if(close<open)
//                Brackets(output + ")", open, close+1, pairs);
//        }
//    }
        
}
/*
2:()
4:()(); (())
6:2+4, 4+2; (4)
8:2+6;4+4;6+4;  (        ()()        )(), this is a 4+4,dame

Two counts add together: ()()();()(());(())();  no count increase:(()()); ((()))
8:2+6,4+4,6+2; (6)


()
(())
()()
((()))
(()())
(())()
()(())
()()()
(((())))
((()()))
((())())
((()))()
(()(()))
(()()())
(()())()
(())(())
(())()()
()((()))
()(()())
()(())()
()()(())
()()()()
*/

