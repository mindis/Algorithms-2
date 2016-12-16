/*
Given an array of integers A and an integer k, find a subarray that contains the largest sum, subject to a constraint that the sum is less than k?
You can do this in O(nlog(n))O(nlog⁡(n))

First thing to note is that sum of subarray (i,j](i,j] is just the sum of the first jj elements less the sum of the first ii elements. Store these cumulative sums in the array cum. Then the problem reduces to finding  i,ji,j such that i<ji<j and cum[j]−cum[i]cum[j]−cum[i] is as close to kk but lower than it.

To solve this, scan from left to right. Put the cum[i]cum[i] values that you have encountered till now into a set. When you are processing cum[j]cum[j] what you need to retrieve from the set is the smallest number in the set such which is bigger than cum[j]−kcum[j]−k. This lookup can be done in O(logn)O(log⁡n) using upper_bound. Hence the overall complexity is O(nlog(n))O(nlog⁡(n)).

Here is a c++ function that does the job, assuming that K>0 and that the empty interval with sum zero is a valid answer. The code can be tweaked easily to take care of more general cases and to return the interval itself.
*/

/*
The naive solution is brute-force, which is O((mn)^2). In order to be more efficient, I tried something similar to Kadane's algorithm. The only difference is that here we have upper bound restriction K. Here's the easily understanding video link for the problem "find the max sum rectangle in 2D array": Maximum Sum Rectangular Submatrix in Matrix dynamic programming/2D kadane (Trust me, it's really easy and straightforward).

Once you are clear how to solve the above problem, the next step is to find the max sum no more than K in an array. This can be done within O(nlogn), and you can refer to this article: max subarray sum no more than k.

For the solution below, I assume that the number of rows is larger than the number of columns. Thus in general time complexity is O[min(m,n)^2 * max(m,n) * log(max(m,n))], space O(max(m, n)).

*/
/*
To find the max sum of an array, we can do as follows:

compute the cumulative sum of the array
find a pair of i and j, constrained to i<j, and cum[j]-cum[i]<=k
do some trick, the inequation above is actually cum[j]-k<=cum[i], we need to find the minimum value of cum[i] in order to maximize cum[j]-cum[i], that is, find TreeSet.ceiling(cum[j]-k)
if founded in the treeset, the value is actually cum[i], by subtract cum[i] from cum[j], we update the result
The Max sum of rectangle on larger than k can be transformed into the problem of finding the max sum of an array no larger than k by slicing the matrix:
*/

public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int result = Integer.MIN_VALUE;
        for (int begin = 0; begin < n; begin++) {
            for (int end = begin + 1; end <= n; end++) {
                int[] arr = new int[m];
                for (int i = 0; i < m; i++) {
                    for (int j = begin; j < end; j++) {
                        arr[i] += matrix[i][j];
                    }
                }
                TreeSet<Integer> treeSet = new TreeSet<>();
                treeSet.add(0);
                int cumulative = 0;
                for (int i : arr) {
                    cumulative += i;
                    Integer ceiling = treeSet.ceiling(cumulative - k);
                    if (ceiling != null) {
                        result = Math.max(result, cumulative - ceiling);
                    }
                    treeSet.add(cumulative);
                }
            }
        }
        return result;
    }

